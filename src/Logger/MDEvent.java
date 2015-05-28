package Logger;

public class MDEvent extends Event {
	
	private static final String eventType = "MailDelivery";
	private String day;
	private String desTo;
	private String desFrom;
	private String priority;
	private int weight;
	private int volume;
	
	
	public MDEvent(String day, String desTo, String desFrom, String priority, int weight, int volume) {
		super();
		this.day = day;
		this.desTo = desTo;
		this.desFrom = desFrom;
		this.priority = priority;
		this.weight = weight;
		this.volume = volume;
	}


	public String getDay() {
		return day;
	}


	public void setDay(String day) {
		this.day = day;
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
