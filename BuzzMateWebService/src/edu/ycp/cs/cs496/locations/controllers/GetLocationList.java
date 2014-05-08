package edu.ycp.cs.cs496.locations.controllers;

import java.util.List;
import java.util.Map;

import edu.ycp.cs.cs496.locations.model.Location;
import edu.ycp.cs.cs496.locations.model.persist.Database;
import edu.ycp.cs.cs496.locations.model.persist.IDatabase;

public class GetLocationList {
	public Map<Integer, Location> getLocationList(){
		IDatabase database = Database.getInstance();
		return database.getLocationListFromDB();
	}

}
