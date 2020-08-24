
package com.websocket.model;

/**
 * The type Hello message.
 */
public class HelloMessage {

    private String name;

    /**
     * Instantiates a new Hello message.
     */
    public HelloMessage() {}

    /**
     * Instantiates a new Hello message.
     *
     * @param name the name
     */
    public HelloMessage(String name) {
        this.name = name;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }
}
