package travelGraph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;

import travelGraph.Path.DayOfWeek;
import travelGraph.Path.TransportType;

/**
 * A class that creates a graph using the Path and Location classes.
 * @author TeAka
 *
 */
public class TravelGraph {

	ArrayList<Location> locations = new ArrayList<Location>();

	public enum Priority{
		AIR, STANDARD;
	}	

	/**
	 * Updates the given Path's price information. If the Path doesn't exist then a new one is created.
	 * @param origin Name of origin
	 * @param destination Name of destination
	 * @param company Name of the transport firm
	 * @param type The type of transport i.e. Land, Air or Sea
	 * @param ppg New price per gram
	 * @param ppcc New price per cubic centimeter
	 * @param dayOfWeek The day of the week the transport departs
	 * @param frequency The frequency in hours between departures
	 * @param duration The duration of the trip in hours
	 * @return The updated path. Returns a new path if no path existed with the given origin, destination, company name, transport type.
	 */
	public Path updatePathPrice(String origin, String destination, String company, Path.TransportType type, float ppg, float ppcc, int maxWeight, int maxVol, Path.DayOfWeek dayOfWeek, int frequency, int duration){
		Location[] locs = getLocs(origin, destination);
		Location originLoc = locs[0];
		Location destLoc = locs[1];

		//If the path exists then update it and return it
		if(originLoc!=null && destLoc!=null){
			for(Path p : originLoc.getPaths()){
				if(p.getDestination()==destLoc){
					if(p.getCompany().equals(company)){
						if(p.getTransportType() == type){
							return p.update(ppg, ppcc, dayOfWeek, frequency, duration);
						}
					}
				}
			}
		}


		///////////////////////////////////////////////////////////////////
		//The given path must not exist so make a new one and return it//
		///////////////////////////////////////////////////////////////////

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
		Path path = new Path(originLoc, destLoc, company, type, ppg, ppcc, maxWeight, maxVol, dayOfWeek, frequency, duration);
		originLoc.addPath(path);

		return path;
	}

	/**
	 * Gets the cheapest route using Dijkstra's search algorithm
	 * @param origin Name of origin
	 * @param destination Name of destination
	 * @param priority The priority given to this package determines which routes will be searched
	 * @return An ordered ArrayList of the Paths from origin to destination. Returns null if no such path exists
	 */
	public ArrayList<Path> getRoute(String origin, String destination, Priority priority){
		ArrayList<Path> route = new ArrayList<Path>();

		Location[] locs = getLocs(origin, destination);
		Location originLoc = locs[0];
		Location destLoc = locs[1];

		if(originLoc==null || destLoc==null){//if one of the locations don't exist
			return null;//THROW A LOCATION DOESNT EXIST EXCEPTION
		}

		//FIND A ROUTE USING DIJKSTRA
		PriorityQueue<Location> queue = new PriorityQueue<Location>();

		for(Location loc : locations){
			loc.setDistance(Double.POSITIVE_INFINITY);
			loc.setVisited(false);

			if(loc==originLoc){
				loc.setDistance(0);
				queue.offer(loc);
			}
		}

		while(!queue.isEmpty()){
			Location from = queue.poll();
			from.setVisited(true);

			if(from==destLoc){
				return getPath(destLoc);//we've found our path
			}


			for(Path p : from.getPaths()){
				//if(airPriority(p, priority)){
				//stdPriority searches for land or sea first nd only searches for air if there is no other choice

				Location to = p.getDestination();
				double weight = p.getCost();
				double weightPlusPath = weight + from.getDistance();
				if(weightPlusPath < to.getDistance()){
					queue.remove(to);//remove it so it can be updated and the re-added. does nothing if the Location isn't in the queue
					to.setDistance(weightPlusPath);
					to.setFrom(from, p);
					queue.offer(to);//add or re-add the node to the queue so that it can update with its new priority
				}
				//}
			}
		}

		return null;
	}

	//create list of air paths. if no from path then check air paths

	private boolean airPriority(Path p, Priority priority){
		return (priority==Priority.DomesticAir || priority==Priority.InternationalAir) && p.getTransportType()==TransportType.AIR;
	}

	private boolean standardPriority(){

	}

	/**
	 * Traces the path from destination to origin, places it into an ArrayList and reverses it so that it is in the right order, that is from origin to destination
	 * @param destination
	 * @return An ordered ArrayList of the Paths from origin to destination.
	 */
	private ArrayList<Path> getPath(Location destination){
		ArrayList<Path> path = new ArrayList<Path>();
		for(Location l = destination; l!=null; l = l.getFromLoc()){
			path.add(l.getFromPath());
		}

		Collections.reverse(path);
		return path;
	}


	/**
	 * Removes a particular Path from the collection of Paths
	 * @param origin Name of origin
	 * @param destination Name of destination
	 * @param company Name of transport firm
	 * @param type The type of transport i.e. Land, Air or Sea
	 * @return True if the path was successfully removed. False otherwise. For a path to be removed it must have the same origin, destination, company and type.
	 */
	public boolean removePath(String origin, String destination, String company, Path.TransportType type, DayOfWeek day){
		Location[] locs = getLocs(origin, destination);
		Location originLoc = locs[0];
		Location destLoc = locs[1];

		if(originLoc!=null && destLoc!=null){
			for(Path p : originLoc.getPaths()){
				if(p.getDestination()==destLoc){
					if(p.getCompany().equals(company)){
						if(p.getTransportType() == type){
							if(p.getDay() == day){
								originLoc.removePath(p);
								return true;
							}
						}
					}
				}
			}
		}

		return false;
	}


	//======================================================
	//====================HELPER METHODS====================
	//======================================================
	/**
	 * Gets an origin and destination Location from the stored collection of Locations and returns them in an array of size 2.
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
