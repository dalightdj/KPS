package jUnitTests;

import static org.junit.Assert.*;

import java.io.File;
import java.util.ArrayList;

import org.junit.Test;

import travelGraph.Path.DayOfWeek;
import travelGraph.Path.TransportType;
import travelGraph.TravelGraph.Priority;
import Logger.CPUEvent;
import Logger.Event;
import Logger.Logger;
import Logger.MDEvent;
import Logger.TCUEvent;
import Logger.TDEvent;

public class Logger_Test {

	@Test
	public void testValidAddAndReadTDEvent(){

		removeEventsDataFile();

		Logger logger = new Logger();

		//Create and add Event to the xml file
		TDEvent tdEvent = new TDEvent("Company A", "Australia", "New Zealand", TransportType.AIR, DayOfWeek.MONDAY);
		logger.addEvent(tdEvent);

		//Read the Event from file
		ArrayList<Event> events = logger.readXML();
		TDEvent readInTDEvent = (TDEvent) events.get(0);

		assertEquals(tdEvent.getCompany(), readInTDEvent.getCompany());
		assertEquals(tdEvent.getDestination(), readInTDEvent.getDestination());
		assertEquals(tdEvent.getOrigin(), readInTDEvent.getOrigin());
		assertEquals(tdEvent.getType(), readInTDEvent.getType());
		assertEquals(tdEvent.getDow(), readInTDEvent.getDow());

	}

	@Test
	public void testValidAddAndReadMDEvent(){

		removeEventsDataFile();

		Logger logger =  new Logger();

		MDEvent mdEvent = new MDEvent("Wed 13:43", "Australia", "New Zealand", Priority.STANDARD, 12, 34);
		logger.addEvent(mdEvent);

		ArrayList<Event> events = logger.readXML();
		MDEvent readInMDEvent = (MDEvent) events.get(0);

		assertEquals(mdEvent.getDate(), readInMDEvent.getDate());
		assertEquals(mdEvent.getDestination(), readInMDEvent.getDestination());
		assertEquals(mdEvent.getOrigin(), readInMDEvent.getOrigin());
		assertEquals(mdEvent.getPriority(), readInMDEvent.getPriority());
		assertEquals(mdEvent.getWeight(), readInMDEvent.getWeight());
		assertEquals(mdEvent.getVolume(), readInMDEvent.getVolume());

	}

	@Test
	public void testValidAddAndReadCPUEvent(){

		removeEventsDataFile();

		Logger logger =  new Logger();

		CPUEvent cpuEvent = new CPUEvent("Australia", "New Zealand", Priority.AIR, 43.5f, 54.8f, DayOfWeek.MONDAY);
		logger.addEvent(cpuEvent);

		ArrayList<Event> events = logger.readXML();
		CPUEvent readInCPUEvent = (CPUEvent) events.get(0);

		assertEquals(cpuEvent.getDestination(), readInCPUEvent.getDestination());
		assertEquals(cpuEvent.getOrigin(), readInCPUEvent.getOrigin());
		assertEquals(cpuEvent.getPriority(), readInCPUEvent.getPriority());
		assertEquals(cpuEvent.getWeightPrice(), readInCPUEvent.getWeightPrice(), 0.00001);
		assertEquals(cpuEvent.getVolumePrice(), readInCPUEvent.getVolumePrice(), 0.00001);
		assertEquals(cpuEvent.getDow(), readInCPUEvent.getDow());

	}

	@Test
	public void testValidAddAndReadTCUEvent(){

		removeEventsDataFile();

		Logger logger =  new Logger();

		TCUEvent tcuEvent = new TCUEvent("Company b", "Australia", "New Zealand", TransportType.SEA, DayOfWeek.THURSDAY, 14.45f, 23.54f, 50, 200, 13, 2);
		logger.addEvent(tcuEvent);

		ArrayList<Event> events = logger.readXML();
		TCUEvent readInTCUEvent = (TCUEvent) events.get(0);
		
		assertEquals(tcuEvent.getCompany(), readInTCUEvent.getCompany());
		assertEquals(tcuEvent.getDestination(), readInTCUEvent.getDestination());
		assertEquals(tcuEvent.getOrigin(), readInTCUEvent.getOrigin());
		assertEquals(tcuEvent.getType(), readInTCUEvent.getType());
		assertEquals(tcuEvent.getDow(), readInTCUEvent.getDow());
		assertEquals(tcuEvent.getWeightCost(), readInTCUEvent.getWeightCost(), 0.00001);
		assertEquals(tcuEvent.getVolumeCost(), readInTCUEvent.getVolumeCost(), 0.00001);
		assertEquals(tcuEvent.getMaxWeight(), readInTCUEvent.getMaxWeight());
		assertEquals(tcuEvent.getMaxVolume(), readInTCUEvent.getMaxVolume());
		assertEquals(tcuEvent.getDuration(), readInTCUEvent.getDuration());
		assertEquals(tcuEvent.getFrequency(), readInTCUEvent.getFrequency());
		
	}


	/**
	 * Just a helper method to remove the xml file so that each test has a fresh start
	 */
	public void removeEventsDataFile(){

		File file = new File("eventsData.xml");

		if(file.exists()){
			file.delete();
		}

	}


}
