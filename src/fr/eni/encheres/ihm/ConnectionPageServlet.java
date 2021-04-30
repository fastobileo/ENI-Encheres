package fr.eni.encheres.ihm;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.encheres.bll.UtilisateurManager;
import fr.eni.encheres.bo.BusinessException;
import fr.eni.encheres.bo.Utilisateur;

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
		
		  Cookie[] cookies = request.getCookies();
	        if (cookies != null) {
	            for (Cookie cookie : cookies) {
	                if (cookie.getName().equals("id")) {
	                	
	                    request.setAttribute("id", cookie.getValue());
	                }
	            }
	        }
		request.getRequestDispatcher("/WEB-INF/jsp/connection.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		
		if (null != request.getParameter("seSouvenirDeMoi")) {
			
	        Cookie cookie = new Cookie("id", id);
	        cookie.setMaxAge(60 * 60 * 24 * 30);
	        response.addCookie(cookie);
		}
		
		try {
			Utilisateur user = userManager.seConnecter(id, password);
			request.setAttribute("user", user);
			HttpSession session = request.getSession();
			session.setAttribute("idUser", user.getId());
			session.setAttribute("user", (Utilisateur) userManager.afficherUtilisateur(user.getId()));
			response.sendRedirect(request.getContextPath() + "/");
			
			
			
		} catch (BusinessException e) {
			request.setAttribute("errorMessage", "impossible de se connecter");
			request.getRequestDispatcher("/WEB-INF/jsp/connection.jsp").forward(request, response);
		}

	}
}
