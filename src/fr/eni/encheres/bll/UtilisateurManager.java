package fr.eni.encheres.bll;

import java.util.List;

import fr.eni.encheres.bo.Utilisateur;
import fr.eni.encheres.dal.DAOFactory;
import fr.eni.encheres.dal.UtilisateurDAO;

public class UtilisateurManager {
	
	private UtilisateurDAO utilisateurDAO;

	public UtilisateurManager(UtilisateurDAO utilisateurDAO) {
		utilisateurDAO = DAOFactory.getUtilisateurDAO();
		}
	
	public Utilisateur afficherUtilisateur(int id) {
		return utilisateurDAO.findById(id);
	}
	
	public List<Utilisateur> afficherTousLesUtilisateurs(){
		return utilisateurDAO.findAll();
	}
	
	public void ajoutUtilisateur(Utilisateur utilisateur) {
		utilisateurDAO.add(utilisateur);
	}
}
