
package com.websocket.model.chat;

/**
 * The type Message.
 */
public class Message {

    /**
     * type 和 id 一起构成唯一的聊天频道. 例如 type = "public" + publicId 构成名师空间（公众号）的聊天频道，
     * type = "{gameName}" + gameRoomId 构成游戏的聊天频道...
     */
    private String type;
    private String id;

    /** 消息内容 TODO: 目前仅考虑文字内容 */
    private String content;

    /**
     * Instantiates a new Message.
     */
    public Message() {}

    /**
     * Instantiates a new Message.
     *
     * @param content the content
     */
    public Message(String content) {
        this.content = content;
    }

    /**
     * Instantiates a new Message.
     *
     * @param type the type
     * @param id the id
     * @param content the content
     */
    public Message(String type, String id, String content) {
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
