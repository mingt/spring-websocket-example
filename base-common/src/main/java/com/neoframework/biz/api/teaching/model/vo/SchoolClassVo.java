
package com.neoframework.biz.api.teaching.model.vo;

import com.neoframework.biz.api.common.vo.UserVo;
import com.thinkgem.jeesite.modules.sys.entity.Office;
import java.io.Serializable;

/**
 * 用户机构信息表 VO.
 *
 * <p>
 * Created by think on 2017-10-30.
 * </p>
 */
public class SchoolClassVo implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 用户信息 */
    UserVo user;
    /** 学校信息 */
    Office school;
    /** 班级信息 */
    Office theClass;

    /**
     * Instantiates a new School class vo.
     */
    public SchoolClassVo() {}

    /**
     * Instantiates a new School class vo.
     *
     * @param user the user
     * @param school the school
     * @param theClass the the class
     */
    public SchoolClassVo(UserVo user, Office school, Office theClass) {
        this.user = user;
        this.school = school;
        this.theClass = theClass;
    }

    /**
     * Gets user.
     *
     * @return the user
     */
    public UserVo getUser() {
        return user;
    }

    /**
     * Sets user.
     *
     * @param user the user
     */
    public void setUser(UserVo user) {
        this.user = user;
    }

    /**
     * Gets school.
     *
     * @return the school
     */
    public Office getSchool() {
        return school;
    }

    /**
     * Sets school.
     *
     * @param school the school
     */
    public void setSchool(Office school) {
        this.school = school;
    }

    /**
     * Gets the class.
     *
     * @return the the class
     */
    public Office getTheClass() {
        return theClass;
    }

    /**
     * Sets the class.
     *
     * @param theClass the the class
     */
    public void setTheClass(Office theClass) {
        this.theClass = theClass;
    }
}
