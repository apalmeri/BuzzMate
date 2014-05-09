package edu.ycp.cs.cs496.persist;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.ycp.cs.cs496.locations.model.Cab;
import edu.ycp.cs.cs496.locations.model.Location;



/*
public class SqliteDatabase implements IDatabase {
	
	private static final String DATASTORE = "H:/BuzzMateWebService.db";
	
	static {
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (Exception e) {
			throw new IllegalStateException("Could not load sqlite driver");
		}
	}
	
	private interface Transaction<ResultType> {
		public ResultType execute(Connection conn) throws SQLException;

		Map<Integer, Location> run(Connection conn) throws SQLException;
	}

	private static final int MAX_ATTEMPTS = 10;


	public Map<Integer, Location> getLocationFromDB(String name) {
		return executeTransaction(new Transaction<Map<Integer, Location>>() {
			@Override
			public Map<Integer, Location> run(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				
				try {
					Map<Integer, Location> result = new HashMap<Integer, Location>();

					stmt = conn.prepareStatement("select locations.id, locations.name, locations.type, locations.street, locations.city, locations.state, locations.zip, locations.phone from locations");
					stmt.setString(1, name());
					stmt.setString(2, location.getType());
					stmt.setString(3, location.getStreet1());
					stmt.setString(4, location.getCity());
					stmt.setString(5, location.getState());
					stmt.setString(6, location.getMailcode());
					stmt.setString(7, location.getPhonenumber());
					
					resultSet = stmt.executeQuery();
					
					while (resultSet.next()) {
						Location location = new Location();
						loadLocationFromResultSet(location, resultSet, index);
						result.put(location.getId(), location);
					}
					
					return result;
				} finally {
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
				}
			}

			@Override
			public Map<Integer, Location> execute(Connection conn)
					throws SQLException {
				// TODO Auto-generated method stub
				return null;
			}
		});
	}



	public Map<Integer, Location> getLocationListFromDB() {
		return executeTransaction(new Transaction<Map<Integer, Location>>() {
			@Override
			public Map<Integer, Location> execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				
				try {
					// Note: no 'where' clause, so all items will be returned
					Map<Integer, Location> result = new HashMap<Integer, Location>();
					stmt = conn.prepareStatement("select locations.id, locations.name, locations.type, locations.street, locations.city, locations.state, locations.zip, locations.phone from locations");

					resultSet = stmt.executeQuery();
					while (resultSet.next()) {
						Location location = new Location();
						loadLocationFromResultSet(resultSet, location);
						result.put(location.getId(), location);
					}
					
					return result;
				} finally {
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
				}
			}
		});
	}

	
	public<ResultType> ResultType executeTransaction(Transaction<ResultType> txn) {
		try {
			return doExecuteTransaction(txn);
		} catch (SQLException e) {
			throw new PersistenceException("Transaction failed", e);
		}
	}

	public<ResultType> ResultType doExecuteTransaction(Transaction<ResultType> txn) throws SQLException {
		Connection conn = connect();
		
		try {
			int numAttempts = 0;
			boolean success = false;
			ResultType result = null;
			
			while (!success && numAttempts < MAX_ATTEMPTS) {
				try {
					result = txn.execute(conn);
					conn.commit();
					success = true;
				} catch (SQLException e) {
					if (e.getSQLState() != null && e.getSQLState().equals("41000")) {
						// Deadlock: retry (unless max retry count has been reached)
						numAttempts++;
					} else {
						// Some other kind of SQLException
						throw e;
					}
				}
			}
			
			if (!success) {
				throw new SQLException("Transaction failed (too many retries)");
			}
			
			// Success!
			return result;
		} finally {
			DBUtil.closeQuietly(conn);
		}
	}

	private Connection connect() throws SQLException {
		Connection conn = DriverManager.getConnection("jdbc:sqlite:test.db");
		
		// Set autocommit to false to allow multiple the execution of
		// multiple queries/statements as part of the same transaction.
		conn.setAutoCommit(false);
		
		return conn;
	}
	
	
	public void addLocationToDB(final Location location) {
		executeTransaction(new Transaction<Boolean>() {

			PreparedStatement stmt = null;
			ResultSet keys = null;

			@Override
			public Boolean execute(Connection conn) throws SQLException {
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
		

	public void createTables() {
		executeTransaction(new Transaction<Boolean>() {
			@Override
			public Boolean execute(Connection conn) throws SQLException {
				PreparedStatement stmtLocations = null;
				PreparedStatement stmtUsers = null;
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
					// Note that the 'id' column is an autoincrement primary key,
					// so SQLite will automatically assign an id when rows
					// are inserted.
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
					
					return true;
				} finally {
					DBUtil.closeQuietly(stmtLocations);
				}
			}
		});
	}
	
	public void loadInitialData() {
		executeTransaction(new Transaction<Boolean>() {
			@Override
			public Boolean execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				
				try {
					stmt = conn.prepareStatement("insert into items (name, phonenumber, notes) values (?, ?, ?)");
					storeCabNoId(new Cab(), stmt, 1);
					stmt.addBatch();
					storeCabNoId(new Cab(), stmt, 1);
					stmt.addBatch();
					storeCabNoId(new Cab(), stmt, 1);
					stmt.addBatch();
					
					stmt.executeBatch();
					
					return true;
				} finally {
					DBUtil.closeQuietly(stmt);
				}
			}
		});
	}

	protected void storeCabNoId(Cab cab, PreparedStatement stmt, int index) throws SQLException {
		// Note that we are assuming that the Item does not have a valid id,
		// and so are not attempting to store the (invalid) id.
		// This is the preferred approach when inserting a new row into
		// a table in which a unique id is automatically generated.
		stmt.setString(index++, location.getName());
		stmt.setString(index++, location.getType());
		stmt.setString(index++, location.getStreet1());
		stmt.setString(index++, location.getCity());
		stmt.setString(index++, location.getState());
		stmt.setString(index++, location.getMailcode());
		stmt.setString(index++, location.getPhonenumber());
		
		stmt.setString(index++, cab.getName());
		stmt.setString(index++, cab.getPhonenumber());
		stmt.setString(index++, cab.getNotes());
	}
	
	private void loadCabFromResultSet(Cab cab, ResultSet resultSet, int index)
			throws SQLException {
		location.setId(resultSet.getInt(index++));
		location.setName(resultSet.getString(index++));
		location.setType(resultSet.getString(index++));
		location.setStreet1(resultSet.getString(index++));
		location.setCity(resultSet.getString(index++));
		location.setState(resultSet.getString(index++));
		location.setMailcode(resultSet.getString(index++));
		location.setPhonenumber(resultSet.getString(index++));
		
		cab.setString(index++, cab.getName());
		cab.setString(index++, cab.getPhonenumber());
		cab.setString(index++, cab.getNotes());
		
		System.out.printf("cab added");
	}
	
	public static void main(String[] args) {
		SqliteDatabase db = new SqliteDatabase();
		System.out.println("Creating tables...");
		db.createTables();
		System.out.println("Loading initial data...");
		db.loadInitialData();
		System.out.println("Done!");
	}
}
*/
