package org.fuid.structure;

import org.fuid.annotation.CloseOn;
import org.fuid.annotation.Location;
import org.fuid.annotation.OpenOn;

public class FuidViewClass {
	private Class viewClass;
	private Location location;
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

}
