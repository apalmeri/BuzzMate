package edu.ycp.cs.cs496.locations.model.persist;

// REFERENCED MY CS320 PROJECT FOR DATABASE WORK WHICH HOVEMEYER AND DREW HELPED WITH

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.ycp.cs.cs496.locations.controllers.User;
import edu.ycp.cs.cs496.locations.model.Cab;
import edu.ycp.cs.cs496.locations.model.Location;
import edu.ycp.cs.cs496.locations.model.persist.IDatabase;

public class DerbyDatabase implements IDatabase {

	private static final String DATASTORE = "H:/BuzzMateWebService.db";

	static {
		try {
			Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
		} catch (Exception e) {
			throw new RuntimeException("Could not load Derby JDBC driver");
		}
	}

	private class DatabaseConnection {
		public Connection conn;
		public int refCount;
	}

	private final ThreadLocal<DatabaseConnection> connHolder = new ThreadLocal<DatabaseConnection>();

	private DatabaseConnection getConnection() throws SQLException {
		DatabaseConnection dbConn = connHolder.get();
		if (dbConn == null) {
			dbConn = new DatabaseConnection();
			dbConn.conn = DriverManager.getConnection("jdbc:derby:" + DATASTORE + ";create=true");
			dbConn.refCount = 0;
			connHolder.set(dbConn);
		}
		dbConn.refCount++;
		return dbConn;
	}

	private void releaseConnection(DatabaseConnection dbConn) throws SQLException {
		dbConn.refCount--;
		if (dbConn.refCount == 0) {
			try {
				dbConn.conn.close();
			} finally {
				connHolder.set(null);
			}
		}
	}

	private<E> E databaseRun(ITransaction<E> transaction) {
		try {
			DatabaseConnection dbConn = getConnection();

			try {
				boolean origAutoCommit = dbConn.conn.getAutoCommit();
				try {
					dbConn.conn.setAutoCommit(false);

					return transaction.run(dbConn.conn);
				} finally {
					dbConn.conn.setAutoCommit(origAutoCommit);
				}
			} finally {
				releaseConnection(dbConn);
			}
		} catch (SQLException e) {
			throw new RuntimeException("SQLException accessing database", e);
		}
	}
	
	public Map<Integer, User> getUsersFromDB() {
		return databaseRun(new ITransaction<Map<Integer, User>>() {
			@Override
			public Map<Integer, User> run(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;

				try {
					Map<Integer, User> result = new HashMap<Integer, User>();

					stmt = conn.prepareStatement("select users.id, users.name, users.password from users");

					resultSet = stmt.executeQuery();
					while (resultSet.next()) {
						User user = new User();
						loadUserFromResultSet(resultSet, user);
						result.put(user.getId(), user);
					}

					return result;
				} finally {
					DBUtil.closeQuietly(stmt);
				}
			}
		});
	}
	
	public void addUserToDB(final User user) {

		databaseRun(new ITransaction<Boolean>() {

			PreparedStatement stmt = null;
			ResultSet keys = null;

			@Override
			public Boolean run(Connection conn) throws SQLException {
				try{

				stmt = conn.prepareStatement("INSERT INTO users (name, password) " +
											 "VALUES (?, ?)", PreparedStatement.RETURN_GENERATED_KEYS);			

				stmt.setString(1, user.getUsername());
				stmt.setString(2, user.getPassword());

				stmt.executeUpdate();

				keys = stmt.getGeneratedKeys();
				if (!keys.next()) {
					throw new SQLException("Couldn't get generated key");
				}
				user.setId(keys.getInt(1));

				return null;

				} finally {
					DBUtil.closeQuietly(stmt);
					DBUtil.closeQuietly(keys);
				}
			}	
		});		
	}
	
	private void loadUserFromResultSet(ResultSet resultSet, User user)
			throws SQLException {
		user.setId(resultSet.getInt(1));
		user.setUsername(resultSet.getString(2));
		user.setPassword(resultSet.getString(3));
	}

