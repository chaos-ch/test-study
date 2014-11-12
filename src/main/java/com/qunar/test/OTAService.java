package com.qunar.test;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.lang.annotation.*;

/**
 * Created by he.chen on 14-10-23.
 */
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Service
public @interface OTAService {
    String value() default "";
}
