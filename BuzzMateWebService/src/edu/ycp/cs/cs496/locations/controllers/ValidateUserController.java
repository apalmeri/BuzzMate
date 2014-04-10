package edu.ycp.cs.cs496.locations.controllers;

import java.util.Collection;

import edu.ycp.cs.cs496.locations.model.persist.IDatabase;

/**
 * @author drew
 *this class is used to apply the logging in logic with a database, the users it contains, and a user object
 *created via the textbox widgets in the UI
 */
public class ValidateUserController {
	
	/**
	 * @param db	the database that is currently attached to the system
	 * @param user	the user object created via the UI textbox fields
	 * @return		if the user does exist in the database then a true is returned
	 * 					otherwise a false is returned and the user can be added to the system
	 */
	public boolean containsUser(IDatabase db, User user){
		
		System.out.println("Database is a " + db.getClass().getName());
		
		Collection<User> allUsers = db.getUsersFromDB().values();
		for (User dbUser : allUsers) {
			if (user.getUsername().equals(dbUser.getUsername()) && user.getPassword().equals(dbUser.getPassword())) {
				return true;
			}
		}
		//the user does not exist and can be added as a new user via the DB methods
		return false;
	}
	
	
}

