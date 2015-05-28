package Logic;

public class MailRecord {
	private double totalWeight;
	private double totalVolume;
	private int totalCount;
	private String destination;
	
	public MailRecord (String destination){
		this.destination = destination;
		totalWeight=0.0;
		totalVolume=0.0;
		totalCount=0;
	}
	
	public void incrementWeight(double w){
		totalWeight += w;
	}
	public void incrementCount(){
		totalCount++;
	}
	public void incrementVolume(double v){
		totalWeight += v;
	}
	
	public String getDestination(){
		return destination;
	}
	public double getTotalWeight(){
		return totalWeight;
	}
	public double getTotalVolume(){
		return totalVolume;
	}
	public int getTotalCount(){
		return totalCount;
	}
}
