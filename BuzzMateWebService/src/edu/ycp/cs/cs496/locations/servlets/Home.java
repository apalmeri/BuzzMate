package edu.ycp.cs.cs496.locations.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ycp.cs.cs496.locations.controllers.GetLocation;

public class Home extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
			req.getRequestDispatcher("/_view/home.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		String logOut = req.getParameter("logOut");
		
		if (logOut != null) {
			System.out.println("logging out");
			req.getRequestDispatcher("/_view/login.jsp").forward(req, resp);
		}else{
			System.out.println("Logging out");
			req.getRequestDispatcher("/_view/login.jsp").forward(req, resp);
		}
		
		
	}
	
}
