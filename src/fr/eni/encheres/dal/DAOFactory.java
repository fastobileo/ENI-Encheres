package fr.eni.encheres.dal;

public class DAOFactory {
	
	public static UtilisateurDAO getUtilisateurDAO() {
		return new UtilisateurDAOImpl();
	}
	
	public static EnchereDAO getEnchereDAO() {
		return new EnchereDAOImpl();
	}
	
	public static ArticleDAO getArticleDAO() {
		return new ArticleDAOImpl();
	}
	
	public static RetraitDAO getRetraitDAO() {
		return new RetraitDAOImpl();
	}
}
