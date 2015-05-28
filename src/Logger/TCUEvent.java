package Logger;

public class TCUEvent extends Event {
	
	private static final String eventType = "TransportCostUpdate";
	private String company;
	private String desTo;
	private String desFrom;
	private String type;
	private String day;
	private int weightCost;
	private int volumeCost;
	private int maxWeight;
	private int maxVolume;
	private int duration;
	private int frequency;
	
	public TCUEvent(String company, String desTo, String desFrom, String type, String day, int weightCost, int volumeCost, int maxWeight, int maxVolume, int duration, int frequency) {
		this.company = company;
		this.desTo = desTo;
		this.desFrom = desFrom;
		this.type = type;
		this.day = day;
		this.weightCost = weightCost;
		this.volumeCost = volumeCost;
		this.maxWeight = maxWeight;
		this.maxVolume = maxVolume;
		this.duration = duration;
		this.frequency = frequency;
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
	public String getDesTo() {
		return desTo;
	}
	public void setDesTo(String desTo) {
		this.desTo = desTo;
	}
	public String getDesFrom() {
		return desFrom;
	}
	public void setDesFrom(String desFrom) {
		this.desFrom = desFrom;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getDay() {
		return day;
	}
	public void setDay(String day) {
		this.day = day;
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


	
}
