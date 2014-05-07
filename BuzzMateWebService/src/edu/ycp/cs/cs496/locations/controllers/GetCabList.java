package edu.ycp.cs.cs496.locations.controllers;

import java.util.List;

import edu.ycp.cs.cs496.locations.model.Cab;
import edu.ycp.cs.cs496.locations.model.Location;
import edu.ycp.cs.cs496.locations.model.persist.Database;
import edu.ycp.cs.cs496.locations.model.persist.IDatabase;

public class GetCabList {
	public List <Cab> getCabList(){
		IDatabase database = Database.getInstance();
		return database.getCab();
	}
}
