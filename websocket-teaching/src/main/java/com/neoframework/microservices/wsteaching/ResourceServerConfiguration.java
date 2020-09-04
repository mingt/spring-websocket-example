
package com.neoframework.microservices.wsteaching;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;
import org.springframework.security.web.header.writers.frameoptions.XFrameOptionsHeaderWriter;

/**
 * 配置 ResourceServerConfigurerAdapter 支持忽略路径.
 *
 * <p>
 * 另一种实现方法是直接由 application 类，如 AuthApplication, UserApplication 继承 ResourceServerConfigurerAdapter，
 * 同时，同样添加下面配置 HttpSecurity 的方法（注释掉的 WebSecurityConfigurerAdapter 可用于忽略资源类）
 * </p>
 *
 **/
@Configuration
@EnableResourceServer
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

    /**
     * 需要忽略的路径，加入下面列表去，并测试通过.
     *
     * @param http HttpSecurity
     * @throws Exception Exception
     */
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()

                // See https://jira.springsource.org/browse/SPR-11496
                .headers()
                .addHeaderWriter(new XFrameOptionsHeaderWriter(XFrameOptionsHeaderWriter.XFrameOptionsMode.SAMEORIGIN))
                .and()

                .authorizeRequests()
                .antMatchers("/sys/version",
                    "/statCrashLog", // TODO: 仅测试
                    "/topic/**", "/app/**", "/wschat/**", "/wsteaching/**", "/wssys/ifExternalBroker" // gs-guide-websocket
                )

                .permitAll().anyRequest().authenticated().and().logout().permitAll().and().formLogin().permitAll().and()
                .exceptionHandling().accessDeniedHandler(new OAuth2AccessDeniedHandler());
    }
}
