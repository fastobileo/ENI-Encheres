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
import fr.eni.encheres.bo.Enchere;
import fr.eni.encheres.bo.Retrait;
import fr.eni.encheres.bo.Utilisateur;
import fr.eni.encheres.ihm.DeconnexionServlet;

public class EnchereDAOImpl implements EnchereDAO{

	private final String FIND_BY_ID = "select no_enchere, date_enchere, montant_enchere, no_article, no_utilisateur FROM ENCHERES where id = ?";
	
	private final String INSERT = "insert into ENCHERES(date_enchere, montant_enchere, no_article, no_utilisateur) values (?,?,?,?)";
		
	private final String FIND_ALL = "select no_enchere, date_enchere, montant_enchere, no_article, no_utilisateur FROM ENCHERES";
	
	private final String DELETE = "DELETE FROM ENCHERES WHERE no_enchere=?";
	
	private final String UPDATE = "UPDATE ENCHERES set date_enchere = ?, montant_enchere = ?, no_article = ?, no_utilisateur = ? WHERE no_enchere = ?";
	
	private final String INNER_JOIN = "SELECT * FROM ARTICLES_VENDUS a INNER JOIN ENCHERES e ON a.no_article = e.no_article INNER JOIN RETRAITS r ON r.no_retrait = a.no_retrait INNER JOIN CATEGORIES c ON c.no_categorie = a.no_categorie INNER JOIN UTILISATEURS u ON u.no_utilisateur = e.no_utilisateur";

	@Override
	public void add(Enchere enchere) throws DALException {

		try (Connection connection = ConnectionProvider.getConnection()) {
			
			PreparedStatement ps = connection.prepareStatement(INSERT,Statement.RETURN_GENERATED_KEYS);
			
			ps.setDate(1, (Date) enchere.getDate_enchere());
			ps.setInt(2, enchere.getMontant_enchere());
			ps.setInt(3, enchere.getArticle().getNo_article());
			ps.setInt(4, enchere.getUtilisateur().getId());
			
			ps.executeUpdate();
			
			ResultSet rs = ps.getGeneratedKeys();
			if (rs.next()) {
				enchere.getArticle().setNo_article(rs.getInt(1));;
			}	
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DALException(e);
		}		
	}

	@Override
	public Enchere findById(int no_enchere) throws DALException {
		
		Enchere enchere = null;
		
		try (Connection connection = ConnectionProvider.getConnection()) {
			
			PreparedStatement ps = connection.prepareStatement(FIND_BY_ID);
			ps.setInt(1, no_enchere);
			
			ResultSet rs = ps.executeQuery();
			
			enchere.setNo_enchere(rs.getInt("no_enchere"));
			enchere.setDate_enchere(rs.getDate("date_enchere"));
			enchere.setMontant_enchere(rs.getInt("montant_enchere"));
			enchere.getArticle().setNo_article(rs.getInt("no_article"));
			enchere.getUtilisateur().setId(rs.getInt("no_utilisateur"));
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DALException(e);
		}		
		return enchere;
	}

	@Override
	public List<Enchere> findAll() throws DALException {

		List<Enchere> listeEnchere = new ArrayList<Enchere>();
		
		try (Connection connection = ConnectionProvider.getConnection()) {
			
			Statement st = connection.createStatement();
			
			ResultSet rs = st.executeQuery(FIND_ALL);
			while(rs.next()) {
				Enchere enchere = new Enchere();
				enchere.setNo_enchere(rs.getInt("no_enchere"));
				enchere.setDate_enchere(rs.getDate("date_enchere"));
				enchere.setMontant_enchere(rs.getInt("montant_enchere"));
				enchere.getArticle().setNo_article(rs.getInt("no_article"));
				enchere.getUtilisateur().setId(rs.getInt("no_utilisateur"));
				listeEnchere.add(enchere);
			}			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DALException(e);
		}		
		return listeEnchere;
	}

	@Override
	public void delete(Enchere enchere) throws DALException {
		
		try(Connection connection = ConnectionProvider.getConnection()) {
			
			PreparedStatement ps = connection.prepareStatement(UPDATE);
			
			ps.setDate(1, (Date) enchere.getDate_enchere());
			ps.setInt(1, enchere.getMontant_enchere());
			ps.setInt(3, enchere.getArticle().getNo_article());
			ps.setInt(4, enchere.getUtilisateur().getId());
			
			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DALException(e);
		}
		
	}

	@Override
	public void update(Enchere enchere) throws DALException {

		try(Connection connection = ConnectionProvider.getConnection()) {
			
			PreparedStatement ps = connection.prepareStatement(DELETE);
						
			ps.setInt(1, enchere.getNo_enchere());
			ps.executeUpdate();
			ps.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DALException(e);
		}
	}
	
	public Enchere getEnchereInnerJoin(int id_enchere) throws DALException {
		
		Enchere enchere = new Enchere();
		Article article = new Article();
		Retrait retrait = new Retrait();
		Utilisateur utilisateur = new Utilisateur();
		
		try (Connection connection = ConnectionProvider.getConnection()){
			
			PreparedStatement ps = connection.prepareStatement(INNER_JOIN);
			ps.setInt(1, id_enchere);
			
			ResultSet rs = ps.executeQuery();
			
			enchere.setNo_enchere(rs.getInt("no_enchere"));
			
			article.setNo_article(rs.getInt("no_article"));
			article.setNom_article(rs.getString("nom_article"));
			article.setDescription(rs.getString("description"));
			article.setPrix_initial(rs.getInt("prix_initial"));
			article.setPrix_vente(rs.getInt("prix_vente"));
			article.getCategorie().setLibelle(rs.getString("libelle"));
			article.setDate_debut_encheres(rs.getDate("date_debut_encheres"));
			article.setDate_fin_encheres(rs.getDate("date_fin_encheres"));
			
			retrait.setId(rs.getInt("no_retrait"));
			retrait.setRue(rs.getString("rue"));
			retrait.setCode_postal(rs.getString("code_postal"));
			retrait.setVille(rs.getString("ville"));
			
			utilisateur.setId(rs.getInt("id"));
			utilisateur.setNom(rs.getString("nom"));
			utilisateur.setRue(rs.getString("rue"));
			utilisateur.setCode_postal(rs.getString("code_postal"));
			utilisateur.setVille(rs.getString("ville"));
			
			enchere.setNo_enchere(rs.getInt("no_enchere"));
			enchere.setDate_enchere(rs.getDate("date_enchere"));
			enchere.setMontant_enchere(rs.getInt("montant_enchere"));
			enchere.setArticle(article);
			enchere.setUtilisateur(utilisateur);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new DALException(e);
		}
		
		return enchere;
	}

}
