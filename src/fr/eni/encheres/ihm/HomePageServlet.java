package fr.eni.encheres.ihm;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.encheres.bll.CategorieManager;
import fr.eni.encheres.bll.EnchereManager;
import fr.eni.encheres.bo.Categorie;
import fr.eni.encheres.bo.Enchere;

@WebServlet("/")
public class HomePageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private CategorieManager categorieManager = new CategorieManager();
	private EnchereManager enchereManager = new EnchereManager();

	public HomePageServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		List<Categorie> listeCategorie = new ArrayList<Categorie>();
		List<Enchere> listeEncheres = new ArrayList<Enchere>();

		// Affichage des ventes de l'utilisateur en cours
		try {
			String id;
			String filtre;
			if (request.getSession().getAttribute("idUser") != null && request.getParameter("filtre1") != null) {
				id = (String) request.getSession().getAttribute("idUser").toString();
				filtre = request.getParameter("filtre1");
				if (filtre.equals("achat")) {
					listeEncheres = enchereManager.getAchats(request.getParameter("open"), request.getParameter("mine"),
							request.getParameter("win"), id);
				} else if (filtre.equals("vente")) {
					listeEncheres = enchereManager.getVentes(request.getParameter("VenteEnCours"),
							request.getParameter("VenteNonDebutees"), request.getParameter("VentesTerminees"), id);
				} else {
					listeEncheres = enchereManager.afficherToutesLesEncheres();
				}
				listeCategorie = categorieManager.afficherToutesLesCategories();
			} else {
				listeEncheres = enchereManager.afficherToutesLesEncheres();
				listeCategorie = categorieManager.afficherToutesLesCategories();
			}
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "La liste des enchères n'est pas disponible");
		}
		request.setAttribute("listeEnchere", listeEncheres);
		request.setAttribute("listeCategorie", listeCategorie);
		request.getRequestDispatcher("/WEB-INF/jsp/home.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