	void createTables() throws SQLException {
		databaseRun(new ITransaction<Boolean>() {
			@Override
			public Boolean run(Connection conn) throws SQLException {
				PreparedStatement stmtLocations = null;
				PreparedStatement stmtUsers = null;
				PreparedStatement stmtCabs = null;
				try {
					stmtUsers = conn.prepareStatement(
							"create table users (" +
							"id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), " +
							"name VARCHAR(64) NOT NULL, " +
							"password VARCHAR(64) " +
							")"
													);
					stmtUsers.executeUpdate();

				} finally {
					DBUtil.closeQuietly(stmtUsers);
				}
				
				try {
					stmtLocations = conn.prepareStatement(
							"create table locations (" +
							"id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), " +
							"name VARCHAR(64) NOT NULL, " +
							"type VARCHAR(64) NOT NULL, " +
							"street VARCHAR(64) NOT NULL, " +
							"city VARCHAR(64) NOT NULL, " + 
							"state VARCHAR(64) NOT NULL, " + 
							"zip VARCHAR(64) NOT NULL, " + 
							"phone VARCHAR(64) NOT NULL " +
							")"
													);
					stmtLocations.executeUpdate();

				} finally {
					DBUtil.closeQuietly(stmtLocations);
				}
				
				try {
					stmtCabs = conn.prepareStatement(
							"create table cabs (" +
							"id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), " +
							"name VARCHAR(64) NOT NULL, " +
							"phonenumber VARCHAR(64) NOT NULL, " +
							"notes VARCHAR(64) NOT NULL " +
							")"
													);
					stmtCabs.executeUpdate();

				} finally {
					DBUtil.closeQuietly(stmtCabs);
				}
				return true;
			}
		});
	}

	private Location loadLocationFromResultSet(ResultSet resultSet)
			throws SQLException {
		Location location = new Location();
		location.setId(resultSet.getInt(1));
		location.setName(resultSet.getString(2));
		location.setType(resultSet.getString(3));
		location.setStreet1(resultSet.getString(4));
		location.setCity(resultSet.getString(5));
		location.setState(resultSet.getString(6));
		location.setMailcode(resultSet.getString(7));
		location.setPhonenumber(resultSet.getString(8));
		
		return location;
	}

	@Override
	public void addLocationToDB(final Location location) {
		databaseRun(new ITransaction<Boolean>() {

			PreparedStatement stmt = null;
			ResultSet keys = null;

			@Override
			public Boolean run(Connection conn) throws SQLException {
				try{

				stmt = conn.prepareStatement("INSERT INTO locations (name, type, street, city, state, zip, phone) " + 
											 "VALUES (?, ?, ?, ?, ?, ?, ?)", PreparedStatement.RETURN_GENERATED_KEYS);			

				stmt.setString(1, location.getName());
				stmt.setString(2, location.getType());
				stmt.setString(3, location.getStreet1());
				stmt.setString(4, location.getCity());
				stmt.setString(5, location.getState());
				stmt.setString(6, location.getMailcode());
				stmt.setString(7, location.getPhonenumber());
				stmt.executeUpdate();
				
				System.out.printf("location added");
				keys = stmt.getGeneratedKeys();
				if (!keys.next()) {
					throw new SQLException("Couldn't get generated key");
				}
				location.setId(keys.getInt(1));

				return null;

				} finally {
					DBUtil.closeQuietly(stmt);
					DBUtil.closeQuietly(keys);
				}
			}	
		});	
		
		
	}


	@Override
	public List<Location> getLocationByTypeFromDB(final String type) {
		return databaseRun(new ITransaction<List<Location>>() {
			@Override
			public List<Location> run(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;

				try {
					List<Location> result = new ArrayList<Location>();

					stmt = conn.prepareStatement("select locations.id, locations.name, locations.type, locations.street, locations.city, locations.state, locations.zip, locations.phone from locations");

					resultSet = stmt.executeQuery();
					while (resultSet.next()) {
						Location location = loadLocationFromResultSet(resultSet);
						if(location.getType().equals(type)){
							result.add(location);
						}
					}

					return result;
				} finally {
					DBUtil.closeQuietly(stmt);
				}
			}
		});
	}

