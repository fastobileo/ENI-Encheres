package fr.eni.encheres.ihm;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.encheres.bll.EnchereManager;
import fr.eni.encheres.bo.Enchere;

@WebServlet("/detailsVente")
public class DetailsVenteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private EnchereManager enchereManager;

	public DetailsVenteServlet() {
		enchereManager = new EnchereManager();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Enchere enchere = null;
		Integer id_enchere = null;

		try {
			id_enchere = Integer.parseInt(request.getParameter("id_enchere"));

			if (enchere.getArticle().getNo_article() < 0) {
				throw new Exception();
			}
			enchere = enchereManager.getEnchereInnerJoin(id_enchere);
		} catch (Exception e) {
			request.setAttribute("errorMessage", "Enchere non disponible");
			e.printStackTrace();
		}
		request.setAttribute("enchere", enchere);

		request.getRequestDispatcher("/WEB-INF/jsp/detailVente.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}