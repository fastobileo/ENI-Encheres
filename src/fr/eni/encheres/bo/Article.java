package fr.eni.encheres.bo;

public class Article {

	private int no_article;
	private String nom_article;
	private String description;
	private int prix_initial;
	private int prix_vente;
	private int no_utilisateur;
	private int no_categorie;
	private int no_retrait;
	
	public Article(int no_article, String nom_article, String description, int prix_initial, int prix_vente,
			int no_utilisateur, int no_categorie, int no_retrait) {
		super();
		this.no_article = no_article;
		this.nom_article = nom_article;
		this.description = description;
		this.prix_initial = prix_initial;
		this.prix_vente = prix_vente;
		this.no_utilisateur = no_utilisateur;
		this.no_categorie = no_categorie;
		this.no_retrait = no_retrait;
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

	@Override
	public String toString() {
		return "article [no_article=" + no_article + ", nom_article=" + nom_article + ", description=" + description
				+ ", prix_initial=" + prix_initial + ", prix_vente=" + prix_vente + ", no_utilisateur=" + no_utilisateur
				+ ", no_categorie=" + no_categorie + ", no_retrait=" + no_retrait + "]";
	}	
}
