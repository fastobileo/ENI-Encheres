package fr.eni.encheres.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.eni.encheres.bo.Retrait;

public class RetraitDAOImpl implements RetraitDAO{

	private final String INSERT = "insert into RETRAITS(no_retrait, rue, code_postal, ville) values (?,?,?,?)";
	
	private final String FIND_BY_ID = "select no_retrait, rue, code_postal, ville FROM RETRAITS where id = ?";
	
	private final String FIND_ALL = "select no_retrait, rue, code_postal, ville FROM RETRAITS";

	@Override
	public void add(Retrait retrait) throws DALException {
		
		try(Connection connection = ConnectionProvider.getConnection()) {
			
			PreparedStatement ps = connection.prepareStatement(INSERT,Statement.RETURN_GENERATED_KEYS);
			
			ps.setInt(1, retrait.getId());
			ps.setString(2, retrait.getRue());
			ps.setString(3, retrait.getCode_postal());
			ps.setString(4, retrait.getVille());	
			
			ps.executeUpdate();
			
			ResultSet rs = ps.getGeneratedKeys();
			if (rs.next()) {
				retrait.setId(rs.getInt(1));
			}	
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new DALException(e);
		}
	}

	@Override
	public Retrait findById(int no_retrait) throws DALException {
		
		Retrait retrait = null;		
		
		try(Connection connection = ConnectionProvider.getConnection()) {

			PreparedStatement ps = connection.prepareStatement(FIND_BY_ID);
			ps.setInt(1,  no_retrait);
			
			ResultSet rs = ps.executeQuery();
			
			retrait.setId(rs.getInt("no_retrait"));
			retrait.setRue(rs.getString("rue"));
			retrait.setCode_postal(rs.getString("code_postal"));
			retrait.setVille(rs.getString("ville"));
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new DALException(e);
		}
		
		return retrait;
	}

	@Override
	public List<Retrait> findAll() throws DALException {
		
			List<Retrait> listeRetrait = new ArrayList<Retrait>();
		
		try (Connection connection = ConnectionProvider.getConnection()) {
			
			Statement st = connection.createStatement();
			
			ResultSet rs = st.executeQuery(FIND_ALL);
			while(rs.next()) {
				Retrait retrait = new Retrait();
				retrait.setId(rs.getInt("no_retrait"));
				retrait.setRue(rs.getString("rue"));
				retrait.setCode_postal(rs.getString("code_postal"));
				retrait.setVille(rs.getString("ville"));
				listeRetrait.add(retrait);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DALException(e);
		}		
		return listeRetrait;
	}
}
