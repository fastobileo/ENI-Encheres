package fr.eni.encheres.bll;

import java.util.ArrayList;
import java.util.List;

import fr.eni.encheres.bo.BusinessException;
import fr.eni.encheres.bo.Categorie;
import fr.eni.encheres.dal.CategorieDAO;
import fr.eni.encheres.dal.DALException;
import fr.eni.encheres.dal.DAOFactory;

public class CategorieManager {

	private CategorieDAO categorieDAO;

	public List<Categorie> afficherToutesLesCategories() throws BusinessException {
		categorieDAO = DAOFactory.getCategorieDAO();
		List<Categorie> listeCategorie = new ArrayList<Categorie>();

		try {
			listeCategorie = categorieDAO.findAll();
		} catch (DALException e) {
			e.printStackTrace();
		}
		return listeCategorie;
	}

	public Categorie findById(int no_categorie) throws BusinessException {
		categorieDAO = DAOFactory.getCategorieDAO();
		Categorie categorie = new Categorie();

		try {
			categorie = categorieDAO.findById(no_categorie);
		} catch (DALException e) {
			e.printStackTrace();
			throw new BusinessException();
		}
		return categorie;
	}
}
