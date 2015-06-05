package jUnitTests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import travelGraph.*;
import travelGraph.Path.DayOfWeek;
import travelGraph.Path.TransportType;

public class TravelGraph_Test {
	
	private ArrayList<Path> paths = null;
	
	@Test
	public void testLocationClass() {
		Location l = new Location("Location");
		assertTrue("Location name compromised", l.getName().equals("Location"));
	}
	
	@Test
	public void testLocationCompareTo(){
		Location loc1 = new Location("loc1");
		Location loc2 = new Location("loc2");
		Location loc3 = new Location("loc3");
		loc1.setDistance(0);
		loc2.setDistance(0);
		loc3.setDistance(20);
		
		assertTrue(loc1.compareTo(loc3)<0);
		assertTrue(loc1.compareTo(loc2)==0);
		assertTrue(loc3.compareTo(loc1)>0);
	}
	
	@Test
	public void testPathValues(){
		createGraph();
		
		Path p1 = paths.get(0);
		assertTrue(p1.getOrigin().getName().equals("Wellington"));
		assertTrue(p1.getDestination().getName().equals("Hamilton"));
		assertTrue(p1.getCompany().equals("Air NZ"));
		assertTrue(p1.getTransportType()==TransportType.AIR);
		assertTrue(p1.getPPG()==5);
		assertTrue(p1.getPPCC()==6);
		assertTrue(p1.getMaxWeight()==50);
		assertTrue(p1.getMaxVolume()==30);
		assertTrue(p1.getDay()==DayOfWeek.MONDAY);
		assertTrue(p1.getFrequency()==6);
		assertTrue(p1.getDuration()==2);
		
		Path p2 = paths.get(1);
		assertTrue(p2.getOrigin().getName().equals("Auckland"));
		assertTrue(p2.getDestination().getName().equals("Wellington"));
		assertTrue(p2.getCompany().equals("Pacific Blue"));
		assertTrue(p2.getTransportType()==TransportType.SEA);
		assertTrue(p2.getPPG()==3);
		assertTrue(p2.getPPCC()==3);
		assertTrue(p2.getMaxWeight()==75);
		assertTrue(p2.getMaxVolume()==50);
		assertTrue(p2.getDay()==DayOfWeek.TUESDAY);
		assertTrue(p2.getFrequency()==1);
		assertTrue(p2.getDuration()==6);
		
		Path p3 = paths.get(2);
		assertTrue(p3.getOrigin().getName().equals("Hamilton"));
		assertTrue(p3.getDestination().getName().equals("Auckland"));
		assertTrue(p3.getCompany().equals("NZ Post"));
		assertTrue(p3.getTransportType()==TransportType.LAND);
		assertTrue(p3.getPPG()==2.5);
		assertTrue(p3.getPPCC()==3.5);
		assertTrue(p3.getMaxWeight()==100);
		assertTrue(p3.getMaxVolume()==100);
		assertTrue(p3.getDay()==DayOfWeek.FRIDAY);
		assertTrue(p3.getFrequency()==4);
		assertTrue(p3.getDuration()==1);
	}
	
