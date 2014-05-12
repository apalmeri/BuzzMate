package edu.ycp.cs.cs496.locations.controllers;

import java.util.Map;

import edu.ycp.cs.cs496.locations.model.Location;
import edu.ycp.cs.cs496.locations.model.persist.Database;
import edu.ycp.cs.cs496.locations.model.persist.IDatabase;

public class GetLocationByName {
	public Location getLocation(String locationName) {
		IDatabase db = Database.getInstance();
		return db.getLocationFromDB(locationName);
	}

}
