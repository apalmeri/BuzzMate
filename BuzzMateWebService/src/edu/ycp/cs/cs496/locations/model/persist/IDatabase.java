package edu.ycp.cs.cs496.locations.model.persist;

import java.util.List;
import java.util.Map;

import edu.ycp.cs.cs496.locations.controllers.User;
import edu.ycp.cs.cs496.locations.model.Location;

public interface IDatabase {

		public Location getLocation(String locationName);
		public List<Location> getLocation();
		public void addUserToDB(User user);
		public Map<Integer, User> getUsersFromDB();
		public List<Location> getLocationListByType(String type);	
}
