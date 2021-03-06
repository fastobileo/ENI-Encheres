package fr.eni.encheres.bo;

import java.util.Date;

public class Enchere {

	private int no_enchere;
	private Date date_enchere;
	private int montant_enchere;
	private Article article;
	private Utilisateur utilisateur;
	private Retrait retrait;
	private Utilisateur dernierEncherisseur;

	public Enchere(int no_enchere, Date date_enchere, int montant_enchere, Article article, Utilisateur utilisateur) {
		super();
		this.no_enchere = no_enchere;
		this.date_enchere = date_enchere;
		this.montant_enchere = montant_enchere;
		this.article = article;
		this.utilisateur = utilisateur;
	}

	public Enchere() {
		super();
	}

	public Enchere(Article article, Utilisateur utilisateur) {
		super();
		this.article = article;
		this.utilisateur = utilisateur;
	}

	public int getNo_enchere() {
		return no_enchere;
	}

	public void setNo_enchere(int no_enchere) {
		this.no_enchere = no_enchere;
	}

	public Date getDate_enchere() {
		return date_enchere;
	}

	public void setDate_enchere(Date date_enchere) {
		this.date_enchere = date_enchere;
	}

	public int getMontant_enchere() {
		return montant_enchere;
	}

	public void setMontant_enchere(int montant_enchere) {
		this.montant_enchere = montant_enchere;
	}

	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}

	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

	public Retrait getRetrait() {
		return retrait;
	}

	public void setRetrait(Retrait retrait) {
		this.retrait = retrait;
	}

	public Utilisateur getDernierEncherisseur() {
		return dernierEncherisseur;
	}

	public void setDernierEncherisseur(Utilisateur dernierEncherisseur) {
		this.dernierEncherisseur = dernierEncherisseur;
	}

	@Override
	public String toString() {
		return "Enchere [no_enchere=" + no_enchere + ", date_enchere=" + date_enchere + ", montant_enchere="
				+ montant_enchere + ", article=" + article + ", utilisateur=" + utilisateur + ", retrait=" + retrait
				+ ", dernierEncherisseur=" + dernierEncherisseur + "]";
	}

}
