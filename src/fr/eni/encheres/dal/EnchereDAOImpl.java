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
import fr.eni.encheres.bo.Categorie;
import fr.eni.encheres.bo.Enchere;
import fr.eni.encheres.bo.Retrait;
import fr.eni.encheres.bo.Utilisateur;

public class EnchereDAOImpl implements EnchereDAO {

	private final String FIND_BY_ID = "select no_enchere, date_enchere, montant_enchere, no_article, no_utilisateur FROM ENCHERES where id = ?";

	private final String INSERT = "insert into ENCHERES(date_enchere, montant_enchere, no_article, no_utilisateur) values (?,?,?,?)";

	private final String FIND_ALL = "SELECT a.no_article, nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, prix_vente, c.no_categorie, libelle, no_enchere, date_enchere,\n"
			+ "montant_enchere, r.no_retrait, r.rue as retraitRue, r.code_postal as retraitCP, r.ville as retraitVille, u.no_utilisateur, pseudo, prenom, nom, email, telephone, u.rue, u.code_postal, u.ville, credit\n"
			+ "FROM ARTICLES_VENDUS a INNER JOIN ENCHERES e ON a.no_article = e.no_article\n"
			+ "INNER JOIN RETRAITS r ON r.no_retrait = a.no_retrait\n"
			+ "INNER JOIN CATEGORIES c ON c.no_categorie = a.no_categorie\n"
			+ "INNER JOIN UTILISATEURS u ON u.no_utilisateur = e.no_utilisateur";

	private final String DELETE = "DELETE FROM ENCHERES WHERE no_enchere=?";

	private final String UPDATE = "UPDATE ENCHERES set date_enchere = ?, montant_enchere = ?, no_article = ?, no_utilisateur = ? WHERE no_enchere = ?";

	private final String ENCHERIR = "UPDATE ENCHERES set montant_enchere = ?, derniereEnchere =? WHERE no_enchere = ?";
	private final String CREDITER = "UPDATE UTILISATEURS set credit = credit + ? WHERE no_utilisateur = ?";
	private final String DEBITER = "UPDATE UTILISATEURS set credit = credit - ? WHERE no_utilisateur = ?";

	private final String INNER_JOIN = "SELECT a.no_article, nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, prix_vente, c.no_categorie, libelle, no_enchere, date_enchere,montant_enchere, r.no_retrait, r.rue as retraitRue, r.code_postal as retraitCP, r.ville as retraitVille, u.no_utilisateur, u.pseudo,u.prenom, u.nom, u.email, u.telephone, u.rue, u.code_postal, u.ville, u.credit,u2.no_utilisateur as no_utilisateurEN, u2.pseudo as pseudoEN,u2.prenom as prenomEN, u2.nom as nomEN, u2.email as emailEN, u2.telephone as telephoneEN, u2.rue as rueEN, u2.code_postal as code_postalEN, u2.ville as villeEN, u2.credit as creditEN\n"
			+ "FROM ARTICLES_VENDUS a \n" + "INNER JOIN ENCHERES e ON a.no_article = e.no_article \n"
			+ "INNER JOIN RETRAITS r ON r.no_retrait = a.no_retrait \n"
			+ "INNER JOIN CATEGORIES c ON c.no_categorie = a.no_categorie \n"
			+ "INNER JOIN UTILISATEURS u ON u.no_utilisateur = e.no_utilisateur \n"
			+ "LEFT JOIN UTILISATEURS u2 ON u2.no_utilisateur = e.derniereEnchere where no_enchere=?";

	@Override
	public void add(Enchere enchere) throws DALException {

		try (Connection connection = ConnectionProvider.getConnection()) {

			PreparedStatement ps = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);

			ps.setDate(1, (Date) enchere.getDate_enchere());
			ps.setInt(2, enchere.getMontant_enchere());
			ps.setInt(3, enchere.getArticle().getNo_article());
			ps.setInt(4, enchere.getUtilisateur().getId());

			ps.executeUpdate();

			ResultSet rs = ps.getGeneratedKeys();
			if (rs.next()) {
				enchere.getArticle().setNo_article(rs.getInt(1));
				;
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DALException(e);
		}
	}

