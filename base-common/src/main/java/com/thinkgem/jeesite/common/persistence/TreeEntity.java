/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights
 * reserved.
 */

package com.thinkgem.jeesite.common.persistence;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.thinkgem.jeesite.common.utils.Reflections;
import com.thinkgem.jeesite.common.utils.StringUtils;
import java.lang.reflect.InvocationTargetException;
import javax.validation.constraints.NotNull;
import org.apache.commons.lang3.StringEscapeUtils;
import org.hibernate.validator.constraints.Length;

/**
 * 树形数据 Entity 类.
 *
 * @param <T> the type parameter
 * @author ThinkGem
 * @version 2014 -05-16
 */
public abstract class TreeEntity<T extends DataEntity> extends DataEntity<T> {

    private static final long serialVersionUID = 1L;

    /**
     * 父级编号.
     */
    protected T parent;
    /**
     * 所有父级编号.
     */
    protected String parentIds;
    /**
     * 机构名称.
     */
    protected String name;
    /**
     * 排序.
     */
    protected Integer sort;

    /**
     * The Parent id.
     */
    protected String parentId;

    /** 标识层级，这个值非持久化，输出到前端时按需使用 值不固定 -> 已持久化基于根的层数 */
    protected Integer level;
    /** 非持久化 是否有子节点 */
    protected Boolean hasChild;

    /** TreeEntity 公共 type字段 */
    protected String type;

    /**
     * 是否已设置 parent 父节点信息.
     *
     * <p>
     * 1. 默认情况下， TreeEntity 保存时都会先进行 get(parentId) 即数据库查询父节点信息，为了照顾批量添加时避免
     * 过多查询数据库，且也能从外部获取到父节点的信息，那就设置这个字段为真，就不会在保存时再先数据库查询父节点信息。
     *
     * 2. 在分页查询树形表数据并使用tree-table显示时，如属性值AttrValue，用这个区分当前页是否含父节点，以便使能正确显示
     * </p>
     */
    protected Boolean ifParentSet;

    /**
     * Instantiates a new Tree entity.
     */
    public TreeEntity() {
        super();
        this.sort = 30;
    }

    /**
     * Instantiates a new Tree entity.
     *
     * @param id the id
     */
    public TreeEntity(String id) {
        super(id);
    }

    /**
     * 父对象，只能通过子类实现，父类实现mybatis无法读取
     *
     * @return parent
     */
    @JsonBackReference
    @NotNull
    public abstract T getParent();

    /**
     * 父对象，只能通过子类实现，父类实现mybatis无法读取
     *
     * @param parent the parent
     */
    public abstract void setParent(T parent);

    /**
     * Gets parent ids.
     *
     * @return the parent ids
     */
    @Length(min = 1, max = 2000)
    public String getParentIds() {
        return parentIds;
    }

    /**
     * Sets parent ids.
     *
     * @param parentIds the parent ids
     */
    public void setParentIds(String parentIds) {
        this.parentIds = parentIds;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    @Length(min = 1, max = 100)
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = StringUtils.replaceHtml(StringEscapeUtils.unescapeHtml4(name));
    }

    /**
     * Gets sort.
     *
     * @return the sort
     */
    public Integer getSort() {
        return sort;
    }

    /**
     * Sets sort.
     *
     * @param sort the sort
     */
    public void setSort(Integer sort) {
        this.sort = sort;
    }

    /**
     * Gets parent id.
     *
     * @return the parent id
     */
    public String getParentId() {
        String id = null;
        if (parent != null) {
            // id = (String)Reflections.getFieldValue(parent, "id");
            id = parent.getId();
        }
        if (StringUtils.isBlank(id) && StringUtils.isNotBlank(parentId)) {
            id = parentId;
        }
        return StringUtils.isNotBlank(id) ? id : "0";
    }

    /**
     * Sets parent id.
     *
     * @param parentId the parent id
     */
    public void setParentId(String parentId) {
        this.parentId = parentId;

        if (StringUtils.isNotBlank(parentId)) {
            Class<T> entityClass = Reflections.getClassGenricType(getClass());
            try {
                parent = entityClass.getConstructor(String.class).newInstance(parentId);
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
        }

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
     * Gets level.
     *
     * @return the level
     */
    public Integer getLevel() {
        return level;
    }

    /**
     * Sets level.
     *
     * @param level the level
     */
    public void setLevel(Integer level) {
        this.level = level;
    }

    /**
     * Gets has child.
     *
     * @return the has child
     */
    public Boolean getHasChild() {
        return hasChild;
    }

    /**
     * Sets has child.
     *
     * @param hasChild the has child
     */
    public void setHasChild(Boolean hasChild) {
        this.hasChild = hasChild;
    }

    /**
     * Gets if parent set.
     *
     * @return the if parent set
     */
    public Boolean getIfParentSet() {
        return ifParentSet;
    }

    /**
     * Sets if parent set.
     *
     * @param ifParentSet the if parent set
     */
    public void setIfParentSet(Boolean ifParentSet) {
        this.ifParentSet = ifParentSet;
    }
}
