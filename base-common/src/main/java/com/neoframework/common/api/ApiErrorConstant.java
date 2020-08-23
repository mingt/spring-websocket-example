
package com.neoframework.common.api;

/**
 * 业务错误代码和信息.
 *
 * <p>
 * 分两类： 一类针对公共常用错误，另一类针对各业务模块的自定义错误。
 * TO-DO:目前暂时统一到本类，以后有需要再各模块自定义
 * </p>
 */
public class ApiErrorConstant {

    private ApiErrorConstant() {
        // 阻止 new 实例
    }

    /**
     * The type Common error.
     */
    public static class CommonError {

        private CommonError() {}

        /** 通用非法请求 */
        public static final Integer BAD_REQUEST = 1;
        /** 通用非法请求信息 */
        public static final String BAD_REQUEST_MSG = "非法请求"; // Bad Request
        /** 通用参数错误 */
        public static final Integer PARAMETER_INVALID = 2;
        /** 通用参数错误信息 */
        public static final String PARAMETER_INVALID_MSG = "参数缺失或无效"; // Parameter Invalid
        /** 通用拒绝请求 */
        public static final Integer ACCESS_DENIED = 3;
        /** 通用拒绝请求信息 */
        public static final String ACCESS_DENIED_MSG = "拒绝访问"; // Access Denied
        /** 访问api拒绝请求 */
        public static final Integer METHOD_ACCESS_DENIED = 777;
        /** 访问api拒绝请求信息 */
        public static final String METHOD_ACCESS_DENIED_MSG = "无访问此api权限"; // method Access Denied
        /** 通用资源未找到 */
        public static final Integer NOT_FOUND = 4;
        /** 通用资源未找到 信息 */
        public static final String NOT_FOUND_MSG = "资源未找到"; // Resource Not Found
        /** 通用服务器内部错误 */
        public static final Integer INTERNAL_ERROR = 5;
        /** 通用服务器内部错误信息 */
        public static final String INTERNAL_ERROR_MSG = "服务器内部错误"; // Internal Server Error
        /** 通用服务方法未实现 */
        public static final Integer NOT_IMPLEMENTED = 6;
        /** 通用服务方法未实现信息 */
        public static final String NOT_IMPLEMENTED_MSG = "方法未实现"; // Not Implemented
        /** OAuth错误 */
        public static final Integer OAUTH_ERROR = 7;

        /**
         * The constant OAUTH_ERROR_MSG.
         *
         * <p>
         * "OAuth错误，留意error和error_description"
         * </p>
         */
        public static final String OAUTH_ERROR_MSG = "OAuth error. See also error & error_description";

        /** 未登录，需要登录认证 */
        public static final Integer UNAUTHORIZED = 8;
        /** 未登录，需要登录认证 信息 */
        public static final String UNAUTHORIZED_MSG = "需要登录认证";

        /** refresh token 过期 */
        public static final Integer REFRESH_TOKEN_INVALID = 90;
        /**
         * The constant REFRESH_TOKEN_INVALID_MSG.
         */
        public static final String REFRESH_TOKEN_INVALID_MSG = "refresh token无效";
    }

    /**
     * 用户登录注册相关错误. 设定为 100 开始的号段.
     *
     * <p>
     * TODO: 待整理为新增类
     * </p>
     */
    public static class UserError {

        private UserError() {}

