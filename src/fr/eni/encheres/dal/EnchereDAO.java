package fr.eni.encheres.dal;

import java.util.List;

import fr.eni.encheres.bo.Enchere;

public interface EnchereDAO {

	void add (Enchere enchere);
	
	Enchere findById(int no_enchere);
	
	List<Enchere> findAll();
	
	void delete(Enchere enchere);
	
	void update(Enchere enchere);
}
