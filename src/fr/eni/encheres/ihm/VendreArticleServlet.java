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
import fr.eni.encheres.servlets.CodesResultatServlets;
import fr.eni.encheres.servlets.HttpSession;
import fr.eni.encheres.servlets.RequestDispatcher;

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
	private static final String URL_VENTE = "/VenteArticle";
	private static final String URL_ACCUEIL = "/Accueil";
	

	public void init() throws ServletException {
		super.init();
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");

		// Variables
		RequestDispatcher dispatcher = null;
		List<Integer> listeCodesErreur = new ArrayList<>();
		HttpSession session = request.getSession();
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
	}

}
