package Logger;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlElement;

@XmlRootElement 
public class TDEvent extends Event {

	private static final String eventType = "TransportDiscontinued";
	private String company;
	private String desTo;
	private String desFrom;
	private String type;


	public TDEvent(String company, String desTo, String desFrom, String type) {
		setCompany(company);
		setDesTo(desTo);		
		setDesFrom(desFrom);	
		setType(type);
	}

	public String getCompany() {
		return company;
	}

	@XmlElement  
	public void setCompany(String company) {
		this.company = company;
	}

	public String getDesTo() {
		return desTo;
	}

	@XmlElement  
	public void setDesTo(String desTo) {
		this.desTo = desTo;
	}

	public String getDesFrom() {
		return desFrom;
	}

	@XmlElement  
	public void setDesFrom(String desFrom) {
		this.desFrom = desFrom;
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
