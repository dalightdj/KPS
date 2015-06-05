package travelGraph;

import java.util.ArrayList;
import java.util.Collections;

/**
 * A class that represents a location where mail is delivered to or from.
 * @author TeAka
 *
 */
public class Location implements Comparable<Location>{
	
	private String city;
	private ArrayList<Path> paths = new ArrayList<Path>();//outgoing paths
	
	//For Dijkstra
	private double distance = Double.POSITIVE_INFINITY;//Current optimal distance from origin to here
	private boolean visited = false;
	private Location fromLoc;//The origin Location of the current optimal path
	private Path fromPath;//The current optimal path
	
	/**
	 * 
	 * @param name Name of Location
	 */
	public Location(String name){
		this.city = name;
	}
	
	public void addPath(Path path){
		paths.add(path);
	}
	
	public void removePath(Path path){
		paths.remove(path);
	}
	
	public String getCity(){
		return city;
	}
	
	/**
	 * 
	 * @return An unmodifiable list of all the outgoing paths.
	 */
	public ArrayList<Path> getPaths(){
		return paths;//(ArrayList<Path>) Collections.unmodifiableList(paths);
	}

	
	//Dijkstra methods
	@Override
	public int compareTo(Location other) {
		return(int) (this.distance - other.distance);
	}
	
	/**
	 * 
	 * @return Current optimal distance from origin to this Location
	 */
	public double getDistance(){
		return distance;
	}
	
	/**
	 * 
	 * @param distance Optimal distance from origin to this Location
	 * @return Returns false if the given distance is not smaller than the current smallest distance
	 */
	public boolean setDistance(double distance){
		if(distance<this.distance){
			this.distance = distance;
			return true;
		}
		return false;		
	}
	
	/**
	 * 
	 * @param visited Whether or not this Location has been visited by a search algorithm
	 */
	public void setVisited(boolean visited){
		this.visited = visited;
	}
	
	/**
	 * 
	 * @return Whether or not this Location has been visited by a search algorithm
	 */
	public boolean isVisited(){
		return visited;
	}
	
	/**
	 * Set the optimal from Path for Dijkstra's algorithm
	 * @param fromLoc The Location from where the current optimal path comes from
	 * @param fromPath The optimal Path
	 */
	public void setFrom(Location fromLoc, Path fromPath){
		this.fromLoc = fromLoc;
		this.fromPath = fromPath;
	}
	
	/**
	 * 
	 * @return The Location from where the current optimal path comes from
	 */
	public Location getFromLoc(){
		return fromLoc;
	}
	
	/**
	 * 
	 * @return The optimal Path
	 */
	public Path getFromPath(){
		return fromPath;
	}
	
}
