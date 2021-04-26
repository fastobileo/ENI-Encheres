package fr.eni.encheres.ihm;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.encheres.bll.UtilisateurManager;
import fr.eni.encheres.bo.BusinessException;
import fr.eni.encheres.bo.Utilisateur;

@WebServlet("/suppression")
public class SuppressionCompteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private UtilisateurManager utilisateurManager = new UtilisateurManager();
	
    public SuppressionCompteServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/jsp/suppression.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Integer id = Integer.parseInt(request.getParameter("id"));
		
		Utilisateur user = new Utilisateur();
		user.setId(id);
		
		System.out.println(user.getId());
		
		try {
			utilisateurManager.delete(user);
			HttpSession session = request.getSession();
			session.invalidate();
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		
		response.sendRedirect(request.getContextPath() + "/");
	}

}
