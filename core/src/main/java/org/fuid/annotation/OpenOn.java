package org.fuid.annotation;

import java.lang.annotation.Retention;

@Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
public @interface OpenOn {
	 String value() default "";
}
