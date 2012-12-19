package org.fuid.annotation;

import java.awt.BorderLayout;
import java.lang.annotation.Retention;

@Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
public @interface Location {
	String location();
	String NORTH = BorderLayout.NORTH;
	String SOUTH = BorderLayout.SOUTH;
	String EAST = BorderLayout.EAST;
	String WEST = BorderLayout.WEST;
	String CENTER = BorderLayout.CENTER;

}
