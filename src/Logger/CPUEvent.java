package Logger;

public class CPUEvent extends Event {
	
	private static final String eventType = "CustomerPriceUpdate";
	private String desTo;
	private String desFrom;
	private String priority;
	private int weightCost;
	private int volumeCost;
	
	
	public CPUEvent(String desTo, String desFrom, String priority, int weightCost, int volumeCost) {
		super();
		this.desTo = desTo;
		this.desFrom = desFrom;
		this.priority = priority;
		this.weightCost = weightCost;
		this.volumeCost = volumeCost;
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
