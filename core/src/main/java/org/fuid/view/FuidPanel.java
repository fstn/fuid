package org.fuid.view;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JPanel;

public class FuidPanel extends JPanel {

	public FuidPanel() {
		super();
		this.setLayout(new GridBagLayout());
	}

	public Component add(Component comp) {

		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		super.add(comp, c);
		return comp;
	}

}
