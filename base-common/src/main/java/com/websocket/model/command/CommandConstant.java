
package com.websocket.model.command;

/**
 * The type Command constant.
 */
public class CommandConstant {

    private CommandConstant() {}

    /**
     * The type Platform.
     */
    public static class Platform {

        private Platform() {}

        /**
         * The constant PC.
         */
        public static final String PC = "pc";
        /**
         * The constant ANDROID.
         */
        public static final String ANDROID = "android";
        /**
         * The constant IOS.
         */
        public static final String IOS = "ios";
        /**
         * The constant WEB.
         */
        public static final String WEB = "web";
    }

    /**
     * The type Timer action.
     */
    public static class TimerAction {

        private TimerAction() {}

        /**
         * The constant START.
         */
        public static final String START = "start";
        /**
         * The constant STOP.
         */
        public static final String STOP = "stop";
        /**
         * The constant PAUSE.
         */
        public static final String PAUSE = "pause";
        /**
         * The constant RESET.
         */
        public static final String RESET = "reset";
        /**
         * The constant FULLSCREEN.
         */
        public static final String FULLSCREEN = "fullscreen";
        /**
         * The constant MINISCREEN.
         */
        public static final String MINISCREEN = "miniscreen";
        /**
         * The constant EXIT.
         */
        public static final String EXIT = "exit";
    }

    /**
     * The type Timer sub type.
     */
    public static class TimerSubType {

        private TimerSubType() {}

        /**
         * The constant COUNT_DOWN.
         */
        public static final String COUNT_DOWN = "countDown";
        /**
         * The constant COUNT_UP.
         */
        public static final String COUNT_UP = "countUp";
    }

    /**
     * The type Call name action.
     */
    public static class CallNameAction {

        private CallNameAction() {}

        /**
         * The constant START.
         */
        public static final String START = "start";
        /**
         * The constant STOP.
         */
        public static final String STOP = "stop";
    }

    /**
     * The type Classroom action.
     */
    public static class ClassroomAction {

        private ClassroomAction() {}

        /**
         * The constant START.
         */
        public static final String START = "start";
        /**
         * The constant STOP.
         */
        public static final String STOP = "stop";
        /**
         * The constant CHANGE_TEXTBOOK.
         */
        public static final String CHANGE_TEXTBOOK = "changeTextbook";
        /**
         * The constant CHANGE_TEXT.
         */
        public static final String CHANGE_TEXT = "changeText";
    }

    /**
     * The type Vote action.
     */
    public static class VoteAction {

        private VoteAction() {}

        /**
         * The constant START.
         */
        public static final String START = "start";
        /**
         * The constant STOP.
         */
        public static final String STOP = "stop";
        /**
         * The constant COMMIT.
         */
        public static final String COMMIT = "commit";
    }

    /**
     * The type Resource action.
     */
    public static class ResourceAction {

        private ResourceAction() {}

        /**
         * The constant CHANGE.
         */
        public static final String CHANGE = "change";
    }

    /**
     * The type Homework action.
     */
    public static class HomeworkAction {

        private HomeworkAction() {}

        /** 布置作业 */
        public static final String ACTION_ASSIGN = "assign";
        /** 单题提交作业 */
        public static final String ACTION_COMMIT_QUESTION = "commitQuestion";
        /** 提交整个作业 */
        public static final String ACTION_COMMIT_HOMEWORK = "commitHomework";
        /** 收作业 */
        public static final String ACTION_COLLECT = "collect";
        /** 打开课堂作业 */
        public static final String ACTION_OPEN = "open";
        /** 课堂作业切换上一题 */
        public static final String ACTION_PRE_QUESTION = "preQuestion";
        /** 课堂作业切换下一题 */
        public static final String ACTION_NEXT_QUESTION = "nextQuestion";
        /** 单个学生单个题目详情投屏 */
        public static final String ACTION_VIEW_STUDENT_ANSWER = "viewStudentAnswer";
        /** 成功代码 */
        public static final Integer SUCCESS_CODE = 1;
        /** 失败代码 */
        public static final Integer FAILED_CODE = -1;
        /** 布置作业成功信息 */
        public static final String ASSIGN_SUCCESS_MSG = "布置课堂作业成功";
        /** 布置作业失败信息 */
        public static final String ASSIGN_FAILED_MSG = "布置课堂作业失败";
        /** 单题提交作业成功信息 */
        public static final String COMMIT_QUESTION_SUCCESS_MSG = "单题提交课堂作业成功";
        /** 单题提交作业失败信息 */
        public static final String COMMIT_QUESTION_FAILED_MSG = "单题提交课堂作业失败";
        /** 提交整个作业成功信息 */
        public static final String COMMIT_HOMEWORK_SUCCESS_MSG = "提交整个课堂作业成功";
        /** 提交整个作业失败信息 */
        public static final String COMMIT_HOMEWORK_FAILED_MSG = "提交整个课堂作业失败";
        /** 收作业成功信息 */
        public static final String COLLECT_SUCCESS_MSG = "收课堂作业成功";
        /** 收作业失败信息 */
        public static final String COLLECT_FAILED_MSG = "收课堂作业失败";
    }
}
