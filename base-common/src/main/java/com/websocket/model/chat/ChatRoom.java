
package com.websocket.model.chat;

import java.io.Serializable;

/**
 * （虚拟）聊天室 Room ，可理解为聊天频道. 在许多场景下都会有聊天场景，设计上尽量统一接口，统一数据格式等.
 */
public class ChatRoom implements Serializable {
    private static final long serialVersionUID = 1L;

    /** 内部编号, 有需要时使用. 暂时以 Controller 的 Atom 元素计算编号 **/
    private Integer no;

    /**
     * type 和 id 一起构成唯一的聊天频道. 例如 type = "public" + publicId 构成名师空间（公众号）的聊天频道，
     * type = "{gameName}" + gameRoomId 构成游戏的聊天频道...
     */
    private String type;
    /**
     * 参照 {@link #type}
     */
    private String id;

    /** 按需使用，名称 */
    private String name;

    /**
     * 是否上下线提醒.
     */
    private Boolean ifRemindOnOffline;

    /**
     * Gets no.
     *
     * @return the no
     */
    public Integer getNo() {
        return no;
    }

    /**
     * Sets no.
     *
     * @param no the no
     */
    public void setNo(Integer no) {
        this.no = no;
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

    /**
     * Gets if remind on offline.
     *
     * @return the if remind on offline
     */
    public Boolean getIfRemindOnOffline() {
        return ifRemindOnOffline;
    }

    /**
     * Sets if remind on offline.
     *
     * @param ifRemindOnOffline the if remind on offline
     */
    public void setIfRemindOnOffline(Boolean ifRemindOnOffline) {
        this.ifRemindOnOffline = ifRemindOnOffline;
    }
}
