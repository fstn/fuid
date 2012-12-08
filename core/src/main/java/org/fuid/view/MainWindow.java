package org.fuid.view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.fuid.annotation.Location;
import org.fuid.exception.FuidException;
import org.fuid.exception.FuildExceptionType;
import org.fuid.structure.FuidViewClass;

public class MainWindow extends JFrame {

	private static final long serialVersionUID = -3782830317938551432L;
	private Map<String, FuidPanel> panels;

	public MainWindow() {
		super();
		this.setLayout(new BorderLayout());
		panels = new HashMap<String, FuidPanel>();
		this.add(new FuidPanel(), BorderLayout.NORTH);
		this.add(new FuidPanel(), BorderLayout.EAST);
		this.add(new FuidPanel(), BorderLayout.WEST);
		this.add(new FuidPanel(), BorderLayout.CENTER);
		this.add(new FuidPanel(), BorderLayout.SOUTH);
	}

	@Override
	public final void add(final Component comp, final Object constraints) {
		if (comp.getClass().getCanonicalName().equals(FuidPanel.class.getName())) {
			panels.put((String) constraints, (FuidPanel) comp);
		}
		super.add(comp, constraints);
	}

	public final void add(final FuidViewClass fuidViewClass)
			throws InstantiationException, IllegalAccessException,
			FuidException {
		JPanel viewClass = (JPanel) fuidViewClass.getViewClass().newInstance();
		String location = fuidViewClass.getLocation().location();
		if (panels.containsKey(location)) {
			panels.get(location).add(viewClass);
		} else {
			throw new FuidException("Bad location: " + location,
					FuildExceptionType.MISCONFIGURED);
		}

	}

}
