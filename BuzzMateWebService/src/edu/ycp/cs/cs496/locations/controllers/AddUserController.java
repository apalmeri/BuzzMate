package edu.ycp.cs.cs496.locations.controllers;

import edu.ycp.cs.cs496.locations.model.persist.IDatabase;

/**
 * @author alana
 * adds the user object to database
 */

public class AddUserController {	
	/**
	 * @param db	this is the database that is attached to the system
	 * @param user	this user object will be added to that database
	 */
	public void addUser(IDatabase db, User user){		
		db.addUserToDB(user);
	}
}
