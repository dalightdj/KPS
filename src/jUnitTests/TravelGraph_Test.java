package jUnitTests;

import static org.junit.Assert.*;

import org.junit.Test;

import travelGraph.*;

public class TravelGraph_Test {

	@Test
	public void testLocationClass() {
		Location l = new Location("Location");
		assertTrue("Location name compromised", l.getCity().equals("Location"));
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

}
