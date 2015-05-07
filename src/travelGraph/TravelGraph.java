package travelGraph;

import java.util.ArrayList;

public class TravelGraph {
	
	ArrayList<Location> locations = new ArrayList<Location>();
	
	public enum Priority{
		InternationalAir, InternationalDomestic, DomesticAir, DomesticStandard;
	}
	
	
	public void updatePathPrice(){
		
	}
				
	
	public ArrayList<Path> getRoute(String origin, String destination, Priority priority){
		ArrayList<Path> route = new ArrayList<Path>();
		
		Location[] locs = getLocs(origin, destination);
		Location originLoc = locs[0];
		Location destLoc = locs[1];
		
		if(originLoc==null || destLoc==null){//if one of the locations don't exist
			return null;//THROW A DESTINATION DOESNT EXIST EXCEPTION
		}
		
		//FIND A ROUTE USING A*
		

		return route;
	}
	
	
	/**
	 * Creates a direct path from origin to destination.
	 * @param origin Name of location. If one doesn't exist then this method creates one.
	 * @param destination Name of destination. If one doesn't exist then this method creates one.
	 * @param company The name of the transport company
	 * @param type The type of transport i.e. Land, Air or Sea
	 */
	public void createPath(String origin, String destination, String company, Path.TransportType type){
		
		Location[] locs = getLocs(origin, destination);
		Location originLoc = locs[0];
		Location destLoc = locs[1];
				
		//if either one of the given locations don't exist then create one and add it to the list of locations
		if(originLoc == null){
			originLoc = new Location(origin);
			locations.add(originLoc);
		}
		if(destLoc == null){
			destLoc = new Location(destination);
			locations.add(destLoc);
		}
		
		//create the path and then add it to the origin's list of outgoing paths
		Path path = new Path(originLoc, destLoc, company, type);
		originLoc.addPath(path);
	}
	
	
	
	//======================================================
	//====================HELPER METHODS====================
	//======================================================
	/**
	 * Gets an origin and destination location and returns them in an array of size 2.
	 * @param origin The name of the origin location
	 * @param destination The name of the destination location
	 * @return An array with index 0 holding the origin location, and index 1 holding the destination location. The location will be null if a location doesn't exist.
	 */
	private Location[] getLocs(String origin, String destination){
		Location[] locs = new Location[2];
		
		for(Location l : locations){
			if(l.getCity().equals(origin)){
				locs[0] = l;
				if(locs[1]!=null) break;//if both have been set, stop
			}
			if(l.getCity().equals(destination)){
				locs[1] = l;
				if(locs[0]!=null) break;//if both have been set, stop
			}
		}
		
		return locs;
	}

}
