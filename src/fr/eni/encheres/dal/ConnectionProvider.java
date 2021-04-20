package fr.eni.encheres.dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class ConnectionProvider {

	public static Connection getConnection() throws SQLException {
		DriverManager.registerDriver(new com.microsoft.sqlserver.jdbc.SQLServerDriver());
		String dbURL = "jdbc:sqlserver://localhost;databaseName=ENCHERES";
		String user = "sa";
		String pass = "leoDLR31102000,";
		Connection conn = DriverManager.getConnection(dbURL, user, pass);
		return conn;
	}
}
