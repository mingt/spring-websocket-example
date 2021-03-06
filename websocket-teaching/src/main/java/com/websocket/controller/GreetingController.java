
package com.websocket.controller;

import com.websocket.config.StompProperties;
import com.websocket.model.Greeting;
import com.websocket.model.HelloMessage;
import java.security.Principal;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageExceptionHandler;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.SimpMessageType;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

/**
 * The type Greeting controller.
 */
@Controller
public class GreetingController extends BaseWebSocketController {

    private static final Logger logger = LoggerFactory.getLogger(GreetingController.class);

    @Autowired
    private SimpMessageSendingOperations messagingTemplate;

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;
    @Autowired
    private StompProperties stompProperties;

    /**
     * Greeting greeting.
     *
     * @param message the message
     * @param principal the principal
     * @return the greeting
     *
     * @throws Exception the exception
     */
    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public Greeting greeting(HelloMessage message, Principal principal) throws Exception {
        String fromUser =
                (principal != null && StringUtils.isNotBlank(principal.getName())) ? principal.getName() : UNKNOWN_USER;
        logger.info("Principal with {}", fromUser);

        // 202008 为了压测，把 sleep 去掉，换回默认的 hello 内容
        // Thread.sleep(1000); // simulated delay
        // return new Greeting("(全部Greeting) " + HtmlUtils.htmlEscape(message.getName()) + "! --- from: " + fromUser);
        return new Greeting("Hello, " + HtmlUtils.htmlEscape(message.getName()) + "!");
    }

    /**
     * Pm greeting.
     *
     * @param message the message
     * @param principal the principal
     * @param sessionId the session id
     * @throws Exception the exception
     */
    @MessageMapping("/pm")
    // @SendToUser("/queue/greetings")
    public void pmGreeting(HelloMessage message, Principal principal, @Header("simpSessionId") String sessionId)
            throws Exception {
        String fromUser =
                (principal != null && StringUtils.isNotBlank(principal.getName())) ? principal.getName() : UNKNOWN_USER;
        logger.info("Principal with {}, sessionId {}", fromUser, sessionId);

        // if (true) {
        // throw new MessagingException("te te testig...");
        // }

        // Thread.sleep(1000); // simulated delay

        // return new Greeting("(自己) Hello, " + HtmlUtils.htmlEscape(message.getName()) + "! --- from: " + fromUser);
        Greeting result =
                new Greeting("(自己) " + HtmlUtils.htmlEscape(message.getName()) + "! --- from: " + fromUser); // Hello,

        SimpMessageHeaderAccessor headerAccessor = SimpMessageHeaderAccessor.create(SimpMessageType.MESSAGE);
        // fromUser 或 sessionId 或不要这里，都不能匹配
        // org.springframework.messaging.simp.user.DefaultUserDestinationResolver#parseMessage
        headerAccessor.setSessionId(sessionId);
        headerAccessor.setLeaveMutable(true);

        if (StompProperties.RABBITMQ.equals(stompProperties.getExternalBroker())) {

            // messagingTemplate.convertAndSend("/exchange/amq.direct/greetings", result,
            // headerAccessor.getMessageHeaders()); // 这个全部都发了

            // messagingTemplate.convertAndSendToUser(fromUser, "/exchange/amq.direct/greetings", result);
            messagingTemplate.convertAndSendToUser(fromUser, "/exchange/amq.direct/greetings", result,
                headerAccessor.getMessageHeaders());

            // 下面这个事实系 convertAndSendToUser 的底层方法，只是 userDestination 手动拼合
            // messagingTemplate.convertAndSend("/user/" + fromUser + "/exchange/amq.direct/greetings", result);

            // SimpMessagingTemplate 和 SimpMessageSendingOperations 确认都可以，但前者封装更多一层。可能也是同一个bean
            // simpMessagingTemplate.convertAndSendToUser(fromUser, "/exchange/amq.direct/greetings", result);
            // simpMessagingTemplate.convertAndSend("/exchange/amq.direct/greetings", result); // 可以
            // simpMessagingTemplate.convertAndSendToUser(fromUser, "/user/queue/greetings", result);

        } else {
            messagingTemplate.convertAndSendToUser(fromUser, "/queue/greetings", result,
                headerAccessor.getMessageHeaders());
            // messagingTemplate.convertAndSendToUser(fromUser, "/queue/greetings", result);
        }

    }

    /**
     * Pm greeting old greeting.
     *
     * <p>注意，经测试，这个注解方式会改善到全部相关的用户，并非私发。注意与上面“pm”的区别</p>
     *
     * @param message the message
     * @param principal the principal
     * @return the greeting
     *
     * @throws Exception the exception
     */
    @MessageMapping("/pmOld")
    @SendTo("/exchange/amq.direct/greetings")
    public Greeting pmGreetingOld(HelloMessage message, Principal principal) throws Exception {
        String fromUser =
                (principal != null && StringUtils.isNotBlank(principal.getName())) ? principal.getName() : UNKNOWN_USER;
        logger.info("Principal with {}", fromUser);

        // Thread.sleep(1000); // simulated delay
        return new Greeting(
                "(注解SendTo) " + HtmlUtils.htmlEscape(message.getName()) + "! --- from: " + fromUser); // Hello,
    }

    /**
     * 错误情况，只发给当前用户.
     *
     * @param exception the exception
     * @return string
     */
    @MessageExceptionHandler
    @SendToUser(destinations = "/queue/errors", broadcast = false)
    // @SendToUser(destinations="/exchange/amq.direct/errors", broadcast=false) // 测试确认同其他一样未生效
    public String handleException(Throwable exception) {
        if (exception != null) {
            logger.error("MessageExceptionHandler", exception);
            return exception.getMessage();
        }
        return "errors";
    }
}
