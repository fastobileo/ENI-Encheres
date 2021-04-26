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

	void encherir(Integer idEnchere, Integer prix) throws DALException;
}
