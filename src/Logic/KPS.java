package Logic;
import java.util.ArrayList;

import Logger.MDEvent;

public class KPS {
	private double revenueTotal;
	private double expendetureTotal;
	private int deliveriesCount;
	private ArrayList<LocalDistributionCentre> LDCs;
	//private ArrayList<Event> events;
	private ArrayList<Journey> journeys;
	//private graph sructure???
	//XML object ?? 
	
	public void discontinueRoute(int reference){
		//XML event
		//return a bool ?
		// take out path from the GRAPH STRUCTURE with the given reference ?
	}
	
	public void mailDelivery(String date, String destination, String origin, int weight, int volume, String priority){
		
		MDEvent event = new MDEvent(date, destination, origin, priority, weight, volume);
		
		//Update appropriate MailRecord
		boolean recordFound = false;
		for(LocalDistributionCentre ldc:LDCs){
			if(ldc.getName().equals(origin)){
				for(MailRecord mr:ldc.getAllRecords()){
					if(mr.getDestination().equals(destination)){
						recordFound = true;
						mr.incrementCount();mr.incrementVolume(volume);mr.incrementWeight(weight);
					}
				}
			}
		}
		//Or create a MailRecord if one doesn't exist for this journey
		if(recordFound == false){
			for(LocalDistributionCentre ldc:LDCs){
				if(ldc.getName().equals(origin)){
					MailRecord rec = new MailRecord(destination);
					rec.incrementCount();rec.incrementVolume(volume);rec.incrementWeight(weight);
					ldc.getAllRecords().add(rec);
				}	
			}
		}
		
		//write XML
		//make boolean ? 
		
	}
	
	public void priceUpdate(){
		//write XML
		//return bool ?
		//change in the GRAPH STRUCTURE
	}
	
	public void costUpdate(){
		//write XML
		//make boolean
		//update JOURNEY ? ??
	}

	
}
