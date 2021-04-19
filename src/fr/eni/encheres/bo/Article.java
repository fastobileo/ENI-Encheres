package fr.eni.encheres.bo;

import java.util.Date;

import fr.eni.encheres.bo.enumeration.Categorie;

public class Article {

	private int no_article;
	private String nom_article;
	private String description;
	private int prix_initial;
	private int prix_vente;
	private int no_utilisateur;
	private int no_categorie;
	private int no_retrait;
	private Categorie categorie;
	private Date date_debut_encheres;
	private Date date_fin_encheres;
	
	public Article(int no_article, String nom_article, String description, int prix_initial, int prix_vente,
			int no_utilisateur, int no_categorie, int no_retrait, Categorie categorie,
			Date date_debut_encheres, Date date_fin_encheres) {
		super();
		this.no_article = no_article;
		this.nom_article = nom_article;
		this.description = description;
		this.prix_initial = prix_initial;
		this.prix_vente = prix_vente;
		this.no_utilisateur = no_utilisateur;
		this.no_categorie = no_categorie;
		this.no_retrait = no_retrait;
		this.categorie = categorie;
		this.date_debut_encheres = date_debut_encheres;
		this.date_fin_encheres = date_fin_encheres;
	}
	
	public Article() {
		
	}

	public int getNo_article() {
		return no_article;
	}

	public void setNo_article(int no_article) {
		this.no_article = no_article;
	}

	public String getNom_article() {
		return nom_article;
	}

	public void setNom_article(String nom_article) {
		this.nom_article = nom_article;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getPrix_initial() {
		return prix_initial;
	}

	public void setPrix_initial(int prix_initial) {
		this.prix_initial = prix_initial;
	}

	public int getPrix_vente() {
		return prix_vente;
	}

	public void setPrix_vente(int prix_vente) {
		this.prix_vente = prix_vente;
	}

	public int getNo_utilisateur() {
		return no_utilisateur;
	}

	public void setNo_utilisateur(int no_utilisateur) {
		this.no_utilisateur = no_utilisateur;
	}

	public int getNo_categorie() {
		return no_categorie;
	}

	public void setNo_categorie(int no_categorie) {
		this.no_categorie = no_categorie;
	}

	public int getNo_retrait() {
		return no_retrait;
	}

	public void setNo_retrait(int no_retrait) {
		this.no_retrait = no_retrait;
	}

	public Categorie getCategorie() {
		return categorie;
	}

	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}

	public Date getDate_debut_encheres() {
		return date_debut_encheres;
	}

	public void setDate_debut_encheres(Date date_debut_encheres) {
		this.date_debut_encheres = date_debut_encheres;
	}

	public Date getDate_fin_encheres() {
		return date_fin_encheres;
	}

	public void setDate_fin_encheres(Date date_fin_encheres) {
		this.date_fin_encheres = date_fin_encheres;
	}

	@Override
	public String toString() {
		return "Article [no_article=" + no_article + ", nom_article=" + nom_article + ", description=" + description
				+ ", prix_initial=" + prix_initial + ", prix_vente=" + prix_vente + ", no_utilisateur=" + no_utilisateur
				+ ", no_categorie=" + no_categorie + ", no_retrait=" + no_retrait + ", categorie=" + categorie
				+ ", date_debut_encheres=" + date_debut_encheres + ", date_fin_encheres=" + date_fin_encheres + "]";
	}
}
