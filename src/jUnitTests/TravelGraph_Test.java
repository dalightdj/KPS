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
		
		Path p = paths.get(0);
		System.out.println(p);
		assertTrue(p.getOrigin().getName().equals("Wellington"));
		assertTrue(p.getDestination().getName().equals("Hamilton"));
		assertTrue(p.getCompany().equals("Air NZ"));
		assertTrue(p.getTransportType()==TransportType.AIR);
		assertTrue(p.getPPG()==5);
		assertTrue(p.getPPCC()==6);
		assertTrue(p.getMaxWeight()==50);
		assertTrue(p.getMaxVolume()==30);
		assertTrue(p.getDay()==DayOfWeek.MONDAY);
		assertTrue(p.getFrequency()==6);
		assertTrue(p.getDuration()==2);
		
		p = paths.get(1);
		assertTrue(p.getOrigin().getName().equals("Auckland"));
		assertTrue(p.getDestination().getName().equals("Wellington"));
		assertTrue(p.getCompany().equals("Pacific Blue"));
		assertTrue(p.getTransportType()==TransportType.SEA);
		assertTrue(p.getPPG()==3);
		assertTrue(p.getPPCC()==3);
		assertTrue(p.getMaxWeight()==75);
		assertTrue(p.getMaxVolume()==50);
		assertTrue(p.getDay()==DayOfWeek.TUESDAY);
		assertTrue(p.getFrequency()==1);
		assertTrue(p.getDuration()==6);
		
		p = paths.get(2);
		assertTrue(p.getOrigin().getName().equals("Hamilton"));
		assertTrue(p.getDestination().getName().equals("Auckland"));
		assertTrue(p.getCompany().equals("NZ Post"));
		assertTrue(p.getTransportType()==TransportType.LAND);
		assertTrue(p.getPPG()==2.5);
		assertTrue(p.getPPCC()==3.5);
		assertTrue(p.getMaxWeight()==100);
		assertTrue(p.getMaxVolume()==100);
		assertTrue(p.getDay()==DayOfWeek.FRIDAY);
		assertTrue(p.getFrequency()==4);
		assertTrue(p.getDuration()==1);
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