        /** 用户名不存在 */
        public static final Integer USERNAME_NOT_FOUND = 100;
        /**
         * The constant USERNAME_NOT_FOUND_MSG.
         */
        public static final String USERNAME_NOT_FOUND_MSG = "用户名不存在";
        /** 查无此用户,例如根据其他条件 */
        public static final Integer USER_NOT_FOUND = 101;
        /**
         * The constant USER_NOT_FOUND_MSG.
         */
        public static final String USER_NOT_FOUND_MSG = "查无此用户";
        /** 查无此学生 */
        public static final Integer STUDENT_NOT_FOUND = 102;
        /**
         * The constant STUDENT_NOT_FOUND_MSG.
         */
        public static final String STUDENT_NOT_FOUND_MSG = "查无此学生";
        /** 查无此老师 */
        public static final Integer TEACHER_NOT_FOUND = 103;
        /**
         * The constant TEACHER_NOT_FOUND_MSG.
         */
        public static final String TEACHER_NOT_FOUND_MSG = "查无此老师";
        /** 该老师没有班级 */
        public static final Integer TEACHER_MISSING_CLASS = 104;
        /**
         * The constant TEACHER_MISSING_CLASS_MSG.
         */
        public static final String TEACHER_MISSING_CLASS_MSG = "该老师没有班级";
        /** 查无此家长 */
        public static final Integer PARENT_NOT_FOUND = 105;
        /**
         * The constant PARENT_NOT_FOUND_MSG.
         */
        public static final String PARENT_NOT_FOUND_MSG = "查无此家长";
        /** 查无此机构 */
        public static final Integer OFFICE_NOT_FOUND = 106;
        /**
         * The constant OFFICE_NOT_FOUND_MSG.
         */
        public static final String OFFICE_NOT_FOUND_MSG = "查无此机构";

        /** 验证不通过 */
        public static final Integer VALIDATE_ERROR = 110;
        /**
         * The constant VALIDATE_ERROR_MSG.
         */
        public static final String VALIDATE_ERROR_MSG = "验证不通过，请检查用户名或密码";
        /**
         * The constant VALIDATE_PASSWORD_ERROR_MSG.
         */
        public static final String VALIDATE_PASSWORD_ERROR_MSG = "密码不匹配";

        /** 不支持的帐号类型 */
        public static final Integer UNSUPPORTED_USERNAME_TYPE_ERROR = 111;
        /**
         * The constant UNSUPPORTED_USERNAME_TYPE_ERROR_MSG.
         */
        public static final String UNSUPPORTED_USERNAME_TYPE_ERROR_MSG = "不支持的帐号类型";

        /** 登录时，检测到老师,学生,家长,代理商角色重复 */
        public static final Integer ROLE_CONFLITS_ERROR = 112;
        /**
         * The constant ROLE_CONFLITS_ERROR_MSG.
         */
        public static final String ROLE_CONFLITS_ERROR_MSG = "老师,学生,家长,代理商角色重复";

        /** 注册错误 */
        public static final Integer REGISTER_PARAMETER_ERROR = 120;
        /** 注册错误信息 */
        public static final String REGISTER_LOGIN_NAME_MISSING_MSG = "loginName:请提供用户名称loginName";
        /**
         * The constant REGISTER_LOGIN_NAME_MIN_SIZE_ERROR_MSG.
         */
        public static final String REGISTER_LOGIN_NAME_MIN_SIZE_ERROR_MSG = "loginName:为了安全和减少冲突，纯数字账号至少12位";
        /**
         * The constant REGISTER_LOGIN_NAME_FORMAT_ERROR_MSG.
         */
        public static final String REGISTER_LOGIN_NAME_FORMAT_ERROR_MSG = "用户名由4-20个数字、字母或‘_’组成，不可以使用纯数字或用‘_’结尾";
        /**
         * The constant REGISTER_MOBILE_MISSING_MSG.
         */
        public static final String REGISTER_MOBILE_MISSING_MSG = "mobile:请提供手机号码";
        /**
         * The constant REGISTER_MOBILE_FORMAT_ERROR_MSG.
         */
        public static final String REGISTER_MOBILE_FORMAT_ERROR_MSG = "mobile:请提供正确的手机号码";
        /**
         * The constant REGISTER_EMAIL_MISSING_MSG.
         */
        public static final String REGISTER_EMAIL_MISSING_MSG = "email:请提供电子邮箱";
        /**
         * The constant REGISTER_EMAIL_FORMAT_ERROR_MSG.
         */
        public static final String REGISTER_EMAIL_FORMAT_ERROR_MSG = "email:请提供正确的电子邮箱";
        /**
         * The constant REGISTER_ROLE_INVALID_MSG.
         */
        public static final String REGISTER_ROLE_INVALID_MSG = "role:目前前端只能注册ROLE_STUDENT或ROLE_PARENT角色";
        /**
         * The constant REGISTER_STUDENT_UID_MISSING_MSG.
         */
        public static final String REGISTER_STUDENT_UID_MISSING_MSG = "uid:家长注册时提供学生博学号";
        /**
         * The constant REGISTER_PASSWORD_LENGTH_ERROR_MSG.
         */
        public static final String REGISTER_PASSWORD_LENGTH_ERROR_MSG = "password:密码长度应为6到50之间";
        /**
         * The constant REGISTER_PARENT_SIZE_MAX_ERROR_MSG.
         */
        public static final String REGISTER_PARENT_SIZE_MAX_ERROR_MSG = "uid:此学生绑定家长成员已达到最大值，不能再绑定：";
        /**
         * The constant REGISTER_CLASS_INTERNAL_ERROR_MSG.
         */
        public static final String REGISTER_CLASS_INTERNAL_ERROR_MSG = "查询学生班级出错，请重试";
        /**
         * The constant REGISTER_CLASS_OID_ERROR_MSG.
         */
        public static final String REGISTER_CLASS_OID_ERROR_MSG = "此班级码有误: ";
        /**
         * The constant REGISTER_CLASS_UPPER_LEVEL_ERROR_MSG.
         */
        public static final String REGISTER_CLASS_UPPER_LEVEL_ERROR_MSG = "此班级码查询上级机构错误，班级码: ";
        /**
         * The constant REGISTER_SCHOOL_INTERNAL_ERROR_MSG.
         */
        public static final String REGISTER_SCHOOL_INTERNAL_ERROR_MSG = "查询学生学校出错，请重试";
        /**
         * The constant REGISTER_SCHOOL_OID_ERROR_MSG.
         */
        public static final String REGISTER_SCHOOL_OID_ERROR_MSG = "此班级码查询上级机构错误: ";