	@Test
	public void testUpdatePathPrice(){
		TravelGraph graph = createGraph();
		
		Path p1 = paths.get(0);
		assertTrue(p1.getOrigin().getName().equals("Wellington"));
		assertTrue(p1.getDestination().getName().equals("Hamilton"));
		assertTrue(p1.getCompany().equals("Air NZ"));
		assertTrue(p1.getTransportType()==TransportType.AIR);
		assertTrue(p1.getPPG()==5);
		assertTrue(p1.getPPCC()==6);
		assertTrue(p1.getMaxWeight()==50);
		assertTrue(p1.getMaxVolume()==30);
		assertTrue(p1.getDay()==DayOfWeek.MONDAY);
		assertTrue(p1.getFrequency()==6);
		assertTrue(p1.getDuration()==2);
		
		
		graph.updatePathPrice("Wellington", "Hamilton", "Air NZ", TransportType.AIR, -1, -1, -1, -1, DayOfWeek.MONDAY, -1, -1);
		assertTrue(p1.getOrigin().getName().equals("Wellington"));
		assertTrue(p1.getDestination().getName().equals("Hamilton"));
		assertTrue(p1.getCompany().equals("Air NZ"));
		assertTrue(p1.getTransportType()==TransportType.AIR);
		assertTrue(p1.getPPG()==5);
		assertTrue(p1.getPPCC()==6);
		assertTrue(p1.getMaxWeight()==50);
		assertTrue(p1.getMaxVolume()==30);
		assertTrue(p1.getDay()==DayOfWeek.MONDAY);
		assertTrue(p1.getFrequency()==6);
		assertTrue(p1.getDuration()==2);
		
		
		graph.updatePathPrice("Wellington", "Hamilton", "Air NZ", TransportType.AIR, 1, 2, 3, 4, DayOfWeek.MONDAY, 5, 6);
		assertTrue(p1.getOrigin().getName().equals("Wellington"));
		assertTrue(p1.getDestination().getName().equals("Hamilton"));
		assertTrue(p1.getCompany().equals("Air NZ"));
		assertTrue(p1.getTransportType()==TransportType.AIR);
		assertTrue(p1.getPPG()==1);
		assertTrue(p1.getPPCC()==2);
		assertTrue(p1.getMaxWeight()==3);
		assertTrue(p1.getMaxVolume()==4);
		assertTrue(p1.getDay()==DayOfWeek.MONDAY);
		assertTrue(p1.getFrequency()==5);
		assertTrue(p1.getDuration()==6);
		
		
		graph.updatePathPrice("Wellington", "Hamilton", "Air NZ", TransportType.AIR, -1, -1, -1, -1, DayOfWeek.MONDAY, -1, -1);
		assertTrue(p1.getOrigin().getName().equals("Wellington"));
		assertTrue(p1.getDestination().getName().equals("Hamilton"));
		assertTrue(p1.getCompany().equals("Air NZ"));
		assertTrue(p1.getTransportType()==TransportType.AIR);
		assertTrue(p1.getPPG()==1);
		assertTrue(p1.getPPCC()==2);
		assertTrue(p1.getMaxWeight()==3);
		assertTrue(p1.getMaxVolume()==4);
		assertTrue(p1.getDay()==DayOfWeek.MONDAY);
		assertTrue(p1.getFrequency()==5);
		assertTrue(p1.getDuration()==6);
	}
	
	
	@Test
	public void testGetAllOriginLocations(){
		TravelGraph graph = createGraph();
		
		ArrayList<Location> locs = graph.getAllOriginLocations();
		
		assertEquals(locs.size(), 3);
		
		ArrayList<String> locNames = new ArrayList<String>();
		for(Location loc : locs){
			locNames.add(loc.getName());
		}
		
		assertTrue(locNames.contains("Wellington"));
		assertTrue(locNames.contains("Hamilton"));
		assertTrue(locNames.contains("Auckland"));
	}
	
	
	private TravelGraph createGraph(){
		TravelGraph graph = new TravelGraph();
		String[] origin = {"Wellington", "Auckland", "Hamilton"};
		String[] destination = {"Hamilton", "Wellington", "Auckland"};
		String[] company = {"Air NZ", "Pacific Blue", "NZ Post"};
		TransportType[] type = {TransportType.AIR, TransportType.SEA, TransportType.LAND};
		float[] ppg = {5.0f, 3.0f, 2.5f};
		float[] ppcc = {6.0f, 3.0f, 3.5f};
		int[] maxWeight = {50, 75, 100};
		int[] maxVolume = {30, 50, 100};
		DayOfWeek[] day = {DayOfWeek.MONDAY, DayOfWeek.TUESDAY, DayOfWeek.FRIDAY};
		int[] frequency = {6, 1, 4};
		int[] duration = {2, 6, 1};
		
		this.paths = new ArrayList<Path>();
		for(int i = 0; i<3; i++){
			Path p = graph.updatePathPrice(origin[i], destination[i], company[i], type[i], ppg[i], ppcc[i], maxWeight[i], maxVolume[i], day[i], frequency[i], duration[i]);
			paths.add(p);
		}
		
		return graph;
	}

}
