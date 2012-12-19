package org.fuid;

import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.fuid.annotation.CloseOn;
import org.fuid.annotation.OpenOn;
import org.fuid.controller.Controller;
import org.fuid.event.FuidEvent;
import org.fuid.event.FuidEventType;
import org.fuid.exception.FuidException;
import org.fuid.structure.FuidViewClass;
import org.fuid.view.MainWindow;

public class FuidViewManager {
	private Map<String, List<FuidViewClass>> openEventList;
	private Map<String, List<FuidViewClass>> closeEventList;
	private MainWindow mainWindow;
	private static FuidViewManager instance = new FuidViewManager();

	private FuidViewManager() {
		openEventList = new HashMap<String, List<FuidViewClass>>();
		closeEventList = new HashMap<String, List<FuidViewClass>>();
		mainWindow = new MainWindow();
		mainWindow.setLayout(new GridLayout());
		mainWindow.setVisible(true);

	}

	public static FuidViewManager getInstance() {
		return instance;
	}

	public void setInstance(FuidViewManager instance) {
		this.instance = instance;
	}

	public final void register(FuidViewClass fuidViewClass)
			throws FuidException {
		OpenOn openOn = fuidViewClass.getOpenOn();
		CloseOn closeOn = fuidViewClass.getCloseOn();
		if (!openEventList.containsKey(openOn.event())) {
			for (int i = 0; i < openOn.event().length; i++) {
				String eventType = openOn.event()[i];
				if (!openEventList.containsKey(eventType)) {
					openEventList
							.put(eventType, new ArrayList<FuidViewClass>());
				}
				openEventList.get(eventType).add(fuidViewClass);
			}
		}
		if (!closeEventList.containsKey(closeOn.event())) {
			for (int i = 0; i < closeOn.event().length; i++) {
				String eventType = closeOn.event()[i];
				if (!closeEventList.containsKey(eventType)) {
					closeEventList.put(eventType,
							new ArrayList<FuidViewClass>());
				}
				closeEventList.get(eventType).add(fuidViewClass);
			}
		}
	}

	public void fireEvent(FuidEvent event) {
		if (event.getType().equals(FuidEventType.CLOSE)) {
			System.exit(0);
		}
		if (!event.getType().equals(FuidEventType.REPACK)) {
			ArrayList<FuidViewClass> toOpen = null;
			ArrayList<FuidViewClass> toClose = null;
			if (openEventList.containsKey(event.getType())) {
				toOpen = (ArrayList<FuidViewClass>) openEventList.get(event
						.getType());
			}
			if (closeEventList.containsKey(event.getType())) {
				toClose = (ArrayList<FuidViewClass>) closeEventList.get(event
						.getType());
			}
			updateView(toOpen, toClose);
		}
	}

	public void updateView(ArrayList<FuidViewClass> toOpen,
			ArrayList<FuidViewClass> toClose) {
		if (toOpen != null) {
			for (FuidViewClass fuidViewClass : toOpen) {
				if (fuidViewClass.getControllerClass() != null) {
					System.out.println("instanciation du controller: "
							+ fuidViewClass.getControllerClass().getName());
					Object controller = fuidViewClass.getControllerInstance();
				}
				try {
					mainWindow.add(fuidViewClass);
				} catch (InstantiationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (FuidException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		if (toClose != null) {
			for (FuidViewClass fuidViewClass : toClose) {
				if (fuidViewClass.getControllerClass() != null) {
					System.out.println("suppression du controller: "
							+ fuidViewClass.getControllerClass().getName());
					Controller controller = fuidViewClass
							.getControllerInstance();
					fuidViewClass.setControllerInstance(null);
					controller.clean();
					controller = null;
				}
				mainWindow.remove(fuidViewClass);
			}
		}

		Session.getInstance().fireEvent(
				new FuidEvent(FuidEventType.REPACK, null));
	}
}
