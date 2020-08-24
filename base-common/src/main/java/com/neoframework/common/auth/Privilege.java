
package com.neoframework.common.auth;

/**
 * 用户权限
 *
 * @author Shengzhao Li
 */
public enum Privilege {

    USER, // Default privilege

    TEACHER, // 老师
    STUDENT, // 学生
    PARENT, // 家长
    AGENT, // 代理商

    UNITY, // 仅测试
    MOBILE // 仅测试
}