	@Override
	public List<Location> getLocationListFromDB() {
		return databaseRun(new ITransaction<List<Location>>() {
			@Override
			public List<Location> run(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;

				try {
					List<Location> result = new ArrayList<Location>();

					stmt = conn.prepareStatement("select locations.id, locations.name, locations.type, locations.street, locations.city, locations.state, locations.zip, locations.phone from locations");

					resultSet = stmt.executeQuery();
					while (resultSet.next()) {
						Location location = loadLocationFromResultSet(resultSet);
						result.add(location);
					}

					return result;
				} finally {
					DBUtil.closeQuietly(stmt);
				}
			}
		});
	}
	
	public Location getLocationFromDB(final String name) {
		return databaseRun(new ITransaction<Location>() {
			@Override
			public Location run(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;

				try {
					Location result = new Location();

					stmt = conn.prepareStatement("select locations.id, locations.name, locations.type, locations.street, locations.city, locations.state, locations.zip, locations.phone from locations");

					resultSet = stmt.executeQuery();
					while (resultSet.next()) {
						Location location = loadLocationFromResultSet(resultSet);
						if(location.getName().equals(name)){
							result = location;
						}
					}

					return result;
				} finally {
					DBUtil.closeQuietly(stmt);
				}
			}
		});
	}
	
	@Override
	public Cab getCab(final String cabName) {
		return databaseRun(new ITransaction<Cab>() {
			@Override
			public Cab run(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;

				try {
					Cab result = new Cab();

					stmt = conn.prepareStatement("select cab.id, cab.name, cab.phonenumber, cab.notes");

					resultSet = stmt.executeQuery();
					while (resultSet.next()) {
						Cab cab = loadCabFromResultSet(resultSet);
						if(cab.getName().equals(cabName)){
							result = cab;
						}
					}

					return result;
				} finally {
					DBUtil.closeQuietly(stmt);
				}
			}
		});
	}

	protected Cab loadCabFromResultSet(ResultSet resultSet) 
			throws SQLException {
		Cab cab = new Cab();
		cab.setId(resultSet.getInt(1));
		cab.setName(resultSet.getString(2));
		cab.setPhone(resultSet.getString(3));
		cab.setNotes(resultSet.getString(4));
		
		return cab;
	}

	@Override
	public List<Cab> getCab() {
		return databaseRun(new ITransaction<List<Cab>>() {
			@Override
			public List<Cab> run(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;

				try {
					List<Cab> result = new ArrayList<Cab>();

					stmt = conn.prepareStatement("select cab.id, cab.name, cab.phonenumber, cab.notes");

					resultSet = stmt.executeQuery();
					while (resultSet.next()) {
						Cab cab = loadCabFromResultSet(resultSet);
						result.add(cab);
					}

					return result;
				} finally {
					DBUtil.closeQuietly(stmt);
				}
			}
		});
	}
	
	@Override
	public void addCabToDB(final Cab cab) {
		databaseRun(new ITransaction<Boolean>() {

			PreparedStatement stmt = null;
			ResultSet keys = null;

			@Override
			public Boolean run(Connection conn) throws SQLException {
				try{

				stmt = conn.prepareStatement("INSERT INTO cabs (name, phonenumber, notes) " + 
											 "VALUES (?, ?, ?)", PreparedStatement.RETURN_GENERATED_KEYS);			

				stmt.setString(1, cab.getName());
				stmt.setString(2, cab.getPhonenumber());
				stmt.setString(3, cab.getNotes());
				stmt.executeUpdate();
				
				keys = stmt.getGeneratedKeys();
				if (!keys.next()) {
					throw new SQLException("Couldn't get generated key");
				}
				cab.setId(keys.getInt(1));

				return null;

				} finally {
					DBUtil.closeQuietly(stmt);
					DBUtil.closeQuietly(keys);
				}
			}	
		});	
		
		
	}
}
