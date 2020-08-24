
package com.websocket.intercept;

import com.neoframework.common.constant.SecurityConstant;
import com.neoframework.microservices.wsteaching.utils.UserUtils;
import com.thinkgem.jeesite.modules.sys.entity.User;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
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

// import com.boxue.elearning.api.common.ElearningConstant;

public class WsChannelInterceptor extends ChannelInterceptorAdapter {

    /* 用于维护在线用户的映射（Key:频道id，Value:该频道在线用户列表） */
    public static final Map<String, List<User>> ONLINE_USERS_MAP = new ConcurrentHashMap<>();

    @Autowired
    DefaultTokenServices tokenServices;

    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        StompHeaderAccessor accessor = MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);
        String channelId = (String) accessor.getHeader(SimpMessageHeaderAccessor.DESTINATION_HEADER);
        OAuth2Authentication userAuth =
                (OAuth2Authentication) accessor.getHeader(SimpMessageHeaderAccessor.USER_HEADER);
        if (StompCommand.CONNECT.equals(accessor.getCommand())) { // 连接WebSocket
            String jwtToken = accessor.getFirstNativeHeader(SecurityConstant.HEADER_X_AUTH_TOKEN);
            Map sessionAttributes = SimpMessageHeaderAccessor.getSessionAttributes(message.getHeaders());
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
            Authentication principal = userAuth.getUserAuthentication();
            User user = UserUtils.getCurrentSysUser(principal);
            if (StringUtils.isNotBlank(channelId) && user != null) {
                List<User> channelUsers = ONLINE_USERS_MAP.get(channelId);
                if (CollectionUtils.isEmpty(channelUsers)) { // 如果当前频道用户列表为空，则新创建一个频道用户列表，并将当前用户添加进去
                    channelUsers = new ArrayList<>();
                    channelUsers.add(user);
                    ONLINE_USERS_MAP.put(channelId, channelUsers);
                } else {
                    if (!channelUsers.contains(user)) {
                        channelUsers.add(user); // 如果当前频道用户列表中不包含当前用户，则将其加入频道用户列表中去
                    }
                }
            }
        } else if (StompCommand.UNSUBSCRIBE.equals(accessor.getCommand())) { // 取消订阅
            User user = UserUtils.getCurrentSysUser(userAuth.getUserAuthentication());
            if (StringUtils.isNotBlank(channelId) && user != null) {
                List<User> channelUsers = ONLINE_USERS_MAP.get(channelId);
                if (CollectionUtils.isNotEmpty(channelUsers)) {
                    if (channelUsers.contains(user)) {
                        channelUsers.remove(user); // 如果当前频道用户列表中包含当前用户，则将其从频道用户列表中移除
                    }
                }
            }
        } else if (StompCommand.DISCONNECT.equals(accessor.getCommand())) { // 断开连接
            User user = UserUtils.getCurrentSysUser(userAuth.getUserAuthentication());
            if (user != null && ONLINE_USERS_MAP.size() > 0) {
                // 断开WebSocket连接时，将从所有频道用户列表中将当前用户移除
                for (List<User> userList : ONLINE_USERS_MAP.values()) {
                    if (userList.contains(user)) {
                        userList.remove(user);
                    }
                }
            }
        }
        return message;
    }
}
