package Logic;

public class MailRecord {
	private float totalWeight;
	private float totalVolume;
	private int totalCount;
	private String destination;

	public MailRecord (String destination){
		this.destination = destination;
		totalWeight=0;
		totalVolume=0;
		totalCount=0;
	}

	public void incrementWeight(float w){
		totalWeight += w;
	}
	public void incrementCount(){
		totalCount++;
	}
	public void incrementVolume(float v){
		totalWeight += v;
	}

	public String getDestination(){
		return destination;
	}
	public float getTotalWeight(){
		return totalWeight;
	}
	public float getTotalVolume(){
		return totalVolume;
	}
	public int getTotalCount(){
		return totalCount;
	}
}
