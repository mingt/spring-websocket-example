
package com.websocket.model.command;

import com.websocket.model.WsUser;
import java.util.ArrayList;
import java.util.List;

/**
 * 点名命令.
 */
public class CallNameCommand extends BaseCommand {

    /**
     * The constant TYPE.
     */
    public static final String TYPE = "callName";

    private Integer num;

    /** TODO: 为什么要传递学生 ids ? 待确认 */
    private List<String> stuIds;

    private List<WsUser> students; // 待点名的所有学生列表

    /**
     * Gets num.
     *
     * @return the num
     */
    public Integer getNum() {
        return num;
    }

    /**
     * Sets num.
     *
     * @param num the num
     */
    public void setNum(Integer num) {
        this.num = num;
    }

    /**
     * Gets stu ids.
     *
     * @return the stu ids
     */
    public List<String> getStuIds() {
        return stuIds;
    }

    /**
     * Sets stu ids.
     *
     * @param stuIds the stu ids
     */
    public void setStuIds(List<String> stuIds) {
        this.stuIds = stuIds;
    }

    /**
     * Gets students.
     *
     * @return the students
     */
    public List<WsUser> getStudents() {
        return students;
    }

    /**
     * Sets students.
     *
     * @param students the students
     */
    public void setStudents(List<WsUser> students) {
        this.students = students;
    }

    /**
     * Sets students sys.
     *
     * @param students the students
     */
    public void setStudentsSys(List<com.thinkgem.jeesite.modules.sys.entity.User> students) {
        if (students != null && students.size() > 0) {
            List<WsUser> stuList = new ArrayList<>();
            for (com.thinkgem.jeesite.modules.sys.entity.User stu : students) {
                stuList.add(new WsUser(stu.getId(), null, null, stu.getName(), null)); // 只保留id和name字段信息
            }
            this.students = stuList;
        }
    }
}
