package fr.eni.encheres.ihm;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.encheres.bll.ArticleManager;
import fr.eni.encheres.bo.Article;
import fr.eni.encheres.bo.BusinessException;
import fr.eni.encheres.bo.Categorie;
import fr.eni.encheres.bo.Retrait;
import fr.eni.encheres.bo.Utilisateur;

/**
 * Servlet implementation class ConnectionPageServlet
 */
@WebServlet("/Vendre")
public class VendreArticleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String ATTRIBUT_UTILISATEUR_CONNECTE = "utilisateurConnecte";
	private static final String ATTRIBUT_ARTICLE_A_INSERER = "articleAInserer";
	private static final String VILLE = "ville";
	private static final String CODE_POSTAL = "codePostal";
	private static final String RUE = "rue";
	private static final String DATE_FIN_ENCHERES = "dateFinEncheres";
	private static final String DATE_DEBUT_ENCHERES = "dateDebutEncheres";
	private static final String PRIX_INITIAL = "prixInitial";
	private static final String PARAM_CATEGORIE_CHOISIE = "categorieChoisie";
	private static final String DESCRIPTION = "description";
	private static final String NOM_ARTICLE = "nomArticle";
	private static final String URL_VENTE = "/Vendre";
	private static final String URL_ACCUEIL = "/Accueil";
	

	public void init() throws ServletException {
		super.init();
		
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getRequestDispatcher("/WEB-INF/jsp/vendreArticle.jsp").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");

		// Variables
		List<Integer> listeCodesErreur = new ArrayList<>();
		ArticleManager ArticleManager = new ArticleManager();

		String nomArticle = request.getParameter(NOM_ARTICLE);
		String description = request.getParameter(DESCRIPTION);
		String sPrixInitial = request.getParameter(PRIX_INITIAL);
		String rue = request.getParameter(RUE);
		String codePostal = request.getParameter(CODE_POSTAL);
		String ville = request.getParameter(VILLE);
		String sDateDebutEncheres = request.getParameter(DATE_DEBUT_ENCHERES);
		String sDateFinEncheres = request.getParameter(DATE_FIN_ENCHERES);

		String categorieChoisie = request.getParameter(PARAM_CATEGORIE_CHOISIE);

		Integer prixInitial = 0;
		try {
			prixInitial = Integer.valueOf(sPrixInitial);
		} catch (NumberFormatException nfe) {
			nfe.printStackTrace();
		}


		LocalDateTime dateDebutEncheres = null;
		if (sDateDebutEncheres != "") {
			try {
				dateDebutEncheres = LocalDateTime.parse((sDateDebutEncheres));
			} catch (DateTimeParseException dtpe) {
				dtpe.printStackTrace();
				listeCodesErreur.add(ResultErreurServlets.VIDE_ARTICLE_DATE_DEBUT_ENCHERES_ERREUR);
			}
		} 
		
		
		LocalDateTime dateFinEncheres = null;
		if (sDateFinEncheres != "") {
			try {
				dateFinEncheres = LocalDateTime.parse((sDateFinEncheres));
			} catch (DateTimeParseException dtpe) {
				dtpe.printStackTrace();
				listeCodesErreur.add(ResultErreurServlets.VIDE_ARTICLE_DATE_FIN_ENCHERES_ERREUR);
			}
		}
		
		Article articleAInserer = new Article();
			
				
		Categorie categorieAInserer = new Categorie();
		ArticleManager articleManager = new ArticleManager();
		List<Categorie> listeCategorie;
		
		Utilisateur vendeur; // = (Utilisateur) session.getAttribute(ATTRIBUT_UTILISATEUR_CONNECTE);
		
		
//		articleAInserer = new Article();
//		articleAInserer.setPrix_initial(prixInitial);
//		Retrait retrait = new Retrait(rue,codePostal,ville);
//		articleAInserer.setRetrait(retrait);
//		articleAInserer.setPrixVente(prixInitial);
//
//		//Appel de la BLL pour ajouter l'article
//		try {
//			articleManager.ajouterArticle(articleAInserer);
//		// Si tout s'est bien passé, retourne à la page d'accueil
//		session.setAttribute(ATTRIBUT_ARTICLE_A_INSERER, articleAInserer);
//		dispatcher = request.getRequestDispatcher(URL_ACCUEIL);
//		dispatcher.forward(request, response);
//		
//		
//		} catch (BusinessException be) {
//		
//	
//		be.printStackTrace();
//		ServletTools.afficherErreurs(request, response, be.getListeCodesErreur(), URL_VENTE);
//	}
				
	}

}
