
package com.websocket.config;

import com.websocket.intercept.WsChannelInterceptor;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.messaging.converter.MessageConverter;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.messaging.support.ChannelInterceptorAdapter;
import org.springframework.security.config.annotation.web.messaging.MessageSecurityMetadataSourceRegistry;
import org.springframework.security.config.annotation.web.socket.AbstractSecurityWebSocketMessageBrokerConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

/**
 * The type Web socket config.
 */
@Configuration
@EnableWebSocketMessageBroker
@Order(Ordered.HIGHEST_PRECEDENCE + 50) // Ordered.HIGHEST_PRECEDENCE + 99
@EnableConfigurationProperties(StompProperties.class)
// implements WebSocketMessageBrokerConfigurer
public class WebSocketConfig extends AbstractSecurityWebSocketMessageBrokerConfigurer {

    @Autowired
    private StompProperties stompProperties;

    /* 用于维护在线用户的映射（Key:频道id，Value:该频道在线用户列表） */
    // public static final Map<String, List<User>> ONLINE_USERS_MAP=new ConcurrentHashMap<>();

    /**
     * Token store token store.
     *
     * @param dataSource the data source
     * @return the token store
     */
    @Bean(name = "tokenStore")
    public TokenStore tokenStore(DataSource dataSource) {
        return new JdbcTokenStore(dataSource);
    }

    /**
     * Token services default token services.
     *
     * @param tokenStore the token store
     * @return the default token services
     */
    @Bean(name = "tokenServices")
    public DefaultTokenServices tokenServices(TokenStore tokenStore) {
        final DefaultTokenServices tokenServices = new DefaultTokenServices();
        tokenServices.setTokenStore(tokenStore);

        // 不需要 //tokenServices.setClientDetailsService(clientDetailsService);
        // 不需要 //tokenServices.setTokenEnhancer(enhancer);

        tokenServices.setSupportRefreshToken(true);
        // 目的是下面这个。如果使用默认的true，refresh_token+jwt会有个 Bug ：
        // https://stackoverflow.com/questions/47318215/refresh-token-grant-type-supplies-another-refresh-token
        // 如果有这个 Bug ，前端就无法一直使用 refresh_token 刷新
        tokenServices.setReuseRefreshToken(false);

        return tokenServices;
    }

    // @Bean // 不需要
    // public ServerEndpointExporter serverEndpointExporter() {
    // return new ServerEndpointExporter();
    // }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {

