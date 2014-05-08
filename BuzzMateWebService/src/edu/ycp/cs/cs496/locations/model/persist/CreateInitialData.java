package edu.ycp.cs.cs496.locations.model.persist;


import java.sql.SQLException;

import edu.ycp.cs.cs496.locations.controllers.User;
import edu.ycp.cs.cs496.locations.model.Location;

public class CreateInitialData {

	public static void main(String[] args) throws SQLException {
		DerbyDatabase db = new DerbyDatabase();			

		User alana = new User(-1, "alana", "pw");

		db.addUserToDB(alana);
				
		Location location = new Location("Pizza", "Food", "456 Street", "York", "PA", "17403", "717-987-9876");
		db.addLocationToDB(location);
		
		System.out.println("Successfully created initial data");
	}

}