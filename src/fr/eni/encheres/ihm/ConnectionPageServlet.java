package fr.eni.encheres.ihm;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.encheres.bll.UtilisateurManager;

/**
 * Servlet implementation class ConnectionPageServlet
 */
@WebServlet("/connection")
public class ConnectionPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UtilisateurManager userManager;

	public ConnectionPageServlet() {
		super();
		userManager = new UtilisateurManager();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/jsp/connection.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		System.out.println(id);
		System.out.println(password);
		// userManager.seConnecter(id, password);
		response.sendRedirect(request.getContextPath() + "/connection");
	}

}
