package edu.ycp.cs.cs496.locations.model.persist;

import java.util.List;
import java.util.Map;

import edu.ycp.cs.cs496.locations.controllers.User;
import edu.ycp.cs.cs496.locations.model.Cab;
import edu.ycp.cs.cs496.locations.model.Location;

public interface IDatabase {

	public Location getLocationFromDB(String name);
	public List<Location> getLocationByTypeFromDB(String type);	
	public List<Location> getLocationListFromDB();
	public void addLocationToDB(Location location);
	
	public void addUserToDB(User user);
	public Map<Integer, User> getUsersFromDB();
	
	public Cab getCab(String cabName);
	public List<Cab> getCab();
	public void addCabToDB(Cab cab);	
}
