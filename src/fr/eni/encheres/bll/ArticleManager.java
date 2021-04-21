package fr.eni.encheres.bll;


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
	
	public void ajouterArticle (Article articleAInserer)throws BusinessException{
		
		try {
			articleDAO.add(articleAInserer);
		} catch (DALException e) {
			e.printStackTrace();
			throw new BusinessException();
		}
	}
	
	public Article findById(int no_article) throws BusinessException {
		
		Article article = new Article();
		
		try {
			article = articleDAO.findById(no_article);
		} catch (DALException e) {
			e.printStackTrace();
			throw new BusinessException();
		}
		return article;
	}
}
