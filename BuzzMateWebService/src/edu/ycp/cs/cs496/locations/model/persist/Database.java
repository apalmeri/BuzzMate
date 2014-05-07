package edu.ycp.cs.cs496.locations.model.persist;

public class Database {
	
	/**
	 * Get the singleton {@link IDatabase} implementation.
	 * 
	 * @return the singleton {@link IDatabase} implementation
	 */
	public static IDatabase theInstance;
		

	public static void setInstance(IDatabase db) {
		// TODO Auto-generated method stub
		theInstance = db;
		
	}

	public static IDatabase getInstance() {
		// TODO Auto-generated method stub
		return theInstance;
	}


}

