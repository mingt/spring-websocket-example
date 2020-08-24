
package com.thinkgem.jeesite.modules.sys.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.thinkgem.jeesite.common.persistence.DataEntity;
import java.util.Objects;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;

/**
 * 用户Entity
 *
 * @author ThinkGem
 * @version 2013-12-05
 */
public class User extends DataEntity<User> {

    private static final long serialVersionUID = 1L;
    private Office company; // 归属公司
    private Office office; // 归属部门
    private String loginName; // 登录名
    private Integer uid; // 整型用户ID
    private String password; // 密码
    private String no; // 学号/工号
    private String name; // 姓名
    private String email; // 邮箱
    private String mobile; // 手机
    private String phone; // 电话
    private String userType;// 用户类型
    private String loginFlag; // 是否允许登陆
    private String photo; // 头像

    /**
     * Instantiates a new User.
     */
    public User() {
        super();
    }

    /**
     * Instantiates a new User.
     *
     * @param id the id
     */
    public User(String id) {
        super(id);
    }

    /**
     * Instantiates a new User.
     *
     * @param id the id
     * @param name the name
     */
    public User(String id, String name) {
        super(id);

        this.name = name;
    }

    /**
     * Gets company.
     *
     * @return the company
     */
    public Office getCompany() {
        return company;
    }

    /**
     * Sets company.
     *
     * @param company the company
     */
    public void setCompany(Office company) {
        this.company = company;
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

    /**
     * Gets login name.
     *
     * @return the login name
     */
    public String getLoginName() {
        return loginName;
    }

    /**
     * Sets login name.
     *
     * @param loginName the login name
     */
    public void setLoginName(String loginName) {
        this.loginName = loginName;
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
     * Gets password.
     *
     * @return the password
     */
    @JsonIgnore
    @Length(min = 6, max = 100, message = "密码长度必须介于 6 和 100 之间")
    public String getPassword() {
        return password;
    }

    /**
     * Sets password.
     *
     * @param password the password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets no.
     *
     * @return the no
     */
    public String getNo() {
        return no;
    }

    /**
     * Sets no.
     *
     * @param no the no
     */
    public void setNo(String no) {
        this.no = no;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets email.
     *
     * @return the email
     */
    @Email(message = "邮箱格式不正确")
    @Length(min = 0, max = 200, message = "邮箱长度必须小于 200")
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
     * Gets phone.
     *
     * @return the phone
     */
    @Length(min = 0, max = 200, message = "电话长度必须介于 1 和 200 之间")
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
     * Gets mobile.
     *
     * @return the mobile
     */
    @Length(min = 0, max = 200, message = "手机长度必须介于 1 和 200 之间")
    public String getMobile() {
        return mobile;
    }

    /**
     * Sets mobile.
     *
     * @param mobile the mobile
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    /**
     * Gets user type.
     *
     * @return the user type
     */
    @JsonIgnore
    @Length(min = 0, max = 100, message = "用户类型长度必须介于 1 和 100 之间")
    public String getUserType() {
        return userType;
    }

    /**
     * Sets user type.
     *
     * @param userType the user type
     */
    public void setUserType(String userType) {
        this.userType = userType;
    }

    /**
     * Gets photo.
     *
     * @return the photo
     */
    public String getPhoto() {
        return photo;
    }

    /**
     * Sets photo.
     *
     * @param photo the photo
     */
    public void setPhoto(String photo) {
        this.photo = photo;
    }

    /**
     * Gets login flag.
     *
     * @return the login flag
     */
    public String getLoginFlag() {
        return loginFlag;
    }

    /**
     * Sets login flag.
     *
     * @param loginFlag the login flag
     */
    public void setLoginFlag(String loginFlag) {
        this.loginFlag = loginFlag;
    }

    @JsonIgnore
    public boolean isDeleted() {
        return DEL_FLAG_DELETE.equals(getDelFlag());
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", name=" + name + ", mobile=" + mobile + "]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        User user = (User) o;
        return Objects.equals(loginName, user.loginName) && Objects.equals(uid, user.uid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(loginName, uid);
    }
}
