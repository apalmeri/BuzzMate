package edu.ycp.cs.cs496.locations.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ycp.cs.cs496.locations.controllers.GetCabByName;
import edu.ycp.cs.cs496.locations.controllers.GetCabList;
import edu.ycp.cs.cs496.locations.model.Cab;
import edu.ycp.cs.cs496.locations.model.json.JSON;

public class CabList extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		

		String pathInfo = req.getPathInfo();
		if(pathInfo == null || pathInfo.equals("") || pathInfo.equals("/")){
			GetCabList controller = new GetCabList();
			List<Cab> cabs = controller.getCabList();
			
			resp.setStatus(HttpServletResponse.SC_OK);
			resp.setContentType("application/json");
			
			// Return the item in JSON format
			JSON.getObjectMapper().writeValue(resp.getWriter(), cabs);
			
			return;			
		}
		
		// Get the item name
		if (pathInfo.startsWith("/")) {
			pathInfo = pathInfo.substring(1);
		}
		
		//Anything else would be the name of a location
		// Use a GetItemByName controller to find the item in the database
		GetCabByName controller = new GetCabByName();
		Cab cab = controller.getCab(pathInfo);
		
		if (cab == null) {
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
		JSON.getObjectMapper().writeValue(resp.getWriter(), cab);
		
	}

}
