package Logger;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlElement;

import travelGraph.Path.DayOfWeek;
import travelGraph.Path.TransportType;


 /**
  * A class that represents a Transport Discontinued event 
  * @author rahulnaidu
  *
  */
public class TDEvent extends Event {

	private String company;
	private String destination;
	private String origin;
	private TransportType type;
	private DayOfWeek dow;

	public TDEvent(String company, String destination, String origin, TransportType type, DayOfWeek dow) {
		super();
		this.company = company;
		this.destination = destination;
		this.origin = origin;
		this.type = type;
		this.dow = dow;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getDestination() {
		return destination;
	}
  
	public void setDestination(String destination) {
		this.destination = destination;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public TransportType getType() {
		return type;
	}
  
	public void setType(TransportType type) {
		this.type = type;
	}

	public static String getEventtype() {
		return eventType;
	}
	
	public DayOfWeek getDow() {
		return dow;
	}

	public void setDow(DayOfWeek dow) {
		this.dow = dow;
	}

}
