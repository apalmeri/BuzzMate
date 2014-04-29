package edu.ycp.cs.cs496.locations.model.persist;


import java.sql.SQLException;

import edu.ycp.cs.cs496.locations.controllers.User;

public class CreateInitialData {

	public static void main(String[] args) throws SQLException {
		DerbyDatabase db = new DerbyDatabase();			
		
		User alana = new User(-1, "alana", "pw");

		db.addUserToDB(alana);
		System.out.println("Successfully created initial data");
	}

}
