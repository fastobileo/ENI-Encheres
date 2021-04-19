package fr.eni.encheres.dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class ConnectionProvider {

	public static Connection getConnection() throws SQLException {
		String dbURL = "jdbc:sqlserver://localhost;user=encheres;password=P@ssw0rd";
		Connection conn = DriverManager.getConnection(dbURL);
		return conn;
	}
}
