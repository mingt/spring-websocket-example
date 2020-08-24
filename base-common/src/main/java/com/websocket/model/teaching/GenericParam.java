
package com.websocket.model.teaching;

import com.websocket.model.command.BaseCommand;

/**
 * 通用命令参数.
 */
public class GenericParam {

    /** 课堂id，可选 */
    private String classId;
    /** 要发送的channel，可选。可包括完整书写如 /topic/XXX , /user/queue/YYY 等频道 */
    private String chan;
    /** 要发送的用户 username 列表，使用#分隔，可选 */
    private String users;

    private BaseCommand command;

    /**
     * Gets class id.
     *
     * @return the class id
     */
    public String getClassId() {
        return classId;
    }

    /**
     * Sets class id.
     *
     * @param classId the class id
     */
    public void setClassId(String classId) {
        this.classId = classId;
    }

    /**
     * Gets chan.
     *
     * @return the chan
     */
    public String getChan() {
        return chan;
    }

    /**
     * Sets chan.
     *
     * @param chan the chan
     */
    public void setChan(String chan) {
        this.chan = chan;
    }

    /**
     * Gets users.
     *
     * @return the users
     */
    public String getUsers() {
        return users;
    }

    /**
     * Sets users.
     *
     * @param users the users
     */
    public void setUsers(String users) {
        this.users = users;
    }

    /**
     * Gets command.
     *
     * @return the command
     */
    public BaseCommand getCommand() {
        return command;
    }

    /**
     * Sets command.
     *
     * @param command the command
     */
    public void setCommand(BaseCommand command) {
        this.command = command;
    }
}
