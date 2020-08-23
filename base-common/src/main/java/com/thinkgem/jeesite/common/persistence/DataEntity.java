/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights
 * reserved.
 */

package com.thinkgem.jeesite.common.persistence;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.thinkgem.jeesite.modules.sys.entity.User;
import java.util.Date;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.constraints.Length;

/**
 * 数据 Entity 类.
 *
 * @param <T> the type parameter
 * @author ThinkGem
 * @version 2014 -05-16
 */
public abstract class DataEntity<T extends BaseEntity> extends BaseEntity<T> {

    private static final long serialVersionUID = 1L;

    /**
     * 备注.
     */
    protected String remarks;
    /**
     * 创建者.
     */
    protected User createBy;
    /**
     * 创建日期.
     */
    protected Date createDate;
    /**
     * 更新者.
     */
    protected User updateBy;
    /**
     * 更新日期.
     */
    protected Date updateDate;
    /**
     * 见 {@link BaseEntity#DEL_FLAG_NORMAL} 附近：
     * 删除标记（0：正常；1：删除；2：审核, 3..., etc.）
     */
    protected String delFlag;

    /**
     * Instantiates a new Data entity.
     */
    public DataEntity() {
        super();
        this.delFlag = DEL_FLAG_NORMAL;
    }

    /**
     * Instantiates a new Data entity.
     *
     * @param id the id
     */
    public DataEntity(String id) {
        super(id);
    }

    /**
     * 插入之前执行方法，需要手动调用
     */
    @Override
    public void preInsert(User user) {
        // // 不限制ID为UUID，调用setIsNewRecord()使用自定义ID
        // if (!this.isNewRecord){
        // setId(IdGen.uuid());
        // }

        // ahming marks: clear UserUtils dependencies
        // User user = UserUtils.getUser();
        if (null != user && StringUtils.isNotBlank(user.getId())) {
            this.updateBy = user;
            this.createBy = user;
        }
        this.updateDate = new Date();
        this.createDate = this.updateDate;
    }

    /**
     * 更新之前执行方法，需要手动调用
     */
    @Override
    public void preUpdate(User user) {
        // ahming marks: clear UserUtils dependencies
        // User user = UserUtils.getUser();
        if (user != null && StringUtils.isNotBlank(user.getId())) {
            this.updateBy = user;
        }
        this.updateDate = new Date();
    }

    /**
     * Gets remarks.
     *
     * @return the remarks
     */
    @Length(min = 0, max = 1000)
    public String getRemarks() {
        return remarks;
    }

    /**
     * Sets remarks.
     *
     * @param remarks the remarks
     */
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    /**
     * Gets create by.
     *
     * @return the create by
     */
    // @JsonIgnore
    public User getCreateBy() {
        return createBy;
    }

    /**
     * Sets create by.
     *
     * @param createBy the create by
     */
    public void setCreateBy(User createBy) {
        this.createBy = createBy;
    }

    /**
     * Gets create date.
     *
     * @return the create date
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * Sets create date.
     *
     * @param createDate the create date
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * Gets update by.
     *
     * @return the update by
     */
    // @JsonIgnore
    public User getUpdateBy() {
        return updateBy;
    }

    /**
     * Sets update by.
     *
     * @param updateBy the update by
     */
    public void setUpdateBy(User updateBy) {
        this.updateBy = updateBy;
    }

    /**
     * Gets update date.
     *
     * @return the update date
     */
    // @JsonIgnore
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public Date getUpdateDate() {
        return updateDate;
    }

    /**
     * Sets update date.
     *
     * @param updateDate the update date
     */
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    /**
     * Gets del flag.
     *
     * @return the del flag
     */
    // @JsonIgnore // 这个若加上影响参数传递了，从前端到后端，会影响一些后台编辑 @张顺 印象之前你加上的，有什么影响请看下
    @Length(min = 1, max = 1)
    public String getDelFlag() {
        return delFlag;
    }

    /**
     * Sets del flag.
     *
     * @param delFlag the del flag
     */
    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

}
