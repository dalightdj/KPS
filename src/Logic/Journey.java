package Logic;

public class Journey {
	private String destination;
	private String origin;
	private int priority;
	private double averageDeliveryTime;
	private double averageDeliveryCost;
	private double averagePrice;
	private double deliveryTimeSum;
	private double deliveryCostSum;
	private double priceSum;
	private int count;

	public Journey(String destination, String origin, int priority,
			double averageDeliveryTime, double averageDeliveryCost,
			double averagePrice) {
		this.destination = destination;
		this.origin = origin;
		this.priority = priority;
		this.averageDeliveryTime = averageDeliveryTime;
		this.averageDeliveryCost = averageDeliveryCost;
		this.averagePrice = averagePrice;
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
	public double getAverageDeliveryTime() {
		return averageDeliveryTime;
	}
	public double getAverageDeliveryCost() {
		return averageDeliveryCost;
	}
	public double getAveragePrice() {
		return averagePrice;
	}
}
