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
			System.out.println(request.getParameter("error"));
			if (id_enchere <= 0 || id_enchere == null) {
				if (request.getParameter("error") != null) {
					throw new BusinessException(request.getParameter("error"));
				} else {
					throw new BusinessException("Enchere non disponible");
				}

			}
			enchere = enchereManager.getEnchereInnerJoin(id_enchere);

		} catch (BusinessException e) {
			System.out.println("test");
			request.setAttribute("errorMessage", e.getMessage());
		}
		request.setAttribute("enchere", enchere);

		request.getRequestDispatcher("/WEB-INF/jsp/detailVente.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id_enchere");
		String enchere = request.getParameter("enchere");
		String idUser = request.getParameter("idUser");
		String error = "";
		try {
			enchereManager.encherir(id, enchere, idUser);
		} catch (BusinessException e) {
			request.setAttribute("errorMessageEnchere", e.getMessage());
			error = "&error=" + e.getMessage();
			System.out.println(error);
		}
		// request.setAttribute("idEnchere", id);
		// request.getRequestDispatcher(request.getContextPath() +
		// "/detailsVente?id_enchere=" + id).forward(request,
		// response);

		response.sendRedirect(request.getContextPath() + "/detailsVente?id_enchere=" + id + error);

	}

}