
package com.websocket.intercept;

import com.neoframework.common.constant.SecurityConstant;
import com.neoframework.microservices.wsteaching.utils.UserUtils;
import com.thinkgem.jeesite.modules.sys.entity.User;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptorAdapter;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.DefaultCsrfToken;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

/**
 * The type Ws channel interceptor.
 */
public class WsChannelInterceptor extends ChannelInterceptorAdapter {
    private static final Logger logger = LoggerFactory.getLogger(WsChannelInterceptor.class);

    /**
     * 用于维护在线用户的映射（Key:频道id，Value:该频道在线用户列表）.
     *
     * <p>TODO: (重要) 当前只是实现了单机（或说单个应用实例）中的在线用户列表。需要支持集群的话，要更多补充、扩展。</p>
     *
     */
    private static final Map<String, List<com.websocket.model.User>> ONLINE_USERS_MAP = new ConcurrentHashMap<>();

    /**
     * The Token services.
     */
    @Autowired
    DefaultTokenServices tokenServices;

    /**
     * 发送前预处理.
     *
     * <p>
     * TODO: （严重）这部分目前的实现应该有严重的 BUG ，例如多个频道订阅会不会有多个 SUBSCRIBE 事件等？待确认，如果有必须修正。
     *
     * 更多相关： {@link DefaultHandshakeHandler} {@link HttpSessionHandshakeInterceptor}
     * </p>
     *
     * @param message 消息
     * @param channel 频道信息
     * @return Message<?>
     */
    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        StompHeaderAccessor accessor = MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);
        String channelId = (String) accessor.getHeader(SimpMessageHeaderAccessor.DESTINATION_HEADER);
        OAuth2Authentication userAuth =
                (OAuth2Authentication) accessor.getHeader(SimpMessageHeaderAccessor.USER_HEADER);
        if (StompCommand.CONNECT.equals(accessor.getCommand())) { // 连接WebSocket
            logger.info("StompCommand.CONNECT");
            String jwtToken = accessor.getFirstNativeHeader(SecurityConstant.HEADER_X_AUTH_TOKEN);
            Map<String, Object> sessionAttributes =
                SimpMessageHeaderAccessor.getSessionAttributes(message.getHeaders());
            if (StringUtils.isNotEmpty(jwtToken)) {
                sessionAttributes.put(CsrfToken.class.getName(),
                    new DefaultCsrfToken("Auth-Token", "Auth-Token", jwtToken));
                OAuth2Authentication authToken = tokenServices.loadAuthentication(jwtToken);
                SecurityContextHolder.getContext().setAuthentication(authToken);
                accessor.setUser(authToken);
            } else {
                // 这表示没有有效的token, 结合 {@link #configureInbound(MessageSecurityMetadataSourceRegistry messages)}
                // 处理好 websocket 的基本安全问题
            }
        } else if (StompCommand.SUBSCRIBE.equals(accessor.getCommand())) { // 订阅频道
            logger.info("StompCommand.SUBSCRIBE");
            Authentication principal = userAuth.getUserAuthentication();
            User user = UserUtils.getCurrentSysUser(principal);
            if (StringUtils.isNotBlank(channelId) && user != null) {
                com.websocket.model.User wsUser = new com.websocket.model.User(user);

                // ahming notes: 注意极端临界条件，活用 interface ConcurrentMap.putIfAbsent, replace 等方法保证数据一致
                while (true) {
                    List<com.websocket.model.User> channelUsers = ONLINE_USERS_MAP.get(channelId);
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
                            List<com.websocket.model.User> newChannelUsers = new ArrayList<>(channelUsers);
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
            }
        } else if (StompCommand.UNSUBSCRIBE.equals(accessor.getCommand())) { // 取消订阅
            logger.info("StompCommand.UNSUBSCRIBE");
            User user = UserUtils.getCurrentSysUser(userAuth.getUserAuthentication());
            if (StringUtils.isNotBlank(channelId) && user != null) {
                com.websocket.model.User wsUser = new com.websocket.model.User(user);
                List<com.websocket.model.User> channelUsers = ONLINE_USERS_MAP.get(channelId);
                if (CollectionUtils.isNotEmpty(channelUsers)) {
                    // 如果当前频道用户列表中包含当前用户，则将其从频道用户列表中移除
                    channelUsers.remove(wsUser);
                }
            }
        } else if (StompCommand.DISCONNECT.equals(accessor.getCommand())) { // 断开连接
            logger.info("StompCommand.DISCONNECT");
            User user = UserUtils.getCurrentSysUser(userAuth.getUserAuthentication());
            if (user != null && ONLINE_USERS_MAP.size() > 0) {
                com.websocket.model.User wsUser = new com.websocket.model.User(user);
                // 断开WebSocket连接时，将从所有频道用户列表中将当前用户移除
                for (List<com.websocket.model.User> userList : ONLINE_USERS_MAP.values()) {
                    userList.remove(wsUser);
                }
            }
        }
        return message;
    }

    /**
     * 从 ONLINE_USERS_MAP 返回当前通道的在线用户.
     *
     * @param channelId 通道 ID
     * @return 在线用户列表 channel users
     */
    public static List<com.websocket.model.User> getChannelUsers(String channelId) {
        List<com.websocket.model.User> channelUsers = ONLINE_USERS_MAP.get(channelId);
        if (channelUsers == null) {
            return Collections.emptyList();
        }
        return channelUsers;
    }
}