        /** 注册名已存在错误 */
        public static final Integer REGISTER_EXISTED_ERROR = 121;
        /** 注册名已存在错误信息 */
        public static final String REGISTER_EXISTED_USERNAME_MSG = "这个账号已被注册";
        /**
         * The constant REGISTER_EXISTED_MOBILE_MSG.
         */
        public static final String REGISTER_EXISTED_MOBILE_MSG = "这个手机号码已被注册";
        /**
         * The constant REGISTER_EXISTED_MOBILE_TRIAL_MSG.
         */
        public static final String REGISTER_EXISTED_MOBILE_TRIAL_MSG = "此手机号已经被试用过";
        /**
         * The constant REGISTER_EXISTED_EMAIL_MSG.
         */
        public static final String REGISTER_EXISTED_EMAIL_MSG = "这个电子邮箱已被注册";
        /**
         * The constant REGISTER_EXISTED_EMAIL_MOBILE_MSG.
         */
        public static final String REGISTER_EXISTED_EMAIL_MOBILE_MSG = "此电子邮箱或手机号已被使用";
        /**
         * The constant REGISTER_EXISTED_NO_MSG.
         */
        public static final String REGISTER_EXISTED_NO_MSG = "这个学号已被注册";

        /** 注册时内部错误 */
        public static final Integer REGISTER_INTERNAL_ERROR = 122;
        /** 注册错误信息 */
        public static final String REGISTER_INTERNAL_ERROR_MSG = "注册出错,请重试";

        /** 绑定内部错误 */
        public static final Integer BIND_INTERNAL_ERROR = 123;
        /**
         * The constant BIND_INTERNAL_ERROR_MSG.
         */
        public static final String BIND_INTERNAL_ERROR_MSG = "绑定内部错误";
        /** 绑定错误 */
        public static final Integer BIND_ERROR = 124;
        /** 绑定错误信息 */
        public static final String BIND_APPLY_AGAIN_ERROR_MSG = "已经申请过了,请勿重复申请";
        /**
         * The constant BIND_APPLY_NOT_EXISTS_MSG.
         */
        public static final String BIND_APPLY_NOT_EXISTS_MSG = "该对应的申请不存在";
        /**
         * The constant BIND_RELATION_REQUIRED_MSG.
         */
        public static final String BIND_RELATION_REQUIRED_MSG = "关系只能为下面其中一个：";
        /**
         * The constant BIND_AGAIN_MSG.
         */
        public static final String BIND_AGAIN_MSG = "已经绑定过了,请勿重新绑定";
        /**
         * The constant BIND_STUDENTID_OR_PARENTID_REQUIRED_MSG.
         */
        public static final String BIND_STUDENTID_OR_PARENTID_REQUIRED_MSG = "需要提供学生ID或家长ID";
        /**
         * The constant BIND_PARENT_STUDENT_NOT_MATCH_MSG.
         */
        public static final String BIND_PARENT_STUDENT_NOT_MATCH_MSG = "学生与家长的关系不匹配";
        /**
         * The constant BIND_PARENT_NOT_BIND_STUDENT_MSG.
         */
        public static final String BIND_PARENT_NOT_BIND_STUDENT_MSG = "该家长与孩子尚未绑定,请先绑定";

