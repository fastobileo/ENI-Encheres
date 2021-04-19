package fr.eni.encheres.dal;

import java.util.List;

import fr.eni.encheres.bo.Article;

public interface ArticleDAO {
	
void add (Article article);
	
	Article findById(int no_article);
	
	List<Article> findAll();

}
