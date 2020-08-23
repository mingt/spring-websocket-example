
package com.neoframework.biz.api.trace.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.neoframework.common.api.BaseModel;
import java.io.Serializable;
import java.util.Date;

/**
 * 下载上报 model .
 */
public class StatAppDownload extends BaseModel<StatAppDownload> implements Serializable {

    private static final long serialVersionUID = 1L;
    /** 自增长ID */
    private Long id;
    /** 发生时间,尽量确保客户端对时 */
    private Date actionDate;
    /** 运营商(1-移动,2-联通,3-电信,0-unkown) */
    private Integer operator;
    /** 网络类型 1=wifi/2=2G/3=3G/4=4G/0=Unknown */
    private Integer network;
    /** 平台(android=1,ios=2,web=0,默认可使用web) */
    private Integer platform;
    /** 平台版本(比如2.0.1=20001,只支持int类型,每小版本占两位) */
    private Integer version;
    /** 应用的版本,可选(参考上面version,目前通常特指web端下载android) */
    private Integer appVersion;
    /** 数据插入时间 */
    private Date creationDate;

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
     * Gets app version.
     *
     * @return the app version
     */
    public Integer getAppVersion() {
        return appVersion;
    }

    /**
     * Sets app version.
     *
     * @param appVersion the app version
     */
    public void setAppVersion(Integer appVersion) {
        this.appVersion = appVersion;
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

    @Override
    public String toString() {
        return "StatAppDownload{" + "id=" + id + ", actionDate=" + actionDate + ", operator="
                + operator + ", network=" + network + ", platform=" + platform + ", version="
                + version + ", appVersion=" + appVersion + ", creationDate=" + creationDate + '}';
    }
}
