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

@WebServlet("/creationCompte")
public class CreationCompteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private UtilisateurManager utilisateurManager;
	
	private static final String PSEUDO = "pseudo";
	private static final String PRENOM = "prenom";
	private static final String NOM = "nom";
	private static final String EMAIL = "email";
	private static final String TELEPHONE = "telephone";
	private static final String RUE = "rue";
	private static final String CODE_POSTAL = "code_postal";
	private static final String VILLE = "ville";
	private static final String MDP = "mot_de_passe";
	private static final String MDP_CONFIRMATION = "mot_de_passe_confirme";
	
    public CreationCompteServlet() {
        super();
        utilisateurManager = new UtilisateurManager();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.getRequestDispatcher("/WEB-INF/jsp/creationCompte.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Utilisateur utilisateur = new Utilisateur();
		
		String pseudoUser = request.getParameter(PSEUDO);
		String prenomUser = request.getParameter(PRENOM);
		String nomUser = request.getParameter(NOM);
		String emailUser = request.getParameter(EMAIL);
		String telephoneUser = request.getParameter(TELEPHONE);
		String rueUser = request.getParameter(RUE);
		String codepostalUser = request.getParameter(CODE_POSTAL);
		String villeUser = request.getParameter(VILLE);
		String mdpUser = request.getParameter(MDP);
		String mdpConfirmUser = request.getParameter(MDP_CONFIRMATION);
		
		utilisateur.setPseudo(pseudoUser);
		utilisateur.setPrenom(prenomUser);
		utilisateur.setNom(nomUser);
		utilisateur.setEmail(emailUser);
		utilisateur.setTelephone(telephoneUser); 
		utilisateur.setRue(rueUser);
		utilisateur.setCode_postal(codepostalUser);
		utilisateur.setVille(villeUser);		
		utilisateur.setAdministrateur(false);
		
		try {
			if (utilisateurManager.checkingPassword(mdpUser, mdpConfirmUser)) {
				utilisateur.setMot_de_passe(mdpUser);
				utilisateurManager.ajoutUtilisateur(utilisateur);
				response.sendRedirect(request.getContextPath() + "/connection");
			} 
		} catch (BusinessException e1) {
			e1.printStackTrace();
			request.setAttribute("errorMessage", "Mots de passe différents");
			request.getRequestDispatcher("/WEB-INF/jsp/creationCompte.jsp").forward(request, response);
		}
		System.out.println(utilisateur.toString());
	}

}
