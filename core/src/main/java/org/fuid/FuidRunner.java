package org.fuid;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

public class FuidRunner {

	public FuidRunner() {

	}

	public void run() {
		run("META-INF/fuid.xml");
	}

	public void run(String fileName) {
		SAXBuilder builder = new SAXBuilder();
		File xmlFile = new File(fileName);

		try {

			Document document = (Document) builder.build(xmlFile);
			Element rootNode = document.getRootElement();
			List list = rootNode.getChildren("views");

			for (int i = 0; i < list.size(); i++) {

				Element node = (Element) list.get(i);

				System.out
						.println("First Name : " + node.getChildText("class"));

			}

		} catch (IOException io) {
			System.out.println(io.getMessage());
		} catch (JDOMException jdomex) {
			System.out.println(jdomex.getMessage());
		}
	}

}
