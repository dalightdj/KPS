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
	private double hoursAfterMidnightFromMondayMorning;
	private int frequency;
	private double duration;
	private double cost;
		
	public enum TransportType{
		AIR, SEA, LAND;
	}
	
	
	public Path(Location origin, Location dest, String company, TransportType type){
		this.origin = origin;
		this.dest = dest;
		this.company = company;
		this.type = type;
	}
	
	
	/**
	 * Update 'price per gram'
	 * @param price The new price per gram
	 */
	public void updatePPG(double price){
		ppGram = price;
	}
	
	/**
	 * Update 'price per centimeter cubed'
	 * @param price
	 */
	public void updatePPCC(double price){
		ppCmCubed = price;
	}
}
