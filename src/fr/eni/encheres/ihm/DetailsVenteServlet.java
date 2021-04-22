package fr.eni.encheres.ihm;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.encheres.bll.ArticleManager;
import fr.eni.encheres.bll.EnchereManager;
import fr.eni.encheres.bll.RetraitManager;
import fr.eni.encheres.bo.Article;
import fr.eni.encheres.bo.Enchere;
import fr.eni.encheres.bo.Retrait;

@WebServlet("/detailsVente")
public class DetailsVenteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ArticleManager articleManager;
	private EnchereManager enchereManager;
	private RetraitManager retraitManager;

	public DetailsVenteServlet() {
		articleManager = new ArticleManager();
		enchereManager = new EnchereManager();
		retraitManager = new RetraitManager();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Article article = null;
		Enchere enchere = null;
		Retrait retrait = null;
		Integer id_article = null;
		Integer id_enchere = null;
		Integer id_retrait = null;

		try {
			id_enchere = Integer.parseInt(request.getParameter("id"));
			id_article = Integer.parseInt(request.getParameter("id_article"));
			id_retrait = Integer.parseInt(request.getParameter("no_retrait"));

			if (article.getNo_article() < 0 || enchere.getNo_article() < 0 || retrait.getId() < 0) {
				throw new Exception();
			}
			article = articleManager.findById(id_article);
			enchere = enchereManager.afficherEnchereById(id_enchere);
			retrait = retraitManager.findById(id_retrait);
		} catch (Exception e) {
			request.setAttribute("errorMessage", "Enchere non disponible");
			e.printStackTrace();
		}
		request.setAttribute("article", article);
		request.setAttribute("enchere", enchere);
		request.setAttribute("retrait", retrait);

		request.getRequestDispatcher("/WEB-INF/jsp/detailVente.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
