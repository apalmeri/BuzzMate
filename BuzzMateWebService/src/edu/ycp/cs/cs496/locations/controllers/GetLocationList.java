package edu.ycp.cs.cs496.locations.controllers;

import java.util.List;

import edu.ycp.cs.cs496.locations.model.Location;
import edu.ycp.cs.cs496.locations.model.persist.Database;
import edu.ycp.cs.cs496.locations.model.persist.IDatabase;

public class GetLocationList {
	public List <Location> getLocationList(){
		IDatabase database = Database.getInstance();
		return database.getLocation();
	}

}
