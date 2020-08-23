
package com.neoframework.microservices.wsteaching;

import com.neoframework.common.database.MyBatisRepository;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

/**
 * The boot application class that defines the spring boot application to have
 * the following properties<br>
 * <br>
 *
 * <ol>
 * <li>Act as a Eureka client; this behavior is provided by the
 * {@link EnableEurekaClient} annotation. The Eureka server URL is provided by
 * the external configuration provided by the config server.</li>
 * <li>@EnableEurekaClient makes the app into both a Eureka "instance" (i.e. it
 * registers itself) and a "client" (i.e. it can query the registry to locate
 * other services).</li>
 * <li>Note that all these annotations work in conjunction with properties
 * defined in the external configuration files specified by the config server.
 * </li>
 * </ol>
 *
 * @author anilallewar
 * @author Elearning Team
 */
@SpringBootApplication
@EnableEurekaClient
@EnableResourceServer
@MapperScan(basePackages = "com.neoframework.microservices.wsteaching.dao",
        annotationClass = MyBatisRepository.class)
@ComponentScan(basePackages = {"com.neoframework.microservices.wsteaching", "com.neoframework.common.config"
        // , "com.neoframework.common.redis"
})
public class WsTeachingApplication {

    public static void main(String[] args) {
        SpringApplication.run(WsTeachingApplication.class, args);
    }

    @Bean
    @LoadBalanced
    public OAuth2RestTemplate restTemplate(OAuth2ProtectedResourceDetails resource,
            OAuth2ClientContext context) {
        return new OAuth2RestTemplate(resource, context);
    }

    // @Bean
    // public FilterRegistrationBean catFilter() {
    // FilterRegistrationBean registration = new FilterRegistrationBean();
    // CatFilter filter = new CatFilter();
    // registration.setFilter(filter);
    // registration.addUrlPatterns("/*");
    // registration.setName("cat-filter");
    // registration.setOrder(1);
    // return registration;
    // }
}
