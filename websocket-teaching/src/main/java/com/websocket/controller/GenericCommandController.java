
package com.websocket.controller;

import com.websocket.model.command.BaseCommand;
import com.websocket.model.teaching.GenericParam;
import java.security.Principal;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.MessagingException;
import org.springframework.messaging.handler.annotation.MessageExceptionHandler;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;
import org.springframework.util.MimeTypeUtils;

/**
 * 通用指令 ws 控制器.
 */
@Controller
public class GenericCommandController extends BaseCommandController<BaseCommand> {

    private static final Logger logger = LoggerFactory.getLogger(GenericCommandController.class);

    /**
     * 移动前端(Android)使用 connect 保持心跳.
     *
     * <p>保存 command history 时可以过滤掉</p>
     */
    private static final String GENERIC_ACTION_CONNECT = "connect";

    /** /user/ 用户频道标识 */
    private static final String USER_DESTINATION_PREFIX = "/user/";
    /** users 分隔符 */
    private static final String USERS_SEPARATOR = "#";
    // @Autowired
    private SimpMessageSendingOperations messagingTemplate;

    @Autowired
    public GenericCommandController(SimpMessageSendingOperations messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    /**
     * 通用指令响应. 根据提供的 channel 和 users 确定接收的对象.
     *
     * @param genericParam 通用参数
     * @param principal 用户信息
     *
     *        SendTo: "/topic/cmd/" 或参数里的 chan
     *        对象： 任意
     */
    @MessageMapping("/command")
    public void command(GenericParam genericParam, Principal principal) {
        BaseCommand baseCommand = genericParam.getCommand();
        if (null == baseCommand || StringUtils.isBlank(baseCommand.getType())) {
            logger.info("GenericCommand in 指令参数缺失command或type");
            throw new MessagingException("指令参数缺失command或type");
        }
        logger.info("GenericCommand in: cmd is {}", baseCommand.toString());

        // 通用指令基本是直接转发过去就可以，后端没有什么业务逻辑，由前端约定
        boolean hasChannel = StringUtils.isNotBlank(genericParam.getChan());
        boolean hasUsers = StringUtils.isNotBlank(genericParam.getUsers());
        // boolean hasClassId = StringUtils.isNotBlank(genericParam.getClassId());
        Map<String, Object> map = new HashMap<>();
        map.put(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON);
        if (hasChannel) {
            // logger.info("hasChannel");
            String chan = genericParam.getChan();
            if (chan.startsWith(USER_DESTINATION_PREFIX)) {
                chan = chan.substring(USER_DESTINATION_PREFIX.length() - 1); // -1 使以 / 开头
                // logger.info("USER_DESTINATION_PREFIX chan={}", chan);
                if (hasUsers) {
                    for (String user : genericParam.getUsers().split(USERS_SEPARATOR)) {
                        // logger.info("has user: " + user);
                        messagingTemplate.convertAndSendToUser(user, chan, genericParam, map);
                    }
                } else {
                    String fromUser =
                            (principal != null && StringUtils.isNotBlank(principal.getName())) ? principal.getName()
                                    : UNKNOWN_USER;
                    // logger.info("not has user, fromUser: " + fromUser);
                    messagingTemplate.convertAndSendToUser(fromUser, chan, genericParam, map);
                }
            } else {
                // logger.info("not USER_DESTINATION_PREFIX chan={}", chan);
                if (hasUsers) {
                    for (String user : genericParam.getUsers().split(USERS_SEPARATOR)) {
                        // logger.info("has user: " + user);
                        messagingTemplate.convertAndSendToUser(user, chan, genericParam, map);
                    }
                } else {
                    // logger.info("not has user");
                    messagingTemplate.convertAndSend(chan, genericParam, map);
                }
            }
        } else {
            // 通用的频道，直接转发
            // logger.info("NOT hasChannel");
            messagingTemplate.convertAndSend("/topic/cmd", genericParam, map);
        }

        // // 记录指令历史数据
        // if (!StringUtils.startsWith(baseCommand.getAction(), GENERIC_ACTION_CONNECT)) {
        //     CommandHistory commandHistory = new CommandHistory(null, null, genericParam.getClassId(),
        //             genericParam.getChan(), genericParam.getUsers(), baseCommand.getFromPlatform(),
        //             baseCommand.getRole(), baseCommand.getTimestamp(), baseCommand.getType(),
        //             baseCommand.getAction(), JsonMapper.toJsonString(baseCommand.getRemarks())
        //     );
        //
        //     commandHistory.setCurrentUser(getCurrentSysUser(principal));
        //     commandHistoryService.save(commandHistory);
        // }
    }

    /**
     * 错误情况，只发给当前用户.
     *
     * @param exception 错误异常
     * @return
     */
    @MessageExceptionHandler
    @SendToUser(destinations = "/queue/errors", broadcast = false)
    public String handleException(Throwable exception) {
        if (exception != null) {
            logger.error("MessageExceptionHandler", exception);
            return exception.getMessage();
        }
        return "errors";
    }
}
