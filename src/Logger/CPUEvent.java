package Logger;

import travelGraph.Path.DayOfWeek;
import travelGraph.TravelGraph.Priority;

/**
 * This is class is used to represent a customer price update event
 * @author rahulnaidu
 *
 */
public class CPUEvent extends Event {
	
	private String destination;
	private String origin;
	private Priority priority;
	private float weightPrice;
	private float volumePrice;
	private DayOfWeek dow;


	public CPUEvent(String destination, String origin, Priority priority, float weightPrice, float volumePrice, DayOfWeek dow) {
		this.destination = destination;
		this.origin = origin;
		this.priority = priority;
		this.weightPrice = weightPrice;
		this.volumePrice = volumePrice;
		this.dow = dow;
	}


	public String getDestination() {
		return destination;
	}


	public void setDestination(String destination) {
		this.destination = destination;
	}


	public String getOrigin() {
		return origin;
	}


	public void setOrigin(String origin) {
		this.origin = origin;
	}


	public Priority getPriority() {
		return priority;
	}


	public void setPriority(Priority priority) {
		this.priority = priority;
	}


	public float getWeightPrice() {
		return weightPrice;
	}


	public void setWeightPrice(int weightPrice) {
		this.weightPrice = weightPrice;
	}


	public float getVolumePrice() {
		return volumePrice;
	}


	public void setVolumePrice(int volumePrice) {
		this.volumePrice = volumePrice;
	}


	public static String getEventtype() {
		return eventType;
	}

	public DayOfWeek getDow() {
		return dow;
	}

	public void setDow(DayOfWeek dow) {
		this.dow = dow;
	}
	
}
