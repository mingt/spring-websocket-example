
package com.neoframework.microservices.wsteaching.dao;

import com.neoframework.biz.api.trace.model.StatCrashLog;
import com.neoframework.common.database.MyBatisRepository;

@MyBatisRepository
public interface StatCrashLogDao {

    /**
     * 插入数据.
     *
     * @param statCrashLog 详情
     * @return
     */
    public int insert(StatCrashLog statCrashLog);
}
