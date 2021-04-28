package fr.eni.encheres.dal;

import java.util.List;

import fr.eni.encheres.bo.Retrait;

public interface RetraitDAO {
	
	Integer add(Retrait retrait) throws DALException;

	Retrait findById(int no_retrait) throws DALException;
	
	List<Retrait> findAll() throws DALException;
}
