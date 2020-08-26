
package com.neoframework.biz.api.common.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.thinkgem.jeesite.modules.sys.entity.User;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.commons.lang3.time.DateFormatUtils;

// import com.google.common.collect.Lists;
// import com.thinkgem.jeesite.common.utils.DateUtils;
// import com.thinkgem.jeesite.common.utils.StringUtils;
// import com.thinkgem.jeesite.modules.sys.entity.Role;
// import com.thinkgem.jeesite.modules.sys.entity.User;

/**
 * Created by think on 2017-09-19.
 */
public class UserVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;
    private Integer uid;
    private String companyName; // 归属公司
    private String officeName; // 归属部门
    private String name; // 姓名
    private String photo; // 头像
    private String roleName; // 根据角色查询用户条件

    private String remarks;
    private String createDate;
    private String expiredDate;
    private String loginName;
    private String email;
    private String mobile;
    private String phone;
    private String sex;
    private String qq;
    private String signature;
    private String referralCode; // 推荐码
    private String fromReferralCode; // 所关联的推荐码，仅作参数传递
    private String no;

    private String loginIp;
    private String loginDate;

    /** 默认教材 id */
    private String bookTagId;
    /** 默认教材 */
    private String bookTagName;

    private Integer studentUid;
    private String relation; // 家长称呼爸爸妈妈其他
    // private List<UserSimpleVo> parents = Lists.newArrayList(); // 学生的家长列表
    //
    // private Role role; // 根据角色查询用户条件
    // private List<Role> roleList = Lists.newArrayList(); // 拥有角色列表
    private List<String> privileges = new ArrayList<>();

    /**
     * Instantiates a new User vo.
     */
    public UserVo() {}

    /**
     * Instantiates a new User vo.
     *
     * @param user the user
     */
    public UserVo(User user) {
        this.id = user.getId();
        this.uid = user.getUid();
        if (user.getCompany() != null) {
            this.companyName = user.getCompany().getName();
        }
        if (user.getOffice() != null) {
            this.officeName = user.getOffice().getName();
        }
        this.name = user.getName();
        this.photo = user.getPhoto();
        // this.roleName = roleName;
        this.remarks = user.getRemarks();
        // this.createDate = DateUtils.formatDateTime(user.getCreateDate());
        // this.expiredDate = DateUtils.formatDateTime(user.getExpiredDate());
        this.createDate = DateFormatUtils.format(user.getCreateDate(), "yyyy-MM-dd HH:mm:ss");
        // this.expiredDate = DateFormatUtils.format((user.getExpiredDate(), "yyyy-MM-dd HH:mm:ss");
        this.loginName = user.getLoginName();
        this.email = user.getEmail();
        this.phone = user.getPhone();
        this.mobile = user.getMobile();

        this.no = user.getNo();

        // // TODO: 使用 ROLE_ roleList 代替
        // // final List<Privilege> privilegeList = user.privileges();
        // // for (Privilege privilege : privilegeList) {
        // // this.privileges.add(privilege.name());
        // // }
        // this.role = user.getRole();
        // this.roleList = user.getRoleList();
        //
        // if (user.getRole() != null && StringUtils.isNoneBlank(user.getRole().getName())) {
        //     this.roleName = user.getRole().getName();
        // } else if (user.getRoleList() != null && user.getRoleList().size() > 0) {
        //     String name = user.getRoleList().get(0).getName();
        //     if (StringUtils.isNoneBlank(name)) {
        //         this.roleName = name;
        //     }
        // }
    }

    /**
     * Gets id.
     *
     * @return the id
     */
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
     * Gets student uid.
     *
     * @return the student uid
     */
    public Integer getStudentUid() {
        return studentUid;
    }

    /**
     * Sets student uid.
     *
     * @param studentUid the student uid
     */
    public void setStudentUid(Integer studentUid) {
        this.studentUid = studentUid;
    }

    /**
     * Gets relation.
     *
     * @return the relation
     */
    public String getRelation() {
        return relation;
    }

    /**
     * Sets relation.
     *
     * @param relation the relation
     */
    public void setRelation(String relation) {
        this.relation = relation;
    }

    /**
     * Gets company name.
     *
     * @return the company name
     */
    public String getCompanyName() {
        return companyName;
    }

    /**
     * Sets company name.
     *
     * @param companyName the company name
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    /**
     * Gets office name.
     *
     * @return the office name
     */
    public String getOfficeName() {
        return officeName;
    }

    /**
     * Sets office name.
     *
     * @param officeName the office name
     */
    public void setOfficeName(String officeName) {
        this.officeName = officeName;
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
     * Gets role name.
     *
     * @return the role name
     */
    public String getRoleName() {
        return roleName;
    }

    /**
     * Sets role name.
     *
     * @param roleName the role name
     */
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    /**
     * Gets remarks.
     *
     * @return the remarks
     */
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
     * Gets create date.
     *
     * @return the create date
     */
    public String getCreateDate() {
        return createDate;
    }

    /**
     * Sets create date.
     *
     * @param createDate the create date
     */
    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    /**
     * Gets expired date.
     *
     * @return the expired date
     */
    // @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public String getExpiredDate() {
        return expiredDate;
    }

    /**
     * Sets expired date.
     *
     * @param expiredDate the expired date
     */
    public void setExpiredDate(String expiredDate) {
        this.expiredDate = expiredDate;
    }

    /**
     * Gets login name.
     *
     * @return the login name
     */
    // @JsonIgnore
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
     * Gets email.
     *
     * @return the email
     */
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
     * Gets sex.
     *
     * @return the sex
     */
    public String getSex() {
        return sex;
    }

    /**
     * Sets sex.
     *
     * @param sex the sex
     */
    public void setSex(String sex) {
        this.sex = sex;
    }

    /**
     * Gets qq.
     *
     * @return the qq
     */
    public String getQq() {
        return qq;
    }

    /**
     * Sets qq.
     *
     * @param qq the qq
     */
    public void setQq(String qq) {
        this.qq = qq;
    }

    /**
     * Gets signature.
     *
     * @return the signature
     */
    public String getSignature() {
        return signature;
    }

    /**
     * Sets signature.
     *
     * @param signature the signature
     */
    public void setSignature(String signature) {
        this.signature = signature;
    }

    /**
     * Gets referral code.
     *
     * @return the referral code
     */
    public String getReferralCode() {
        return referralCode;
    }

    /**
     * Sets referral code.
     *
     * @param referralCode the referral code
     */
    public void setReferralCode(String referralCode) {
        this.referralCode = referralCode;
    }

    /**
     * Gets from referral code.
     *
     * @return the from referral code
     */
    public String getFromReferralCode() {
        return fromReferralCode;
    }

    /**
     * Sets from referral code.
     *
     * @param fromReferralCode the from referral code
     */
    public void setFromReferralCode(String fromReferralCode) {
        this.fromReferralCode = fromReferralCode;
    }

    /**
     * Gets login ip.
     *
     * @return the login ip
     */
    public String getLoginIp() {
        return loginIp;
    }

    /**
     * Sets login ip.
     *
     * @param loginIp the login ip
     */
    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp;
    }

    /**
     * Gets login date.
     *
     * @return the login date
     */
    public String getLoginDate() {
        return loginDate;
    }

    /**
     * Sets login date.
     *
     * @param loginDate the login date
     */
    public void setLoginDate(String loginDate) {
        this.loginDate = loginDate;
    }

    /**
     * Gets book tag id.
     *
     * @return the book tag id
     */
    public String getBookTagId() {
        return bookTagId;
    }

    /**
     * Sets book tag id.
     *
     * @param bookTagId the book tag id
     */
    public void setBookTagId(String bookTagId) {
        this.bookTagId = bookTagId;
    }

    /**
     * Gets book tag name.
     *
     * @return the book tag name
     */
    public String getBookTagName() {
        return bookTagName;
    }

    /**
     * Sets book tag name.
     *
     * @param bookTagName the book tag name
     */
    public void setBookTagName(String bookTagName) {
        this.bookTagName = bookTagName;
    }

    // public List<UserSimpleVo> getParents() {
    // return parents;
    // }
    //
    // public void setParents(List<UserSimpleVo> parents) {
    // this.parents = parents;
    // }

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

    // @JsonIgnore
    // public Role getRole() {
    // return role;
    // }
    //
    // public void setRole(Role role) {
    // this.role = role;
    // }
    //
    // public List<Role> getRoleList() {
    // return roleList;
    // }
    //
    // public void setRoleList(List<Role> roleList) {
    // this.roleList = roleList;
    // }

    /**
     * TODO: 待处理后也要输入角色信息.
     *
     * @return 角色信息 privileges
     */
    @JsonIgnore
    public List<String> getPrivileges() {
        return privileges;
    }

    /**
     * Sets privileges.
     *
     * @param privileges the privileges
     */
    public void setPrivileges(List<String> privileges) {
        this.privileges = privileges;
    }

    @Override
    public String toString() {
        return "UserVo{" + "id='" + id + '\'' + ", name='" + name + '\'' + '}';
    }
}
