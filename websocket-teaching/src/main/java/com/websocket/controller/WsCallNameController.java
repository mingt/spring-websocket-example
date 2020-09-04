
package com.websocket.controller;

import com.thinkgem.jeesite.modules.sys.entity.User;
import com.websocket.config.StompProperties;
import com.websocket.model.command.CallNameCommand;
import com.websocket.model.command.CommandConstant;
import com.websocket.model.teaching.CallNameParam;
import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.collections.CollectionUtils;
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
 * 课堂：点名 ws 控制器.
 */
@Controller
public class WsCallNameController extends BaseCommandController {

    private static final Logger logger = LoggerFactory.getLogger(WsCallNameController.class);

    // @Autowired
    private SimpMessageSendingOperations messagingTemplate;

    @Autowired
    private StompProperties stompProperties;

    // spring提供的发送消息模板
    // @Autowired
    // private SimpMessagingTemplate msgTemplate;
    //
    // @Autowired
    // private SimpUserRegistry userRegistry;

    @Autowired
    public WsCallNameController(SimpMessageSendingOperations messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    /**
     * 点名命令响应.
     *
     * <p> SendTo: "/topic/callname/" + classId
     * 对象： 课堂全部人员都订阅，客户端只有点名返回结果包含自己时处理。或待再确认
     * </p>
     *
     * @param callNameParam 点名参数
     * @param principal 当前用户
     */
    @MessageMapping("/teaching/callname/command")
    public void command(CallNameParam callNameParam, Principal principal) {
        String classId = callNameParam.getClassId();
        CallNameCommand callNameCommand = callNameParam.getCommand();
        if (StringUtils.isBlank(classId) || null == callNameCommand || StringUtils.isBlank(callNameCommand.getType())
                || StringUtils.isBlank(callNameCommand.getAction())) {
            throw new MessagingException("课堂id或指令参数缺失");
        }
        if (!CallNameCommand.TYPE.equals(callNameCommand.getType())) {
            throw new MessagingException("指令类型不匹配callName，而是" + callNameCommand.getType());
        }

        // 查询同一个课堂下同一班级的学生列表 --> TODO: 目前演示使用虚拟数据
        // TcClassroom classroom = tcClassroomImpl.get(classId);
        // if (classroom == null) {
        //     throw new MessagingException("课堂不存在！");
        // }
        // List<User> students = userService.getDetailUserInfoList(
        //     new User(null, null, new Office(classroom.getClassId())), ElearningConstant.UserRole.STUDENT);

        Integer num = callNameCommand.getNum() == null || callNameCommand.getNum() <= 0 || callNameCommand.getNum() > 20
            ? 2 : callNameCommand.getNum();
        List<User> students = new ArrayList<>();
        for (int i = 1; i <= num; i++) {
            students.add(new User("100" + i, "学生" + i));
        }

        if (CommandConstant.CallNameAction.START.equals(callNameCommand.getAction())) {
            callNameCommand.setStudentsSys(students);
        } else if (CommandConstant.CallNameAction.STOP.equals(callNameCommand.getAction())) {
            if (callNameCommand.getNum() == null || callNameCommand.getNum() <= 0) {
                throw new MessagingException("点名人数为空或小于等于0！");
            }
            // 从PC端获取到被随机选中学生用户id，然后通知到每个被点中学生用户
            List<String> stuIds = callNameCommand.getStuIds();
            if (CollectionUtils.isNotEmpty(students) && CollectionUtils.isNotEmpty(stuIds)) {
                for (String stuId : stuIds) {
                    for (User user : students) {
                        if (stuId != null && stuId.equalsIgnoreCase(user.getId())) {
                            // 只将消息发送给被随机选中学生
                            messagingTemplate.convertAndSendToUser(user.getLoginName(), "/queue/message",
                                "课堂点名提醒：你已被点中！");
                            break; // 找到并通知被选中学生后，直接跳出内层for循环
                        }
                    }
                }
            }
        } else {
            // noop 不在预定的actions里，也不抛异常，直接转发
            logger.info("callName: action {}", callNameCommand.getAction());
        }

        Map<String, Object> map = new HashMap<>();
        map.put(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON);

        if (StompProperties.RABBITMQ.equals(stompProperties.getExternalBroker())) {
            messagingTemplate.convertAndSend("/topic/callname." + classId, callNameParam, map);
        } else {
            messagingTemplate.convertAndSend("/topic/callname/" + classId, callNameParam, map);
        }

    }

    /**
     * 错误情况，只发给当前用户.
     *
     * @param exception 错误异常
     * @return Error message
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
