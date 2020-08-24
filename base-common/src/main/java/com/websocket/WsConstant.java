
package com.websocket;

/**
 * WebSocket相关配置常量.
 *
 * @author Ahming
 * @since 2020/4/22
 */
public class WsConstant {

    /**
     * 分隔符。 应用到路径，topic/queue等名称上。
     *
     * <p>为了兼容和后缀兼容性，建议使用{@link #getNameSeparator(boolean)}
     *
     * 出于如 RabbitMQ 的 topic/queue 等名称不能使用 / 作为分隔符，所以在 Spring 层、前端也尽量不使用 / ，而使用 . 来分隔，这样能避免
     * 后期返工调整. 参考：
     * https://docs.spring.io/spring/docs/current/spring-framework-reference/web.html#websocket-stomp-destination-separator
     * </p>
     */
    public static final String NAME_SEPARATOR = ".";

    /**
     * 之前的分隔符。后面请不要使用
     */
    private static final String NAME_SEPARATOR_SLASH = "/";

    /**
     * 当前为了保证前端（如移动端）的兼容性，在不使用 RabbitMQ 的情况下，仍然保留使用。
     * TODO：但新的实现或启用 RabbitMQ 后，就必须避免使用了。
     *
     * @return
     */
    public static String getNameSeparator(boolean isExternalBroker) {
        if (isExternalBroker) {
            return NAME_SEPARATOR;
        } else {
            return NAME_SEPARATOR_SLASH;
        }
    }
}
