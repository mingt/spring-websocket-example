
package com.neoframework.biz.api.trace.model;

import java.io.Serializable;
import java.util.List;

/**
 * 行为上报VO.
 *
 * <p>即各类型的批量上报的集合，目前在积分统计时作为参数传递.
 * Date: 2019/11/8 Ahming</p>
 */
public class StatReportVo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 访问列表.
     */
    private List<StatAccess> accessList;

    /**
     * 资源访问列表.
     */
    private List<StatResourceAction> resourceActionList;

    /**
     * Gets access list.
     *
     * @return the access list
     */
    public List<StatAccess> getAccessList() {
        return accessList;
    }

    /**
     * Sets access list.
     *
     * @param accessList the access list
     */
    public void setAccessList(List<StatAccess> accessList) {
        this.accessList = accessList;
    }

    /**
     * Gets resource action list.
     *
     * @return the resource action list
     */
    public List<StatResourceAction> getResourceActionList() {
        return resourceActionList;
    }

    /**
     * Sets resource action list.
     *
     * @param resourceActionList the resource action list
     */
    public void setResourceActionList(List<StatResourceAction> resourceActionList) {
        this.resourceActionList = resourceActionList;
    }
}
