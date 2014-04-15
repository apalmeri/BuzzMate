package edu.ycp.cs.cs496.locations.controllers;

import java.util.List;

import edu.ycp.cs.cs496.locations.model.Location;
import edu.ycp.cs.cs496.locations.model.persist.Database;
import edu.ycp.cs.cs496.locations.model.persist.IDatabase;

public class GetLocationsByType {
	public List <Location> getLocationListByType(String type){
		IDatabase database = Database.getInstance();
		return database.getLocationListByType(type);
	}
}
