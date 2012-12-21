package org.fuid.view;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import org.fuid.FuidColor;
import org.fuid.FuidRunner;
import org.fuid.structure.FuidViewClass;

public class FuidPanel extends JPanel {

	Logger logger = Logger.getLogger(FuidPanel.class.getCanonicalName());
	private JComponent content;

	private List<FuidViewClass> children = null;

	public FuidPanel() {
		super();
		this.setLayout(new BorderLayout());
		this.setBackground(FuidColor.BACKGROUND);
		content = new JPanel();
		children = new ArrayList<FuidViewClass>();
		this.add(content);
	}

	public void addComponent(FuidViewClass fuidViewClass) {
		children.add(fuidViewClass);
	}

	public void remove(FuidViewClass fuidViewClass) {
		children.remove(fuidViewClass);
	}

	public void updateView() {
		if (children.size() > 0 && children.get(0).hasTab()) {
			logger.info("before order update");
			for (FuidViewClass fuidViewClass : children) {

				if (fuidViewClass.isTab()) {
					System.out.println(String.valueOf(fuidViewClass.getTab().index()));
				}
			}
			logger.info(" end before order update");
			logger.info("start update");
			Collections.sort(children);
		}
		try {
			for (FuidViewClass fuidViewClass : children) {
				if (fuidViewClass.isTab()) {
					System.out.println(String.valueOf(fuidViewClass.getTab().index()));
					if (!(content instanceof JTabbedPane)) {
						this.remove(content);
						content = new JTabbedPane();
						super.add(content);
					}
					((JTabbedPane) content).addTab(fuidViewClass.getTab()
							.title(), fuidViewClass.getViewInstance());
				} else {
					if (!(content instanceof JPanel)) {
						content = new JPanel();
						super.add(content);
					}
					content.add(fuidViewClass.getViewInstance());
				}
			}

			if (children.size() > 0 && children.get(0).hasTab()) {
				logger.info("end update");
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			e.printStackTrace();
		}
	}
}
