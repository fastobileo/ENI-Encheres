package fr.eni.encheres.bll;

import fr.eni.encheres.bo.BusinessException;
import fr.eni.encheres.bo.Retrait;
import fr.eni.encheres.dal.DALException;
import fr.eni.encheres.dal.DAOFactory;
import fr.eni.encheres.dal.RetraitDAO;

public class RetraitManager {

	private RetraitDAO retraitDAO;

	public RetraitManager() {
		retraitDAO = DAOFactory.getRetraitDAO();
	}
	
}
