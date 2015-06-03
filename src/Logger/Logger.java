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

import travelGraph.Path.DayOfWeek;
import travelGraph.Path.TransportType;
import travelGraph.TravelGraph.Priority;




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
	private void addTDEvent(Element parentElement, Event event){


		TDEvent tdEvent = (TDEvent) event;

		Element TDENode = new Element("TDEvent");
		TDENode.addContent(new Element("company").setText(tdEvent.getCompany()));
		TDENode.addContent(new Element("origin").setText(tdEvent.getOrigin()));
		TDENode.addContent(new Element("destination").setText(tdEvent.getDestination()));
		TDENode.addContent(new Element("type").setText(tdEvent.getType().toString()));
		TDENode.addContent(new Element("dow").setText(tdEvent.getDow().toString()));

		parentElement.addContent(TDENode);

	}


	/**
	 * Create and add a CPUEvent to the xml file
	 * @param parentElement
	 * @param event
	 */
	private void addCPUEvent(Element parentElement, Event event){

		CPUEvent cpuEvent = (CPUEvent) event;

		Element cpuNode = new Element("CPUEvent");
		cpuNode.addContent(new Element("origin").setText(cpuEvent.getDesFrom()));
		cpuNode.addContent(new Element("destination").setText(cpuEvent.getDesTo()));
		cpuNode.addContent(new Element("priority").setText(cpuEvent.getPriority().toString()));
		cpuNode.addContent(new Element("weightPrice").setText(Float.toString(cpuEvent.getWeightPrice())));
		cpuNode.addContent(new Element("volumePrice").setText(Float.toString(cpuEvent.getVolumePrice())));
		cpuNode.addContent(new Element("dow").setText(cpuEvent.getDow().toString()));

		parentElement.addContent(cpuNode);				

	}


	/**
	 * Create and add a TCUEvent to the xml file
	 * @param parentElement
	 * @param event
	 */
	private void addTCUEvent(Element parentElement, Event event){

		TCUEvent tcuEvent = (TCUEvent) event;

		Element tcuNode = new Element("TCUEvent");
		tcuNode.addContent(new Element("company").setText(tcuEvent.getCompany()));
		tcuNode.addContent(new Element("origin").setText(tcuEvent.getOrigin()));
		tcuNode.addContent(new Element("destination").setText(tcuEvent.getDestination()));
		tcuNode.addContent(new Element("type").setText(tcuEvent.getType().toString()));
		tcuNode.addContent(new Element("weightCost").setText(Float.toString(tcuEvent.getWeightCost())));
		tcuNode.addContent(new Element("volumeCost").setText(Float.toString(tcuEvent.getVolumeCost())));
		tcuNode.addContent(new Element("dow").setText(tcuEvent.getDow().toString()));
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
	private void addMDEvent(Element parentElement, Event event){

		MDEvent mdEvent = (MDEvent) event;

		Element mdNode = new Element("MDEvent");
		mdNode.addContent(new Element("origin").setText(mdEvent.getOrigin()));
		mdNode.addContent(new Element("destination").setText(mdEvent.getDestination()));
		mdNode.addContent(new Element("priority").setText(mdEvent.getPriority().toString()));
		mdNode.addContent(new Element("day").setText(mdEvent.getDate()));
		mdNode.addContent(new Element("weight").setText(Integer.toString(mdEvent.getWeight())));
		mdNode.addContent(new Element("volume").setText(Integer.toString(mdEvent.getVolume())));
		
		parentElement.addContent(mdNode);

	}

	public ArrayList<Event> readXML(){

		
		
		ArrayList<Event> events = new ArrayList<Event>();

		SAXBuilder saxBuilder = new SAXBuilder();
		File xmlFile = new File("eventsData.xml");
		
		if (!xmlFile.exists()) {
			return new ArrayList<Event>();
		}
		
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
							getType(ele.getChildText("type")),
							getDow(ele.getChildText("dow")));
					
					events.add(tdEvent);
					break;
				case "MDEvent":
					
					MDEvent mdEvent = new MDEvent(ele.getChildText("day"),
							ele.getChildText("destination"), 
							ele.getChildText("origin"), 
							getPriority(ele.getChildText("priority")), 
							Integer.parseInt(ele.getChildText("weight")), 
							Integer.parseInt(ele.getChildText("volume")));
					
					events.add(mdEvent);
					break;
				case "CPUEvent":
					CPUEvent cpuEvent = new CPUEvent(ele.getChildText("destination"), 
							ele.getChildText("origin"), 
							getPriority(ele.getChildText("priority")), 
							Float.parseFloat(ele.getChildText("weightPrice")), 
							Float.parseFloat(ele.getChildText("volumePrice")),
							getDow(ele.getChildText("dow")));
					
					events.add(cpuEvent);
					break;
				case "TCUEvent":
					TCUEvent tcuEvent = new TCUEvent(ele.getChildText("company"), 
							ele.getChildText("destination"), 
							ele.getChildText("origin"), 
							getType(ele.getChildText("type")),
							getDow(ele.getChildText("dow")),
							Float.parseFloat(ele.getChildText("weightCost")), 
							Float.parseFloat(ele.getChildText("volumeCost")), 
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
	
	
	public Priority getPriority(String priority){
		
		Priority p = null;
		
	if(priority.equals("AIR")){
		p = Priority.AIR;
	}else{
		p = Priority.STANDARD;
	}
		
		return p;	
	}
	
	
	public TransportType getType(String type){
		
		TransportType t = null;
		
		switch (type) {
		case "AIR":
			t = TransportType.AIR;
			break;
		case "LAND":
			t = TransportType.LAND;
		default:
			t = TransportType.SEA;
			break;
		}
		
		return t;
		
	}
	
	public DayOfWeek getDow(String dow){
		
		DayOfWeek d = null;
		
		switch (dow) {
		case "MONDAY":
			d = DayOfWeek.MONDAY;
			break;
		case "TUESDAY":
			d = DayOfWeek.TUESDAY;
			break;
		case "WEDNESDAY":
			d = DayOfWeek.WEDNESDAY;
			break;
		case "THURSDAY":
			d = DayOfWeek.THURSDAY;
			break;
		case "FRIDAY":
			d = DayOfWeek.FRIDAY;
			break;

		default:
			break;
		}
		
		return d;
		
	}
	
	

	/*
	public static void main(String[] args) {
		Logger logger = new Logger();
		//TDEvent tdEvent = new  TDEvent("ddddd", "ddddd", "ddddddd", TransportType.AIR, DayOfWeek.FRIDAY);
		//MDEvent mdEvent = new MDEvent("Monday", "England", "New Zealand", "International Air", 10, 14);
		TCUEvent tcuEvent = new TCUEvent("ddsfd", "dfsdf", "dsfsdf", TransportType.AIR, DayOfWeek.FRIDAY, 12, 12, 12, 12, 12, 12);
		//CPUEvent cpuEvent = new CPUEvent("India", "New Zealand", "International Air", 34, 43);
		//logger.addEvent(tdEvent);
		//logger.addEvent(cpuEvent);
		//logger.addEvent(mdEvent);
		logger.addEvent(tcuEvent);
		ArrayList<Event> e = logger.readXML();
		System.out.println("sda");
	}*/

}
