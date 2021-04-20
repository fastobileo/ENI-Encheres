package fr.eni.encheres.bll;

import java.util.ArrayList;
import java.util.List;

import fr.eni.encheres.bo.Article;
import fr.eni.encheres.bo.BusinessException;
// import fr.eni.encheres.bo.Categorie;
import fr.eni.encheres.dal.ArticleDAO;
import fr.eni.encheres.dal.DALException;
import fr.eni.encheres.dal.DAOFactory;


public class ArticleManager {
	private ArticleDAO articleDAO;

	public ArticleManager() {
		this.articleDAO = DAOFactory.getArticleDAO();
	}
	
	public Article ajouterArticle (Article articleAInserer)throws BusinessException{
		
		try {
			articleDAO.add(articleAInserer);
		} catch (DALException e) {
			e.printStackTrace();
			throw new BusinessException();
		}
		
		return articleAInserer;
	}
	
/*	public List<Categorie> getCategorie () throws BusinessException{
		
		List<Categorie> listeCategorie = new ArrayList<>();
		try {
			listeCategorie = articleDAO.findAllCategorie();
		} catch (DALException e) {
			e.printStackTrace();
			throw new BusinessException();
		}		
		return listeCategorie;
	} */
}
