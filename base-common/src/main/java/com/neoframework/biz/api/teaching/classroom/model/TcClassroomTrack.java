
package com.neoframework.biz.api.teaching.classroom.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 课堂轨迹.
 *
 * @author igylove
 * @create 2018-11-29 10:13
 */
public class TcClassroomTrack extends DataEntity<TcClassroomTrack> {

    /** 班级id **/
    private String classId;
    /** 课堂id **/
    private String classroomId;
    /** 频道 **/
    private String channel;
    /** 发送指令的平台 **/
    private String fromPlatform;
    /** 时间戳 **/
    private Long timestamp;
    /** 课堂开始的时间 **/
    private Long passTime;
    /** 指令类型 **/
    private String type;
    /** 指令动作 **/
    private String action;
    /** 扩展 **/
    private String remarks;
    /** 教材id **/
    private String textBookId;
    /** 课本id **/
    private String textId;
    /** 资源类型 **/
    private String attrType;
    /** 资源id **/
    private String resourceId;
    /** 作业id **/
    private String homeworkId;
    /** 题目id **/
    private String questionId;
    /** 参数传递 **/
    private Integer count;

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public String getClassroomId() {
        return classroomId;
    }

    public void setClassroomId(String classroomId) {
        this.classroomId = classroomId;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getFromPlatform() {
        return fromPlatform;
    }

    public void setFromPlatform(String fromPlatform) {
        this.fromPlatform = fromPlatform;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public Long getPassTime() {
        return passTime;
    }

    public void setPassTime(Long passTime) {
        this.passTime = passTime;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getTextBookId() {
        return textBookId;
    }

    public void setTextBookId(String textBookId) {
        this.textBookId = textBookId;
    }

    public String getTextId() {
        return textId;
    }

    public void setTextId(String textId) {
        this.textId = textId;
    }

    public String getAttrType() {
        return attrType;
    }

    public void setAttrType(String attrType) {
        this.attrType = attrType;
    }

    public String getResourceId() {
        return resourceId;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }

    public String getHomeworkId() {
        return homeworkId;
    }

    public void setHomeworkId(String homeworkId) {
        this.homeworkId = homeworkId;
    }

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    @JsonIgnore
    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
