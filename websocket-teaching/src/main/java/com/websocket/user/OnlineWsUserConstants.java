package com.websocket.user;

/**
 * Date: 2020/9/8 Ahming
 */
public class OnlineWsUserConstants {
    private OnlineWsUserConstants(){}

    /**
     * 用户信息 hash 键模板.
     * %s: 频道id
     * %d: 用户uid
     */
    public static final String USER_HASH_KEY_PATTERN = "wstc:u:h:%s:uid:%d";

    /**
     * 在线用户 uid set 键前缀.
     */
    public static final String USERS_UID_SET_KEY_PREFIX = "wstc:u:s:";

    /**
     * 在线用户 uid set 键全部匹配.
     */
    public static final String USERS_UID_SET_KEY_ALL_PATTERN = "wstc:u:s:*";

    /**
     * 在线用户 uid set 键模板.
     * %s: 频道id
     */
    public static final String USERS_UID_SET_KEY_PATTERN = "wstc:u:s:%s";
}
