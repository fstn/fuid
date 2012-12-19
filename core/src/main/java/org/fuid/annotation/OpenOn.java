package org.fuid.annotation;

import java.lang.annotation.Retention;

import org.fuid.event.FuidEventType;

@Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
public @interface OpenOn {
	String[] event();
}
