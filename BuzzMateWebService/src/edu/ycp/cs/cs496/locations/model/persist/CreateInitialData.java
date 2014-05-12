package edu.ycp.cs.cs496.locations.model.persist;

import java.sql.SQLException;

import edu.ycp.cs.cs496.locations.controllers.User;
import edu.ycp.cs.cs496.locations.model.Cab;
import edu.ycp.cs.cs496.locations.model.Location;

public class CreateInitialData {

	public static void main(String[] args) throws SQLException {
		DerbyDatabase db = new DerbyDatabase();			

		User alana = new User(-1, "alana", "pw");

		db.addUserToDB(alana);
				
		Location foodLocation = new Location(-1,"Pizza", "Food", "456 Street", "York", "PA", "17403", "717-987-9876");
		db.addLocationToDB(foodLocation);
		
		Location barLocation = new Location(-1,"BarName", "Bar", "456 Street", "York", "PA", "17403", "717-987-9876");
		db.addLocationToDB(barLocation);
		
		Cab cab = new Cab(-1,"CabName", "717-855-7894", "Only takes cash");
		db.addCabToDB(cab);
		
		System.out.println("Successfully created initial data");
	}

}