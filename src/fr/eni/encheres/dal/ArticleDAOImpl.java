package fr.eni.encheres.dal;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.eni.encheres.bo.Article;

public class ArticleDAOImpl implements ArticleDAO{

	private final String FIND_BY_ID = "select no_article, nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, prix_vente, no_utilisateur, no_categorie, no_retrait FROM ARTICLES_VENDUS where id = ?";
	
	private final String INSERT = "insert into ARTICLES_VENDUS (nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, prix_vente, no_utilisateur, no_categorie, no_retrait) values (?,?,?,?,?,?,?,?,?)";
		
	private final String FIND_ALL = "select no_article, nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, prix_vente, no_utilisateur, no_categorie, no_retrait";
	
	private final String DELETE = "DELETE FROM ARTICLES_VENDUS WHERE no_article=?";
	
	private final String UPDATE = "UPDATE ARTICLES_VENDUS set nom_article = ?, description = ?, date_debut_encheres = ?, date_fin_encheres = ?, prix_initial = ?, prix_vente = ?, no_utilisateur = ?, no_categorie = ?, no_retrait = ? WHERE no_article = ?";
	
	@Override
public void add(Article article) throws DALException {
		
		try(Connection connection = ConnectionProvider.getConnection()) {
			
			PreparedStatement ps = connection.prepareStatement(INSERT,Statement.RETURN_GENERATED_KEYS);
			
			ps.setString(1, article.getNom_article());
			ps.setString(2, article.getDescription());
			ps.setDate(3, (Date) article.getDate_debut_encheres());
			ps.setDate(4, (Date) article.getDate_fin_encheres());
			ps.setInt(5, article.getPrix_initial());
			ps.setInt(6, article.getPrix_vente());		
			
			ResultSet rs = ps.getGeneratedKeys();
			if (rs.next()) {
				article.setNo_article(rs.getInt(1));
			}							
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DALException(e);
		}		
	}

	@Override
	public Article findById(int no_article) throws DALException {
		
		Article article = null;
		
		try(Connection connection = ConnectionProvider.getConnection()) {
			
			PreparedStatement ps = connection.prepareStatement(FIND_BY_ID);
			
			ps.setInt(1, no_article);			
			ResultSet rs = ps.executeQuery();
			
			ps.setString(1, article.getNom_article());
			ps.setString(2, article.getDescription());
			ps.setDate(3, (Date) article.getDate_debut_encheres());
			ps.setDate(4, (Date) article.getDate_fin_encheres());
			ps.setInt(5, article.getPrix_initial());
			ps.setInt(6, article.getPrix_vente());
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DALException(e);
		}		
		return article;
	}

	@Override
	public List<Article> findAll() throws DALException {
		
		List<Article> listeArticles = new ArrayList<Article>();
		
		try(Connection connection = ConnectionProvider.getConnection()) {
			
			Statement st = connection.createStatement();
			
			ResultSet rs = st.executeQuery(FIND_ALL);
			
			while(rs.next()) {
				Article article = new Article();
				article.setNo_article(rs.getInt("no_article"));
				article.setNom_article(rs.getString("nom_article"));
				article.setDescription(rs.getString("description"));
				article.setDate_debut_encheres(rs.getDate("date_debut_encheres"));
				article.setDate_fin_encheres(rs.getDate("date_fin_encheres"));
				article.setPrix_initial(rs.getInt("prix_initial"));
				article.setPrix_vente(rs.getInt("prix_vente"));
				listeArticles.add(article);
			}			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DALException(e);
		}		
		return listeArticles;
	}

	@Override
	public void delete(Article article) throws DALException {
		
		try(Connection connection = ConnectionProvider.getConnection()) {
			
			PreparedStatement ps = connection.prepareStatement(UPDATE);
			
			ps.setString(1, article.getNom_article());
			ps.setString(2, article.getDescription());
			ps.setDate(3, (Date) article.getDate_debut_encheres());
			ps.setDate(4, (Date) article.getDate_fin_encheres());
			ps.setInt(5, article.getPrix_initial());
			ps.setInt(6, article.getPrix_vente());
			
			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DALException(e);
		}	
	}

	@Override
	public void update(Article article) throws DALException {		

		try (Connection connection = ConnectionProvider.getConnection()) {
			
			PreparedStatement ps = connection.prepareStatement(DELETE);
			
			ps.setInt(1, article.getNo_article());
			ps.executeUpdate();
			ps.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DALException(e);
		}
	}

}
