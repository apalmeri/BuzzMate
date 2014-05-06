package edu.ycp.cs.cs496.locations.model.persist;

public abstract class Database {
	
	private static IDatabase theInstance;
	
	/**
	 * Set the singleton instance of {@link IDatabase}.
	 * 
	 * @param db the singleton instance of {@link IDatabase}
	 */
	public static void setInstance(IDatabase db) {
		theInstance = db;
	}
	
	/**
	 * Get the singleton {@link IDatabase} implementation.
	 * 
	 * @return the singleton {@link IDatabase} implementation
	 */
	public static IDatabase getInstance() {
		return theInstance;
	}

}

