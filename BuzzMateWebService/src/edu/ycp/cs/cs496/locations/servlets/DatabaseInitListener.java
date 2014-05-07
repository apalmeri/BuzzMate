package edu.ycp.cs.cs496.locations.servlets;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import edu.ycp.cs.cs496.locations.model.persist.Database;
import edu.ycp.cs.cs496.locations.model.persist.DerbyDatabase;

public class DatabaseInitListener implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent e) {
		// Web app is starting up
		Database.setInstance(new DerbyDatabase());
		System.out.println("Initialized database!");
	}

	@Override
	public void contextDestroyed(ServletContextEvent e) {
		// Web app is shutting down

	}

}
