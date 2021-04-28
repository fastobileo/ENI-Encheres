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
	
	public Retrait findById(int no_retrait) throws BusinessException {
		
		Retrait retrait = new Retrait();
		
		try {
			retrait = retraitDAO.findById(no_retrait);
		} catch (DALException e) {
			e.printStackTrace();
			throw new BusinessException();
		}
		
		return retrait;
	}
	
	public Integer ajoutRetrait(Retrait retrait) throws BusinessException {
		Integer id;
		try {
			id = retraitDAO.add(retrait);
		} catch (DALException e) {
			e.printStackTrace();
			throw new BusinessException();
		}
		
		return id;
	} 
}
