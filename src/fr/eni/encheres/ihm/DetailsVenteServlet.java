package fr.eni.encheres.ihm;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.encheres.bll.EnchereManager;
import fr.eni.encheres.bo.BusinessException;
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
			if (id_enchere < 0) {
				throw new Exception();
			}
			enchere = enchereManager.getEnchereInnerJoin(id_enchere);

		} catch (Exception e) {
			request.setAttribute("errorMessage", "Enchere non disponible");
		}
		request.setAttribute("enchere", enchere);

		request.getRequestDispatcher("/WEB-INF/jsp/detailVente.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id_enchere");
		String enchere = request.getParameter("enchere");
		String idUser = request.getParameter("idUser");

		try {
			enchereManager.encherir(id, enchere, idUser);
		} catch (BusinessException e) {
			request.setAttribute("errorMessageEnchere", e.getMessage());
		}
		// request.setAttribute("idEnchere", id);
		request.getRequestDispatcher("/WEB-INF/jsp/detailVente.jsp?id_enchere=" + id).forward(request, response);

		// response.sendRedirect(request.getContextPath() + "/detailsVente?id_enchere="
		// + id);

	}

}