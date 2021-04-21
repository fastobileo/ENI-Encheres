package fr.eni.encheres.ihm;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.encheres.bll.UtilisateurManager;
import fr.eni.encheres.bo.Utilisateur;

/**
 * Servlet implementation class ModifierProfilServlet
 */
@WebServlet("/modifierProfil")
public class ModifierProfilServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UtilisateurManager utilisateurManager;

	public ModifierProfilServlet() {
		super();
		utilisateurManager = new UtilisateurManager();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Utilisateur utilisateur = null;
		Integer id = null;

		try {
			id = Integer.parseInt(request.getParameter("id"));
			utilisateur = utilisateurManager.afficherUtilisateur(id);
			if (utilisateur.getId() == null) {
				throw new Exception();
			}
			utilisateur = utilisateurManager.afficherUtilisateur(id);
		} catch (Exception e) {
			request.setAttribute("errorMessage", "l'utilisateur n'est pas disponible");

			e.printStackTrace();
		}
		request.setAttribute("user", utilisateur);
		request.getRequestDispatcher("/WEB-INF/jsp/modifierProfil.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Integer id = Integer.parseInt(request.getParameter("id"));
		Utilisateur user = new Utilisateur();
		user.setId(id);
		user.setNom(request.getParameter("Nom"));

		response.sendRedirect(request.getContextPath() + "/AfficherProfil?id=" + id);

	}

}
