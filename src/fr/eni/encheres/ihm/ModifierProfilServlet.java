package fr.eni.encheres.ihm;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.encheres.bll.UtilisateurManager;
import fr.eni.encheres.bo.BusinessException;
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
		user.setPrenom(request.getParameter("Prenom"));
		user.setAdministrateur(false);
		user.setCode_postal(request.getParameter("cp"));
		user.setEmail(request.getParameter("Email"));
		user.setMot_de_passe(request.getParameter("mdp"));
		user.setPseudo(request.getParameter("Pseudo"));
		user.setRue(request.getParameter("Rue"));
		user.setTelephone(request.getParameter("tel"));
		user.setVille(request.getParameter("Ville"));
		System.out.println(user.toString());
		try {
			utilisateurManager.update(user);
		} catch (BusinessException e) {
			e.printStackTrace();
		}

		response.sendRedirect(request.getContextPath() + "/AfficherProfil?id=" + id);

	}

}
