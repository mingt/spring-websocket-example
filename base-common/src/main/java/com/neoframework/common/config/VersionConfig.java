
package com.neoframework.common.config;

import java.io.Serializable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * 版本信息项.
 *
 * <p>Created by think on 2018-06-26.</p>
 */
@Configuration
@PropertySource(value = "classpath:version.properties", ignoreResourceNotFound = true)
public class VersionConfig implements Serializable {

    /** serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /** 简要版本名称 默认空值 */
    @Value("${version:unknown version}")
    private String version;
    /** 详细版本名称 */
    @Value("${versionDetail:unknown version detail}")
    private String versionDetail;
    /** 整型版本号 */
    @Value("${versionCode:0}")
    private Integer versionCode;
    /** 项目名称，目前是各子项目的build.gradle中的项目名称，大概是项目的目录名称 */
    @Value("${name:unknown name}")
    private String name;

    /**
     * Gets version.
     *
     * @return the version
     */
    public String getVersion() {
        return version;
    }

    /**
     * Sets version.
     *
     * @param version the version
     */
    public void setVersion(String version) {
        this.version = version;
    }

    /**
     * Gets version detail.
     *
     * @return the version detail
     */
    public String getVersionDetail() {
        return versionDetail;
    }

    /**
     * Sets version detail.
     *
     * @param versionDetail the version detail
     */
    public void setVersionDetail(String versionDetail) {
        this.versionDetail = versionDetail;
    }

    /**
     * Gets version code.
     *
     * @return the version code
     */
    public Integer getVersionCode() {
        return versionCode;
    }

    /**
     * Sets version code.
     *
     * @param versionCode the version code
     */
    public void setVersionCode(Integer versionCode) {
        this.versionCode = versionCode;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }
}
