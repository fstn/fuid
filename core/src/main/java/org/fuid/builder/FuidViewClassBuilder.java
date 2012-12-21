package org.fuid.builder;

import java.lang.annotation.Annotation;

import org.fuid.annotation.CloseOn;
import org.fuid.annotation.Controller;
import org.fuid.annotation.Location;
import org.fuid.annotation.OpenOn;
import org.fuid.annotation.Tab;
import org.fuid.structure.FuidViewClass;

public class FuidViewClassBuilder {
	private static FuidViewClassBuilder instance = new FuidViewClassBuilder();

	private FuidViewClassBuilder() {
		super();
	}

	public static FuidViewClassBuilder getInstance() {
		return instance;
	}

	public FuidViewClass build(String viewClassName)
			throws ClassNotFoundException {
		CloseOn closeOn = null;
		OpenOn openOn = null;
		Controller controller = null;
		Tab tab=null;
		FuidViewClass fuidViewClass = new FuidViewClass();

		Class viewClass = Class.forName(viewClassName);
		fuidViewClass.setViewClass(viewClass);
		Annotation[] annotations = viewClass.getAnnotations();
		Location location = (Location) viewClass.getAnnotation(Location.class);
		fuidViewClass.setLocation(location);
		openOn = (OpenOn) viewClass.getAnnotation(OpenOn.class);
		fuidViewClass.setOpenOn(openOn);
		closeOn = (CloseOn) viewClass.getAnnotation(CloseOn.class);
		if (viewClass.getAnnotation(Controller.class) != null) {
			controller = (Controller) viewClass.getAnnotation(Controller.class);
			fuidViewClass.setControllerClass(controller.name());
		}

		if (viewClass.getAnnotation(Tab.class) != null) {
			tab = (Tab) viewClass.getAnnotation(Tab.class);
			fuidViewClass.setTab(tab);
		}
		fuidViewClass.setCloseOn(closeOn);
		return fuidViewClass;
	}

}
