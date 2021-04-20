package fr.eni.encheres.dal;

import java.util.List;

import fr.eni.encheres.bo.Utilisateur;

public interface UtilisateurDAO{

	void add(Utilisateur utilisateur) throws DALException;
	
	Utilisateur findById(int id) throws DALException;
	 
	List<Utilisateur> findAll() throws DALException;	
	
	void update(Utilisateur utilisateur) throws DALException;
	
	void delete(Utilisateur utilisateur) throws DALException;
	
	public Utilisateur seConnecter(int id) throws DALException;
}
