package fr.eni.encheres.bll;

import java.util.List;

import fr.eni.encheres.bo.Enchere;
import fr.eni.encheres.dal.EnchereDAO;

public class EnchereManager {

	private EnchereDAO enchereDAO;

	public EnchereManager(EnchereDAO encherDAO) {
		enchereDAO = fr.eni.encheres.dal.DAOFactory.getEnchereDAO();
	}

	public Enchere afficherEnchereById(int id) {		
		return enchereDAO.findById(id);			 	
	}
	
	public List<Enchere> afficherToutesLesEncheres() {
		return enchereDAO.findAll();
	}
	
	public void ajoutEnchere(Enchere enchere) {
		enchereDAO.add(enchere);
	}
	
}
