/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights
 * reserved.
 */

package com.thinkgem.jeesite.common.persistence;

import com.thinkgem.jeesite.modules.sys.entity.User;
import java.io.Serializable;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

/**
 * Entity 支持基类.
 *
 * @param <T> the type parameter
 * @author ThinkGem
 * @version 2014 -05-16
 */
public abstract class BaseEntity<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * The constant DATA_SCOPE_KEY.
     */
    public static final String DATA_SCOPE_KEY = "dsf";

    /**
     * 实体编号（唯一标识）.
     *
     * <p>TODO: 有必要使用更通用的 ID 数据类型，支持长整型，或同时支持两者</p>
     */
    protected String id;

    // /**
    // * 当前用户
    // */
    // protected User currentUser;
    //
    // /**
    // * 当前实体分页对象
    // */
    // protected Page<T> page;
    //
    // /**
    // * 自定义SQL（SQL标识，SQL内容）
    // */
    // protected Map<String, String> sqlMap;

    // /**
    // * 是否是新记录（默认：false），调用setIsNewRecord()设置新记录，使用自定义ID。
    // * 设置为true后强制执行插入语句，ID不会自动生成，需从手动传入。
    // */
    // protected boolean isNewRecord = false;

    /**
     * Instantiates a new Base entity.
     */
    public BaseEntity() {

    }

    /**
     * Instantiates a new Base entity.
     *
     * @param id the id
     */
    public BaseEntity(String id) {
        this();
        this.id = id;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    // @SupCol(isUnique="true", isHide="true")
    public String getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(String id) {
        this.id = id;
    }

    // /**
    // * 见：{@link com.thinkgem.jeesite.modules.sys.utils.UserUtils#getUser(BaseEntity)}
    // *
    // * @return
    // */
    // @JsonIgnore
    // @XmlTransient
    // public User getCurrentUser() {
    // // if(currentUser == null){
    // // currentUser = UserUtils.getUser(); // ahming marks: remove UserUtils dependencies
    // // }
    // return currentUser;
    // }

    // public void setCurrentUser(User currentUser) {
    // this.currentUser = currentUser;
    // }
    //
    // @JsonIgnore
    // @XmlTransient
    // public Page<T> getPage() {
    // // //modify by zhangshun 不需要默认值
    // // if (page == null){
    // // page = new Page<T>();
    // // }
    // return page;
    // }
    //
    // public Page<T> setPage(Page<T> page) {
    // this.page = page;
    // return page;
    // }
    //
    // @JsonIgnore
    // @XmlTransient
    // public Map<String, String> getSqlMap() {
    // if (sqlMap == null){
    // sqlMap = Maps.newHashMap();
    // }
    // return sqlMap;
    // }
    //
    // public void setSqlMap(Map<String, String> sqlMap) {
    // this.sqlMap = sqlMap;
    // }

    /**
     * 插入之前执行方法，子类实现
     *
     * @param user the user
     */
    public abstract void preInsert(User user);

    /**
     * 更新之前执行方法，子类实现
     *
     * @param user the user
     */
    public abstract void preUpdate(User user);

    // /**
    // * 是否是新记录（默认：false），调用setIsNewRecord()设置新记录，使用自定义ID。
    // * 设置为true后强制执行插入语句，ID不会自动生成，需从手动传入。
    // * @return
    // */
    // @JsonIgnore
    // public boolean getIsNewRecord() {
    // return isNewRecord || StringUtils.isBlank(getId());
    // }
    //
    // /**
    // * 是否是新记录（默认：false），调用setIsNewRecord()设置新记录，使用自定义ID。
    // * 设置为true后强制执行插入语句，ID不会自动生成，需从手动传入。
    // */
    // public void setIsNewRecord(boolean isNewRecord) {
    // this.isNewRecord = isNewRecord;
    // }
    //
    // /**
    // * 全局变量对象
    // */
    // @JsonIgnore
    // public Global getGlobal() {
    // return Global.getInstance();
    // }
    //
    // /**
    // * 获取数据库名称
    // */
    // @JsonIgnore
    // public String getDbName(){
    // return Global.getConfig("jdbc.type");
    // }

    @Override
    public boolean equals(Object obj) {
        if (null == obj) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if (!getClass().equals(obj.getClass())) {
            return false;
        }
        BaseEntity<?> that = (BaseEntity<?>) obj;
        return null == this.getId() ? false : this.getId().equals(that.getId());
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }

    /**
     * 删除标记（0：正常；1：删除；2：审核；3：草稿编辑中[如点读资源的编辑，全部信息完整后再设为 NORMAL]）
     */
    public static final String DEL_FLAG_NORMAL = "0";
    /**
     * The constant DEL_FLAG_DELETE.
     */
    public static final String DEL_FLAG_DELETE = "1";
    /**
     * The constant DEL_FLAG_AUDIT.
     */
    public static final String DEL_FLAG_AUDIT = "2";
    /**
     * The constant DEL_FLAG_DRAFT.
     */
    public static final String DEL_FLAG_DRAFT = "3";

    /**
     * marks: 在通过 del_flag 查询时，因为 DataEntity 默认值为零，有时希望默认（以0的值）查询除了已删除（1）之外的其他
     * 全部，这时就使用一个新的值表示只查询为零的记录
     */
    public static final String DEL_FLAG_QUERY_NORMAL_ONLY = "10";
}
