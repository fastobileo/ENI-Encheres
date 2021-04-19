package fr.eni.encheres.bll;

import java.util.ArrayList;
import java.util.List;

import fr.eni.encheres.bo.Article;
import fr.eni.encheres.bo.BusinessException;
import fr.eni.encheres.bo.Categorie;
import fr.eni.encheres.dal.ArticleDAO;
import fr.eni.encheres.dal.DAOFactory;


public class ArticleManager {
	private ArticleDAO articleDAO;

	public ArticleManager() {
		this.articleDAO = DAOFactory.getArticleDAO();
	}
	
	public Article ajouterArticle (Article articleAInserer)throws BusinessException{
		
		articleDAO.add(articleAInserer);
		
		return articleAInserer;
	}
	
	public List<Categorie> getCategorie () throws BusinessException{
		
		List<Categorie> listeCategorie = new ArrayList<>();
		listeCategorie = articleDAO.findAllCategorie();
		return listeCategorie;
		
	}

	
}
