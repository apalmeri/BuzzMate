package edu.ycp.cs.cs496.locations.controllers;

import edu.ycp.cs.cs496.locations.model.Cab;
import edu.ycp.cs.cs496.locations.model.Location;
import edu.ycp.cs.cs496.locations.model.persist.Database;
import edu.ycp.cs.cs496.locations.model.persist.IDatabase;

public class GetCabByName {
	public Cab getCab(String cabName) {
		IDatabase db = Database.getInstance();
		return db.getCab(cabName);
	}
}
