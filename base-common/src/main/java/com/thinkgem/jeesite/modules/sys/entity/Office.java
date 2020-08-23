
package com.thinkgem.jeesite.modules.sys.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.thinkgem.jeesite.common.persistence.TreeEntity;
import java.util.List;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

/**
 * 机构 Entity .
 *
 * @author ThinkGem
 * @version 2013 -05-15
 */
public class Office extends TreeEntity<Office> {

    private static final long serialVersionUID = 1L;

    /**
     * 顶级标签 "1".
     */
    public static final String DEFAULT_PARENT_ID = "1";

    private Integer oid; // 整型机构ID

    private Area area; // 归属区域
    private String code; // 机构编码
    // private String name; // 机构名称
    // private Integer sort; // 排序
    private String type; // 机构类型（1：公司；2：部门；3：小组）
    private String grade; // 机构等级（1：一级；2：二级；3：三级；4：四级）
    private String address; // 联系地址
    private String zipCode; // 邮政编码
    private String master; // 负责人
    private String phone; // 电话
    private String fax; // 传真
    private String email; // 邮箱

    /**
     * Instantiates a new Office.
     */
    public Office() {
        super();
        // // this.sort = 30;
        // this.type = "2";
    }

    /**
     * Instantiates a new Office.
     *
     * @param id the id
     */
    public Office(String id) {
        super(id);
    }

    /**
     * 使用了递归，支持任意级. 增加 level 参数，可能设置其 level .
     *
     * @param list 输出排序了结果
     * @param sourceList 原始，可能需要已排序
     * @param parentId 从某 pId 下一级输出
     */
    public static void sortList(List<Office> list, List<Office> sourceList, String parentId) {
        for (int i = 0; i < sourceList.size(); i++) {
            Office e = sourceList.get(i);
            if (e.getParent() != null && e.getParent().getId() != null && e.getParent().getId().equals(parentId)) {
                e.setHasChild(false);
                list.add(e);
                // 判断是否还有子节点, 有则继续获取子节点
                for (int j = 0; j < sourceList.size(); j++) {
                    Office child = sourceList.get(j);
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
        Office other = (Office) obj;
        if (id == null) {
            if (other.id != null) {
                return false;
            }
        } else if (!id.equals(other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    /**
     * Gets oid.
     *
     * @return the oid
     */
    public Integer getOid() {
        return oid;
    }

    /**
     * Sets oid.
     *
     * @param oid the oid
     */
    public void setOid(Integer oid) {
        this.oid = oid;
    }

    // @JsonBackReference
    // @NotNull
    public Office getParent() {
        return parent;
    }

    public void setParent(Office parent) {
        this.parent = parent;
    }

    /**
     * Gets area.
     *
     * @return the area
     */
    @NotNull
    public Area getArea() {
        return area;
    }

    /**
     * Sets area.
     *
     * @param area the area
     */
    public void setArea(Area area) {
        this.area = area;
    }

    @Length(min = 1, max = 1)
    // @ExcelField(title="机构类型", align=2, sort=90) // , dictType="sys_user_type"
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    /**
     * Gets grade.
     *
     * @return the grade
     */
    @Length(min = 1, max = 1)
    @JsonIgnore
    public String getGrade() {
        return grade;
    }

    /**
     * Sets grade.
     *
     * @param grade the grade
     */
    public void setGrade(String grade) {
        this.grade = grade;
    }

    /**
     * Gets address.
     *
     * @return the address
     */
    @Length(min = 0, max = 255)
    // @ExcelField(title="联系地址", align=2, sort=100)
    public String getAddress() {
        return address;
    }

    /**
     * Sets address.
     *
     * @param address the address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Gets zip code.
     *
     * @return the zip code
     */
    @Length(min = 0, max = 100)
    // @ExcelField(title="邮政编码", align=2, sort=110)
    public String getZipCode() {
        return zipCode;
    }

    /**
     * Sets zip code.
     *
     * @param zipCode the zip code
     */
    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    /**
     * Gets master.
     *
     * @return the master
     */
    @Length(min = 0, max = 100)
    // @ExcelField(title="负责人", align=2, sort=120)
    public String getMaster() {
        return master;
    }

    /**
     * Sets master.
     *
     * @param master the master
     */
    public void setMaster(String master) {
        this.master = master;
    }

    /**
     * Gets phone.
     *
     * @return the phone
     */
    @Length(min = 0, max = 200)
    // @ExcelField(title="电话", align=2, sort=130)
    public String getPhone() {
        return phone;
    }

    /**
     * Sets phone.
     *
     * @param phone the phone
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * Gets fax.
     *
     * @return the fax
     */
    @Length(min = 0, max = 200)
    // @ExcelField(title="传真", align=2, sort=140)
    public String getFax() {
        return fax;
    }

    /**
     * Sets fax.
     *
     * @param fax the fax
     */
    public void setFax(String fax) {
        this.fax = fax;
    }

    /**
     * Gets email.
     *
     * @return the email
     */
    @Length(min = 0, max = 200)
    // @ExcelField(title="邮箱", align=2, sort=150)
    public String getEmail() {
        return email;
    }

    /**
     * Sets email.
     *
     * @param email the email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets code.
     *
     * @return the code
     */
    @Length(min = 0, max = 100)
    // @ExcelField(title="区域编码", align=2, sort=170) // , dictType="sys_user_type"
    public String getCode() {
        return code;
    }

    /**
     * Sets code.
     *
     * @param code the code
     */
    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return name;
    }

}
