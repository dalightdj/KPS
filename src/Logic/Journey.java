package Logic;

import java.util.ArrayList;

import travelGraph.Path;
import travelGraph.Path.DayOfWeek;
import travelGraph.TravelGraph.Priority;

public class Journey {
	private String destination;
	private String origin;
	private Priority priority;
	private Double weightPrice;
	private Double volumePrice;
	private ArrayList<Path> usedPaths;
	private DayOfWeek dow;
//	private double averageDeliveryTime;
//	private double averagePrice;
//	private double deliveryTimeSum;
//	private double priceSum;
//	private int count;

	public Journey(String destination, String origin, Priority priority,
			double weightPrice, double volumePrice, ArrayList<Path> usedPaths, DayOfWeek dow) {
		this.destination = destination;
		this.origin = origin;
		this.priority = priority;
		this.weightPrice = weightPrice;
		this.volumePrice = volumePrice;
		this.usedPaths = usedPaths;
		this.dow = dow;
//		this.averageDeliveryTime = averageDeliveryTime;
//		this.averagePrice = averagePrice;
	}

	public Double getPrice(int weight, int volume){
		double price = weightPrice*weight + volumePrice*volume;
		price = Math.round(price * 100);
		return price/100;
	}

	public Double getCost(int weight, int volume){
		for(Path p: usedPaths){
			p.getCostToKPS(weight, volume);
		}
	}

//	public boolean checkJourney(String destination, String origin, Priority priority, ArrayList<Path> paths, DayOfWeek dow){
//		if(this.destination.equals(destination) && this.origin.equals(origin) && this.priority.equals(priority)
//				&& this.usedPaths.equals(o))
//	}

	public String getDestination() {
		return destination;
	}
	public String getOrigin() {
		return origin;
	}
	public Priority getPriority() {
		return priority;
	}
	public DayOfWeek getDow(){
		return dow;
	}
	public ArrayList<Path> getPaths(){
		return usedPaths;
	}
//	public double getAverageDeliveryTime() {
//		return averageDeliveryTime;
//	}
//	public double getAveragePrice() {
//		return averagePrice;
//	}

}
