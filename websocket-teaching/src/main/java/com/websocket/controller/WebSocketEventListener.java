
package com.websocket.controller;

import com.neoframework.microservices.wsteaching.utils.UserUtils;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.websocket.model.WsUser;
import com.websocket.model.chat.ChatConfig;
import com.websocket.model.chat.ChatMessage;
import com.websocket.model.chat.ChatMessage.MessageType;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;
import org.springframework.web.socket.messaging.SessionSubscribeEvent;
import org.springframework.web.socket.messaging.SessionUnsubscribeEvent;

/**
 * 监听 Events ，必要时处理互动.
 *
 * <p>
 * 参考：
 * https://docs.spring.io/spring/docs/current/spring-framework-reference/web.html#websocket-stomp-appplication-context-events
 * </p>
 *
 * @author ahming
 */
@Component
public class WebSocketEventListener {

    private static final Logger logger = LoggerFactory.getLogger(WebSocketEventListener.class);

    /**
     * 用于维护在线用户的映射（Key:频道id，Value:该频道在线用户列表）.
     *
     * <p>TODO: (重要) 当前只是实现了单机（或说单个应用实例）中的在线用户列表。需要支持集群的话，要更多补充、扩展。</p>
     *
     */
    private static final Map<String, List<WsUser>> ONLINE_USERS_MAP = new ConcurrentHashMap<>();

    @Autowired
    private SimpMessageSendingOperations messagingTemplate;

    /**
     * Handle web socket connect listener.
     *
     * @param event the event
     */
    @EventListener
    public void handleWebSocketConnectListener(SessionConnectedEvent event) {
        logger.info("Received a new web socket connection");

        // StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
        // // 下面 getHeader 只有 unsubscribe 才对应频道ID
        // String channelIdSub = (String) headerAccessor.getHeader(SimpMessageHeaderAccessor.SUBSCRIPTION_ID_HEADER);
        // logger.info("SessionConnectedEvent channelIdSub={}", channelIdSub); // null
        // // 下面 getDestination 只有 subscribe 时才对应频道ID
        // String channelId = headerAccessor.getDestination();
        // logger.info("SessionConnectedEvent channelId={}", channelId); // null
    }

    /**
     * Handle web socket subscribe listener.
     *
     * @param sessionSubscribeEvent the session subscribe event
     */
    @EventListener
    public void handleWebSocketSubscribeListener(SessionSubscribeEvent sessionSubscribeEvent) {
        logger.info("-----sessionSubscribeEvent start------");

        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(sessionSubscribeEvent.getMessage());

        // 下面 getHeader 只有 unsubscribe 才对应频道ID
        // String channelIdSub = (String) headerAccessor.getHeader(SimpMessageHeaderAccessor.SUBSCRIPTION_ID_HEADER);
        // logger.info("SessionConnectedEvent channelIdSub={}", channelIdSub); // 类似 sub-0
        // 下面 getDestination 只有 subscribe 时才对应频道ID
        String channelId = headerAccessor.getDestination();

        Principal principal = headerAccessor.getUser();
        User user = UserUtils.getCurrentSysUser(principal);
        logger.info("channelId={}, username={}", channelId, user != null ? user.getName() : null);
        if (StringUtils.isNotBlank(channelId) && user != null) {
            WsUser wsUser = new WsUser(user);

            // ahming notes: 注意极端临界条件，活用 interface ConcurrentMap.putIfAbsent, replace 等方法保证数据一致
            while (true) {
                List<WsUser> channelUsers = ONLINE_USERS_MAP.get(channelId);
                // 下面是要区分是否为 null ,而不是数组 null 或非 null 但 size = 0
                if (channelUsers == null) { // wrong: (CollectionUtils.isEmpty(channelUsers)) {
                    // 如果当前频道用户列表为空，则新创建一个频道用户列表，并将当前用户添加进去
                    channelUsers = new ArrayList<>();
                    channelUsers.add(wsUser);
                    // 下面与 null 比较，要注意成功新增才返回 null ，如果已存在才非空，返回已存在的值
                    if ((ONLINE_USERS_MAP.putIfAbsent(channelId, channelUsers)) == null) {
                        break;
                    }
                } else {
                    if (!channelUsers.contains(wsUser)) {
                        // 如果当前频道用户列表中不包含当前用户，则将其加入频道用户列表中去
                        List<WsUser> newChannelUsers = new ArrayList<>(channelUsers);
                        // newChannelUsers.addAll(channelUsers);
                        newChannelUsers.add(wsUser);
                        if (ONLINE_USERS_MAP.replace(channelId, channelUsers, newChannelUsers)) {
                            break;
                        }
                    } else {
                        break;
                    }
                }
            }

            // 聊天频道的话，需要时向相关频道提示用户上线下线等
            if (ChatConfig.ifChatRoomRemideOnOffline(channelId)) {
                Map<String, Object> map = new HashMap<>();
                map.put(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON);
                ChatMessage chatMessage = new ChatMessage(String.format("%s 进入频道", user.getName()));
                chatMessage.setMessageType(MessageType.JOIN);
                messagingTemplate.convertAndSend(channelId, chatMessage, map);
            }
        }

        // logger.info("订阅事件" + JsonMapper.toJsonString(headerAccessor));
        // Map<String, Object> sessionAttributes = headerAccessor.getSessionAttributes();
        // logger.info("订阅事件" + JsonMapper.toJsonString(sessionAttributes));

        logger.info("-----sessionSubscribeEvent end------");
    }

