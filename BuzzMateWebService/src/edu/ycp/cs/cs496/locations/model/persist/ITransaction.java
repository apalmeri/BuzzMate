package edu.ycp.cs.cs496.locations.model.persist;


import java.sql.Connection;
import java.sql.SQLException;

public interface ITransaction<E> {
	public E run(Connection conn) throws SQLException;
}
