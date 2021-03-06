package fr.eni.encheres.bll;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import fr.eni.encheres.bo.BusinessException;
import fr.eni.encheres.bo.Enchere;
import fr.eni.encheres.dal.DALException;
import fr.eni.encheres.dal.EnchereDAO;

public class EnchereManager {

	private EnchereDAO enchereDAO;

	public EnchereManager() {
		enchereDAO = fr.eni.encheres.dal.DAOFactory.getEnchereDAO();
	}

	public Enchere afficherEnchereById(int id) throws BusinessException {
		Enchere enchere = new Enchere();
		try {
			enchere = enchereDAO.findById(id);
		} catch (DALException e) {
			e.printStackTrace();
		}
		return enchere;
	}

	public List<Enchere> afficherToutesLesEncheres() throws BusinessException {

		List<Enchere> listeEnchere = new ArrayList<Enchere>();
		try {

			listeEnchere = enchereDAO.findAll();
		} catch (DALException e) {
			e.printStackTrace();
		}
		return listeEnchere;
	}

	public void ajoutEnchere(Enchere enchere) throws BusinessException {

		try {
			enchereDAO.add(enchere);
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Enchere getEnchereInnerJoin(Integer no_enchere) throws BusinessException {

		Enchere enchere = null;
		try {
			enchere = enchereDAO.getEnchereInnerJoin(no_enchere);
		} catch (DALException e) {
			e.printStackTrace();
		}
		return enchere;
	}

	public void encherir(String idEnchere, String prix, String newEncherisseur) throws BusinessException {

		try {
			Integer intEnchere = Integer.parseInt(idEnchere);
			Integer intPrix = Integer.parseInt(prix);
			Integer intIdUserDeb = Integer.parseInt(newEncherisseur);
			Enchere e = enchereDAO.getEnchereInnerJoin(intEnchere);
			if (compareDate(e.getArticle().getDate_fin_encheres().toString())) {
				Integer newPrix = intPrix;
				enchereDAO.debiter(intIdUserDeb, intPrix);
				enchereDAO.crediter(e.getDernierEncherisseur().getId(), e.getMontant_enchere());
				enchereDAO.encherir(intEnchere, newPrix, intIdUserDeb);
			} else {
				throw new BusinessException("l'enchere est termin??e");
			}
		} catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
	}

	private Boolean compareDate(String date) throws BusinessException {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd,HH:mm:ss");
		Date dateActuelle = new Date(System.currentTimeMillis());
		Date dateChoisie = null;
		try {
			dateChoisie = formatter.parse(date + ",23:59:59");
		} catch (ParseException e) {
			throw new BusinessException();
		}
		if (dateChoisie.compareTo(dateActuelle) >= 0) {
			return true;
		}
		return false;
	}

	private Boolean compareDate2(String date) throws BusinessException {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd,HH:mm:ss");
		Date dateActuelle = new Date(System.currentTimeMillis());
		Date dateChoisie = null;
		try {
			dateChoisie = formatter.parse(date + ",23:59:59");
		} catch (ParseException e) {
			throw new BusinessException();
		}
		if (dateChoisie.compareTo(dateActuelle) < 0) {
			return true;
		}
		return false;
	}

	public List<Enchere> getOpen(List<Enchere> listeEnchere) throws BusinessException {
		List<Enchere> newList = new ArrayList<Enchere>();
		for (Enchere enchere : listeEnchere) {
			if (compareDate(enchere.getArticle().getDate_fin_encheres().toString())) {
				newList.add(enchere);
			}
		}
		return newList;
	}

	public List<Enchere> getAchats(String mineOpenCheck, String mineCheck, String myWinCheck, String no_utilisateur)
			throws BusinessException {

		String mineOpen = "";
		String mine = "";
		String myWin = "";
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

		Date now = new Date();
		String dateNow = formatter.format(now);

		List<Enchere> listeEnchere = new ArrayList<Enchere>();
		try {
			if (mineOpenCheck != null) {
				mineOpen = "";
			} else if (mineCheck != null) {
				mine = "";
			} else if (myWinCheck != null) {
				myWin = "AND date_fin_encheres<'" + dateNow + "' AND u2.no_utilisateur = " + no_utilisateur;
			}
			listeEnchere = enchereDAO.findAllAchats(mine, mineOpen, myWin);
		} catch (DALException e) {
			e.printStackTrace();
		}
		return listeEnchere;
	}

	public List<Enchere> getVentes(String VenteEnCourCheck, String VenteNonDebuteesCheck, String VentesTermineesCheck,
			String no_utilisateur) throws BusinessException {
		String VenteEnCour = "";
		String VenteNonDebutees = "";
		String VentesTerminees = "";
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

		Date now = new Date();
		String dateNow = formatter.format(now);

		List<Enchere> listeEnchere = new ArrayList<Enchere>();
		try {
			if (VenteEnCourCheck != null) {
				VenteEnCour = "AND u.no_utilisateur = " + no_utilisateur + " AND date_debut_encheres < '" + dateNow
						+ "' AND date_fin_encheres >= '" + dateNow + "' ";
			} else if (VenteNonDebuteesCheck != null) {
				VenteNonDebutees = "AND u.no_utilisateur = " + no_utilisateur + " AND date_debut_encheres > '" + dateNow
						+ "' ";
			} else if (VentesTermineesCheck != null) {
				VentesTerminees = "AND date_fin_encheres<'" + dateNow + "' AND u.no_utilisateur = " + no_utilisateur;
			}
			listeEnchere = enchereDAO.findAllVentes(VenteEnCour, VenteNonDebutees, VentesTerminees);
		} catch (DALException e) {
			e.printStackTrace();
		}
		return listeEnchere;
	}

}
