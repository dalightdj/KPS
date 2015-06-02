package travelGraph;

/**
 * A class the represents a path taken from one location to another, an origin to a destination.
 * @author TeAka
 *
 */
public class Path {
	
	private final Location origin;
	private final Location dest;//destination
	private final String company;
	private final TransportType type;
	
	private float ppGram;//price per gram
	private float ppCmCubed;//price per cm cubed
	private int maxWeight;
	private int maxVolume;
	private DayOfWeek day;//private double hoursAfterMidnightFromMondayMorning;
	private int frequency;//hours between each departure
	private int duration;//duration of trip in hours
	private float cost;
		
	/**
	 * The transport type of a particular path i.e. Air, Sea or Land
	 * @author TeAka
	 *
	 */
	public enum TransportType{
		AIR, SEA, LAND;
	}
	
	/**
	 * The day of the week that a particular path operates
	 * @author TeAka
	 *
	 */
	public enum DayOfWeek{
		MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY;
	}
	
	/**
	 * 
	 * @param origin Name of origin
	 * @param dest Name of destination
	 * @param company Name of the transport firm that uses this path
	 * @param type The type of transport i.e. Air, Sea or Land
	 * @param ppg Price per gram for a package
	 * @param ppcc Price per cubic centimeter for a package
	 * @param dayOfWeek The day of the week this path operates
	 * @param frequency The frequency in hours between departures
	 * @param duration The duration of time from origin to destination
	 */
	public Path(Location origin, Location dest, String company, TransportType type, float ppg, float ppcc, int maxWeight, int maxVolume, Path.DayOfWeek dayOfWeek, int frequency, int duration){
		this.origin = origin;
		this.dest = dest;
		this.company = company;
		this.type = type;
		
		updateCost();
	}
	
	
	/**
	 * Update 'price per gram'
	 * @param price The new price per gram
	 */
	public void updatePPG(float price){
		ppGram = price;
		updateCost();
	}
	
	/**
	 * Update 'price per centimeter cubed'
	 * @param price
	 */
	public void updatePPCC(float price){
		ppCmCubed = price;
		updateCost();
	}
	
	/**
	 * Updates the cost field which is used to determine the weight of this path in Dijkstra's search algorithm 
	 */
	private void updateCost(){
		cost = ppGram+ppCmCubed;
	}
	
	/**
	 * Update this path's information
	 * @param ppg The new price per gram. 0< to keep the previous price per gram
	 * @param ppcc The new price per cubic centimeter. 0< to keep the previous price per cubic centimeter
	 * @param maxWeight The new maximum weight. 0< to keep the previous maximum weight
	 * @param maxVol The new maximum volume. 0< to keep the previous maximum volume
	 * @param frequency The new frequency between departures in hours. 0< to keep the previous frequency
	 * @param duration The new duration of time it takes to travel from origin to destination in hours. 0< to keep the previous duration
	 * @return The updated path
	 */
	public Path update(float ppg, float ppcc, int maxWeight, int maxVol, int frequency, int duration){
		if(ppg<0) {this.ppGram = ppg;}
		if(ppcc<0) {this.ppCmCubed = ppcc;}
		if(maxWeight<0) {this.maxWeight = maxWeight;}
		if(maxVol<0) {this.maxVolume = maxVol;}
		if(frequency<0) {this.frequency = frequency;}
		if(duration<0) {this.duration = duration;}
		
		return this;
	}
	
	public Location getDestination(){
		return dest;
	}
	
	public String getCompany(){
		return company;
	}
	
	public TransportType getTransportType(){
		return type;
	}
	
	public DayOfWeek getDay(){
		return day;
	}
	
	public float getCost(){
		return cost;
	}
}
