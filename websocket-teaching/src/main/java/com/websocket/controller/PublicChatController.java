
package com.websocket.controller;

// import com.anilallewar.microservices.user.service.SpPublicAdminServiceImpl;
// import com.anilallewar.microservices.user.service.UserService;
import com.websocket.WsConstant;
import com.websocket.config.StompProperties;
import com.websocket.model.WsUser;
import com.websocket.model.chat.Message;
import com.websocket.model.chat.Room;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.MessagingException;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;
import org.springframework.util.MimeTypeUtils;

/**
 * 公共空间聊天.
 */
@Controller
public class PublicChatController extends GenericChatController {

    private static final Logger logger = LoggerFactory.getLogger(PublicChatController.class);

    @Autowired
    private SimpMessageSendingOperations messagingTemplate;
    @Autowired
    private StompProperties stompProperties;

    // @Autowired
    // private SpPublicAdminServiceImpl spPublicAdminService;
    // @Autowired
    // private UserService userService;

    /**
     * 继承，直接调用父类的通用发信息，避免 controller: Ambiguous mapping found.
     *
     * @param message 带有频道信息 type 和 id
     * @param principal 当前用户
     * @throws Exception Ex
     */
    @Override
    @MessageMapping("/publicChat/sendMsg")
    public void sendMsg(Message message, Principal principal) throws Exception {
        super.sendMsg(message, principal);
    }

    /**
     * 获取名师空间的用户列表. 父类只列出当前用户，作为默认实现，子类再具体处理.
     *
     * <p>TODO: 事实名师空间不以空间的管理员列表返回。只要一个老师的称呼和当前学生就可以（或者全部学生？）</p>
     *
     * @param room 带频道信息
     * @param principal 当前用户
     */
    @MessageMapping("/publicChat/users")
    // @SendTo("/topic/users/{type}/{id}")
    public void publicChatUsers(Room room, Principal principal) {

        if (StringUtils.isBlank(room.getType()) || StringUtils.isBlank(room.getId())) {
            throw new MessagingException("频道type和id不能为空");
        }
        String separator = WsConstant.getNameSeparator(stompProperties.ifExternalBroker());
        // 下面为了兼容前端（如移动端）旧版本，要注意保留相同的分隔符，直到需要全面切到 . 之后可调整。rabbitmq 不支持 /
        String channelId = "/topic/users" + separator + room.getType() + separator + room.getId();

        List<WsUser> users = getUsers(room, channelId, principal);

        // 若为人人通，这里查询出管理员 admin 的加进入。并可尝试设置正确的状态（在线或离线）

        Map<String, Object> map = new HashMap<>();
        map.put(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON);
        messagingTemplate.convertAndSend(channelId, users, map); // "/topic/users." + channelId
    }
}
