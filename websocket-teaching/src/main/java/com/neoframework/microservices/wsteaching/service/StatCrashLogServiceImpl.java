
package com.neoframework.microservices.wsteaching.service;

import com.neoframework.biz.api.trace.model.StatCrashLog;
import com.neoframework.biz.api.trace.service.StatCrashLogService;
import com.neoframework.microservices.wsteaching.dao.StatCrashLogDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StatCrashLogServiceImpl implements StatCrashLogService {

    private static final Logger logger = LoggerFactory.getLogger(StatCrashLogServiceImpl.class);

    @Autowired
    private StatCrashLogDao statCrashLogDao;

    /**
     * 插入数据.
     *
     * @param statCrashLog 详情
     * @return
     */
    @Override
    public int insert(StatCrashLog statCrashLog) {
        return statCrashLogDao.insert(statCrashLog);
    }

}
