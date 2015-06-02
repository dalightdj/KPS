package jUnit;

import static org.junit.Assert.*;

import org.junit.Test;

import travelGraph.*;

public class TravelGraph_Test {

	@Test
	public void testLocation() {
		Location l = new Location("Location");
		assertTrue("Location name compromised", l.getCity().equals("Location"));
	}

}
