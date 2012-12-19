package org.fuid.structure;

import java.awt.Component;

import org.fuid.annotation.CloseOn;
import org.fuid.annotation.Location;
import org.fuid.annotation.OpenOn;
import org.fuid.annotation.Tab;
import org.fuid.controller.Controller;

public class FuidViewClass {
	private Class viewClass;
	private Component viewInstance;
	private Class controllerClass;
	private Controller controllerInstance;
	private Location location;
	private Tab tab;
	private OpenOn openOn;
	private CloseOn closeOn;

	@SuppressWarnings("rawtypes")
	public final Class getViewClass() {
		return viewClass;
	}

	@SuppressWarnings("rawtypes")
	public final void setViewClass(final Class viewClassParam) {
		this.viewClass = viewClassParam;
	}

	public final Location getLocation() {
		return location;
	}

	public final void setLocation(final Location locationParam) {
		this.location = locationParam;
	}

	public final OpenOn getOpenOn() {
		return openOn;
	}

	public final void setOpenOn(final OpenOn openOnParam) {
		this.openOn = openOnParam;
	}

	public final CloseOn getCloseOn() {
		return closeOn;
	}

	public final void setCloseOn(final CloseOn closeOnParam) {
		this.closeOn = closeOnParam;
	}

	public final Class getControllerClass() {
		return controllerClass;
	}

	public final void setControllerClass(Class controllerClass) {
		this.controllerClass = controllerClass;
	}

	public Component getViewInstance() {
		if(viewInstance==null){
			try {
				viewInstance=(Component) viewClass.newInstance();
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return viewInstance;
	}

	public void setViewInstance(Component viewInstance) {
		this.viewInstance = viewInstance;
	}

	public Controller getControllerInstance() {
		if(controllerInstance==null){
			try {
				controllerInstance=(Controller) controllerClass.newInstance();
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return controllerInstance;
	}

	public void setControllerInstance(Controller controllerInstance) {
		this.controllerInstance = controllerInstance;
	}

	public Tab getTab() {
		return tab;
	}

	public void setTab(Tab tab) {
		this.tab = tab;
	}
	public boolean hasTab(){
		return this.tab!=null;
	}
	
	

}
