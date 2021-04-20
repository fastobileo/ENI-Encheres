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
 * Servlet implementation class AfficherProfilServlet
 */
@WebServlet("/AfficherProfil")
public class AfficherProfilServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private UtilisateurManager utilisateurManager;
	
	
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AfficherProfilServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Utilisateur utilisateur = null;
		
		try {
			utilisateur = utilisateurManager.afficherUtilisateur(2);
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setAttribute("user", utilisateur);

		request.getRequestDispatcher("/WEB-INF/jsp/profil.jsp").forward(request, response);
	}
}
