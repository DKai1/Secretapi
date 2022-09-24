package com.kevin.secret.annotation;

import java.lang.annotation.*;

/**
 * @author dengkai
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SecretApi {
}
