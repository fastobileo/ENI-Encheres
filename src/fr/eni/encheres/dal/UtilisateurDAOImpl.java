package fr.eni.encheres.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.eni.encheres.bo.Utilisateur;

public class UtilisateurDAOImpl implements UtilisateurDAO{

	private final String FIND_BY_ID = "select id, pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur FROM UTILISATEURS where id = ?";
	
	private final String INSERT = "insert into UTILISATEURS(pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit) values (?,?,?,?,?,?,?,?,?,?)";
	
	private final String FIND_ALL = "select id, pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur FROM UTILISATEURS";
	
	private final String DELETE = "DELETE FROM UTILISATEURS WHERE id=?";
	
	private final String UPDATE = "UPDATE UTILISATEURS set pseudo = ?, nom = ?, prenom = ?, email = ?, telephone = ?, rue = ?, code_postal = ?, ville = ?, mot_de_passe = ?, credit = ? WHERE id = ?";
	
	private final String GET_PASSWORD = "select pseudo, mot_de_passe FROM UTILISATEURS where id = ?";
	
	@Override
	public void add(Utilisateur utilisateur) throws DALException {
		
		try (Connection connection = ConnectionProvider.getConnection()) {
			
			PreparedStatement ps = connection.prepareStatement(INSERT,Statement.RETURN_GENERATED_KEYS);
			
			ps.setString(1, utilisateur.getPseudo());
			ps.setString(2, utilisateur.getNom());
			ps.setString(3, utilisateur.getPrenom());
			ps.setString(4, utilisateur.getEmail());
			ps.setString(5, utilisateur.getTelephone());
			ps.setString(6, utilisateur.getRue());
			ps.setString(7, utilisateur.getCode_postal());
			ps.setString(8, utilisateur.getVille());
			ps.setString(9, utilisateur.getMot_de_passe());
			ps.setInt(10, utilisateur.getCredit());
			
			ps.executeUpdate();
			
			ResultSet rs = ps.getGeneratedKeys();
			if (rs.next()) {
				utilisateur.setId(rs.getInt(1));
			}			
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DALException(e);
		}		
	}

	@Override
	public Utilisateur findById(int id) throws DALException {
		
		Utilisateur utilisateur = null;
		
		try (Connection connection = ConnectionProvider.getConnection()){
			
			PreparedStatement ps = connection.prepareStatement(FIND_BY_ID);
			ps.setInt(1,  id);
			
			ResultSet rs = ps.executeQuery();
			
			utilisateur.setId(rs.getInt("id"));
			utilisateur.setPseudo(rs.getString("pseudo"));
			utilisateur.setNom(rs.getString("nom"));
			utilisateur.setPrenom(rs.getString("prenom"));
			utilisateur.setEmail(rs.getString("email"));
			utilisateur.setTelephone(rs.getString("telephone"));
			utilisateur.setRue(rs.getString("rue"));
			utilisateur.setCode_postal(rs.getString("code_postal"));
			utilisateur.setVille(rs.getString("ville"));
			utilisateur.setMot_de_passe(rs.getString("mot_de_passe"));
			utilisateur.setCredit(rs.getInt("credit"));
			utilisateur.setAdministrateur(rs.getBoolean("administrateur"));			
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DALException(e);
		}		
		return utilisateur;
	}

	@Override
	public List<Utilisateur> findAll() throws DALException {
		
		List<Utilisateur> listeUtilisateur = new ArrayList<Utilisateur>();
		
		try (Connection connection = ConnectionProvider.getConnection()) {
			
			Statement st = connection.createStatement();
			
			ResultSet rs = st.executeQuery(FIND_ALL);
			while(rs.next()) {
				Utilisateur utilisateur = new Utilisateur();
				utilisateur.setId(rs.getInt("id"));
				utilisateur.setPseudo(rs.getString("pseudo"));
				utilisateur.setNom(rs.getString("nom"));
				utilisateur.setPrenom(rs.getString("prenom"));
				utilisateur.setEmail(rs.getString("email"));
				utilisateur.setTelephone(rs.getString("telephone"));
				utilisateur.setRue(rs.getString("rue"));
				utilisateur.setCode_postal(rs.getString("code_postal"));
				utilisateur.setVille(rs.getString("ville"));
				utilisateur.setMot_de_passe(rs.getString("mot_de_passe"));
				utilisateur.setCredit(rs.getInt("credit"));
				utilisateur.setAdministrateur(rs.getBoolean("administrateur"));
				listeUtilisateur.add(utilisateur);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DALException(e);
		}		
		return listeUtilisateur;
	}

	@Override
	public void update(Utilisateur utilisateur) throws DALException {		
		
		try(Connection connection = ConnectionProvider.getConnection()) {
			
			PreparedStatement ps = connection.prepareStatement(UPDATE);
			
			ps.setString(1, utilisateur.getPseudo());
			ps.setString(2, utilisateur.getNom());
			ps.setString(3, utilisateur.getPrenom());
			ps.setString(4, utilisateur.getEmail());
			ps.setString(5, utilisateur.getTelephone());
			ps.setString(6, utilisateur.getRue());
			ps.setString(7, utilisateur.getCode_postal());
			ps.setString(8, utilisateur.getVille());
			ps.setString(9, utilisateur.getMot_de_passe());
			ps.setInt(10, utilisateur.getCredit());
			
			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DALException(e);
		}		
	}

	@Override
	public void delete(Utilisateur utilisateur) throws DALException {
		
		try (Connection connection = ConnectionProvider.getConnection()) {
			
			PreparedStatement ps = connection.prepareStatement(DELETE);
			
			ps.setInt(1, utilisateur.getId());
			ps.executeUpdate();
			ps.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DALException(e);
		}		
	}
	
	public Utilisateur seConnecter(int id) throws DALException {
	
		String mdputilisateur = null;
		String pseudoUtilisateur = null;
		Utilisateur utilisateur = new Utilisateur(pseudoUtilisateur, mdputilisateur);
		
		try(Connection connection = ConnectionProvider.getConnection()) {
			
			PreparedStatement ps = connection.prepareStatement(GET_PASSWORD); 
			
			ResultSet rs = ps.executeQuery();
			
			mdputilisateur = rs.getString("mot_de_passe");
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new DALException(e);
		}
		return utilisateur;
		
	}
}
