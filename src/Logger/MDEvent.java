package Logger;

import travelGraph.TravelGraph.Priority;

/**
 * This class represents a Mail Delivery event 
 * @author rahulnaidu
 *
 */
public class MDEvent extends Event {
	
	private String date;
	private String destination;
	private String origin;
	private Priority priority;
	private int weight;
	private int volume;
	
	
	public MDEvent(String date, String destination, String origin, Priority priority, int weight, int volume) {
		super();
		this.date = date;
		this.destination = destination;
		this.origin = origin;
		this.priority = priority;
		this.weight = weight;
		this.volume = volume;
	}


	public String getDate() {
		return date;
	}


	public void setDate(String date) {
		this.date = date;
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


	public int getWeight() {
		return weight;
	}


	public void setWeight(int weight) {
		this.weight = weight;
	}


	public int getVolume() {
		return volume;
	}


	public void setVolume(int volume) {
		this.volume = volume;
	}


	public static String getEventtype() {
		return eventType;
	}

	
	
	
}
