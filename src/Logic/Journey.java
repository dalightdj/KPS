package Logic;

import java.util.ArrayList;

import travelGraph.Path;
import travelGraph.Path.DayOfWeek;
import travelGraph.Path.ExceededVolumeException;
import travelGraph.Path.OverweightException;
import travelGraph.Path.TransportType;
import travelGraph.TravelGraph.Priority;

public class Journey {
	private String destination;
	private String origin;
	private Priority priority;
	private float weightPrice;
	private float volumePrice;
	private ArrayList<Path> usedPaths;
	private DayOfWeek dow;
//	private double averageDeliveryTime;
//	private double averagePrice;
//	private double deliveryTimeSum;
//	private double priceSum;
//	private int count;

	public Journey(String destination, String origin, Priority priority,
			float weightPrice, float volumePrice, ArrayList<Path> usedPaths, DayOfWeek dow) {
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

	public float getCost(int weight, int volume) {
		float total = 0;
		for(Path p: usedPaths){
				total += p.calcCost(weight, volume);
		}
		return total;
	}

	public boolean checkPath(String origin, String destination, String commpany, TransportType type, DayOfWeek dow){
		for(Path p: usedPaths){
			if(p.getOrigin().getCity().equals(origin) && p.getDestination().getCity().equals(destination) && p.getCompany().equals(commpany) &&
					p.getTransportType()==type && p.getDay().equals(dow)){
				return true;
			}
		}
		return false;
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
	public void setWeightPrice(float wp){
		weightPrice = wp;
	}
	public void setVolumePrice(float vp){
		volumePrice = vp;
	}
//	public double getAverageDeliveryTime() {
//		return averageDeliveryTime;
//	}
//	public double getAveragePrice() {
//		return averagePrice;
//	}

}
