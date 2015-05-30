package Logic;

import java.util.ArrayList;

import travelGraph.Path;

public class Journey {
	private String destination;
	private String origin;
	private int priority;
	private Double weightPrice;
	private Double volumePrice;
	private ArrayList<Path> usedPaths;
//	private double averageDeliveryTime;
//	private double averagePrice;
//	private double deliveryTimeSum;
//	private double priceSum;
//	private int count;

	public Journey(String destination, String origin, int priority, 
			double weightPrice, double volumePrice, ArrayList<Path> usedPaths) {
		this.destination = destination;
		this.origin = origin;
		this.priority = priority;
		this.weightPrice = weightPrice;
		this.volumePrice = volumePrice;
		this.usedPaths = usedPaths;
//		this.averageDeliveryTime = averageDeliveryTime;
//		this.averagePrice = averagePrice;
	}

	public String getDestination() {
		return destination;
	}
	public String getOrigin() {
		return origin;
	}
	public int getPriority() {
		return priority;
	}
//	public double getAverageDeliveryTime() {
//		return averageDeliveryTime;
//	}
//	public double getAveragePrice() {
//		return averagePrice;
//	}

}
