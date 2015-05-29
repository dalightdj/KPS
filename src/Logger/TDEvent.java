package Logger;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlElement;

@XmlRootElement 
public class TDEvent extends Event {

	private static final String eventType = "TransportDiscontinued";
	private String company;
	private String destination;
	private String origin;
	private String type;


	public TDEvent(String company, String destination, String origin, String type) {
		this.company = company;
		this.company = company;
		this.origin = origin;
		this.type = type;
	}

	public String getCompany() {
		return company;
	}

	@XmlElement  
	public void setCompany(String company) {
		this.company = company;
	}

	public String getDestination() {
		return destination;
	}

	@XmlElement  
	public void setDestination(String destination) {
		this.destination = destination;
	}

	public String getOrigin() {
		return origin;
	}

	@XmlElement  
	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public String getType() {
		return type;
	}

	@XmlElement  
	public void setType(String type) {
		this.type = type;
	}

	public static String getEventtype() {
		return eventType;
	}

}
