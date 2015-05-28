package Logic;

public class Delivery {
	
	//IS THIS CLASS NECESSARY? OR JUST PUT THIS INFO IN MAIL RECORD ? WHEN WILL WE USE THIS CLASS
	
	private double weight;
	private double volume;
	//1= international air, 2= international standard, 3= domestic
	private int priority;
	private Time entryTime;
	
	public Delivery(double w, double v, int p, Time entryTime){
		weight = w;
		volume = v;
		priority = p;
		this.entryTime = entryTime;
	}
	
}
