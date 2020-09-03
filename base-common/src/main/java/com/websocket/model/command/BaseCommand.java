
package com.websocket.model.command;

import java.util.HashMap;
import java.util.Map;

/**
 * ws 命令 model 基类.
 */
public class BaseCommand {

    /** 从哪个平台发出的: pc | ios | android | web */
    private String fromPlatform;
    /** 角色. 如 @ link ElearningConstant.UserRole */
    private String role;
    /** 时间戳 */
    private Long timestamp;
    /** 类型 */
    private String type;
    /** 动作，不同类型带不同的动作 */
    private String action;

    /**
     * 可由前端自定义的可变指定参数.
     *
     * <p>需要时，前端自定义可变参数，在多端前端交互时使用。参数的意义，由前端确定和使用，后端只是帮助传递</p>
     */
    private Map<String, Object> remarks;

    /**
     * Instantiates a new Base command.
     */
    public BaseCommand() {
        remarks = new HashMap<>();
    }

    /**
     * Gets from platform.
     *
     * @return the from platform
     */
    public String getFromPlatform() {
        return fromPlatform;
    }

    /**
     * Sets from platform.
     *
     * @param fromPlatform the from platform
     */
    public void setFromPlatform(String fromPlatform) {
        this.fromPlatform = fromPlatform;
    }

    /**
     * Gets role.
     *
     * @return the role
     */
    public String getRole() {
        return role;
    }

    /**
     * Sets role.
     *
     * @param role the role
     */
    public void setRole(String role) {
        this.role = role;
    }

    /**
     * Gets timestamp.
     *
     * @return the timestamp
     */
    public Long getTimestamp() {
        return timestamp;
    }

    /**
     * Sets timestamp.
     *
     * @param timestamp the timestamp
     */
    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
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
     * Gets action.
     *
     * @return the action
     */
    public String getAction() {
        return action;
    }

    /**
     * Sets action.
     *
     * @param action the action
     */
    public void setAction(String action) {
        this.action = action;
    }

    /**
     * Gets remarks.
     *
     * @return the remarks
     */
    public Map<String, Object> getRemarks() {
        return remarks;
    }

    /**
     * Sets remarks.
     *
     * @param remarks the remarks
     */
    public void setRemarks(Map<String, Object> remarks) {
        this.remarks = remarks;
    }

    @Override
    public String toString() {
        // return "{fromPlatform='" + fromPlatform +
        // "',role='" + role +
        // "',type='" + type +
        // "',action='" + action +
        // "'}";
        StringBuffer sb = new StringBuffer();
        sb.append("{fromPlatform='").append(fromPlatform).append("',role='").append(role).append("',type='")
                .append(type).append("',action='").append(action).append("'}");
        return sb.toString();
    }
}
