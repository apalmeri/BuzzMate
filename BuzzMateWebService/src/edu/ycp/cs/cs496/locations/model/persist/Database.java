package edu.ycp.cs.cs496.locations.model.persist;

public class Database {
	
private static final IDatabase theInstance = new FakeDatabase();
	
	/**
	 * Get the singleton {@link IDatabase} implementation.
	 * 
	 * @return the singleton {@link IDatabase} implementation
	 */
	public static IDatabase getInstance() {
		return theInstance;
	}

}

