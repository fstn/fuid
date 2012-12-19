package org.fuid.event;

public class FuidEvent {
	protected String type =null;
	protected Object arg;

	public FuidEvent(final String type, final Object arg) {
		this.type=type;
		this.arg = arg;
	}

	public Object getArg() {
		return arg;
	}

	public void setArg(Object arg) {
		this.arg = arg;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}


}
