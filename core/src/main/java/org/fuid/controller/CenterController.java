package org.fuid.controller;

import java.io.File;
import java.util.Iterator;
import java.util.List;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;

public class CenterController implements Controller {

	public CenterController() {
		Document document = null;
		Element racine;
		String filename="src/META-INF/viewConfiguration.xml";
	      SAXBuilder sxb = new SAXBuilder();
	      try
	      {
	         document = sxb.build(new File(filename));
		      racine = document.getRootElement();
		      List<Element> views = racine.getChild("fuid").getChild("views").getChildren();
		      for(Element element : views){
		    	  String viewClass=element.getAttributeValue("class");
		    	  
		      }
		      //On crée un Iterator sur notre liste
		      Iterator i = views.iterator();
		      while(i.hasNext())
		      {
		         //On recrée l'Element courant à chaque tour de boucle afin de
		         //pouvoir utiliser les méthodes propres aux Element comme :
		         //selectionner un noeud fils, modifier du texte, etc...
		         Element courant = (Element)i.next();
		         //On affiche le nom de l'element courant
		         System.out.println("courant "+courant.getAttributeValue("class"));
		      }
		   
			 
	      }
	      catch(Exception e){
	    	  
	    	  e.printStackTrace();
	      }
	}

}
