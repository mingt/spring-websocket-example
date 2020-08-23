
package com.neoframework.biz.api.teaching.classroom.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.modules.sys.entity.Office;
import com.thinkgem.jeesite.modules.sys.entity.User;
import java.io.Serializable;
import java.util.Date;

/**
 * Model class of 授课课堂表.
 *
 * @author ahming
 */
public class TcClassroom extends DataEntity<TcClassroom> implements Serializable {

    /** serialVersionUID. */
    private static final long serialVersionUID = 1L;

    // /** 编号. */
    // private String id;

    /** 班级ID. */
    private String classId;

    /** 班级详情信息. */
    private Office classInfo;

    /** 教材标签ID */
    private String textbookId;

    /** 课文标签ID */
    private String textId;

    /** 科目. */
    private String subject;

    /** 开始时间(与前面创建时间分开，可使用来记录课堂在课程表上的时间) */
    private Date startDate;

    /** 结束时间(主动结束，或定时任务默认时间结束---可考虑按课程表) */
    private Date endDate;

    /** 状态(暂定,0刚创建,1开始后进行中,2结束,3取消 */
    private Integer status;

    /** 类型，以后扩展用 */
    private String type;

    /**
     * Instantiates a new Tc classroom.
     */
    public TcClassroom() {}

    /**
     * Instantiates a new Tc classroom.
     *
     * @param id the id
     */
    public TcClassroom(String id) {
        super(id);
    }

    /**
     * Instantiates a new Tc classroom.
     *
     * @param id the id
     * @param classId the class id
     * @param textbookId the textbook id
     * @param subject the subject
     */
    public TcClassroom(String id, String classId, String textbookId, String subject) {
        super(id);
        this.classId = classId;
        this.textbookId = textbookId;
        this.subject = subject;
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
     * Gets class info.
     *
     * @return the class info
     */
    public Office getClassInfo() {
        return classInfo;
    }

    /**
     * Sets class info.
     *
     * @param classInfo the class info
     */
    public void setClassInfo(Office classInfo) {
        this.classInfo = classInfo;
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
     * Gets text id.
     *
     * @return the text id
     */
    public String getTextId() {
        return textId;
    }

    /**
     * Sets text id.
     *
     * @param textId the text id
     */
    public void setTextId(String textId) {
        this.textId = textId;
    }

    /**
     * Gets subject.
     *
     * @return the subject
     */
    public String getSubject() {
        return subject;
    }

    /**
     * Sets subject.
     *
     * @param subject the subject
     */
    public void setSubject(String subject) {
        this.subject = subject;
    }

    /**
     * Gets start date.
     *
     * @return the start date
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public Date getStartDate() {
        return startDate;
    }

    /**
     * Sets start date.
     *
     * @param startDate the start date
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     * Gets end date.
     *
     * @return the end date
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public Date getEndDate() {
        return endDate;
    }

    /**
     * Sets end date.
     *
     * @param endDate the end date
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    /**
     * Gets status.
     *
     * @return the status
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * Sets status.
     *
     * @param status the status
     */
    public void setStatus(Integer status) {
        this.status = status;
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
     * 返回在父类中被JsonIgnore的创建人字段，并重命名为creator
     *
     * @return creator
     */
    public User getCreator() {
        return super.getCreateBy();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        TcClassroom other = (TcClassroom) obj;
        if (id == null) {
            if (other.id != null) {
                return false;
            }
        } else if (!id.equals(other.id)) {
            return false;
        }
        return true;
    }
}
