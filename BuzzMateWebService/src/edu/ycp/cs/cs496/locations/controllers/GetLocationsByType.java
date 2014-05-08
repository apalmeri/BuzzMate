package edu.ycp.cs.cs496.locations.controllers;

import java.util.List;
import java.util.Map;

import edu.ycp.cs.cs496.locations.model.Location;
import edu.ycp.cs.cs496.locations.model.persist.Database;
import edu.ycp.cs.cs496.locations.model.persist.IDatabase;

public class GetLocationsByType {
	public Map<Integer, Location> getLocationListByType(String type){
		IDatabase database = Database.getInstance();
		return database.getLocationByTypeFromDB(type);
	}
}
