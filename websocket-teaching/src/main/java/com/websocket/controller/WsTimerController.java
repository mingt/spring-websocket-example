
package com.websocket.controller;

import com.websocket.model.command.TimerCommand;
import com.websocket.model.teaching.TimerParam;
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
 * 课堂：计时器 ws 控制器.
 */
@Controller
public class WsTimerController extends BaseCommandController {

    private static final Logger logger = LoggerFactory.getLogger(WsTimerController.class);
    // @Autowired
    private SimpMessageSendingOperations messagingTemplate;

    @Autowired
    public WsTimerController(SimpMessageSendingOperations messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    /**
     * 计时器指令响应.
     *
     * @param timerParam 计时器参数
     * @param principal 用户信息
     *
     *        SendTo: "/topic/timer/" + classId
     *        对象： 看似一般只有 PC 端订阅？
     */
    @MessageMapping("/teaching/timer/command")
    public void command(TimerParam timerParam, Principal principal) {
        String classId = timerParam.getClassId();
        TimerCommand timerCommand = timerParam.getCommand();
        if (StringUtils.isBlank(classId) || null == timerCommand || StringUtils.isBlank(timerCommand.getType())
                || StringUtils.isBlank(timerCommand.getSubType()) || StringUtils.isBlank(timerCommand.getAction())) {
            throw new MessagingException("课堂id或指令参数缺失");
        }
        if (!TimerCommand.TYPE.equals(timerCommand.getType())) {
            throw new MessagingException("指令类型不匹配timer，而是" + timerCommand.getType());
        }

        // 计时器的指令基本是直接转发过去就可以，没有什么业务逻辑
        Map<String, Object> map = new HashMap<>();
        map.put(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON);
        messagingTemplate.convertAndSend("/topic/timer/" + classId, timerParam, map);
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
