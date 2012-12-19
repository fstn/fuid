package org.fuid.builder;

import java.lang.annotation.Annotation;
import java.util.ArrayList;

import org.fuid.annotation.CloseOn;
import org.fuid.annotation.Location;
import org.fuid.annotation.OpenOn;
import org.fuid.exception.FuidException;
import org.fuid.exception.FuildExceptionType;
import org.fuid.structure.FuidViewClass;

public class FuidViewClassBuilder {
private static FuidViewClassBuilder instance=new FuidViewClassBuilder();

private FuidViewClassBuilder() {
	super();
	// TODO Auto-generated constructor stub
}

public static FuidViewClassBuilder getInstance(){
	return instance;
}
public FuidViewClass  build(String viewClassName) throws ClassNotFoundException{
	CloseOn closeOn = null;
	OpenOn openOn = null;
	FuidViewClass fuidViewClass = new FuidViewClass();

	Class viewClass = Class.forName(viewClassName);
	fuidViewClass.setViewClass(viewClass);
	System.out.println(viewClass);
	Annotation[] annotations = viewClass.getAnnotations();
	Location location = (Location) viewClass
			.getAnnotation(Location.class);
	System.out.println(location.location());
	System.out.println(location.index());
	fuidViewClass.setLocation(location);
	openOn = (OpenOn) viewClass.getAnnotation(OpenOn.class);
	System.out.println(openOn.event());
	fuidViewClass.setOpenOn(openOn);
	closeOn = (CloseOn) viewClass.getAnnotation(CloseOn.class);
	System.out.println(closeOn.event());
	fuidViewClass.setCloseOn(closeOn);
	return fuidViewClass;
}

}
