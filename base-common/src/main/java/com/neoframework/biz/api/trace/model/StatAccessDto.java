/*
 * Copyright (c) 2019. Project: Elearning.
 *
 * All rights reserved.
 */

package com.neoframework.biz.api.trace.model;

import java.util.Date;

/**
 * 用户访问上报实体 dto.
 */
public class StatAccessDto extends StatAccess {

    private static final long serialVersionUID = 1L;

    private Date startDate;
    private Date endDate;

    /** 活跃时长 sum 总计 */
    private Long acSum;

    /**
     * Gets start date.
     *
     * @return the start date
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * Sets start date.
     *
     * @param startDate the start date
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     * Gets end date.
     *
     * @return the end date
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * Sets end date.
     *
     * @param endDate the end date
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    /**
     * Gets ac sum.
     *
     * @return the ac sum
     */
    public Long getAcSum() {
        return acSum;
    }

    /**
     * Sets ac sum.
     *
     * @param acSum the ac sum
     */
    public void setAcSum(Long acSum) {
        this.acSum = acSum;
    }
}