	@Override
	public Enchere findById(int no_enchere) throws DALException {

		Enchere enchere = new Enchere();

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
			while (rs.next()) {

				Article article = new Article();
				Utilisateur utilisateur = new Utilisateur();
				Retrait retrait = new Retrait();
				Enchere enchere = new Enchere(article, utilisateur);
				enchere.setNo_enchere(rs.getInt("no_enchere"));
				article.setNo_article(rs.getInt("no_article"));
				article.setNom_article(rs.getString("nom_article"));
				article.setDescription(rs.getString("description"));
				article.setPrix_initial(rs.getInt("prix_initial"));
				article.setPrix_vente(rs.getInt("prix_vente"));
				article.setCategorie(
						new Categorie(Integer.parseInt(rs.getString("no_categorie")), rs.getString("libelle")));
				article.setDate_debut_encheres(rs.getDate("date_debut_encheres"));
				article.setDate_fin_encheres(rs.getDate("date_fin_encheres"));

				retrait.setId(rs.getInt("no_retrait"));
				retrait.setRue(rs.getString("retraitRue"));
				retrait.setCode_postal(rs.getString("retraitCP"));
				retrait.setVille(rs.getString("retraitVille"));

				utilisateur.setId(rs.getInt("no_utilisateur"));
				utilisateur.setPseudo(rs.getString("pseudo"));
				utilisateur.setPrenom(rs.getString("prenom"));
				utilisateur.setEmail(rs.getString("email"));
				utilisateur.setTelephone(rs.getString("telephone"));
				utilisateur.setNom(rs.getString("nom"));
				utilisateur.setRue(rs.getString("rue"));
				utilisateur.setCode_postal(rs.getString("code_postal"));
				utilisateur.setVille(rs.getString("ville"));

				enchere.setNo_enchere(rs.getInt("no_enchere"));
				enchere.setDate_enchere(rs.getDate("date_enchere"));
				enchere.setMontant_enchere(rs.getInt("montant_enchere"));
				enchere.setArticle(article);
				enchere.setUtilisateur(utilisateur);
				enchere.setRetrait(retrait);
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

		try (Connection connection = ConnectionProvider.getConnection()) {

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

		try (Connection connection = ConnectionProvider.getConnection()) {

			PreparedStatement ps = connection.prepareStatement(DELETE);

			ps.setInt(1, enchere.getNo_enchere());
			ps.executeUpdate();
			ps.close();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DALException(e);
		}
	}

	public Enchere getEnchereInnerJoin(Integer id_enchere) throws DALException {

		Enchere enchere = new Enchere();
		Article article = new Article();
		Retrait retrait = new Retrait();
		Utilisateur utilisateur = new Utilisateur();
		Utilisateur encherisseur = new Utilisateur();

		try (Connection connection = ConnectionProvider.getConnection()) {

			PreparedStatement ps = connection.prepareStatement(INNER_JOIN);
			ps.setInt(1, id_enchere);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				enchere.setNo_enchere(rs.getInt("no_enchere"));
				article.setNo_article(rs.getInt("no_article"));
				article.setNom_article(rs.getString("nom_article"));
				article.setDescription(rs.getString("description"));
				article.setPrix_initial(rs.getInt("prix_initial"));
				article.setPrix_vente(rs.getInt("prix_vente"));
				article.setCategorie(
						new Categorie(Integer.parseInt(rs.getString("no_categorie")), rs.getString("libelle")));
				article.setDate_debut_encheres(rs.getDate("date_debut_encheres"));
				article.setDate_fin_encheres(rs.getDate("date_fin_encheres"));

				retrait.setId(rs.getInt("no_retrait"));
				retrait.setRue(rs.getString("retraitRue"));
				retrait.setCode_postal(rs.getString("retraitCP"));
				retrait.setVille(rs.getString("retraitVille"));

				utilisateur.setId(rs.getInt("no_utilisateur"));
				utilisateur.setPseudo(rs.getString("pseudo"));
				utilisateur.setPrenom(rs.getString("prenom"));
				utilisateur.setEmail(rs.getString("email"));
				utilisateur.setTelephone(rs.getString("telephone"));
				utilisateur.setNom(rs.getString("nom"));
				utilisateur.setRue(rs.getString("rue"));
				utilisateur.setCode_postal(rs.getString("code_postal"));
				utilisateur.setVille(rs.getString("ville"));

				encherisseur.setId(rs.getInt("no_utilisateurEN"));
				encherisseur.setPseudo(rs.getString("pseudoEN"));
				encherisseur.setPrenom(rs.getString("prenomEN"));
				encherisseur.setEmail(rs.getString("emailEN"));
				encherisseur.setTelephone(rs.getString("telephoneEN"));
				encherisseur.setNom(rs.getString("nomEN"));
				encherisseur.setRue(rs.getString("rueEN"));
				encherisseur.setCode_postal(rs.getString("code_postalEN"));
				encherisseur.setVille(rs.getString("villeEN"));

				enchere.setNo_enchere(rs.getInt("no_enchere"));
				enchere.setDate_enchere(rs.getDate("date_enchere"));
				enchere.setMontant_enchere(rs.getInt("montant_enchere"));
				enchere.setArticle(article);
				enchere.setUtilisateur(utilisateur);
				enchere.setDernierEncherisseur(encherisseur);
				enchere.setRetrait(retrait);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DALException(e);
		}

		return enchere;
	}

	public void encherir(Integer idEnchere, Integer prix, Integer last) throws DALException {

		try (Connection connection = ConnectionProvider.getConnection()) {

			PreparedStatement ps = connection.prepareStatement(ENCHERIR);

			ps.setInt(1, prix);
			ps.setInt(2, last);
			ps.setInt(3, idEnchere);
			ps.executeUpdate();
			ps.close();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DALException(e);
		}
	}

	public void debiter(Integer idUser, Integer credit) throws DALException {
		try (Connection connection = ConnectionProvider.getConnection()) {

			PreparedStatement ps = connection.prepareStatement(DEBITER);

			ps.setInt(1, credit);
			ps.setInt(2, idUser);
			ps.executeUpdate();
			ps.close();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DALException(e);
		}
	}

	public void crediter(Integer idUser, Integer credit) throws DALException {
		try (Connection connection = ConnectionProvider.getConnection()) {
			PreparedStatement ps = connection.prepareStatement(CREDITER);

			ps.setInt(1, credit);
			ps.setInt(2, idUser);
			ps.executeUpdate();
			ps.close();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DALException(e);
		}

	}

}
