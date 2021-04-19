package fr.eni.encheres.bll;

import java.sql.Connection;
import java.sql.SQLException;

import fr.eni.encheres.dal.ConnectionProvider;
import fr.eni.encheres.dal.DAOFactory;
import fr.eni.encheres.dal.UtilisateurDAO;

public class TestManager {
	static UtilisateurDAO con;

	public TestManager() {
		con = DAOFactory.getUtilisateurDAO();
	}

	public static void main(String[] args) {
		try {
			Connection connection = ConnectionProvider.getConnection();
			System.out.println(connection.isClosed());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
