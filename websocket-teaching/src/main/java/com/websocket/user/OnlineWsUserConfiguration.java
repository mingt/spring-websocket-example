package com.websocket.user;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 在线 WS 用户相关配置，如 Bean 定义.
 *
 * <p>Date: 2020/9/7 Ahming</p>
 */
@Configuration
public class OnlineWsUserConfiguration {

    /**
     * 在线 WS 用户存储器.
     *
     * @return
     */
    @Bean(name = "onlineWsUserStorage")
    public OnlineWsUserStore onlineWsUserStore() {

        // TODO: MemoryOnlineWsUserStorage 只支持单实例内的情况，需要增如 Redis 的实现
        return new MemoryOnlineWsUserStore();
    }
}
