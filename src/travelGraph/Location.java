package travelGraph;

import java.util.ArrayList;
import java.util.Collections;

/**
 * A class that represents a location where mail is delivered to or from.
 * @author TeAka
 *
 */
public class Location {
	
	String city;
	ArrayList<Path> paths = new ArrayList<Path>();//outgoing paths
	
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

}
