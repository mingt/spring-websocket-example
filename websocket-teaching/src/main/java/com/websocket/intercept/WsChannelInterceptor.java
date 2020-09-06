
package com.websocket.intercept;

import com.neoframework.common.constant.SecurityConstant;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessagingException;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptorAdapter;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.common.exceptions.InvalidTokenException;
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

                // TO-DO: (重要) 这里应该捕获可能的异常，并适当做处理。当前在线上生产服经常看到的日志：
                // org.springframework.messaging.MessageDeliveryException: Failed to send message to
                // ExecutorSubscribableChannel[clientInboundChannel]; 等，可能与这里有关，或不知可以从这里得到启发，可能在某此
                // 处理没有捕获必要及时处理的异常
                //
                // 因为前端订阅对终端用户应该透明，这里适当的方式可能系提示用户重新登录，使用未过期/合法的token重启请求
                try {
                    OAuth2Authentication authToken = tokenServices.loadAuthentication(jwtToken);
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                    accessor.setUser(authToken);
                } catch (InvalidTokenException | AuthenticationException e) {
                    throw new MessagingException("请重新登录重试");
                }
            } else {
                // 这表示没有有效的token, 结合 {@link #configureInbound(MessageSecurityMetadataSourceRegistry messages)}
                // 处理好 websocket 的基本安全问题
            }
        } else if (StompCommand.SUBSCRIBE.equals(accessor.getCommand())) { // 订阅频道
            logger.info("StompCommand.SUBSCRIBE");

        } else if (StompCommand.UNSUBSCRIBE.equals(accessor.getCommand())) { // 取消订阅
            logger.info("StompCommand.UNSUBSCRIBE");

        } else if (StompCommand.DISCONNECT.equals(accessor.getCommand())) { // 断开连接
            logger.info("StompCommand.DISCONNECT");

            // 根据 Spring 文档提示， StompCommand.DISCONNECT 可能会被消费多次，这里多次被调用，所以建议改为
            // com.websocket.listener.WebSocketEventListener.handleWebSocketDisconnectListener 去处理
        }
        return message;
    }

}
