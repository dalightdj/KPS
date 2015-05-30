package Logger;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class Event {


	private String dateString;
	
	

	public Event(){
		
		DateFormat dateFormat = new SimpleDateFormat("EEE HH:mm");
		Date date = new Date();
		dateString = (dateFormat.format(date));
		
	}
	
	public String getDateString() {
		return dateString;
	}

	public void setDateString(String dateString) {
		this.dateString = dateString;
	}
	
	
	
}
