package edu.ycp.cs.cs496.locations.model.persist;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import edu.ycp.cs.cs496.locations.controllers.User;
import edu.ycp.cs.cs496.locations.model.Cab;
import edu.ycp.cs.cs496.locations.model.Location;

/**
 * fake database which contains hard coded information to pull from 
 */

public class FakeDatabase  implements IDatabase{
	private List<Location> locations;
	private List<Cab> cabs;
	
	private Map<Integer, User> userMap; // map of user ids to users
	private List <User> userList;
	
	public FakeDatabase(){
		locations = new ArrayList<Location>();
		cabs = new ArrayList<Cab>();
		userList = new 	ArrayList<User>();
		userMap = new TreeMap<Integer, User>();
		//Sample Locations
		locations.add(new Location(1, "BarName", "Bar", "123 Street", "York", "PA", "17403", "717-123-1234"));
		locations.add(new Location(2, "Pizza", "Food", "456 Street", "York", "PA", "17403", "717-987-9876"));
		cabs.add(new Cab(1,"Cab Service", "717-789-7894", "Only excepts Cash"));
	
		User user = new User();
		user.setId(-1);
		user.setUsername("alana");
		user.setPassword("palmerini");
		userMap.put(user.getId(), user);
	
	}
	
	public Location getLocation(String locationName){
		for (Location location : locations){
			if(location.getName().equals(locationName)){
				return new Location(location.getId(), location.getName(), location.getType(), location.getStreet1(), location.getCity(), location.getState(), location.getMailcode(), location.getPhonenumber());
			}
		}
		
		return null;
	}
	
	
	public Cab getCab(String cabName){
		for (Cab cab : cabs){
			if(cab.getName().equals(cabName)){
				return new Cab(cab.getID(), cab.getName(), cab.getPhonenumber(), cab.getNotes());
			}
		}
		
		return null;
	}
	
	
	@Override
	public List<Cab> getCab() {
		// return a copy
		return new ArrayList<Cab>(cabs);
	}

	@Override
	public void addUserToDB(User user) {
		userList.add(user);
	}

	@Override
	public Map<Integer, User> getUsersFromDB() {
		return userMap;
	}

	@Override
	public void addLocationToDB(Location location) {
		throw new UnsupportedOperationException("TODO - implement");
	}

	@Override
	public Location getLocationFromDB(String name) {
		throw new UnsupportedOperationException("TODO - implement");
	}

	@Override
	public List<Location> getLocationByTypeFromDB(String type) {
		throw new UnsupportedOperationException("TODO - implement");
	}

	@Override
	public List<Location> getLocationListFromDB() {
		throw new UnsupportedOperationException("TODO - implement");
	}

	@Override
	public void addCabToDB(Cab cab) {
		throw new UnsupportedOperationException("TODO - implement");
		
	}

}

