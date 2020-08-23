
package com.neoframework.biz.api.teaching.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.thinkgem.jeesite.common.persistence.TreeEntity;
import com.thinkgem.jeesite.modules.sys.entity.Office;
import java.io.Serializable;
import java.util.List;

/**
 * Model class of 教材标签表.
 *
 */
public class TcTag extends TreeEntity<TcTag> implements Serializable {

    /**
     * The constant DEFAULT_PARENT_ID.
     */
    public static final String DEFAULT_PARENT_ID = "1"; // 顶级标签 "1"

    /** serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /** 标签id. */
    private String id;

    /** 归属部门. */
    private Office office;

    /** 详细内容. */
    private String content;

    /** 关联的url. */
    private String url;

    /** 图片名称. */
    private String icon;

    /** 类型： 年级，教材，科目，试卷等. */
    private String type;

    /** 参数传递 */
    private List<String> pids;
    private List<String> sids;
    private List<String> gids;

    /**
     * Constructor.
     */
    public TcTag() {
        this.sort = 30;
        this.delFlag = DEL_FLAG_NORMAL;
    }

    /**
     * Instantiates a new Tc tag.
     *
     * @param id the id
     */
    public TcTag(String id) {
        this();
        this.id = id;
    }

    /**
     * Gets office.
     *
     * @return the office
     */
    public Office getOffice() {
        return office;
    }

    /**
     * Sets office.
     *
     * @param office the office
     */
    public void setOffice(Office office) {
        this.office = office;
    }

    @Override
    public TcTag getParent() {
        return parent;
    }

    @Override
    public void setParent(TcTag parent) {
        this.parent = parent;
    }

    /**
     * Set the 标签id.
     *
     * @param id
     *        标签id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Get the 标签id.
     *
     * @return 标签id
     */
    public String getId() {
        return this.id;
    }

    /**
     * Set the 所有父级编号.
     *
     * @param parentIds
     *        所有父级编号
     */
    public void setParentIds(String parentIds) {
        this.parentIds = parentIds;
    }

    /**
     * Get the 所有父级编号.
     *
     * @return 所有父级编号
     */
    public String getParentIds() {
        return this.parentIds;
    }

    /**
     * Set the 详细内容.
     *
     * @param content 详细内容
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * Get the 详细内容.
     *
     * @return 详细内容 content
     */
    public String getContent() {
        return this.content;
    }

    /**
     * Set the 关联的url.
     *
     * @param url 关联的url
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * Get the 关联的url.
     *
     * @return 关联的url url
     */
    @JsonIgnore
    public String getUrl() {
        return this.url;
    }

    /**
     * Set the 图片名称.
     *
     * @param icon 图片名称
     */
    public void setIcon(String icon) {
        this.icon = icon;
    }

    /**
     * Get the 图片名称.
     *
     * @return 图片名称 icon
     */
    @JsonIgnore
    public String getIcon() {
        return this.icon;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    /**
     * Gets pids.
     *
     * @return the pids
     */
    public List<String> getPids() {
        return pids;
    }

    /**
     * Sets pids.
     *
     * @param pids the pids
     */
    public void setPids(List<String> pids) {
        this.pids = pids;
    }

    /**
     * Gets sids.
     *
     * @return the sids
     */
    public List<String> getSids() {
        return sids;
    }

    /**
     * Sets sids.
     *
     * @param sids the sids
     */
    public void setSids(List<String> sids) {
        this.sids = sids;
    }

    /**
     * Gets gids.
     *
     * @return the gids
     */
    public List<String> getGids() {
        return gids;
    }

    /**
     * Sets gids.
     *
     * @param gids the gids
     */
    public void setGids(List<String> gids) {
        this.gids = gids;
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
        TcTag other = (TcTag) obj;
        if (id == null) {
            if (other.id != null) {
                return false;
            }
        } else if (!id.equals(other.id)) {
            return false;
        }
        return true;
    }

    /**
     * 使用了递归，支持任意级.
     *
     * @param list 输出排序了结果
     * @param sourceList 原始，可能需要已排序
     * @param parentId 从某 pId 下一级输出
     * @param level 基于该level手动计算输出。如果传入小于 0 的值，则使用 TcTag 原来的level值
     */
    public static void sortList(List<TcTag> list, List<TcTag> sourceList, String parentId, int level) {
        for (int i = 0; i < sourceList.size(); i++) {
            TcTag e = sourceList.get(i);
            if (e.getParent() != null && e.getParent().getId() != null && e.getParent().getId().equals(parentId)) {
                if (level >= 0) {
                    e.setLevel(level);
                }
                list.add(e);
                // 判断是否还有子节点, 有则继续获取子节点
                for (int j = 0; j < sourceList.size(); j++) {
                    TcTag child = sourceList.get(j);
                    if (child.getParent() != null && child.getParent().getId() != null
                            && child.getParent().getId().equals(e.getId())) {
                        sortList(list, sourceList, e.getId(), level + 1);
                        break;
                    }
                }
            }
        }
    }

    /**
     * 使用了递归，支持任意级. 增加 level 参数，可能设置其 level .
     *
     * @param list 输出排序了结果
     * @param sourceList 原始，可能需要已排序
     * @param parentId 从某 pId 下一级输出
     */
    public static void sortList(List<TcTag> list, List<TcTag> sourceList, String parentId) {
        for (int i = 0; i < sourceList.size(); i++) {
            TcTag e = sourceList.get(i);
            if (e.getParent() != null && e.getParent().getId() != null && e.getParent().getId().equals(parentId)) {
                e.setHasChild(false);
                list.add(e);
                // 判断是否还有子节点, 有则继续获取子节点
                for (int j = 0; j < sourceList.size(); j++) {
                    TcTag child = sourceList.get(j);
                    if (child.getParent() != null && child.getParent().getId() != null
                            && child.getParent().getId().equals(e.getId())) {
                        e.setHasChild(true);
                        sortList(list, sourceList, e.getId());
                        break;
                    }
                }
            }
        }
    }

    /**
     * Gets ids.
     *
     * @return the ids
     */
    public String getIds() {
        return (this.getParentIds() != null ? this.getParentIds().replaceAll(",", " ") : "")
                + (this.getId() != null ? this.getId() : "");
    }

    /**
     * Is root boolean.
     *
     * @return the boolean
     */
    public boolean isRoot() {
        return isRoot(this.id);
    }

    /**
     * Is root boolean.
     *
     * @param id the id
     * @return the boolean
     */
    public static boolean isRoot(String id) {
        return id != null && id.equals(TcTag.DEFAULT_PARENT_ID);
    }

}