        /** 验证码不正确 */
        public static final Integer VALIDATION_CODE_NOT_MATCH = 130;
        /**
         * The constant VALIDATION_CODE_NOT_MATCH_MSG.
         */
        public static final String VALIDATION_CODE_NOT_MATCH_MSG = "验证码不正确";
        /** 对应的验证码已过期 */
        public static final Integer VALIDATION_CODE_EXPIRED = 131;
        /**
         * The constant VALIDATION_CODE_EXPIRED_MSG.
         */
        public static final String VALIDATION_CODE_EXPIRED_MSG = "对应的验证码已过期";

        /** 此班级对应的学校不存在 */
        public static final Integer CLASS_SCHOOL_NOT_FOUND = 140;
        /**
         * The constant CLASS_SCHOOL_NOT_FOUND_MSG.
         */
        public static final String CLASS_SCHOOL_NOT_FOUND_MSG = "此班级对应的学校不存在";

        /** 忘记密码参数错误 */
        // 沿用 CommonError.PARAMETER_INVALID // public static final Integer FIND_PASSWORD_PARAMETER_MISSING = ;
        public static final String FIND_PASSWORD_PARAMETER_MISSING_MSG = "mobile/email/parentMobile/parentEmail至少一个必填!";

        /** 非法userId参数 */
        public static final Integer INVALID_USERID_PARAM = 150;
        /**
         * The constant INVALID_USERID_PARAM_MSG.
         */
        public static final String INVALID_USERID_PARAM_MSG = "非法userId参数";

        /** 非法opUserId参数 */
        public static final Integer INVALID_OPUSERID_PARAM = 151;
        /**
         * The constant INVALID_OPUSERID_PARAM_MSG.
         */
        public static final String INVALID_OPUSERID_PARAM_MSG = "非法opUserId参数";

        /** 二维码登录获取二维码错误 */
        public static final Integer QR_CODE_INFO_ERROR = 160;
        /**
         * The constant QR_CODE_INFO_ERROR_MSG.
         */
        public static final String QR_CODE_INFO_ERROR_MSG = "生成二维码信息出错，请重试";
        /** 二维码信息格式不符 */
        public static final Integer QR_CODE_INFO_INVALID = 161;
        /**
         * The constant QR_CODE_INFO_INVALID_MSG.
         */
        public static final String QR_CODE_INFO_INVALID_MSG = "二维码信息有误";
        /** 二维码信息过期 */
        public static final Integer QR_CODE_INFO_EXPIRED = 162;
        /**
         * The constant QR_CODE_INFO_EXPIRED_MSG.
         */
        public static final String QR_CODE_INFO_EXPIRED_MSG = "二维码信息已过期，请刷新";

        /** 未找到对应的邀请码 */
        public static final Integer REFERRAL_CODE_NOT_FOUND = 165;
        /**
         * The constant REFERRAL_CODE_NOT_FOUND_MSG.
         */
        public static final String REFERRAL_CODE_NOT_FOUND_MSG = "未找到对应的邀请码";

        /** 注册用户未选定班级 如果用户还在默认注册的博学宝教育软件公司下班级中的话，就是未指定自己的班级 */
        public static final Integer REGISTER_USER_NOT_YET_SET_CLASS = 170;
        /**
         * The constant REGISTER_USER_NOT_YET_SET_CLASS_MSG.
         */
        public static final String REGISTER_USER_NOT_YET_SET_CLASS_MSG = "注册用户未选定班级";
    }

}
