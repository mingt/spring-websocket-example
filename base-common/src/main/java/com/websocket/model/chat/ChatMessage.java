
package com.websocket.model.chat;

import java.io.Serializable;

/**
 * The type Message.
 */
public class ChatMessage implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 聊天消息类型.
     */
    public static class MessageType {
        private MessageType(){}

        /**
         * 默认.
         */
        public static final int DEFAULT = 0;
        /**
         * 对话.
         */
        public static final int CHAT = 1;
        /**
         * 加入频道.
         */
        public static final int JOIN = 2;
        /**
         * 离开频道.
         */
        public static final int LEAVE = 3;
    }

    /**
     * type 和 id 一起构成唯一的聊天频道. 例如 type = "public" + publicId 构成名师空间（公众号）的聊天频道，
     * type = "{gameName}" + gameRoomId 构成游戏的聊天频道...
     */
    private String type;
    /**
     * 参照 {@link #type}
     */
    private String id;

    /**
     * 消息类型. See {@link MessageType}
     */
    private Integer messageType;

    /** 消息内容 TODO: 目前仅考虑文字内容 */
    private String content;

    /**
     * Instantiates a new Message.
     */
    public ChatMessage() {}

    /**
     * Instantiates a new Message.
     *
     * @param content the content
     */
    public ChatMessage(String content) {
        this.content = content;
    }

    /**
     * Instantiates a new Message.
     *
     * @param type the type
     * @param id the id
     * @param content the content
     */
    public ChatMessage(String type, String id, String content) {
        this.type = type;
        this.id = id;
        this.content = content;
    }

    /**
     * Gets type.
     *
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * Sets type.
     *
     * @param type the type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Gets message type.
     *
     * @return the message type
     */
    public Integer getMessageType() {
        return messageType;
    }

    /**
     * Sets message type.
     *
     * @param messageType the message type
     */
    public void setMessageType(Integer messageType) {
        this.messageType = messageType;
    }

    /**
     * Gets content.
     *
     * @return the content
     */
    public String getContent() {
        return content;
    }

    /**
     * Sets content.
     *
     * @param content the content
     */
    public void setContent(String content) {
        this.content = content;
    }
}
