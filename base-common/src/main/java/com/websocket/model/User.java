
package com.websocket.model;

/**
 * 用户信息，websocket简化
 */
public class User {

    /** UUID */
    private String id;
    /** 博学号 */
    private Integer uid;
    /** 对应 Authentication 中的 username / principal 等 */
    private String loginName;
    /** 显示的姓名 */
    private String name;
    /** 角色名称，如 ROLE_TEACHER 等 */
    private String role;

    /**
     * Instantiates a new User.
     */
    public User() {}

    /**
     * Instantiates a new User.
     *
     * @param loginName the login name
     */
    public User(String loginName) {
        this.loginName = loginName;
    }

    /**
     * Instantiates a new User.
     *
     * @param id the id
     * @param uid the uid
     * @param loginName the login name
     * @param name the name
     * @param role the role
     */
    public User(String id, Integer uid, String loginName, String name, String role) {
        this.id = id;
        this.uid = uid;
        this.loginName = loginName;
        this.name = name;
        this.role = role;
    }

    /**
     * Instantiates a new User.
     *
     * @param sysUser the sys user
     */
    public User(com.thinkgem.jeesite.modules.sys.entity.User sysUser) {
        if (null != sysUser) {
            this.id = sysUser.getId();
            this.uid = sysUser.getUid();
            this.loginName = sysUser.getLoginName();
            this.name = sysUser.getName();
            // this.role = sysUser.getRoleEnName(); // 应该是 userDetails 里的 role
            // // this.role = sysUser.getRoleNames();
        }
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
     * Gets role.
     *
     * @return the role
     */
    public String getRole() {
        return role;
    }

    /**
     * Sets role.
     *
     * @param role the role
     */
    public void setRole(String role) {
        this.role = role;
    }
}
