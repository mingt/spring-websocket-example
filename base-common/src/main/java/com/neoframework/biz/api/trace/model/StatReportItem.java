
package com.neoframework.biz.api.trace.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.neoframework.common.api.BaseModel;
import java.io.Serializable;
import java.util.Date;

/**
 * 用户访问上报实体，这个覆盖了之前的资源上报和访问上报.
 *
 * <p>之前两种上报设为不同的实体类算较失败的设计，事实可以归到一个实体类就可以，两者不同只是一两个字段.
 * {@link StatAccess} {@link StatResourceAction}
 *
 * 现在合并出来在积分中统一使用。以后可考虑全局把两者统一为这个，有必要时。</p>
 */
public class StatReportItem extends BaseModel<StatReportItem> implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 自增长ID
     */
    private Long id;
    /**
     * 发生时间,尽量确保客户端对时
     */
    private Date actionDate;
    /**
     * 运营商(1-移动,2-联通,3-电信,0-unkown)
     */
    private Integer operator;
    /**
     * 网络类型 1=wifi/2=2G/3=3G/4=4G/0=Unknown
     */
    private Integer network;
    /**
     * 平台(android=1,ios=2,web=0,默认可使用web)
     */
    private Integer platform;
    /**
     * 平台版本(比如2.0.1=20001,只支持int类型,每小版本占两位)
     */
    private Integer version;
    /**
     * 针对访问类型,login/logout/boot/page
     * 针对资源操作类型，类型 f(add favorite) s(share) u(use)
     */
    private String type;
    /**
     * 针对访问，自定义(页面)简单名称
     * 针对资源，qt(question)/hw(homeword)/ex(exam)/ppt/doc/xls/txt/read/video等等
     */
    private String object;

    /**
     * 针对资源：对象ID,如题目ID,作业ID等
     */
    private String objectId;

    /**
     * 针对访问：时长,单位毫秒
     */
    private Long duration;

    /**
     * 用户uid,即博学号
     */
    private Integer uid;

    /**
     * 数据插入时间
     */
    private Date creationDate;

    /**
     * 用于前端上报传参的字段个数
     */
    public static final int PARAM_FIELDS_COUNT = 9;

    // /**
    // * 积分行为相关所用.
    // */
    // private String beTypes;
    //
    // private User user;

    /**
     * Gets id.
     *
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets action date.
     *
     * @return the action date
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public Date getActionDate() {
        return actionDate;
    }

    /**
     * Sets action date.
     *
     * @param actionDate the action date
     */
    public void setActionDate(Date actionDate) {
        this.actionDate = actionDate;
    }

    /**
     * Gets operator.
     *
     * @return the operator
     */
    public Integer getOperator() {
        return operator;
    }

    /**
     * Sets operator.
     *
     * @param operator the operator
     */
    public void setOperator(Integer operator) {
        this.operator = operator;
    }

    /**
     * Gets network.
     *
     * @return the network
     */
    public Integer getNetwork() {
        return network;
    }

    /**
     * Sets network.
     *
     * @param network the network
     */
    public void setNetwork(Integer network) {
        this.network = network;
    }

    /**
     * Gets platform.
     *
     * @return the platform
     */
    public Integer getPlatform() {
        return platform;
    }

    /**
     * Sets platform.
     *
     * @param platform the platform
     */
    public void setPlatform(Integer platform) {
        this.platform = platform;
    }

    /**
     * Gets version.
     *
     * @return the version
     */
    public Integer getVersion() {
        return version;
    }

    /**
     * Sets version.
     *
     * @param version the version
     */
    public void setVersion(Integer version) {
        this.version = version;
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
     * Gets object.
     *
     * @return the object
     */
    public String getObject() {
        return object;
    }

    /**
     * Sets object.
     *
     * @param object the object
     */
    public void setObject(String object) {
        this.object = object;
    }

    /**
     * Gets object id.
     *
     * @return the object id
     */
    public String getObjectId() {
        return objectId;
    }

    /**
     * Sets object id.
     *
     * @param objectId the object id
     */
    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    /**
     * Gets duration.
     *
     * @return the duration
     */
    public Long getDuration() {
        return duration;
    }

    /**
     * Sets duration.
     *
     * @param duration the duration
     */
    public void setDuration(Long duration) {
        this.duration = duration;
    }

    /**
     * Gets uid.
     *
     * @return the uid
     */
    public Integer getUid() {
        return uid;
    }

    /**
     * Sets uid.
     *
     * @param uid the uid
     */
    public void setUid(Integer uid) {
        this.uid = uid;
    }

    /**
     * Gets creation date.
     *
     * @return the creation date
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public Date getCreationDate() {
        return creationDate;
    }

    /**
     * Sets creation date.
     *
     * @param creationDate the creation date
     */
    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    // @JsonIgnore
    // public String getBeTypes() {
    // return beTypes;
    // }
    //
    // public void setBeTypes(String beTypes) {
    // this.beTypes = beTypes;
    // }
    //
    // @JsonIgnore
    // public User getUser() {
    // return user;
    // }
    //
    // public void setUser(User user) {
    // this.user = user;
    // }
}
