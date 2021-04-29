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
import fr.eni.encheres.bo.BusinessException;
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
		List<Enchere> listeVentes = new ArrayList<Enchere>();
		List<Enchere> listeAchats = new ArrayList<Enchere>();
		
		// Affichage des ventes de l'utilisateur en cours 
		try {
			listeCategorie = categorieManager.afficherToutesLesCategories();
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "La liste des enchères n'est pas disponible");
		}
		
		try {
			listeVentes = enchereManager.getVentes();
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			listeAchats = enchereManager.getAchats();
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		request.setAttribute("listeCategorie", listeCategorie);
		request.getRequestDispatcher("/WEB-INF/jsp/home.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
