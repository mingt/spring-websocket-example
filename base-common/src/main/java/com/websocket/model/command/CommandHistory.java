
package com.websocket.model.command;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.thinkgem.jeesite.common.mapper.JsonMapper;
import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 指令历史
 */
public class CommandHistory extends DataEntity<CommandHistory> {

    /** 班级id */
    private String classId;
    /** 课堂id */
    private String classroomId;
    /** 频道 */
    private String channel;
    /** 要通知的用户名（多个以逗号分隔） */
    private String users;
    /** 从哪个平台发出的: pc | ios | android | web */
    private String fromPlatform;
    /** 角色. 如 @ link ElearningConstant.UserRole */
    private String role;
    /** 时间戳 */
    private Long timestamp;
    /** 动作类型 */
    private String type;
    /** 动作，不同类型带不同的动作 */
    private String action;
    /** 作业id */
    private String homeworkId;
    /** 题目id */
    private String questionId;
    /** 随机选人个数 */
    private Integer num;
    /** 教材id */
    private String textbookId;
    /** 资源类型 */
    private String attrType;
    /** 资源id */
    private String resourceId;
    /** 计时器子类型 */
    private String subType;
    /** 计时器时间 */
    private String time;
    /** 查询时的数据条数限制 */
    private int queryNumLimit;

    /**
     * Instantiates a new Command history.
     */
    public CommandHistory() {}

    /**
     * Instantiates a new Command history.
     *
     * @param id the id
     */
    public CommandHistory(String id) {
        super(id);
    }

    /**
     * Instantiates a new Command history.
     *
     * @param id the id
     * @param classId the class id
     * @param classroomId the classroom id
     * @param channel the channel
     * @param users the users
     * @param fromPlatform the from platform
     * @param role the role
     * @param timestamp the timestamp
     * @param type the type
     * @param action the action
     * @param remarks the remarks
     */
    public CommandHistory(String id, String classId, String classroomId, String channel, String users,
            String fromPlatform, String role, Long timestamp, String type, String action, String remarks) {
        super(id);
        this.classId = classId;
        this.classroomId = classroomId;
        this.channel = channel;
        this.users = users;
        this.fromPlatform = fromPlatform;
        this.role = role;
        this.timestamp = timestamp;
        this.type = type;
        this.action = action;
        super.remarks = remarks;
    }

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
     * Gets classroom id.
     *
     * @return the classroom id
     */
    public String getClassroomId() {
        return classroomId;
    }

    /**
     * Sets classroom id.
     *
     * @param classroomId the classroom id
     */
    public void setClassroomId(String classroomId) {
        this.classroomId = classroomId;
    }

    /**
     * Gets channel.
     *
     * @return the channel
     */
    public String getChannel() {
        return channel;
    }

    /**
     * Sets channel.
     *
     * @param channel the channel
     */
    public void setChannel(String channel) {
        this.channel = channel;
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
     * Gets homework id.
     *
     * @return the homework id
     */
    public String getHomeworkId() {
        return homeworkId;
    }

    /**
     * Sets homework id.
     *
     * @param homeworkId the homework id
     */
    public void setHomeworkId(String homeworkId) {
        this.homeworkId = homeworkId;
    }

    /**
     * Gets question id.
     *
     * @return the question id
     */
    public String getQuestionId() {
        return questionId;
    }

    /**
     * Sets question id.
     *
     * @param questionId the question id
     */
    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    /**
     * Gets num.
     *
     * @return the num
     */
    public Integer getNum() {
        return num;
    }

    /**
     * Sets num.
     *
     * @param num the num
     */
    public void setNum(Integer num) {
        this.num = num;
    }

    /**
     * Gets textbook id.
     *
     * @return the textbook id
     */
    public String getTextbookId() {
        return textbookId;
    }

    /**
     * Sets textbook id.
     *
     * @param textbookId the textbook id
     */
    public void setTextbookId(String textbookId) {
        this.textbookId = textbookId;
    }

    /**
     * Gets attr type.
     *
     * @return the attr type
     */
    public String getAttrType() {
        return attrType;
    }

    /**
     * Sets attr type.
     *
     * @param attrType the attr type
     */
    public void setAttrType(String attrType) {
        this.attrType = attrType;
    }

    /**
     * Gets resource id.
     *
     * @return the resource id
     */
    public String getResourceId() {
        return resourceId;
    }

    /**
     * Sets resource id.
     *
     * @param resourceId the resource id
     */
    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }

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

    /**
     * Gets query num limit.
     *
     * @return the query num limit
     */
    @JsonIgnore
    public int getQueryNumLimit() {
        return queryNumLimit;
    }

    /**
     * Sets query num limit.
     *
     * @param queryNumLimit the query num limit
     */
    public void setQueryNumLimit(int queryNumLimit) {
        this.queryNumLimit = queryNumLimit;
    }

    /**
     * Gets remarks json.
     *
     * @return the remarks json
     */
    public Object getRemarksJson() {
        Object json = null;
        try {
            // json = JSON.parse(super.remarks);
            json = JsonMapper.fromJsonString(super.remarks, Object.class);
        } catch (Exception e) {
            System.out.println("parsing command history remarks error: " + e.getMessage());
            e.printStackTrace();
        }
        return json;
        // if(super.remarks!=null){
        // return JSON.parse(super.remarks);
        // }
        // return new HashMap<>();
    }

}
