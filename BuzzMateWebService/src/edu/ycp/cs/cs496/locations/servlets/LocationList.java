package edu.ycp.cs.cs496.locations.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ycp.cs.cs496.locations.controllers.GetLocationByName;
import edu.ycp.cs.cs496.locations.controllers.GetLocationList;
import edu.ycp.cs.cs496.locations.model.Location;
import edu.ycp.cs.cs496.locations.model.json.JSON;

public class LocationList extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		

		String pathInfo = req.getPathInfo();
		if(pathInfo == null || pathInfo.equals("") || pathInfo.equals("/")){
			GetLocationList controller = new GetLocationList();
			List<Location> locations = controller.getLocationList();
			
			resp.setStatus(HttpServletResponse.SC_OK);
			resp.setContentType("application/json");
			
			// Return the item in JSON format
			JSON.getObjectMapper().writeValue(resp.getWriter(), locations);
			
			return;			
		}
		
		// Get the item name
				if (pathInfo.startsWith("/")) {
					pathInfo = pathInfo.substring(1);
				}
				
				// Use a GetItemByName controller to find the item in the database
				GetLocationByName controller = new GetLocationByName();
				Location location = controller.getLocation(pathInfo);
				
				if (location == null) {
					// No such item, so return a NOT FOUND response
					resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
					resp.setContentType("text/plain");
					resp.getWriter().println("No such item: " + pathInfo);
					return;
				}
				
				// Set status code and content type
				resp.setStatus(HttpServletResponse.SC_OK);
				resp.setContentType("application/json");
				
				// Return the item in JSON format
				JSON.getObjectMapper().writeValue(resp.getWriter(), location);
		
	}

}
