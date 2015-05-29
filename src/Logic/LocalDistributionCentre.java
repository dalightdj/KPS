package Logic;

import java.util.ArrayList;

public class LocalDistributionCentre {
	
	private String name;
	private ArrayList<MailRecord> mailRecords;
	
	public String getName(){
		return name;
	}
	
	public ArrayList<MailRecord> getAllRecords(){
		return mailRecords;
	}
	
	/**
	 * Searches for a mail record with the given name for this Local Distribution Centre.
	 * If it exists in the records, return that record otherwise return null
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