    /**
     * Handle web socket unsubscribe listener.
     *
     * @param sessionUnsubscribeEvent the session unsubscribe event
     */
    @EventListener
    public void handleWebSocketUnsubscribeListener(SessionUnsubscribeEvent sessionUnsubscribeEvent) {
        logger.info("-----sessionUnsubscribeEvent start------");

        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(sessionUnsubscribeEvent.getMessage());
        // 下面 getHeader 只有 unsubscribe 才对应频道ID
        String channelIdSub = (String) headerAccessor.getHeader(SimpMessageHeaderAccessor.SUBSCRIPTION_ID_HEADER);
        logger.info("SessionConnectedEvent channelIdSub={}", channelIdSub);
        // 下面 getDestination 只有 subscribe 时才对应频道ID
        // String channelId = headerAccessor.getDestination();
        // logger.info("SessionUnsubscribeEvent channelId={}", channelId);

        Principal principal = headerAccessor.getUser();
        User user = UserUtils.getCurrentSysUser(principal);
        if (StringUtils.isNotBlank(channelIdSub) && user != null) {
            WsUser wsUser = new WsUser(user);
            List<WsUser> channelUsers = ONLINE_USERS_MAP.get(channelIdSub);
            if (CollectionUtils.isNotEmpty(channelUsers)) {
                // 如果当前频道用户列表中包含当前用户，则将其从频道用户列表中移除
                channelUsers.remove(wsUser);
            }

            // 聊天频道的话，需要时向相关频道提示用户上线下线等
            if (ChatConfig.ifChatRoomRemideOnOffline(channelIdSub)) {
                Map<String, Object> map = new HashMap<>();
                map.put(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON);
                ChatMessage chatMessage = new ChatMessage(String.format("%s 离开频道", user.getName()));
                chatMessage.setMessageType(MessageType.LEAVE);
                messagingTemplate.convertAndSend(channelIdSub, chatMessage, map);
            }
        }

        logger.info("-----sessionUnsubscribeEvent end------");
    }

    /**
     * Handle web socket disconnect listener.
     *
     * @param event the event
     */
    @EventListener
    public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {
        logger.info("Received a web socket disconnection");
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());

        Principal principal = headerAccessor.getUser();
        User user = UserUtils.getCurrentSysUser(principal);

        if (user != null && ONLINE_USERS_MAP.size() > 0) {
            WsUser wsUser = new WsUser(user);
            // 断开WebSocket连接时，将从所有频道用户列表中将当前用户移除
            for (List<WsUser> userList : ONLINE_USERS_MAP.values()) {
                userList.remove(wsUser);
            }
        }

    }

    /**
     * 从 ONLINE_USERS_MAP 返回当前通道的在线用户.
     *
     * @param channelId 通道 ID
     * @return 在线用户列表 channel users
     */
    public static List<WsUser> getChannelUsers(String channelId) {
        List<WsUser> channelUsers = ONLINE_USERS_MAP.get(channelId);
        if (channelUsers == null) {
            return Collections.emptyList();
        }
        return channelUsers;
    }
}
