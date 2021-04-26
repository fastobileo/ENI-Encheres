package fr.eni.encheres.dal;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.eni.encheres.bo.Categorie;

public class CategorieDAOImpl implements CategorieDAO{


	private final String FIND_ALL = "select no_categorie, libelle FROM CATEGORIES";
	
	public List<Categorie> findAll() throws DALException {
		
		List<Categorie> listeCategories = new ArrayList<Categorie>();
		
		try(Connection connection = ConnectionProvider.getConnection()) {
			
			Statement st = connection.createStatement();
			
			ResultSet rs = st.executeQuery(FIND_ALL);
			
			while(rs.next()) {
				Categorie categorie = new Categorie();
				categorie.setNoCategorie(rs.getInt("no_categorie"));
				categorie.setLibelle(rs.getString("libelle"));
				listeCategories.add(categorie);
			}			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DALException(e);
		}		
		return listeCategories;
	}
}
