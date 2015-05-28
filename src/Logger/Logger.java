package Logger;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

import javax.xml.bind.Marshaller;




public class Logger {
	
	private String filename = "/Users/rahulnaidu/Documents/workspace/KPS/eventsData.xml";
	private TDEvent tdEvent;

	
	public Logger(){
		tdEvent = new TDEvent("NX Post", "Japan", "New Zealand", "something");
		writeXML(filename);
	}
	
	public void readXML(String filename){
		
		
		
	}
	
	public void writeXML(String filename){
		
		
		try {
			File file = new File(filename);

			JAXBContext jaxbContext = JAXBContext.newInstance(TDEvent.class);
			Marshaller marshaller = jaxbContext.createMarshaller();
			
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			
			marshaller.marshal(tdEvent, file);
			marshaller.marshal(tdEvent, System.out);
			
			
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	

}
