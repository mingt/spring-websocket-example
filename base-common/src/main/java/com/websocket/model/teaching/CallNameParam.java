
package com.websocket.model.teaching;

import com.websocket.model.command.CallNameCommand;

/**
 * 点名命令参数.
 *
 */
public class CallNameParam {

    private String classId;
    private CallNameCommand command;

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
     * Gets command.
     *
     * @return the command
     */
    public CallNameCommand getCommand() {
        return command;
    }

    /**
     * Sets command.
     *
     * @param command the command
     */
    public void setCommand(CallNameCommand command) {
        this.command = command;
    }
}
