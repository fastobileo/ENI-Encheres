package fr.eni.encheres.dal;

import java.util.List;

import fr.eni.encheres.bo.Article;

public interface ArticleDAO {
	
	void add (Article article) throws DALException;
	
	Article findById(int no_article) throws DALException;
	
	List<Article> findAll() throws DALException;
	
	void delete(Article article) throws DALException;
	
	void update(Article article) throws DALException;

}
