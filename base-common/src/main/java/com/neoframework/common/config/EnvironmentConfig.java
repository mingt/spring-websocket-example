
package com.neoframework.common.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 从 config-server 读取 Environment 相关配置值.
 *
 * <p>键名为： environment.name </p>
 *
 * <p>例如，区分本地环境，测试环境，生产环境，如果仅从 active profile 等已有的配置好像未能满足一些需要，
 * 例如，希望测试环境和本地环境的 bos 的上传 file_id 前缀能区分（放不同的目录，如生产服 /somepath/somefile
 * 在 本地服或测试服为 /test/somepath/somefile）</p>
 */
@Component
@ConfigurationProperties
public class EnvironmentConfig {

    /** 生产环境 */
    public static final String PROD = "prod";
    /** 本地环境 */
    public static final String LOCAL = "local";
    /** 测试环境 */
    public static final String TEST = "test";
    /** 默认环境/未指定环境 */
    public static final String DEFAULT = "default";

    private final Environment environment = new Environment();

    /**
     * Gets environment.
     *
     * @return the environment
     */
    public Environment getEnvironment() {
        return environment;
    }

    /**
     * The type Environment.
     */
    public static class Environment {

        private String name;

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

}
