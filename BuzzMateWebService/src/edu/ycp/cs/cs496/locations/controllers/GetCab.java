package edu.ycp.cs.cs496.locations.controllers;

import edu.ycp.cs.cs496.locations.model.Cab;
import edu.ycp.cs.cs496.locations.model.Location;
import edu.ycp.cs.cs496.locations.model.persist.Database;
import edu.ycp.cs.cs496.locations.model.persist.IDatabase;


public class GetCab {

	public Cab getCab(String cabName){
		IDatabase database = Database.getInstance();
		return database.getCab(cabName);
	}
}
