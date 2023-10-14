package com.example.config;

import java.lang.annotation.*;

/**
 * @version 1.0
 * @author： xiaoxu
 * @date： 2022-12-05 20:54
 */

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AuthAccess {
}
