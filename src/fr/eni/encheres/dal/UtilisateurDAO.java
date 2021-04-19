package fr.eni.encheres.dal;

import java.util.List;

import fr.eni.encheres.bo.Utilisateur;

public interface UtilisateurDAO{

	void add(Utilisateur utilisateur);
	
	Utilisateur findById(int id);
	
	List<Utilisateur> findAll();	
	
	void update(Utilisateur utilisateur);
	
	void delete(Utilisateur utilisateur);
}
