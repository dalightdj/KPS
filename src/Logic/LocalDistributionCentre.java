package Logic;

import java.util.ArrayList;

import travelGraph.Location;

public class LocalDistributionCentre extends Location{

	private ArrayList<MailRecord> mailRecords;

	public LocalDistributionCentre(String city) {
		super(city);
		mailRecords = new ArrayList<MailRecord>();
		// TODO Auto-generated constructor stub
	}

	public String getName(){
		return getCity();
	}

	public ArrayList<MailRecord> getAllRecords(){
		return mailRecords;
	}

	/**
	 * Searches for a mail record with the given destination from this Local
	 * Distribution Centre. If it exists in the records, return that record
	 * otherwise return null.
	 * @param destination, the place used to search the records
	 * @return either the matching MailRecord object, or null.
	 */
	public MailRecord getRecord(String destination){
		MailRecord record = null;
		for(MailRecord m:mailRecords){
			if(m.getDestination().equals(destination)){
				record = m;
			}
		}
		return record;
	}
}
