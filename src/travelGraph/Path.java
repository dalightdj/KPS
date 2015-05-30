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
	
	private double ppGram;//price per gram
	private double ppCmCubed;//price per cm cubed
	private DayOfWeek day;//private double hoursAfterMidnightFromMondayMorning;
	private int frequency;//hours between each departure
	private double duration;//duration of trip in hours
	private double cost;
		
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
	public Path(Location origin, Location dest, String company, TransportType type, float ppg, float ppcc, Path.DayOfWeek dayOfWeek, int frequency, int duration){
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
	protected void updatePPG(double price){
		ppGram = price;
		updateCost();
	}
	
	/**
	 * Update 'price per centimeter cubed'
	 * @param price
	 */
	protected void updatePPCC(double price){
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
	 * @param ppg The new price per gram. -1 to keep the previous price per gram
	 * @param ppcc The new price per cubic centimeter
	 * @param dayOfWeek The new day of the week that this path operates
	 * @param frequency The new frequency between departures in hours
	 * @param duration The new duration of time it takes to travel from origin to destination in hours
	 * @return The updated path
	 */
	protected Path update(float ppg, float ppcc, DayOfWeek dayOfWeek, int frequency, int duration){
		this.ppGram = ppg;
		this.ppCmCubed = ppcc;
		this.day = dayOfWeek;
		this.frequency = frequency;
		this.duration = duration;
		
		return this;
	}
	
	protected Location getDestination(){
		return dest;
	}
	
	protected String getCompany(){
		return company;
	}
	
	protected TransportType getTransportType(){
		return type;
	}
	
	protected double getCost(){
		return cost;
	}
}
