
package com.neoframework.microservices.wsteaching.api;

import com.neoframework.common.config.VersionConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 系统相关接口.
 */
@RestController
@RequestMapping("sys")
public class SysController {

    private static final Logger logger = LoggerFactory.getLogger(SysController.class);

    @Autowired
    VersionConfig versionConfig;

    /**
     * 获取版本信息.
     *
     * @return
     */
    @GetMapping("version")
    public VersionConfig getVersion() {
        // 勿直接返回 versionConfig，因为 spring 依赖注入添加额外信息但不能正常序列化
        VersionConfig result = new VersionConfig();
        result.setVersion(versionConfig.getVersion());
        result.setVersionDetail(versionConfig.getVersionDetail());
        result.setVersionCode(versionConfig.getVersionCode());
        result.setName(versionConfig.getName());

        // logger.info("sys/version: {} {}", result.getName(), result.getVersionDetail());
        return result;
    }
}
