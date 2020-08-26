
package com.neoframework.common.auth.model;

import com.neoframework.common.api.ApiErrorConstant;
import com.neoframework.common.api.exception.InternalErrorException;
import com.neoframework.common.auth.Privilege;
import com.thinkgem.jeesite.modules.sys.entity.User;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * Spring Security 中的 UserDetails 实现
 *
 * @author Shengzhao Li
 * @author Intergrated by Ahming
 */
public class CustomUserDetails implements UserDetails {

    private static final long serialVersionUID = 3960629356924497112L;

    /**
     * 角色权限 前缀
     *
     * @see org.springframework.security.access.vote.RoleVoter
     */
    protected static final String ROLE_PREFIX = "ROLE_";

    /**
     * 默认的 用户角色
     * ROLE_USER
     */
    protected static final GrantedAuthority DEFAULT_USER_ROLE =
            new SimpleGrantedAuthority(ROLE_PREFIX + Privilege.USER.name());

    /**
     * The User.
     */
    protected User user;

    /**
     * 用户的授权集合
     */
    protected List<GrantedAuthority> grantedAuthorities = new ArrayList<>();

    /**
     * The Role.
     */
    protected String role;

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

    /**
     * Instantiates a new Custom user details.
     */
    public CustomUserDetails() {}

    /**
     * Instantiates a new Custom user details.
     *
     * @param user the user
     * @throws InternalErrorException the internal error exception
     */
    public CustomUserDetails(User user) throws InternalErrorException {
        this.user = user;
        if (!initialAuthorities()) {
            // throw new InternalErrorException("老师,学生,家长,代理商角色重复");
            throw new InternalErrorException(ApiErrorConstant.UserError.ROLE_CONFLITS_ERROR,
                    ApiErrorConstant.UserError.ROLE_CONFLITS_ERROR_MSG);
        }
    }

    /**
     * 初始化用户角色,权限.
     *
     * @return the boolean
     */
    public boolean initialAuthorities() {
        grantedAuthorities.clear();

        // Default, everyone have it 每个人都有一个默认的 ROLE_USER 角色，
        this.grantedAuthorities.add(DEFAULT_USER_ROLE);

        // // final List<Privilege> privileges = user.getPrivileges();
        // // for (Privilege privilege : privileges) {
        // // this.grantedAuthorities.add(new SimpleGrantedAuthority(ROLE_PREFIX + privilege.name()));
        // // }

        return true;
    }

    /**
     * Return authorities, more information see {@link #initialAuthorities()}
     *
     * @return Collection of GrantedAuthority
     */
    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        return this.grantedAuthorities;
    }

    /**
     * 保存一个更改密码方法.
     *
     * @param password the password
     */
    public void setPassword(String password) {
        if (null != user) {
            user.setPassword(password);
        }
    }

    @Override
    public String getPassword() {
        return user.getPassword(); // user.password();
    }

    /**
     * Clear password.
     */
    public void clearPassword() {
        if (null != user) {
            user.setPassword(null);
        }
    }

    @Override
    public String getUsername() {
        return user.getLoginName(); // user.username();
    }

    /* 账户是否未过期 */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /* 账户是否未锁定 */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /* 密码是否未过期 */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * 账户是否启用,默认true (启用).
     */
    @Override
    public boolean isEnabled() {
        // loginFlag ?
        return !user.isDeleted();
    }

    /**
     * User user.
     *
     * @return the user
     */
    public User user() {
        return user;
    }

    /**
     * Gets user.
     *
     * @return the user
     */
    public User getUser() {
        return user;
    }

    /**
     * Sets user.
     *
     * @param user the user
     */
    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "{" + "user=" + user + ", grantedAuthorities=" + grantedAuthorities + '}';
    }
}
