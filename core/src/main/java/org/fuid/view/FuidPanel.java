package org.fuid.view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.fuid.FuidColor;
import org.fuid.Session;
import org.fuid.event.FuidResizeEvent;
import org.fuid.structure.FuidViewClass;
import org.fuid.util.FuidSessionConstante;

public class FuidPanel extends JPanel implements FuidViewElement {

	Logger						logger		= Logger.getLogger(FuidPanel.class.getCanonicalName());
	private FuidPanelContent	content;
	private List<FuidViewClass>	children	= null;

	public FuidPanel() {
		super();
		this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
		this.setBackground(FuidColor.BACKGROUND);
		children = new ArrayList<FuidViewClass>();
		content = new FuidPanelContentImpl(children);
		this.add((Component) content);
	}

	public void addComponent(FuidViewClass fuidViewClass) {
		children.add(fuidViewClass);
	}

	public void remove(FuidViewClass fuidViewClass) {
		children.remove(fuidViewClass);
	}

	public void updateView() {
		if (children.size()>0 && children.get(0).hasTab() && !(content instanceof FuidTabPanelContentImpl)){
			content=new FuidTabPanelContentImpl(children);
			this.add((Component) content,BorderLayout.CENTER);			
		}
		content.updateView();
	}

	public void onResize(FuidResizeEvent fuidResizeEvent) {
		for (FuidViewClass fuidViewClass : children) {
			FuidViewElement viewElement = fuidViewClass.getViewElementInstance();
			viewElement.onResize(fuidResizeEvent);
		}
	}
}
