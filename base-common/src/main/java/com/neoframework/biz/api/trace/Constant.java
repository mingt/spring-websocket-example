
package com.neoframework.biz.api.trace;

/**
 * 上报相关常量.
 *
 * <p>Created by igylove on 2018/5/17.</p>
 */
public class Constant {

    private Constant() {}

    /** 指标: 注册统计 */
    public static final String REGISTER_COUNT = "registerCount";
    /** 指标: 激活帐号的统计 */
    public static final String ACTIVE_ACCOUNT_COUNT = "activeAccountCount";

    /** 指标: app download统计 */
    public static final String APP_DOWNLOAD = "appDownload";

    /** 指标: resource使用（访问） 统计 */
    public static final String RESOURCE_SUM_USE = "resourceSumUse";
    /** 指标: resource收藏 统计 */
    public static final String RESOURCE_SUM_FAVORITE = "resourceSumFavorite";
    /** 指标: resource分享 统计 */
    public static final String RESOURCE_SUM_SHARE = "resourceSumShare";
    /** 组合key分隔符，例如 u-hw 指访问作业次数 */
    public static final String SEPARATOR_KEY = "-";

    /**
     * 启动次数 统计
     * 这个启动特定是每个用户当天第一次启动应用时上报,表示每天启动应用用户量的指标.
     * 具体打点时机是在 login 时判断（包括登录操作或者进入应用界面时），判断时间是否跨天了，若是就插入一个boot类型上报
     */
    public static final String BOOT_COUNT = "bootCount";

    /**
     * 登入,登出的次数统计指标也有意义，可直接反映用户使用情况。要注意logout因为非正常关闭应用等，前端要尽量相应做好处理
     */
    public static final String LOGIN_COUNT = "loginCount";
    /**
     * The constant LOGOUT_COUNT.
     */
    public static final String LOGOUT_COUNT = "logoutCount";
    /** (所有用户)活跃时长总数 统计 */
    public static final String ACTIVE_DURATION = "activeDuration";
    /**
     * 活跃用户数 统计
     * --> 活跃不按单次上报判断，这个仅待用
     */
    public static final String ACTIVE_COUNT = "activeCount";
    /** 判定活跃时长标准（暂定10分钟），单位：毫秒 */
    public static final long ACTIVE_TIMEMILLS = 600 * 1000;

    /**
     * 浏览页面次数
     */
    public static final String PAGE_COUNT = "pageCount";
    /** (浏览页面时长统计 */
    public static final String PAGE_DURATION = "pageDuration";

    /** 动态数量 */
    public static final String SPACE_MSG_COUNT = "spaceMsgCount";
    /** 动态评论数量 */
    public static final String SPACE_COMMENT_COUNT = "spaceCommentCount";
    /** 动态点赞数量，包括点赞评论或动态 */
    public static final String SPACE_LIKE_COUNT = "spaceLikeCount";

    /** \n分隔符 */
    public static final String SEPARATOR_ENTER = "\\\\n";
    /** \t分隔符 */
    public static final String SEPARATOR_TAB = "\\\\t";

    /** 活跃排行前几名 */
    public static final Integer ACTIVE_RANK_LIMIT = 200;

    /** 运营商 */
    public static class Operator {

        private Operator() {}

        /** 未知 */
        public static final Integer UNKOWN = 0;
        /** 移动 */
        public static final Integer MOBILE = 1;
        /** 联通 */
        public static final Integer UNICOM = 2;
        /** 电信 */
        public static final Integer TELECOM = 3;
    }

    /** 平台 */
    public static class Platform {

        private Platform() {}

        /**
         * The constant WEB.
         */
        public static final Integer WEB = 0;
        /**
         * The constant ANDROID.
         */
        public static final Integer ANDROID = 1;
        /**
         * The constant IOS.
         */
        public static final Integer IOS = 2;
        /**
         * The constant PC.
         */
        public static final Integer PC = 3;

        /**
         * Convert string.
         *
         * @param plat the plat
         * @return the string
         */
        public static String convert(Integer plat) {
            if (WEB.equals(plat)) {
                return "web";
            } else if (ANDROID.equals(plat)) {
                return "android";
            } else if (IOS.equals(plat)) {
                return "ios";
            } else if (PC.equals(plat)) {
                return "pc";
            }

            return "unknown";
        }
    }

    /** 网络 */
    public static class Network {

        private Network() {}

        /** 未知 */
        public static final Integer UNKOWN = 0;
        /** WIFI */
        public static final Integer WIFI = 1;
        /** 2G */
        public static final Integer G2 = 2;
        /** 3G */
        public static final Integer G3 = 3;
        /** 4G */
        public static final Integer G4 = 4;
    }

    /** 用户访问 */
    public static class Access {

        private Access() {}

        /** 启动 */
        public static final String TYPE_BOOT = "boot";
        /**
         * 登入.
         * 已经和前端、任工讨论确定，登入是指用户进入APP界面。活跃时长基于这个登入点和最近的登出点来计算.
         * “除了常规登入登出，需考虑按home键登出，重新登入，强制关闭登出”
         */
        public static final String TYPE_LOGIN = "login";
        /** 登出 */
        public static final String TYPE_LOGOUT = "logout";
        /** 浏览页面 */
        public static final String TYPE_PAGE = "page";
    }

    /** 资源操作类型 */
    public static class ResourceOpType {

        private ResourceOpType() {}

        /** 使用 use */
        public static final String USE = "u";
        /** 收藏 add favorite */
        public static final String FAVORITE = "f";
        /** 分享 share */
        public static final String SHARE = "s";
        /** 下载 download */
        public static final String DOWNLOAD = "dl";
    }

    /**
     * 活跃计算范围类型
     */
    public static class ActiveType {

        private ActiveType() {}

        /**
         * The constant DAY.
         */
        public static final String DAY = "day";
        /**
         * The constant WEEK.
         */
        public static final String WEEK = "week";
        /**
         * The constant MONTH.
         */
        public static final String MONTH = "month";
        /**
         * The constant YEAR.
         */
        public static final String YEAR = "year";
    }

}
