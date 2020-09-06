
package com.websocket.controller;

import com.websocket.WsConstant;
import com.websocket.config.StompProperties;
import com.websocket.listener.WebSocketEventListener;
import com.websocket.model.WsUser;
import com.websocket.model.chat.ChatMessage;
import com.websocket.model.chat.ChatRoom;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
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
import org.springframework.web.util.HtmlUtils;

/**
 * The type Generic chat controller.
 */
@Controller
// 不好扩展 //@MessageMapping("/chat")
public class GenericChatController extends BaseWebSocketController {

    private static final Logger logger = LoggerFactory.getLogger(GenericChatController.class);

    /**
     * The constant roomNo.
     */
    protected static AtomicInteger roomNo = new AtomicInteger(0);

    @Autowired
    private SimpMessageSendingOperations messagingTemplate;
    @Autowired
    private StompProperties stompProperties;

    /**
     * 通用的给某指定频道发消息.
     *
     * <p>TODO: 改为最终消息的相关信息。目前仅为配合测试页面返回更多信息的文字</p>
     *
     * @param message 带有频道信息 type 和 id
     * @param principal 当前用户
     * @throws Exception Exception
     */
    @MessageMapping("/chat/sendMsg")
    // @SendTo("/topic/room/{type}/{id}")请重新登录重试
    public void sendMsg(ChatMessage message, Principal principal) throws Exception {
        String fromUser =
                (principal != null && StringUtils.isNotBlank(principal.getName())) ? principal.getName() : UNKNOWN_USER;
        logger.info("Principal with {}", fromUser);

        if (StringUtils.isBlank(message.getType()) || StringUtils.isBlank(message.getId())) {
            throw new MessagingException("频道type和id不能为空");
        }
        String separator = WsConstant.getNameSeparator(stompProperties.ifExternalBroker());
        // 下面为了兼容前端（如移动端）旧版本，要注意保留相同的分隔符，直到需要全面切到 . 之后可调整。rabbitmq 不支持 /
        String channelId = "/topic/messages" + separator + message.getType() + separator + message.getId();

        // Thread.sleep(1000); // simulated delay

        Map<String, Object> map = new HashMap<>();
        map.put(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON);

        // TODO: 改为最终消息的相关信息

        ChatMessage result = new ChatMessage("(频道 " + channelId + ") 消息: " + HtmlUtils.htmlEscape(message.getContent())
                + "! ----- from: " + fromUser);
        result.setId(message.getId());
        result.setType(message.getType());
        messagingTemplate.convertAndSend(channelId, result, map);
    }

    /**
     * 获取聊天室当前的用户列表. 父类只列出当前用户，作为默认实现，子类再具体处理.
     *
     * @param room 带频道信息
     * @param channelId 频道ID
     * @param principal 当前用户
     * @return users
     */
    protected List<WsUser> getUsers(ChatRoom room, String channelId, Principal principal) {
        // // String fromUser = (principal != null && StringUtils.isNotBlank(principal.getName()))
        // // ? principal.getName() : UNKNOWN_USER;
        // // logger.info("Principal with {}", fromUser);
        //
        // CustomUserDetails userDetails = getCurrentCustomUserDetails(principal);
        // com.thinkgem.jeesite.modules.sys.entity.User sysUser =
        // (null != userDetails) ? userDetails.getUser() : new com.thinkgem.jeesite.modules.sys.entity.User();
        // List<User> users = new ArrayList<>();
        // if (null != sysUser.getLoginName()) {
        // User user = new User(sysUser.getId(), sysUser.getUid(), sysUser.getLoginName(), sysUser.getName(),
        // userDetails.getRole());
        // users.add(user);
        // } else {
        // String fromUser = (principal != null && StringUtils.isNotBlank(principal.getName())) ? principal.getName()
        // : UNKNOWN_USER;
        // User user = new User(fromUser);
        // users.add(user);
        // }
        // return users;

        return WebSocketEventListener.getChannelUsers(channelId);
    }

    /**
     * 错误情况，只发给当前用户.
     *
     * @param exception 错误异常
     * @return string
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
