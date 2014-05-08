package edu.ycp.cs.cs496.locations.model.persist;

import java.util.List;
import java.util.Map;

import edu.ycp.cs.cs496.locations.controllers.User;
import edu.ycp.cs.cs496.locations.model.Cab;
import edu.ycp.cs.cs496.locations.model.Location;

public interface IDatabase {

	public Map<Integer, Location> getLocationFromDB(String name);
	public Map<Integer, Location> getLocationListFromDB();
	public Map<Integer, Location> getLocationByTypeFromDB(String type);	
	
	public void addUserToDB(User user);
	public void addLocationToDB(Location location);
	public Map<Integer, User> getUsersFromDB();
	
	public Cab getCab(String cabName);
	public List<Cab> getCab();	
}
