package fr.eni.encheres.bll;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.chrono.ChronoLocalDate;
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
	
	public Enchere getEnchereInnerJoin(int no_enchere) throws BusinessException {
		
		Enchere enchere = new Enchere();
		
		try {
			enchereDAO.getEnchereInnerJoin(no_enchere);		
		} catch (DALException e) {
			e.printStackTrace();
		}
		return enchere;
	}
	    
	public boolean verifEnchere(Enchere enchere, int enchereProposee) throws BusinessException{
		
		boolean enchereAcceptee = false;
		
		ChronoLocalDate chronoLocalDate = asChronoLocalDate(asLocalDate(enchere.getArticle().getDate_fin_encheres()));
		
		if (LocalDate.now().isBefore(chronoLocalDate) && enchereProposee > enchere.getMontant_enchere()) {
			enchereAcceptee = true;
		}		
		return enchereAcceptee;		
	}
	
	public int acceptationEnchere (Enchere enchere, int enchereProposee)  throws BusinessException{
		
		int enchereEnvoyee = 0;
		
		if (verifEnchere(enchere, enchereProposee)) {
			enchereEnvoyee = enchereProposee;
		} else {
			enchereEnvoyee = enchere.getMontant_enchere();
		}		
		return enchereEnvoyee;
	}
	
	public static ChronoLocalDate asChronoLocalDate(LocalDate date) {
	    return ChronoLocalDate.from(((LocalDate) date).atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
	}
	
	public static LocalDate asLocalDate(Date date) {
	    return Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
	  }	
}
