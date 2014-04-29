package edu.ycp.cs.cs496.locations.model.persist;

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

public class DerbyDatabase {

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
		// FIXME: retry if transaction times out due to deadlock

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

				stmt = conn.prepareStatement("INSERT INTO users (name, password)" +
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



}
