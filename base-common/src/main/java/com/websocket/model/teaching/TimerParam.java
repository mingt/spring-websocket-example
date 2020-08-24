
package com.websocket.model.teaching;

import com.websocket.model.command.TimerCommand;

/**
 * 计时器命令参数.
 */
public class TimerParam {

    private String classId;
    private TimerCommand command;

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
    public TimerCommand getCommand() {
        return command;
    }

    /**
     * Sets command.
     *
     * @param command the command
     */
    public void setCommand(TimerCommand command) {
        this.command = command;
    }
}
