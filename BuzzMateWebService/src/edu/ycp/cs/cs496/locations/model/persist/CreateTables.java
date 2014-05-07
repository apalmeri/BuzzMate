package edu.ycp.cs.cs496.locations.model.persist;

import java.sql.SQLException;

public class CreateTables {

	public static void main(String[] args) throws SQLException {
		DerbyDatabase db = new DerbyDatabase();
		db.createTables();
		System.out.println("Successfully created tables");
	}

}

