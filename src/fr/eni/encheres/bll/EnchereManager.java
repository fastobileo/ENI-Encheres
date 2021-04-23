package fr.eni.encheres.bll;

import java.util.ArrayList;
import java.util.List;

import fr.eni.encheres.bo.BusinessException;
import fr.eni.encheres.bo.Enchere;
import fr.eni.encheres.dal.DALException;
import fr.eni.encheres.dal.EnchereDAO;

public class EnchereManager {

	private EnchereDAO enchereDAO;

	public EnchereManager() {
		enchereDAO = fr.eni.encheres.dal.DAOFactory.getEnchereDAO();
	}

	public Enchere afficherEnchereById(int id) throws BusinessException {
		Enchere enchere = new Enchere();
		try {
			enchere = enchereDAO.findById(id);
		} catch (DALException e) {
			e.printStackTrace();
		}
		return enchere;
	}

	public List<Enchere> afficherToutesLesEncheres() throws BusinessException {

		List<Enchere> listeEnchere = new ArrayList<Enchere>();

		try {
			listeEnchere = enchereDAO.findAll();
		} catch (DALException e) {
			e.printStackTrace();
		}
		return listeEnchere;
	}

	public void ajoutEnchere(Enchere enchere) throws BusinessException {

		try {
			enchereDAO.add(enchere);
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Enchere getEnchereInnerJoin(Integer no_enchere) throws BusinessException {

		Enchere enchere = null;
		try {
			enchere = enchereDAO.getEnchereInnerJoin(no_enchere);
		} catch (DALException e) {
			e.printStackTrace();
		}
		return enchere;
	}

}
