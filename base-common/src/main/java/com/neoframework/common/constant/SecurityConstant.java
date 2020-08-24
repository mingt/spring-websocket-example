package com.neoframework.common.constant;

/**
 * 帐号安全相关常量.
 *
 * @since 2020/8/24
 */
public class SecurityConstant {
    private SecurityConstant() {}

    /**
     * BCrypt 开头字符.
     */
    public static final String BCRYPT_PASSWORD_PREFIX = "$2a$";

    /**
     * BCryptPasswordEncoder 相关.
     */
    public static final int BCRYPT_STRENGTH = 10;

    /**
     * SHA-1 相关.
     */
    public static final int HASH_INTERATIONS = 1024;
    public static final int SALT_SIZE = 8;

    /**
     * 目前导入用户的默认密码.
     */
    public static final String IMPORT_USER_DEFAULT_PASSWORD = "123456";

    /**
     * WebSocket 传递 access_token 的 header 名称，与 OAuth 传递 Bearer access_token 的  Authorization 区分.
     */
    public static final String HEADER_X_AUTH_TOKEN = "X-Auth-Token";
}
