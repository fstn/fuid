package org.fuid.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

import org.fuid.Session;
import org.fuid.annotation.Tab;
import org.fuid.event.FuidEvent;
import org.fuid.event.FuidEventType;
import org.fuid.event.FuidListener;
import org.fuid.exception.FuidException;
import org.fuid.exception.FuildExceptionType;
import org.fuid.structure.FuidViewClass;

public class MainWindow extends JFrame implements FuidListener {

	private static final long serialVersionUID = -3782830317938551432L;
	private Map<String, FuidPanel> panels;
	private JComponent panel;

	public MainWindow() {
		super();
		Session.getInstance().addListener(this);
		panel = new JPanel();
		this.add(panel);
		panel.setLayout(new BorderLayout());

		panels = new HashMap<String, FuidPanel>();
		this.getContentPane().setBackground(Color.BLACK);

		this.addFuidPanel(new FuidPanel(), BorderLayout.NORTH);
		this.addFuidPanel(new FuidPanel(), BorderLayout.EAST);
		this.addFuidPanel(new FuidPanel(), BorderLayout.WEST);
		this.addFuidPanel(new FuidPanel(), BorderLayout.CENTER);
		this.addFuidPanel(new FuidPanel(), BorderLayout.SOUTH);

	}

	public final void addFuidPanel(final Component comp,
			final Object constraints) {
		if (comp.getClass().getCanonicalName()
				.equals(FuidPanel.class.getName())) {
			panels.put((String) constraints, (FuidPanel) comp);
		}
		panel.add(comp, constraints);
	}

	public final void add(final FuidViewClass fuidViewClass)
			throws InstantiationException, IllegalAccessException,
			FuidException {
		JComponent viewClass = (JComponent) fuidViewClass.getViewInstance();
		String location = fuidViewClass.getLocation().location();
		if (panels.containsKey(location)) {
			if (fuidViewClass.hasTab()) {
				Tab tab = fuidViewClass.getTab();
				panels.get(location).addTab(viewClass, tab);
			} else {
				panels.get(location).addComponent(viewClass);
			}
		} else {
			throw new FuidException("Bad location: " + location,
					FuildExceptionType.MISCONFIGURED);
		}
		this.pack();
	}

	public void onEvent(FuidEvent event) {
		if (event.getType().equals(FuidEventType.REPACK)) {
			this.pack();
		}

	}

	public void remove(FuidViewClass fuidViewClass) {
		JComponent viewClass;
		try {
			viewClass = (JComponent) fuidViewClass.getViewInstance();
			String location = fuidViewClass.getLocation().location();
			if (panels.containsKey(location)) {
				if (viewClass != null) {
					panels.get(location).remove(viewClass);
					viewClass = null;
				}
			} else {
				throw new FuidException("Bad location: " + location,
						FuildExceptionType.MISCONFIGURED);
			}
		} catch (FuidException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void clean() {
		Session.getInstance().removeListener(this);

	}

}
