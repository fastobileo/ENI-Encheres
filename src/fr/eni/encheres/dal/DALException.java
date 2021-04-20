package fr.eni.encheres.dal;

import java.sql.SQLException;

public class DALException extends Exception{

	public DALException(SQLException e) {
		super(e);
	}
	
}
