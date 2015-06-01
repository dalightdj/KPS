package Logic;

import java.util.ArrayList;

import travelGraph.Path.DayOfWeek;
import travelGraph.Path.TransportType;
import travelGraph.TravelGraph;
import travelGraph.TravelGraph.Priority;
import Logger.Event;
import Logger.Logger;
import Logger.MDEvent;
import Logger.TCUEvent;

public class KPS {

	private double revenueTotal;
	private double expendetureTotal;
	private int deliveriesCount;
	private ArrayList<LocalDistributionCentre> LDCs;
	private ArrayList<Event> events;
	private ArrayList<Journey> journeys;
	private Logger logger;
	private TravelGraph travelGraph;
	//private graph sructure???

	public KPS(){
		revenueTotal = 0.0;
		expendetureTotal = 0.0;
		deliveriesCount = 0;
		LDCs = new ArrayList<LocalDistributionCentre>();
		events = new ArrayList<Event>();
		journeys = new ArrayList<Journey>();
		logger = new Logger(); //Does not affect the XML file.
		loadEvents(); //WILL BE NULL AT THE START UNLESS WE HAVE A DEFAULT XML FILE
		travelGraph = new TravelGraph();
	}

	public void discontinueRoute(int reference){
		//XML event
		//return a bool ?
		// take out path from the GRAPH STRUCTURE with the given reference ?
	}

	/**
	 *
	 * Deals with a mail Delivery event appropriately (and creates the new MDEvent object
	 * if given a createNew boolean as true
	 *
	 * @param createNew Is this a new mailDelivery event being entered from the GUI. (always true for Ewan/Neal)
	 */
	public void mailDelivery(String date, String destination, String origin, int weight, int volume, Priority priority, boolean createNew){

		//Create an event object, add it to the arrayList and write it in XML using the Logger.
		if(createNew){
			MDEvent event = new MDEvent(date, destination, origin, priority, weight, volume);
			events.add(event);
			logger.addEvent(event);
		}
		//Update appropriate MailRecord
		boolean recordFound = false;
		for(LocalDistributionCentre ldc:LDCs){
			if(ldc.getName().equals(origin)){
				MailRecord mr = ldc.getRecord(destination);
				mr.incrementCount();mr.incrementVolume(volume);mr.incrementWeight(weight);
				recordFound = true;
			}
		}
		//Or create a MailRecord if one doesn't exist for this journey
		if(recordFound == false){
			for(LocalDistributionCentre ldc:LDCs){
				if(ldc.getName().equals(origin)){
					MailRecord rec = new MailRecord(destination);
					rec.incrementCount();rec.incrementVolume(volume);rec.incrementWeight(weight);
					ldc.getAllRecords().add(rec);
				}
			}
		}
		//TODO: make boolean ?
	}

	public void loadEvents(){
		if(logger.readXML() == null){
			return;
		}
		events = logger.readXML();
		for (Event e:events){
			if(e instanceof MDEvent ){
				mailDelivery(((MDEvent) e).getDate(), ((MDEvent) e).getDestination(), ((MDEvent) e).getOrigin(),
						((MDEvent) e).getWeight(), ((MDEvent) e).getVolume(), ((MDEvent) e).getPriority(), false);
			}

		}
	}


	public void priceUpdate(){
		//write XML
		//return bool ?
		//change in the GRAPH STRUCTURE
	}

	public void costUpdate(String company, String destination, String origin, TransportType type, String date,
			int weightCost, int volumeCost, int maxWeight, int maxVolume, int duration, int frequency, boolean createNew){

		//Create an event object, add it to the arrayList and write it in XML using the Logger.
		if(createNew){
			TCUEvent event = new TCUEvent(company, destination, origin, type, date,
					weightCost, volumeCost, maxWeight, maxVolume, duration, frequency);
			events.add(event);
			logger.addEvent(event);
		}

		travelGraph.updatePathPrice(origin, destination, company, priority, weightCost, volumeCost, getDay(date), frequency, duration);




		//write XML
		//make boolean
		//update JOURNEY ? ??
	}

	/**Takes in the date String of format "EEE HH:mm" and returns the day as type enum DayOfWeek.
	 *
	 * @param date, the string in the form "EEE HH:mm" where EEE is the name of the day (first 3 characters)
	 * HH is hours and mm is minutes. Only concerned about the name of the day here.
	 *
	 * @return enum DayOfWeek
	 */
	private DayOfWeek getDay(String date) {
		// TODO Auto-generated method stub
		String day = date.substring(0, 3);
		if(day.equalsIgnoreCase("Mon")){
			return DayOfWeek.MONDAY;
		}
		else if(day.equalsIgnoreCase("Tue")){
			return DayOfWeek.TUESDAY;
		}
		else if(day.equalsIgnoreCase("Wed")){
			return DayOfWeek.WEDNESDAY;
		}
		else if(day.equalsIgnoreCase("Thu")){
			return DayOfWeek.THURSDAY;
		}
		else if(day.equalsIgnoreCase("Fri")){
			return DayOfWeek.FRIDAY;
		}
		else if(day.equalsIgnoreCase("Sat")){
			return DayOfWeek.SATURDAY;
		}
		else {
			return DayOfWeek.SUNDAY;
		}
	}


}
