package org.fuid.event;


public interface FuidListener {
	public void onEvent(FuidEvent event) ;
	public void clean();
}
