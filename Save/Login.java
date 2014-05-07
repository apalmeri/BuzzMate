package edu.ycp.cs.cs496.locations.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ycp.cs.cs496.locations.controllers.AddUserController;
import edu.ycp.cs.cs496.locations.controllers.User;
import edu.ycp.cs.cs496.locations.controllers.ValidateUserController;
import edu.ycp.cs.cs496.locations.model.persist.Database;
import edu.ycp.cs.cs496.locations.model.persist.IDatabase;

public class Login extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.getRequestDispatcher("/_view/login.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		User user = new User();
		user.setUsername(username);
		user.setPassword(password);
		
		
		// TODO: use controller/database to check whether username/password are known
		ValidateUserController validateUser = new ValidateUserController();
		AddUserController addUser = new AddUserController();
		
		if(validateUser.containsUser(Database.getInstance(), user) == true){
			// Redirect to home page
			resp.sendRedirect("./home");
		} else {
			//req.setAttribute("message", "Unknown username/password");
			addUser.addUser(Database.getInstance(), user);
			req.setAttribute("message", "new user added");
			req.getRequestDispatcher("/_view/login.jsp").forward(req, resp);
		}
	}
}
