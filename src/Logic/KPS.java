package Logic;

import java.util.ArrayList;

import Logger.Event;
import Logger.Logger;
import Logger.MDEvent;

public class KPS {

	private double revenueTotal;
	private double expendetureTotal;
	private int deliveriesCount;
	private ArrayList<LocalDistributionCentre> LDCs;
	private ArrayList<Event> events;
	private ArrayList<Journey> journeys;
	private Logger logger;
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
	public void mailDelivery(String date, String destination, String origin, int weight, int volume, String priority, boolean createNew){

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
				for(MailRecord mr:ldc.getAllRecords()){
					if(mr.getDestination().equals(destination)){
						recordFound = true;
						mr.incrementCount();mr.incrementVolume(volume);mr.incrementWeight(weight);
					}
				}
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

	public void costUpdate(){
		//write XML
		//make boolean
		//update JOURNEY ? ??
	}


}
