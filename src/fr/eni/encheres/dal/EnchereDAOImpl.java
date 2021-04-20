package fr.eni.encheres.dal;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.eni.encheres.bo.Enchere;

public class EnchereDAOImpl implements EnchereDAO{

	private final String FIND_BY_ID = "select no_enchere, date_enchere, montant_enchere, no_article, no_utilisateur FROM ENCHERES where id = ?";
	
	private final String INSERT = "insert into ENCHERES(date_enchere, montant_enchere, no_article, no_utilisateur) values (?,?,?,?)";
		
	private final String FIND_ALL = "select no_enchere, date_enchere, montant_enchere, no_article, no_utilisateur FROM ENCHERES";
	
	private final String DELETE = "DELETE FROM ENCHERES WHERE no_enchere=?";
	
	private final String UPDATE = "UPDATE ENCHERES set date_enchere = ?, montant_enchere = ?, no_article = ?, no_utilisateur = ? WHERE no_enchere = ?";
	
	@Override
	public void add(Enchere enchere) throws DALException {
		
		try (Connection connection = ConnectionProvider.getConnection()) {
			
			PreparedStatement ps = connection.prepareStatement(INSERT,Statement.RETURN_GENERATED_KEYS);
			
			ps.setDate(1, (Date) enchere.getDate_enchere());
			ps.setInt(1, enchere.getMontant_enchere());
			ps.setInt(3, enchere.getNo_article());
			ps.setInt(4, enchere.getNo_utilisateur());
			
			ps.executeUpdate();
			
			ResultSet rs = ps.getGeneratedKeys();
			if (rs.next()) {
				enchere.setNo_article(rs.getInt(1));
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
			enchere.setNo_article(rs.getInt("no_article"));
			enchere.setNo_utilisateur(rs.getInt("no_utilisateur"));
			
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
				enchere.setNo_article(rs.getInt("no_article"));
				enchere.setNo_utilisateur(rs.getInt("no_utilisateur"));
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
			ps.setInt(3, enchere.getNo_article());
			ps.setInt(4, enchere.getNo_utilisateur());
			
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

}
