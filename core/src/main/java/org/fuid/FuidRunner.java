package org.fuid;

import java.awt.GridLayout;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.fuid.annotation.CloseOn;
import org.fuid.annotation.OpenOn;
import org.fuid.builder.FuidViewClassBuilder;
import org.fuid.event.FuidEvent;
import org.fuid.event.FuidEventType;
import org.fuid.exception.FuidException;
import org.fuid.exception.FuildExceptionType;
import org.fuid.structure.FuidViewClass;
import org.fuid.view.MainWindow;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

public class FuidRunner {

	public FuidRunner() {
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
					FuidViewManager.getInstance().register(fuidViewClass);
					Class controller = fuidViewClass.getControllerClass();

				}

			}

		} catch (IOException io) {
			System.out.println(io.getMessage());
		} catch (JDOMException jdomex) {
			System.out.println(jdomex.getMessage());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FuidException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public final void run() {
		Session.getInstance().fireEvent(new FuidEvent(FuidEventType.INIT,null));
	}
}
