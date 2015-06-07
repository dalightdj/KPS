package Logic;

import java.io.File;
import java.util.ArrayList;

import travelGraph.Location;
import travelGraph.Path.DayOfWeek;
import travelGraph.Path.TransportType;
import travelGraph.Path;
import travelGraph.TravelGraph;
import travelGraph.TravelGraph.Priority;
import travelGraph.TravelGraph.UnknownLocationException;
import Logger.CPUEvent;
import Logger.Event;
import Logger.Logger;
import Logger.MDEvent;
import Logger.TCUEvent;
import Logger.TDEvent;

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
		travelGraph = new TravelGraph();
		revenueTotal = 0.0;
		expendetureTotal = 0.0;
		deliveriesCount = 0;
		LDCs = new ArrayList<LocalDistributionCentre>();
		events = new ArrayList<Event>();
		journeys = new ArrayList<Journey>();
		logger = new Logger(); //Does not affect the XML file.
		loadEvents(); //WILL BE NULL AT THE START UNLESS WE HAVE A DEFAULT XML FILE
		//System.out.println("saff");
	}

	/**
	 * Returns an array of strings that represent the destinations that a mail delivery can
	 * be sent from the current origin
	 * @param origin
	 * @return
	 */
	public ArrayList<String> getDestinations(String origin){
		ArrayList<String> dests = new ArrayList<String>();
		for(Journey j: journeys){
			if(j.getOrigin().equals(origin)){
				dests.add(j.getDestination());
			}
		}
		return dests;
	}

	/**
	 * Returns an array of strings that represent the origins that a mail delivery can
	 * be sent from.
	 * @return
	 */
	public ArrayList<String> getOrigins(){
		ArrayList<String> origns = new ArrayList<String>();
		for(Journey j: journeys){
			if(!origns.contains(j.getOrigin())){
				origns.add(j.getOrigin());
			}
		}
		return origns;
	}
	
	public ArrayList<String> getCompanies(DayOfWeek dow){
		ArrayList<String> companyNames = new ArrayList<String>();
		for(Path p: travelGraph.getAllPaths()){
			if (p.getDay().equals(dow) && !companyNames.contains(p.getCompany())){
				companyNames.add(p.getCompany());
			}
		}
		return companyNames;
	}
	
	public ArrayList<String> getOriginsOfCompany(DayOfWeek dow, String company){
		ArrayList<String> originsOfCompany = new ArrayList<String>();
		for(Path p:travelGraph.getAllPaths()){
			if(p.getDay().equals(dow) && p.getCompany().equals(company) && !originsOfCompany.contains(p.getOrigin().getName())){
				originsOfCompany.add(p.getOrigin().getName());
			}
		}
		return originsOfCompany;
	}
	
	public ArrayList<String> getDestinationsOfOriginOfCompany(DayOfWeek dow, String company, String origin){
		ArrayList<String> destsOfOriginOfCompany = new ArrayList<String>();
		for(Path p:travelGraph.getAllPaths()){
			if(p.getDay().equals(dow) && p.getCompany().equals(company) && p.getOrigin().equals(origin) && 
					!destsOfOriginOfCompany.contains(p.getDestination().getName())){
				destsOfOriginOfCompany.add(p.getDestination().getName());
			}
		}
		return destsOfOriginOfCompany;
	}

	/**
	 * @throws NothingToDeleteException
	 *
	 */
	public ArrayList<String> getJourneyDestinations(String origin, Priority priority) throws NothingToDeleteException{
		ArrayList<String> stringReachables = new ArrayList<String>();
		ArrayList<Location> reachables;
		try {
			reachables = travelGraph.allReachableLocations(origin, priority);
			for(Location l: reachables){
				stringReachables.add(l.getName());
			}

		} catch (UnknownLocationException e) {
			throw new NothingToDeleteException();
		}
		return stringReachables;

	}

	public class NothingToDeleteException extends Exception{

	}

	public ArrayList<Journey> getCriticals(){
		System.out.println("getCriticals called");
		ArrayList<Journey> criticals = new ArrayList<Journey>();
		for(Journey j:journeys){
			if(j.isCritical()){
				criticals.add(j);
			}
		}
		return criticals;
	}

	public ArrayList<String> getJourneyOrigins(){
		ArrayList<String> graphNodes = new ArrayList<String>();
		ArrayList<Location> locs = travelGraph.getAllOriginLocations();
		for(Location location:locs){
			graphNodes.add(location.getName());
		}
		return graphNodes;
	}

	public void loadEvents(){
		if(logger.readXML().size() == 0){
			//File xmlFile = new File("eventsData.xml");
			return;
		}
		events = logger.readXML();
		//System.out.println("in loadEvents stop");
		for (Event e:events){
			if(e instanceof MDEvent ){
				mailDelivery(((MDEvent) e).getDate(), ((MDEvent) e).getDestination(), ((MDEvent) e).getOrigin(),
						((MDEvent) e).getWeight(), ((MDEvent) e).getVolume(), ((MDEvent) e).getPriority(), false);
			}
			else if(e instanceof TCUEvent){
				costUpdate(((TCUEvent) e).getCompany(), ((TCUEvent) e).getDestination(), ((TCUEvent) e).getOrigin(), ((TCUEvent) e).getType(),
						((TCUEvent) e).getDow(), ((TCUEvent) e).getWeightCost(), ((TCUEvent) e).getVolumeCost(), ((TCUEvent) e).getMaxWeight(),
						((TCUEvent) e).getMaxVolume(), ((TCUEvent) e).getDuration(), ((TCUEvent) e).getFrequency(),false);
			}
			else if(e instanceof TDEvent){
				discontinueRoute(((TDEvent)e).getOrigin(), ((TDEvent)e).getDestination(), ((TDEvent)e).getCompany(), ((TDEvent)e).getType(),
						((TDEvent)e).getDow(), false);
			}
			else{
				priceUpdate(((CPUEvent)e).getDestination(), ((CPUEvent)e).getOrigin(), ((CPUEvent)e).getPriority(), ((CPUEvent)e).getWeightPrice(),
						((CPUEvent)e).getVolumePrice(), ((CPUEvent)e).getDow(), false);
			}

		}
	}

	/**
	 * Deals with a discontinue transport event appropriately. (and creates the new TDEvent object/ writes the XML
	 * if the given createNew boolean is true)
	 * @param origin
	 * @param destination
	 * @param company
	 * @param type
	 * @param day
	 * @param createNew
	 */
	public void discontinueRoute(String origin, String destination, String company, Path.TransportType type, DayOfWeek day, boolean createNew){

		// return a bool ?
		// take out path from the GRAPH STRUCTURE with the given reference ?

		//If a new XML needs to be created, create it. Also add a new TD event to the array of events

		//System.out.println("discontinue Route method called.");

		if(createNew){
			TDEvent event = new TDEvent(company, destination, origin, type, day);
			events.add(event);
			logger.addEvent(event);

		}

		ArrayList<Journey> newJourneys = new ArrayList<Journey>();
		for(Journey j:journeys){
			if(j.checkPath(origin, destination, company, type, day)){
				j.disactivate();
			}
		}
		for(Journey j:journeys){
			if(j.isActive()){
				newJourneys.add(j);
			}
		}
		journeys = newJourneys;

		travelGraph.removePath(origin, destination, company, type, day);
	}

	public void costUpdate(String company, String destination, String origin, TransportType type, DayOfWeek dow,
			float weightCost, float volumeCost, int maxWeight, int maxVolume, int duration, int frequency, boolean createNew){

		//System.out.println("costUpdate method called.");

		//If a new XML needs to be created, create it. Also add a new CU event to the array of events
		if(createNew){
			TCUEvent event = new TCUEvent(company, destination, origin, type, dow,
					weightCost, volumeCost, maxWeight, maxVolume, duration, frequency);
			events.add(event);
			logger.addEvent(event);
		}

		travelGraph.updatePathPrice(origin, destination, company, type, weightCost, volumeCost, maxWeight, maxVolume, dow, frequency, duration);


		//write XML
		//make boolean
		//update JOURNEY ? ??
	}

	/**
	 *
	 * Deals with a mail Delivery event appropriately (and creates the new MDEvent object
	 * if given a createNew boolean as true)
	 *
	 * @param createNew Is this a new mailDelivery event being entered from the GUI. (always true for Ewan/Neal)
	 */
	public boolean mailDelivery(String date, String destination, String origin, int weight, int volume, Priority priority, boolean createNew){

		//Checks if the given origin/destination/day/priority given relates to a journey offered by KPS.
		//If it does, adjust the total revenue and expendeture accordingly. Else return false.

		//System.out.println("mailDeliver method called.");
		//System.out.println("getDay(date): " + getDay(date));
		Journey usedJourney = getJourney(origin, destination, priority, getDay(date));
		if(usedJourney == null){
			return false;
		}
		else{
			expendetureTotal += usedJourney.getCost(weight, volume);
			revenueTotal += usedJourney.getPrice(weight, volume);
			deliveriesCount++;
		}

		//If a new XML needs to be created, create it. Also add a new MD event to the array of events

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
				return true;
			}
		}
		//Or create a MailRecord if one doesn't exist for this journey
		if(recordFound == false){
			for(LocalDistributionCentre ldc:LDCs){
				if(ldc.getName().equals(origin)){
					MailRecord rec = new MailRecord(destination);
					rec.incrementCount();rec.incrementVolume(volume);rec.incrementWeight(weight);
					ldc.getAllRecords().add(rec);
					return true;
				}
			}
		}

		//Should never reach this return statement (as it either updates or creates a new mailRecord if the journey exists.
		return false;


		//TODO: make boolean ?
	}


	/**
	 * Updates the Journey class that it corresponds to, if it doesn't exist create a new Journey class.
	 * Also cretes the event and
	 * @param destination of the journey being updated
	 * @param origin of the journey being updated
	 * @param priority of the journey being updated
	 * @param weightPrice the new price of weight per gram
	 * @param volumePrice the new price of volume per cm^3
	 * @param dow the day of week of the journey being updated
	 * @param createNew weather to create a new XML item or not (if it is brand new entered via GUI)
	 */
	public void priceUpdate(String destination, String origin, Priority priority, float weightPrice, float volumePrice, DayOfWeek dow, boolean createNew){
		//write XML
		//return bool ?
		//change in the GRAPH STRUCTURE

		//System.out.println("price update method called.");

		ArrayList<Path> pathings;
		try {
			pathings = travelGraph.getRoute(origin, destination, priority);
			if(pathings == null){
				return;
			}



		//If a new XML needs to be created, create it. Also add a new CPU event to the array of events
		if(createNew){
			CPUEvent event = new CPUEvent(destination, origin, priority, weightPrice, volumePrice, dow);
			events.add(event);
			logger.addEvent(event);
		}

		//checks if the Journey exists, if so update it, otherwise create a new Journey object.
		Journey jrny = getJourney(origin,destination,priority, dow);
		if(jrny==null){
			Journey j = new Journey(destination, origin, priority, weightPrice, volumePrice,pathings, dow);
			journeys.add(j);
		}
		else{
			for(Journey j:journeys){

				if(jrny==j){
					j.setVolumePrice(volumePrice);
					j.setWeightPrice(weightPrice);
					return;
				}
			}
		}
		} catch (UnknownLocationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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

	private Journey getJourney(String origin, String destination, Priority priority, DayOfWeek dow){
		Journey result = null;
		for(Journey j:journeys){
			if(j.getOrigin().equals(origin) && j.getDestination().equals(destination) &&
					j.getPriority().equals(priority) && j.getDow().equals(dow)){
				result = j;
			}
		}
		return result;
	}

	public double getExpenses(){
		return expendetureTotal;
	}

	public double getRevenue(){
		return revenueTotal;
	}

	public int getNoDeliveries(){
		System.out.println("noDeliveries: " + deliveriesCount);
		return deliveriesCount;
	}

	public int getNoEvents(){
		return events.size();
	}

	public ArrayList<Event> getEvents() {
		return events;
	}

	public TravelGraph getTravelGraph() {
		return travelGraph;
	}
}
