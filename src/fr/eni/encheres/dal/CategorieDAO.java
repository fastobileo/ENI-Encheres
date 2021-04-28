package fr.eni.encheres.dal;

import java.util.List;

import fr.eni.encheres.bo.Categorie;

public interface CategorieDAO {
	
	List<Categorie> findAll() throws DALException;
	
	Categorie findById(int no_article) throws DALException;
}
