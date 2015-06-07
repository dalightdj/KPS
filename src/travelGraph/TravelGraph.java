package travelGraph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

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
	 * @param ppg New price per gram. 0< to keep the previous price per gram
	 * @param ppcc New price per cubic centimeter. 0< to keep the previous price per cubic centimeter
	 * @param maxWeight New maximum weight. 0< to keep the previous maximum weight
	 * @param maxVol New maximum volume. 0< to keep the previous maximum volume
	 * @param dayOfWeek The day of the week the transport departs
	 * @param frequency The new frequency in hours between departures. 0< to keep the previous frequency 
	 * @param duration The new duration of the trip in hours. . 0< to keep the previous duration
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
							if(p.getDay() == dayOfWeek){
								return p.update(ppg, ppcc, maxWeight, maxVol, frequency, duration);
							}
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
	 * @throws UnknownLocationException If either origin or destination aren't an existing Location
	 */
	public ArrayList<Path> getRoute(String origin, String destination, Priority priority) throws UnknownLocationException{
		Location[] locs = getLocs(origin, destination);
		Location originLoc = locs[0];
		Location destLoc = locs[1];

		if(originLoc==null || destLoc==null){//if one of the locations don't exist
			throw new UnknownLocationException();
		}

		//FIND A ROUTE USING DIJKSTRA
		PriorityQueue<Location> queue = new PriorityQueue<Location>();

		//reset values
		for(Location loc : locations){
			loc.setDistance(Double.POSITIVE_INFINITY);
			loc.setVisited(false);

			//mark the origin with a distance of 0 from origin then add it to the priority queue
			if(loc==originLoc){
				loc.setDistance(0);
				queue.offer(loc);
			}
		}

		boolean airPriority = priority==Priority.AIR;//there are currently only 2 priorities, Air and Standard. so if this is false then the priority is Standard
		while(!queue.isEmpty()){
			Location from = queue.poll();
			from.setVisited(true);

			if(from==destLoc){
				return getPath(destLoc);//we've found our path
			}

			ArrayList<Path> airPaths = new ArrayList<Path>();//used for standard priority to hold Air paths, just in case there are no Land or Sea paths
			boolean foundAPath = false;//used for standard priority, if this is true then we dont have to look through airpaths
			for(Path p : from.getPaths()){

				//if the path doesnt match air priority then we dont really care about it
				if(airPriority && p.getTransportType()!=TransportType.AIR){
					continue;
				}

				//if the path doesnt match standard priority then add the path to the airpaths and we'll check it later
				if(!airPriority && p.getTransportType()==TransportType.AIR){
					airPaths.add(p);
					continue;
				}

				foundAPath = true;
				Location to = p.getDestination();
				if(!to.isVisited()){
					double weight = p.getWeight();
					double weightPlusPath = weight + from.getDistance();
					if(weightPlusPath < to.getDistance()){
						queue.remove(to);//remove it so it can be updated and the re-added, this ensures that the queue's priorities are always up to date. does nothing if the Location isn't in the queue
						to.setDistance(weightPlusPath);
						to.setFrom(from, p);
						queue.offer(to);//add or re-add the node to the queue so the queue can update with its new priority
					}
				}
			}

			//if standard priority and no standard path was found then find the best air path
			if(!foundAPath){
				double bestWeight = Double.POSITIVE_INFINITY;
				Path bestPath = null;
				for(Path p : airPaths){
					Location to = p.getDestination();
					if(!to.isVisited()){
						double weight = p.getWeight();
						double weightPlusPath = weight + from.getDistance();
						if(weightPlusPath < to.getDistance()){
							bestWeight = weightPlusPath;
							bestPath = p;
						}
					}
				}
				if(bestPath==null){return null;}
				Location to = bestPath.getDestination();
				queue.remove(to);//remove it so it can be updated and the re-added, this ensures that the queue's priorities are always up to date. does nothing if the Location isn't in the queue
				to.setDistance(bestWeight);
				to.setFrom(from, bestPath);
				queue.offer(to);//add or re-add the node to the queue so the queue can update with its new priority
			}
		}

		return null;
	}

	/**
	 * Traces the path from destination to origin, places it into an ArrayList and reverses it so that it is in the right order, that is from origin to destination
	 * @param destination
	 * @return An ordered ArrayList of the Paths from origin to destination.
	 */
	private ArrayList<Path> getPath(Location destination){
		ArrayList<Path> path = new ArrayList<Path>();
		for(Location l = destination; l.getFromLoc()!=null; l = l.getFromLoc()){
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

	/**
	 * 
	 * @return A list containing all Locations that have an outgoing path
	 */
	public ArrayList<Location> getAllOriginLocations(){
		ArrayList<Location> origins = new ArrayList<Location>();
		
		for(Location l : locations){
			if(!l.getPaths().isEmpty()){
				origins.add(l);
			}
		}
		
		return origins;
	}

	/**
	 * Gets all Locations accessible from a Location using only an identified TransportType
	 * @param origin Name of origin
	 * @param priority Air will only search through paths that provide Air TransportType. Standard will search through all navigable paths
	 * @return A list of all the possible Locations accessible from the origin using the given priority
	 * @throws UnknownLocationException  If the given origin isn't an existing Location
	 */
	public ArrayList<Location> allReachableLocations(String origin, Priority priority) throws UnknownLocationException{
		Location originLoc = getLocs(origin, origin)[0];

		if(originLoc==null){throw new UnknownLocationException();}

		ArrayList<Location> locs = new ArrayList<Location>();

		Queue<Location> queue = new LinkedList<Location>();
		queue.offer(originLoc);

		//reset all Location visited fields
		for(Location l : locations){
			l.setVisited(false);
		}

		while(!queue.isEmpty()){
			Location from = queue.poll();
			from.setVisited(true);

			for(Path p : from.getPaths()){

				//If its the correct type of path
				if((priority==Priority.AIR && p.getTransportType()==TransportType.AIR) || (priority==Priority.STANDARD)){
					//and the path's destination isn't already in the list of reachable Locations 
					if((p.getDestination()!=originLoc) && (!locs.contains(p.getDestination()))){
						queue.offer(p.getDestination());
						locs.add(p.getDestination());
					}					
				}
			}
		}

		return locs;
	}
	
	
	public ArrayList<Path> getAllPaths(){
		ArrayList<Path> paths = new ArrayList<Path>();
		
		for(Location l : locations){
			for(Path p : l.getPaths()){
				paths.add(p);
			}			
		}
		
		return paths;
	}
	

	public class UnknownLocationException extends Exception{

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
			if(l.getName().equals(origin)){
				locs[0] = l;
				if(locs[1]!=null) break;//if both have been set, stop
			}
			if(l.getName().equals(destination)){
				locs[1] = l;
				if(locs[0]!=null) break;//if both have been set, stop
			}
		}

		return locs;
	}

}
