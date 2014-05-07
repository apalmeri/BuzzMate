package edu.ycp.cs.cs496.locations.controllers;


import edu.ycp.cs.cs496.locations.model.Location;
import edu.ycp.cs.cs496.locations.model.persist.Database;
import edu.ycp.cs.cs496.locations.model.persist.IDatabase;

public class GetLocation {
	public Location getLocation(String locationName){
		IDatabase database = Database.getInstance();
		return database.getLocation(locationName);
	}

}
