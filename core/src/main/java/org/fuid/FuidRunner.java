package org.fuid;

import java.awt.GridLayout;
import java.io.File;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JPanel;

import org.fuid.annotation.CloseOn;
import org.fuid.annotation.Location;
import org.fuid.annotation.OpenOn;
import org.fuid.builder.FuidViewClassBuilder;
import org.fuid.event.FuidEventType;
import org.fuid.exception.FuidException;
import org.fuid.exception.FuildExceptionType;
import org.fuid.structure.FuidViewClass;
import org.fuid.view.MainWindow;
import org.fuid.view.design.ViewElement;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

public class FuidRunner {
	private Map<String, List<FuidViewClass>> openEventList;
	private Map<String, List<FuidViewClass>> closeEventList;

	public FuidRunner() {
		openEventList = new HashMap<String, List<FuidViewClass>>();
		closeEventList = new HashMap<String, List<FuidViewClass>>();
	}

	public final void loadConfig() {
		loadConfig("META-INF/fuid.xml");
	}

	@SuppressWarnings({ "unchecked", "rawtypes", "unused" })
	public final void loadConfig(final String fileName) {
		SAXBuilder builder = new SAXBuilder();
		File xmlFile = new File(fileName);

		try {

			Document document = (Document) builder.build(xmlFile);
			Element rootNode = document.getRootElement();
			Element viewsNode = rootNode.getChild("views");
			List<Element> list = viewsNode.getChildren("view");
			for (Element node : list) {
				String viewClassName = node.getAttributeValue("class");
				if (viewClassName != "null") {

					FuidViewClass fuidViewClass = FuidViewClassBuilder
							.getInstance().build(viewClassName);
					OpenOn openOn = fuidViewClass.getOpenOn();
					CloseOn closeOn = fuidViewClass.getCloseOn();
					if (!openEventList.containsKey(openOn.event())) {
						openEventList.put(openOn.event(),
								new ArrayList<FuidViewClass>());
					}
					if (!closeEventList.containsKey(closeOn.event())) {
						closeEventList.put(closeOn.event(),
								new ArrayList<FuidViewClass>());
					}
					if (openOn != null) {
						openEventList.get(openOn.event()).add(fuidViewClass);
					} else {
						throw new FuidException("must have a @OpenOn",
								FuildExceptionType.MISCONFIGURED);
					}
					if (closeOn != null) {
						closeEventList.get(closeOn.event()).add(fuidViewClass);
					} else {
						throw new FuidException("must have a @CloseOn",
								FuildExceptionType.MISCONFIGURED);
					}
				}

			}

		} catch (IOException io) {
			System.out.println(io.getMessage());
		} catch (JDOMException jdomex) {
			System.out.println(jdomex.getMessage());
		} catch (FuidException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public final void run() {
		try {
			List<FuidViewClass> fuidClassesToOpen = openEventList
					.get(FuidEventType.INIT);
			MainWindow mainWindow = new MainWindow();
			mainWindow.setLayout(new GridLayout());
			mainWindow.setVisible(true);
			for (FuidViewClass fuidViewClass : fuidClassesToOpen) {
				mainWindow.add(fuidViewClass);
				System.out.println(fuidViewClass.toString());
			}
			mainWindow.pack();
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
