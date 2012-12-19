package org.fuid.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import org.fuid.FuidColor;
import org.fuid.annotation.Tab;

public class FuidPanel extends JPanel {
	private JComponent content;

	public FuidPanel() {
		super();
		this.setLayout(new BorderLayout());
		this.setBackground(FuidColor.BACKGROUND);
		content = new JPanel();
		this.add(content);
	}

	public JComponent addComponent(JComponent comp) {
		content.add(comp, BorderLayout.CENTER);
		return comp;
	}

	public void remove(JComponent comp) {
		super.remove(comp);
	}

	public JComponent addTab(JComponent comp, Tab tab) {
		if (!(content instanceof JTabbedPane)) {
			this.remove(content);
			content = new JTabbedPane();
			super.add(content);
		}
		GridBagConstraints c = new GridBagConstraints();
		((JTabbedPane) content).addTab(tab.title(), comp);
		return comp;

	}
}