        if (stompProperties.ifExternalBroker()) { // StringUtils.isBlank(stompProperties.getExternalBroker())
            // 配置请求都以/app打头，没有特殊意义，例如：@MessageMapping("/hello")，其实真实路径是/app/hello
            config.setApplicationDestinationPrefixes("/app", "/exchange")
                    .setUserDestinationPrefix("/user")
                    .enableStompBrokerRelay("/topic", "/queue", "/exchange", "/amq/queue") // TODO: try /amq/queue?
                    .setVirtualHost("/").setRelayHost(stompProperties.getServer())
                    .setRelayPort(stompProperties.getPort()).setClientLogin(stompProperties.getUsername()) // elearning
                    .setClientPasscode(stompProperties.getPassword()).setSystemLogin(stompProperties.getUsername())
                    .setSystemPasscode(stompProperties.getPassword())
            // .setRelayHost("localhost")
            // .setRelayPort(61613)
            // .setClientLogin("guest")
            // .setClientPasscode("guest")
            // .setSystemLogin("guest")
            // .setSystemPasscode("guest")
            //
            // .setUserDestinationBroadcast("/topic/greetings") // ahming marks: 需要不停广播时使用
            // .setUserRegistryBroadcast("/topic/greetings")
            //
            // .setSystemHeartbeatSendInterval(5000) // 默认是不是这两个？可以改
            // .setSystemHeartbeatReceiveInterval(4000)
            ;

        } else {
            // 下面配置不必要加 context 即不必要 /api/app/hello , 下面末尾是否带斜杆都可，建议带上
            // 定义了两个客户端订阅地址的前缀信息，也就是客户端接收服务端发送消息的前缀信息
            config.enableSimpleBroker("/queue", "/topic");
            // 定义了服务端接收地址的前缀，也即客户端给服务端发消息的地址前缀
            config.setApplicationDestinationPrefixes("/app");
            // // 推送用户前缀 {@link "https://segmentfault.com/a/1190000007853460"}
            // // 默认就是 /user 。The default prefix used to identify such destinations is "/user/"
            config.setUserDestinationPrefix("/user");

        }
    }

    /**
     * 因以 endpoint 开始的路径暂时排除 oauth 之外，所以统一加上 ws 作为标识前缀，避免与其他已有服务有冲突。
     *
     * @param registry StompEndpointRegistry
     */
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // 这里不能加context，即不要 /api/gs-guide-websocket 。(另外前面的斜杆 / 测试发现，有或没有都可以，但建议加上)
        registry.addEndpoint("/wschat").setAllowedOrigins("*").withSockJS();
        // 结果表明没有不同 // registry.addEndpoint("/wschatnojs").setAllowedOrigins("*").withSockJS();
        registry.addEndpoint("/wsteaching").setAllowedOrigins("*").withSockJS();
        // 结果表明没有不同 // registry.addEndpoint("/wsteachingnojs").setAllowedOrigins("*").withSockJS();
    }

    /**
     * 在 OAuth 下，必须添加下面这两个配置.
     * Refers to:
     * https://docs.spring.io/spring-security/site/docs/current/reference/htmlsingle/#websocket-authorization
     *
     * <p>
     * 否则会有以下错误：
     * ERROR
     * message:Failed to send message to ExecutorSubscribableChannel[clientInboundChannel]; nested exception is
     * org.springframework.security.web.csrf.MissingCsrfTokenException\c Could not verify the provided CSRF token
     * because your session was not found. content-length:0
     * </p>
     *
     * @param messages MessageSecurityMetadataSourceRegistry
     */
    @Override
    protected void configureInbound(MessageSecurityMetadataSourceRegistry messages) {
        messages.anyMessage().permitAll();

        // // 使用更严格的规则 TODO: 待完善
        // messages
        // .nullDestMatcher().authenticated() // Any message without a destination will require the user to be
        // authenticated
        // .simpSubscribeDestMatchers("/user/queue/errors").permitAll() // Anyone can subscribe to /user/queue/errors
        // .simpTypeMatchers(
        // // SimpMessageType.CONNECT,
        // SimpMessageType.MESSAGE,
        // SimpMessageType.SUBSCRIBE).authenticated()
        // .simpTypeMatchers(
        // SimpMessageType.CONNECT, // 注意
        // SimpMessageType.UNSUBSCRIBE,
        // SimpMessageType.DISCONNECT).permitAll()
        // //.nullDestMatcher().authenticated()
        // .anyMessage().denyAll();
    }

    @Override
    protected boolean sameOriginDisabled() {
        return true;
    }

    // /**
    // * Configure options related to the processing of messages received from and
    // * sent to WebSocket clients.
    // *
    // * @param registry
    // */
    // @Override
    // public void configureWebSocketTransport(WebSocketTransportRegistration registry) {
    //
    // }

    /**
     * 指定顺序并没用. 不过，把 "/gs-guide-websocket/**" 放到 permitAll 里，确保
     * app.js 中 stompClient.connect({"Authorization1":"Bearer eyJhbGciOiJSUz... 这个请求 header 的名称不为
     * “Authorization1”，则这里的 ChannelInterceptorAdapter 接收到传递过来的 token 相关值，可自定义处理
     *
     * <p>
     * 这个参考令人费解：
     * https://github.com/spring-projects/spring-framework/blob/master/src/docs/asciidoc/web/websocket.adoc
     *
     * Also note that when using Spring Security’s authorization for messages, at present you will need to
     * ensure that the authentication ChannelInterceptor config is ordered ahead of Spring Security’s. This
     * is best done by declaring the custom interceptor in its own implementation of
     * WebSocketMessageBrokerConfigurer marked with @Order(Ordered.HIGHEST_PRECEDENCE + 99).
     *
     * --> updated: 这里给出了相关的解决方法和更多的说明：
     * https://stackoverflow.com/questions/30887788/json-web-token-jwt-with-spring-based-sockjs-stomp-web-socket
     *
     * TO-DO: 待整合这个 JWT 的实现 -> 已整合
     * </p>
     *
     * <p>
     * 与 Oauth2 认证主要是下面这个 ChannelInterceptorAdapter
     * 1. 为了与 api 接口的 Authorization Bearer Token 区分，使用了不同的 header 名 X_AUTH_TOKEN。
     * 2. 直接按 auth-server，调用 tokenServices 去解释 access_token，验证，放入后面的流程里。
     * 3. 后面有些需要，应该考虑添加更多的 Interceptor 处理。
     * </p>
     *
     * @return ChannelInterceptorAdapter channel interceptor adapter
     */
    @Bean
    // @Order(-3000) // Ordered.HIGHEST_PRECEDENCE + 99
    public ChannelInterceptorAdapter channelInterceptorAdapter() {
        return new WsChannelInterceptor();
    }

    /**
     * configureClientInboundChannel 在父类为 final ，不过有这个 customizeClientInboundChannel .
     *
     * @param registration ChannelRegistration
     */
    @Override
    public void customizeClientInboundChannel(ChannelRegistration registration) {
        registration.setInterceptors(channelInterceptorAdapter());
        // //ChannelSecurityInterceptor interceptor = new ChannelSecurityInterceptor();
        // registration.setInterceptors(channelInterceptorAdapter(), new SecurityContextChannelInterceptor());
    }

    // /**
    // * Configure the {@link MessageChannel} used for
    // * outbound messages to WebSocket clients. By default the channel is backed
    // * by a thread pool of size 1. It is recommended to customize thread pool
    // * settings for production use.
    // *
    // * @param registration
    // */
    // @Override
    // public void configureClientOutboundChannel(ChannelRegistration registration) {
    //
    // }
    //
    // /**
    // * Add resolvers to support custom controller method argument types.
    // * <p>This does not override the built-in support for resolving handler
    // * method arguments. To customize the built-in support for argument
    // * resolution, configure {@code SimpAnnotationMethodMessageHandler} directly.
    // *
    // * @param argumentResolvers the resolvers to register (initially an empty list)
    // * @since 4.1.1
    // */
    // @Override
    // public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
    //
    // }
    //
    // /**
    // * Add handlers to support custom controller method return value types.
    // * <p>Using this option does not override the built-in support for handling
    // * return values. To customize the built-in support for handling return
    // * values, configure {@code SimpAnnotationMethodMessageHandler} directly.
    // *
    // * @param returnValueHandlers the handlers to register (initially an empty list)
    // * @since 4.1.1
    // */
    // @Override
    // public void addReturnValueHandlers(List<HandlerMethodReturnValueHandler> returnValueHandlers) {
    //
    // }

    /**
     * ahming notes: TODO：目前未清楚当时的考虑，未确定是否一定要。
     *
     * <p>
     * Configure the message converters to use when extracting the payload of
     * messages in annotated methods and when sending messages (e.g. through the
     * "broker" SimpMessagingTemplate).</p>
     *
     * <p>
     * The provided list, initially empty, can be used to add message converters
     * while the boolean return value is used to determine if default message should
     * be added as well.</p>
     *
     * @param messageConverters the converters to configure (initially an empty list)
     * @return whether to also add default converter or not
     */
    @Override
    public boolean configureMessageConverters(List<MessageConverter> messageConverters) {
        return false;
    }
}
