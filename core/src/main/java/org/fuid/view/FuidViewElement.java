package org.fuid.view;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import org.fuid.event.FuidResizeEvent;

public interface  FuidViewElement {

	void onResize(FuidResizeEvent fuidResizeEvent);
}