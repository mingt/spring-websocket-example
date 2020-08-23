
package com.neoframework.biz.api.trace.service;

import com.neoframework.biz.api.trace.model.StatCrashLog;

/**
 * 崩溃日志上报 service.
 */
public interface StatCrashLogService {

    /**
     * 插入数据.
     *
     * @param statCrashLog the stat crash log
     * @return int
     */
    public int insert(StatCrashLog statCrashLog);

}
