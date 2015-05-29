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
	private double distance = Double.POSITIVE_INFINITY;
	private boolean visited = false;
	private Location fromLoc;
	private Path fromPath;
	
	
	public Location(String city){
		this.city = city;
	}
	
	protected void addPath(Path path){
		paths.add(path);
	}
	
	protected void removePath(Path path){
		paths.remove(path);
	}
	
	protected String getCity(){
		return city;
	}
	
	/**
	 * 
	 * @return An unmodifiable list of all the paths.
	 */
	protected ArrayList<Path> getPaths(){
		return (ArrayList<Path>) Collections.unmodifiableList(paths);
	}

	
	//Dijkstra methods
	@Override
	public int compareTo(Location other) {
		return(int) (this.distance - other.distance);
	}
	
	public double getDistance(){
		return distance;
	}
	
	public void setDistance(double distance){
		this.distance = distance;
	}
	
	public void setVisited(boolean visited){
		this.visited = visited;
	}
	
	public Location getFromLoc(){
		return fromLoc;
	}
	
	public void setFromLoc(Location from){
		this.fromLoc = from;
	}
	
	public void setFromPath(Path from){
		this.fromPath = from;
	}
	
	public Path getFromPath(){
		return fromPath;
	}
	
}
