package fr.eni.encheres.dal;

import java.util.List;

import fr.eni.encheres.bo.Enchere;

public interface EnchereDAO {

	void add(Enchere enchere) throws DALException;

	Enchere findById(int no_enchere) throws DALException;

	List<Enchere> findAll() throws DALException;

	void delete(Enchere enchere) throws DALException;

	void update(Enchere enchere) throws DALException;

	Enchere getEnchereInnerJoin(Integer id_enchere) throws DALException;
	
	List<Enchere> findAllAchats() throws DALException;
	
	List<Enchere> findAllVentes() throws DALException;

	void encherir(Integer idEnchere, Integer prix, Integer last) throws DALException;

	void debiter(Integer idUser, Integer credit) throws DALException;

	void crediter(Integer idUser, Integer credit) throws DALException;

}
