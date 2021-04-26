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

/**
 * Servlet implementation class HomePageServlet
 */
@WebServlet("/")
public class HomePageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private EnchereManager enchereManager = new EnchereManager();
	private CategorieManager categorieManager = new CategorieManager();
	
	public HomePageServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{	
		
		List<Enchere> listeEnchere = new ArrayList<Enchere>();
		List<Categorie> listeCategorie = null;
		
		try {
			listeEnchere = enchereManager.afficherToutesLesEncheres();
			listeCategorie = categorieManager.afficherToutesLesCategories();			
			
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "La liste des enchères n'est pas disponible");
		}
		
		System.out.println(listeEnchere.get(0).toString());
		
		request.setAttribute("listeCategorie", listeCategorie);
		request.setAttribute("listeEnchere", listeEnchere);
		request.getRequestDispatcher("/WEB-INF/jsp/home.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
