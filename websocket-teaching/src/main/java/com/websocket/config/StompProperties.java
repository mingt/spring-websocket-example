
package com.websocket.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Stomp 配置参数.
 *
 * <p>Date: 2020/4/21 Ahming</p>
 */
@ConfigurationProperties(prefix = "stomp", ignoreUnknownFields = false)
public class StompProperties {

    /**
     * The constant RABBITMQ.
     */
    public static final String RABBITMQ = "rabbitmq";
    /**
     * The constant ROCKETMQ.
     */
    public static final String ROCKETMQ = "rocketmq";

    /**
     * 使用的 External Broker 名称。非空则为对应的名称，为空则使用 Simple Broker .
     */
    private String externalBroker;

    private String server;
    private Integer port;
    private String username;
    private String password;

    /**
     * If external broker boolean.
     *
     * @return the boolean
     */
    public boolean ifExternalBroker() {
        return externalBroker != null && externalBroker.length() > 0;
    }

    /**
     * Gets external broker.
     *
     * @return the external broker
     */
    public String getExternalBroker() {
        return externalBroker;
    }

    /**
     * Sets external broker.
     *
     * @param externalBroker the external broker
     */
    public void setExternalBroker(String externalBroker) {
        this.externalBroker = externalBroker;
    }

    /**
     * Gets server.
     *
     * @return the server
     */
    public String getServer() {
        return server;
    }

    /**
     * Sets server.
     *
     * @param server the server
     */
    public void setServer(String server) {
        this.server = server;
    }

    /**
     * Gets port.
     *
     * @return the port
     */
    public Integer getPort() {
        return port;
    }

    /**
     * Sets port.
     *
     * @param port the port
     */
    public void setPort(Integer port) {
        this.port = port;
    }

    /**
     * Gets username.
     *
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets username.
     *
     * @param username the username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Gets password.
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets password.
     *
     * @param password the password
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
