
package com.websocket.model.command;

/**
 * 计时器命令.
 */
public class TimerCommand extends BaseCommand {

    /**
     * The constant TYPE.
     */
    public static final String TYPE = "timer";

    private String subType;

    private String time;

    /**
     * Gets sub type.
     *
     * @return the sub type
     */
    public String getSubType() {
        return subType;
    }

    /**
     * Sets sub type.
     *
     * @param subType the sub type
     */
    public void setSubType(String subType) {
        this.subType = subType;
    }

    /**
     * Gets time.
     *
     * @return the time
     */
    public String getTime() {
        return time;
    }

    /**
     * Sets time.
     *
     * @param time the time
     */
    public void setTime(String time) {
        this.time = time;
    }
}
