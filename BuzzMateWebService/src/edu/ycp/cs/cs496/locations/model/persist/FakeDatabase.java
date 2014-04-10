package edu.ycp.cs.cs496.locations.model.persist;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import edu.ycp.cs.cs496.locations.controllers.User;
import edu.ycp.cs.cs496.locations.model.Location;

public class FakeDatabase  implements IDatabase{
	private List<Location> locations;
	
	private Map<Integer, User> userMap; // map of user ids to users
	private List <User> userList;
	
	public FakeDatabase(){
		locations = new ArrayList<Location>();
		userList = new 	ArrayList<User>();
		userMap = new TreeMap<Integer, User>();
		//Sample Locations
		locations.add(new Location("BarName", "Bar", "123 Street", "York", "PA", "17403", "717-123-1234"));
		locations.add(new Location("Pizza", "Food", "456 Street", "York", "PA", "17403", "717-987-9876"));
	
		User user = new User();
		user.setId(-1);
		user.setUsername("alana");
		user.setPassword("palmerini");
		userMap.put(user.getId(), user);
	
	}
	
	public Location getLocation(String locationName){
		for (Location location : locations){
			if(location.getName().equals(locationName)){
				return new Location(location.getName(), location.getType(), location.getStreet1(), location.getCity(), location.getState(), location.getMailcode(), location.getPhonenumber());
			}
		}
		
		return null;
	}
	
	@Override
	public List<Location> getLocation() {
		// return a copy
		return new ArrayList<Location>(locations);
	}

	@Override
	public void addUserToDB(User user) {
		userList.add(user);
	}

	@Override
	public Map<Integer, User> getUsersFromDB() {
		return userMap;
	}
	
}

