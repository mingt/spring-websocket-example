
package com.neoframework.common.auth.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 二维码登录记录 model .
 */
public class QrCode implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 主键 uuid */
    private String id;

    /** 登录名 在这里应该指 User.loginName */
    private String username;

    /** 创建时间，以应用服务器端判断为准（注意不是 mysql 端） 当前未用，以后有需要时使用 */
    private Date createDate;

    /** 过期时间，以应用服务器端判断为准（注意不是 mysql 端） */
    private Date expiredDate;

    /**
     * 如果扫描了则为真, 结合 expiredDate , 如果未到 expiredDate , 则允许更新 ifScanned , 否则不允许
     * 另一方面，如果 ifScanned 为真，则表示可根据 username 匹配用户了
     */
    private Boolean scanned;

    /** 这个 token保存，目前也暂时是为了防重入等. 可能不必要 */
    private String token;

    /** 安全目的所用，避免前端任意调用带 二维码 auth_type 的 oauth/token 方法获取到任意账号，到时要作为参数传入, 也是uuid */
    private String code;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getExpiredDate() {
        return expiredDate;
    }

    public void setExpiredDate(Date expiredDate) {
        this.expiredDate = expiredDate;
    }

    public Boolean getScanned() {
        return scanned;
    }

    public void setScanned(Boolean scanned) {
        this.scanned = scanned;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
