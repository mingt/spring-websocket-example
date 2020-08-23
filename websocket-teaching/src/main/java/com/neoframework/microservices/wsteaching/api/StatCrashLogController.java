
package com.neoframework.microservices.wsteaching.api;

import com.neoframework.biz.api.trace.model.StatCrashLog;
import com.neoframework.common.api.R;
import com.neoframework.microservices.wsteaching.service.StatCrashLogServiceImpl;
import java.util.Date;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 前端崩溃日志服上报接口.
 *
 */
@RestController
@RequestMapping("")
public class StatCrashLogController {

    private static final Logger logger = LoggerFactory.getLogger(StatCrashLogController.class);

    @Autowired
    private StatCrashLogServiceImpl statCrashLogService;

    /**
     * 上报崩溃日志.
     *
     * <p>为提高性能，也不需要确保数据高度完整，所以无需事务支持或很多容错处理.</p>
     *
     * @param statCrashLog 详情
     * @return
     */
    @PostMapping("/statCrashLog")
    public R statCrashLog(StatCrashLog statCrashLog) {

        if (null == statCrashLog || StringUtils.isBlank(statCrashLog.getCrashLog())
                || null == statCrashLog.getPlatform() || null == statCrashLog.getActionDate()) {
            // return new SimpleResult(HttpStatus.BAD_REQUEST.value(), "缺少参数！");
            return R.failed("缺少参数");
        }

        try {
            statCrashLog.setCreationDate(new Date());
            statCrashLogService.insert(statCrashLog);
        } catch (Exception e) {
            logger.error("statCrashLog error", e);
            // return new SimpleResult(HttpStatus.BAD_REQUEST.value(), "数据上报失败!");
            return R.failed("数据上报失败");
        }
        // return new SimpleResult(HttpStatus.OK.value(), "数据上报成功!");
        return R.ok(null, "数据上报成功");
    }

}
