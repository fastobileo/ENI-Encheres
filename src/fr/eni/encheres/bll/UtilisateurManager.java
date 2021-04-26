package fr.eni.encheres.bll;

import java.util.ArrayList;
import java.util.List;

import fr.eni.encheres.bo.BusinessException;
import fr.eni.encheres.bo.Utilisateur;
import fr.eni.encheres.dal.DALException;
import fr.eni.encheres.dal.DAOFactory;
import fr.eni.encheres.dal.UtilisateurDAO;

public class UtilisateurManager {

	private UtilisateurDAO utilisateurDAO;

	public UtilisateurManager() {
		utilisateurDAO = DAOFactory.getUtilisateurDAO();
	}

	public Utilisateur afficherUtilisateur(int id) throws BusinessException {
		Utilisateur utilisateur = null;

		try {
			utilisateur = utilisateurDAO.findById(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return utilisateur;
	}

	public List<Utilisateur> afficherTousLesUtilisateurs() throws BusinessException {

		List<Utilisateur> listeUtilisteur = new ArrayList<Utilisateur>();

		try {
			listeUtilisteur = utilisateurDAO.findAll();
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listeUtilisteur;
	}

	public void ajoutUtilisateur(Utilisateur utilisateur) throws BusinessException {

		try {
			utilisateurDAO.add(utilisateur);
		} catch (DALException e) {
			e.printStackTrace();
		}
	}

	public Utilisateur seConnecter(String pseudo, String password) throws BusinessException {

		try {
			Utilisateur utilisateurBase = utilisateurDAO.seConnecter(pseudo, password);

			if (utilisateurBase.getId() == null) {
				throw new BusinessException();
			} else {
				return utilisateurBase;
			}

		} catch (DALException e) {
			e.printStackTrace();
		}
		return null;

	}

	public void update(Utilisateur user) throws BusinessException {
		try {
			utilisateurDAO.update(user);
		} catch (Exception e) {
			throw new BusinessException();
		}
	}
	
	public boolean checkingPassword(String mdp, String mdpConfirme) throws BusinessException {
		
		boolean mdpIdentique = false;
		
		if (mdp.equals(mdpConfirme)) {
			
			mdpIdentique = true;
		}
		
		return mdpIdentique;
		
	}
}
