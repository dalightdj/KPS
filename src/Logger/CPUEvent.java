package Logger;

public class CPUEvent extends Event {
	
	private static final String eventType = "CustomerPriceUpdate";
	private String destination;
	private String origin;
	private String priority;
	private int weightCost;
	private int volumeCost;
	
	
	public CPUEvent(String destination, String origin, String priority, int weightCost, int volumeCost) {
		this.destination = destination;
		this.origin = origin;
		this.priority = priority;
		this.weightCost = weightCost;
		this.volumeCost = volumeCost;
	}


	public String getDesTo() {
		return destination;
	}


	public void setDesTo(String destination) {
		this.destination = destination;
	}


	public String getDesFrom() {
		return origin;
	}


	public void setDesFrom(String origin) {
		this.origin = origin;
	}


	public String getPriority() {
		return priority;
	}


	public void setPriority(String priority) {
		this.priority = priority;
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


	public static String getEventtype() {
		return eventType;
	}

	
	
}
