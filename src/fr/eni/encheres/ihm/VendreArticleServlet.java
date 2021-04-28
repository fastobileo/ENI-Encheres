package fr.eni.encheres.ihm;

import java.io.IOException;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.encheres.bll.ArticleManager;
import fr.eni.encheres.bll.CategorieManager;
import fr.eni.encheres.bll.RetraitManager;
import fr.eni.encheres.bll.UtilisateurManager;
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

	private static final String VILLE = "ville";
	private static final String CODE_POSTAL = "codePostal";
	private static final String RUE = "rue";
	private static final String DATE_FIN_ENCHERES = "dateFinEncheres";
	private static final String DATE_DEBUT_ENCHERES = "dateDebutEncheres";
	private static final String PRIX_INITIAL = "prixInitial";
	private static final String PARAM_CATEGORIE_CHOISIE = "categorieChoisie";
	private static final String DESCRIPTION = "description";
	private static final String NOM_ARTICLE = "nomArticle";

	public void init() throws ServletException {
		super.init();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		CategorieManager categorieManager = new CategorieManager();
		List<Categorie> listeCategorie = null;
		
		try {
			HttpSession session = request.getSession();
			Integer IdUser = (Integer) session.getAttribute("idUser");
			Utilisateur user = (Utilisateur) session.getAttribute("user");
			if(IdUser == null) {
				request.setAttribute("errorMessage", "Il faut se connecter pour vendre un article");
				request.getRequestDispatcher("/WEB-INF/jsp/connection.jsp").forward(request, response);
			}else{
				
				request.setAttribute("user", user);
				listeCategorie = categorieManager.afficherToutesLesCategories();

				request.setAttribute("listeCategorie", listeCategorie);
				
			}
		} catch (Exception e) {
			request.setAttribute("errorMessage", "Il faut se connecter pour vendre un article");
		}
		request.getRequestDispatcher("/WEB-INF/jsp/vendreArticle.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		
		System.out.println("boum !");
		
		ArticleManager articleManager = new ArticleManager();
		RetraitManager retraitManager = new RetraitManager();
		CategorieManager categorieManager = new CategorieManager();
		UtilisateurManager utilisateurManager = new UtilisateurManager();
		
		Article article = new Article();
		Retrait retrait = new Retrait();
		Categorie categorie = new Categorie();

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

		try {
			
			String nomArticle = request.getParameter(NOM_ARTICLE);
			String description = request.getParameter(DESCRIPTION);
			Integer prixInitial = Integer.parseInt(request.getParameter(PRIX_INITIAL));
			String rue = request.getParameter(RUE);
			String codePostal = request.getParameter(CODE_POSTAL);
			String ville = request.getParameter(VILLE);
			Date dateDebutEncheres = simpleDateFormat.parse(request.getParameter(DATE_DEBUT_ENCHERES));
			Date dateFinEncheres = simpleDateFormat.parse(request.getParameter(DATE_FIN_ENCHERES));
			
			System.out.println(dateFinEncheres);
			Integer categorieChoisie = Integer.parseInt(request.getParameter(PARAM_CATEGORIE_CHOISIE));
			System.out.println(categorieChoisie);
			categorie = categorieManager.findById(categorieChoisie);
			article.setCategorie(categorie);	
			
			HttpSession session = request.getSession();
			Integer IdUser = (Integer) session.getAttribute("idUser");
			Utilisateur user = utilisateurManager.afficherUtilisateur(IdUser);
			article.setUtilisateur(user);
			
			System.out.println(user);
			
			article.setNom_article(nomArticle);
			article.setDescription(description);
			article.setPrix_initial(prixInitial);
			article.setDate_debut_encheres(dateDebutEncheres);
			article.setDate_fin_encheres(dateFinEncheres);
			
			retrait.setRue(rue);
			retrait.setCode_postal(codePostal);
			retrait.setVille(ville);		
			
			
			Integer idRetrait = retraitManager.ajoutRetrait(retrait);
			article.setRetrait(retrait);
			System.out.println(retrait);
			
			articleManager.ajouterArticle(article);
			System.out.println(article);
			
		} catch (BusinessException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}

		
			
		
		response.sendRedirect(request.getContextPath());

	}

}
