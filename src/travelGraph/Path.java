package travelGraph;

/**
 * A class the represents a path taken from one location to another, an origin to a destination.
 * @author TeAka
 *
 */
public class Path {
	
	private final Location origin;
	private final Location destination;//destination
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
		this.destination = dest;
		this.company = company;
		this.type = type;
		this.ppGram = ppg;
		this.ppCmCubed = ppcc;
		this.maxWeight = maxWeight;
		this.maxVolume = maxVolume;
		this.day = dayOfWeek;
		this.frequency = frequency;
		this.duration = duration;
		updateCost();
	}
	
	/**
	 * Calculates the cost of a package delivery on this path
	 * @param weight The weight of the package in grams
	 * @param volume The volume of the package in cubic centimeters
	 * @return The cost to deliver a package with the given weight and volume on this path. Returns -1 if it the given weight or volume exceeds the maximum weight or volume allowed on this path
	 * @throws OverweightException If the weight is greater than the maximum possible weight for this Path
	 * @throws ExceededVolumeException  If the volume is greater than the maximum possible volume for this Path
	 */
	public float calcCost(int weight, int volume) throws OverweightException, ExceededVolumeException{
		if(weight>maxWeight) {throw new OverweightException();}
		if(volume>maxVolume) {throw new  ExceededVolumeException();}
		return (weight*ppGram) + (volume*ppCmCubed);
	}
	
	public class OverweightException extends Exception{
		
	}
	
	public class ExceededVolumeException extends Exception{
		
	}
	
	/**
	 * Update 'price per gram'
	 * @param price The new price per gram
	 */
	protected void updatePPG(float price){
		ppGram = price;
		updateCost();
	}
	
	/**
	 * Update 'price per centimeter cubed'
	 * @param price
	 */
	protected void updatePPCC(float price){
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
	protected Path update(float ppg, float ppcc, int maxWeight, int maxVol, int frequency, int duration){
		if(ppg>0) {this.ppGram = ppg;}
		if(ppcc>0) {this.ppCmCubed = ppcc;}
		if(maxWeight>0) {this.maxWeight = maxWeight;}
		if(maxVol>0) {this.maxVolume = maxVol;}
		if(frequency>0) {this.frequency = frequency;}
		if(duration>0) {this.duration = duration;}
		
		return this;
	}
	
	
	/**
	 * 
	 * @return The weight of this Path. For Dijkstra's algorithm
	 */
	public float getWeight(){
		return cost;
	}

	public Location getOrigin() {
		return origin;
	}

	public Location getDestination() {
		return destination;
	}

	public String getCompany() {
		return company;
	}

	public TransportType getTransportType() {
		return type;
	}

	/**
	 * 
	 * @return price per gram
	 */
	public float getPPG() {
		return ppGram;
	}

	/**
	 * 
	 * @return price per centimeter squared
	 */
	public float getPPCC() {
		return ppCmCubed;
	}

	public int getMaxWeight() {
		return maxWeight;
	}

	public int getMaxVolume() {
		return maxVolume;
	}

	public DayOfWeek getDay() {
		return day;
	}

	public int getFrequency() {
		return frequency;
	}

	public int getDuration() {
		return duration;
	}
	
	
	public String toString(){
		return company + " '" + type + "' travel from '" + origin.getName() + "' to '" + destination.getName() + "'. Price per gram = '" + ppGram + "', price per centimeter cubed = '" + ppCmCubed
				+ "'. Max weight = '" + maxWeight + "', Max volume = '" + maxVolume + "'. Travels on '" + day + "', '" + frequency + "' times a day, duration = '" + duration + "' hours";
	}
		
}
