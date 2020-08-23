
package com.neoframework.common.database;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.springframework.stereotype.Component;

/**
 * 标识 MyBatis 的 DAO ,方便 {@link org.mybatis.spring.mapper.MapperScannerConfigurer}
 * 的扫描.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Component
public @interface MyBatisRepository {

    /**
     * Value string.
     *
     * @return the string
     */
    String value() default "";
}
