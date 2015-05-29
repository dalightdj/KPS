package Logger;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.XMLOutputter;




public class Logger {


	public Logger(){

	}

/**
 * Given a Event the method adds the Event to the xml file
 * @param event
 */
	public void addEvent(Event event) {

		File xmlFile = new File("eventsData.xml");

		//Checking if the file exists or not
		if (xmlFile.exists()) {

			try {
				SAXBuilder builder = new SAXBuilder();
				Document doc = (Document) builder.build(xmlFile);
				Element rootElement = doc.getRootElement();

				if(event instanceof TDEvent){
					addTDEvent(rootElement, event);
				}else if (event instanceof CPUEvent){
					addCPUEvent(rootElement, event);
				}else if (event instanceof TCUEvent){
					addTCUEvent(rootElement, event);
				}else if (event instanceof MDEvent){
					addMDEvent(rootElement, event);			

				}

				XMLOutputter xmlOutputter = new XMLOutputter();
				try {
					xmlOutputter.output(doc, new FileWriter("eventsData.xml"));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}



			} catch (JDOMException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}




		} else {

			Element events = new Element("events");
			Document doc = new Document(events);

			if(event instanceof TDEvent){
				addTDEvent(events, event);
			}else if (event instanceof CPUEvent){
				addCPUEvent(events, event);
			}else if (event instanceof TCUEvent){
				addTCUEvent(events, event);
			}else if (event instanceof MDEvent){
				addMDEvent(events, event);			
			}

			XMLOutputter xmlOutputter = new XMLOutputter();
			try {
				xmlOutputter.output(doc, new FileWriter("eventsData.xml"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}


		}

	}

	/**
	 * Create and add a TDEvent to the xml file
	 * @param parentElement
	 * @param event
	 */
	public void addTDEvent(Element parentElement, Event event){


		TDEvent tdEvent = (TDEvent) event;

		Element TDENode = new Element("TDEvent");
		TDENode.addContent(new Element("company").setText(tdEvent.getCompany()));
		TDENode.addContent(new Element("origin").setText(tdEvent.getDesFrom()));
		TDENode.addContent(new Element("destination").setText(tdEvent.getDesTo()));
		TDENode.addContent(new Element("type").setText(tdEvent.getType()));

		parentElement.addContent(TDENode);

	}


	/**
	 * Create and add a CPUEvent to the xml file
	 * @param parentElement
	 * @param event
	 */
	public void addCPUEvent(Element parentElement, Event event){

		CPUEvent cpuEvent = (CPUEvent) event;

		Element cpuNode = new Element("CPUEvent");
		cpuNode.addContent(new Element("origin").setText(cpuEvent.getDesFrom()));
		cpuNode.addContent(new Element("destination").setText(cpuEvent.getDesTo()));
		cpuNode.addContent(new Element("priority").setText(cpuEvent.getPriority()));
		cpuNode.addContent(new Element("weightCost").setText(Integer.toString(cpuEvent.getWeightCost())));
		cpuNode.addContent(new Element("volumeCost").setText(Integer.toString(cpuEvent.getVolumeCost())));

		parentElement.addContent(cpuNode);				

	}


	/**
	 * Create and add a TCUEvent to the xml file
	 * @param parentElement
	 * @param event
	 */
	public void addTCUEvent(Element parentElement, Event event){

		TCUEvent tcuEvent = (TCUEvent) event;

		Element tcuNode = new Element("TCUEvent");
		tcuNode.addContent(new Element("company").setText(tcuEvent.getCompany()));
		tcuNode.addContent(new Element("origin").setText(tcuEvent.getDesFrom()));
		tcuNode.addContent(new Element("destination").setText(tcuEvent.getDesTo()));
		tcuNode.addContent(new Element("type").setText(tcuEvent.getType()));
		tcuNode.addContent(new Element("weightCost").setText(Integer.toString(tcuEvent.getWeightCost())));
		tcuNode.addContent(new Element("volumeCost").setText(Integer.toString(tcuEvent.getVolumeCost())));
		tcuNode.addContent(new Element("day").setText(tcuEvent.getDay()));
		tcuNode.addContent(new Element("maxWeight").setText(Integer.toString(tcuEvent.getMaxWeight())));
		tcuNode.addContent(new Element("maxVolume").setText(Integer.toString(tcuEvent.getMaxVolume())));
		tcuNode.addContent(new Element("duration").setText(Integer.toString(tcuEvent.getDuration())));
		tcuNode.addContent(new Element("frequency").setText(Integer.toString(tcuEvent.getFrequency())));

		parentElement.addContent(tcuNode);		
	}


	/**
	 * Create and add a MDEvent to the xml file
	 * @param parentElement
	 * @param event
	 */
	public void addMDEvent(Element parentElement, Event event){

		MDEvent mdEvent = (MDEvent) event;

		Element mdNode = new Element("MDEvent");
		mdNode.addContent(new Element("origin").setText(mdEvent.getDesFrom()));
		mdNode.addContent(new Element("destination").setText(mdEvent.getDesTo()));
		mdNode.addContent(new Element("priority").setText(mdEvent.getPriority()));
		mdNode.addContent(new Element("day").setText(mdEvent.getDay()));
		mdNode.addContent(new Element("weight").setText(Integer.toString(mdEvent.getWeight())));
		mdNode.addContent(new Element("volume").setText(Integer.toString(mdEvent.getVolume())));

		parentElement.addContent(mdNode);

	}

	public ArrayList<Event> readXML(){

		ArrayList<Event> events = new ArrayList<Event>();

		SAXBuilder saxBuilder = new SAXBuilder();
		File xmlFile = new File("eventsData.xml");

		try {
			Document doc = (Document) saxBuilder.build(xmlFile);
			Element rootElement = doc.getRootElement();

			ArrayList<Element> eventElements = new ArrayList<Element>();

			//Gather all the elements of the xml
			eventElements.addAll(rootElement.getChildren());

			for(Element ele : eventElements){

				switch (ele.getName()) {
				case "TDEvent":
					TDEvent tdEvent = new TDEvent(ele.getChildText("company"),
							ele.getChildText("destination"),
							ele.getChildText("origin"), 
							ele.getChildText("type"));
					
					events.add(tdEvent);
					break;
				case "MDEvent":
					MDEvent mdEvent = new MDEvent(ele.getChildText("day"),
							ele.getChildText("destination"), 
							ele.getChildText("origin"), 
							ele.getChildText("priority"), 
							Integer.parseInt(ele.getChildText("weight")), 
							Integer.parseInt(ele.getChildText("volume")));
					
					events.add(mdEvent);
					break;
				case "CPUEvent":
					CPUEvent cpuEvent = new CPUEvent(ele.getChildText("destination"), 
							ele.getChildText("origin"), 
							ele.getChildText("priority"), 
							Integer.parseInt(ele.getChildText("weightCost")), 
							Integer.parseInt(ele.getChildText("volumeCost")));
					
					events.add(cpuEvent);
					break;
				case "TCUEvent":
					TCUEvent tcuEvent = new TCUEvent(ele.getChildText("company"), 
							ele.getChildText("destination"), 
							ele.getChildText("origin"), 
							ele.getChildText("type"), 
							ele.getChildText("day"), 
							Integer.parseInt(ele.getChildText("weightCost")), 
									Integer.parseInt(ele.getChildText("volumeCost")), 
									Integer.parseInt(ele.getChildText("maxWeight")), 
									Integer.parseInt(ele.getChildText("maxVolume")), 
									Integer.parseInt(ele.getChildText("duration")), 
									Integer.parseInt(ele.getChildText("frequency")));
					
					events.add(tcuEvent);
				default:
					break;
					
				}

			}



		} catch (JDOMException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		return events;

	}


	public static void main(String[] args) {
		Logger logger = new Logger();
		TDEvent tdEvent = new TDEvent("NX Post", "Japan", "New Zealand", "something");
		MDEvent mdEvent = new MDEvent("Monday", "England", "New Zealand", "International Air", 10, 14);
		TCUEvent tcuEvent = new TCUEvent("company y", "England", "New Zealand", "Air", "Sunday", 100, 123, 200, 200, 12, 321);
		CPUEvent cpuEvent = new CPUEvent("India", "New Zealand", "International Air", 34, 43);
		logger.addEvent(tdEvent);
		logger.addEvent(cpuEvent);
		logger.addEvent(mdEvent);
		logger.addEvent(tcuEvent);
		ArrayList<Event> e = logger.readXML();
		System.out.println("sda");
	}

}
