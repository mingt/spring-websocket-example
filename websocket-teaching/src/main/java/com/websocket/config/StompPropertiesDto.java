
package com.websocket.config;

import java.io.Serializable;

/**
 * StompProperties Dto.
 *
 * <p>Date: 2020/4/21 Ahming</p>
 */
public class StompPropertiesDto implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 使用的 External Broker 名称。非空则为对应的名称，为空则使用 Simple Broker .
     */
    private String externalBroker;

    /**
     * 确定是否外部 broker . 根据 externalBroker 是否为空来判断.
     *
     * <del>externalBroker 非空则 true ，否则为 false</del>
     */
    private Boolean ifExternalBroker;
    // public boolean ifExternalBroker() {
    //     return externalBroker != null && externalBroker.length() > 0;
    // }

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
     * Gets if external broker.
     *
     * @return the if external broker
     */
    public Boolean getIfExternalBroker() {
        return ifExternalBroker;
    }

    /**
     * Sets if external broker.
     *
     * @param ifExternalBroker the if external broker
     */
    public void setIfExternalBroker(Boolean ifExternalBroker) {
        this.ifExternalBroker = ifExternalBroker;
    }
}
