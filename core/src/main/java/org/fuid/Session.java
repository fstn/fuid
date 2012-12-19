package org.fuid;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.fuid.event.FuidEvent;
import org.fuid.event.FuidListener;

public class Session {
	private static Session instance = new Session();

	private List<FuidListener> fuidListeners;
	private boolean inUse = false;
	private Map<String,Object> current= new HashMap<String, Object>();

	private Session() {
		fuidListeners = new ArrayList<FuidListener>();
	}

	public static Session getInstance() {
		return instance;
	}

	public  void addListener(FuidListener listener) {
		fuidListeners.add(listener);
	}

	public  void removeListener(FuidListener listener) {
		fuidListeners.remove(listener);
	}

	public  void fireEvent(FuidEvent event) {
		 List<FuidListener> tmpFuidListeners =new ArrayList<FuidListener>();
		for (FuidListener listener : fuidListeners) {
			tmpFuidListeners.add(listener);
		}
		for (FuidListener listener : tmpFuidListeners) {
			listener.onEvent(event);
		}
		FuidViewManager.getInstance().fireEvent(event);
		tmpFuidListeners.clear();
	}

	public Map<String, Object> getCurrent() {
		return current;
	}

	public void setCurrent(Map<String, Object> current) {
		this.current = current;
	}
	

}
