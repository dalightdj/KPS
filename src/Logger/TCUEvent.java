package Logger;

import travelGraph.Path.DayOfWeek;
import travelGraph.Path.TransportType;

public class TCUEvent extends Event {
	
	private static final String eventType = "TransportCostUpdate";
	private String company;
	private String destination;
	private String origin;
	private TransportType type;
	private int weightCost;
	private int volumeCost;
	private int maxWeight;
	private int maxVolume;
	private int duration;
	private int frequency;
	private DayOfWeek dow;



	public TCUEvent(String company, String destination, String origin, TransportType type, DayOfWeek dow, int weightCost, int volumeCost, int maxWeight, int maxVolume, int duration, int frequency) {
		super();
		this.company = company;
		this.destination = destination;
		this.origin = origin;
		this.type = type;
		this.weightCost = weightCost;
		this.volumeCost = volumeCost;
		this.maxWeight = maxWeight;
		this.maxVolume = maxVolume;
		this.duration = duration;
		this.frequency = frequency;
		this.dow = dow;
	}
	
	
	
	public static String getEventtype() {
		return eventType;
	}

	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
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
	public TransportType getType() {
		return type;
	}
	public void setType(TransportType type) {
		this.type = type;
	}
	public int getWeightCost() {
		return weightCost;
	}
	public void setWeightCost(int weightCost) {
		this.weightCost = weightCost;
	}
	public int getVolumeCost() {
		return volumeCost;
	}
	public void setVolumeCost(int volumeCost) {
		this.volumeCost = volumeCost;
	}
	public int getMaxWeight() {
		return maxWeight;
	}
	public void setMaxWeight(int maxWeight) {
		this.maxWeight = maxWeight;
	}
	public int getMaxVolume() {
		return maxVolume;
	}
	public void setMaxVolume(int maxVolume) {
		this.maxVolume = maxVolume;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	public int getFrequency() {
		return frequency;
	}
	public void setFrequency(int frequency) {
		this.frequency = frequency;
	}
	public DayOfWeek getDow() {
		return dow;
	}

	public void setDow(DayOfWeek dow) {
		this.dow = dow;
	}
	
}
