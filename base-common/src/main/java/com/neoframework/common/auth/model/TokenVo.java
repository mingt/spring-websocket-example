
package com.neoframework.common.auth.model;

import java.io.Serializable;

/**
 * oauth/token 返回的结果 VO .
 *
 * <pre>
 * {
 *     "access_token": "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.xxxxxxxxxxxx",
 *     "token_type": "bearer",
 *     "refresh_token": "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.xxxxxxxxxxxx",
 *     "expires_in": 30851,
 *     "scope": "openid",
 *     "jti": "f4a07e3a-0b46-4b7b-9f1f-1cdbb35eaad6"
 * }
 * </pre>
 *
 */
public class TokenVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String accessToken;
    private String tokenType;
    private String refreshToken;
    private String scope;
    private String jti;
    /** 过期时间 单位: 秒 */
    private Integer expiresIn;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getJti() {
        return jti;
    }

    public void setJti(String jti) {
        this.jti = jti;
    }

    public Integer getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(Integer expiresIn) {
        this.expiresIn = expiresIn;
    }
}
