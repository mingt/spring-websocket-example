
package com.websocket.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Date: 2020/4/21 Ahming
 */
@ConfigurationProperties(prefix = "stomp", ignoreUnknownFields = false)
public class StompProperties {

    public static final String RABBITMQ = "rabbitmq";
    public static final String ROCKETMQ = "rocketmq";

    /**
     * 使用的 External Broker 名称。非空则为对应的名称，为空则使用 Simple Broker .
     */
    private String externalBroker;

    private String server;
    private Integer port;
    private String username;
    private String password;

    public boolean ifExternalBroker() {
        return externalBroker != null && externalBroker.length() > 0;
    }

    public String getExternalBroker() {
        return externalBroker;
    }

    public void setExternalBroker(String externalBroker) {
        this.externalBroker = externalBroker;
    }

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
