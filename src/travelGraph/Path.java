package travelGraph;

/**
 * A class the represents a path taken from one location to another.
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
		
	public enum TransportType{
		AIR, SEA, LAND;
	}
	
	public enum DayOfWeek{
		MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY;
	}
	
	public Path(Location origin, Location dest, String company, TransportType type, float ppg, float ppcc, Path.DayOfWeek dayOfWeek, int frequency, int duration){
		this.origin = origin;
		this.dest = dest;
		this.company = company;
		this.type = type;
	}
	
	
	/**
	 * Update 'price per gram'
	 * @param price The new price per gram
	 */
	protected void updatePPG(double price){
		ppGram = price;
	}
	
	/**
	 * Update 'price per centimeter cubed'
	 * @param price
	 */
	protected void updatePPCC(double price){
		ppCmCubed = price;
	}
	
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
}
