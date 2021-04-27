package fr.eni.encheres.bll;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import fr.eni.encheres.bo.BusinessException;
import fr.eni.encheres.dal.DAOFactory;
import fr.eni.encheres.dal.UtilisateurDAO;

public class TestManager {
	static UtilisateurDAO con;

	public TestManager() {
		con = DAOFactory.getUtilisateurDAO();
	}

	public static void main(String[] args) {
		try {
			Boolean test = compareDate("26/04/2021");
			System.out.println(test);
		} catch (BusinessException e) {
			e.printStackTrace();
		}
	}

	public static void test() {

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		Date test = null;
		try {
			test = formatter.parse("2000-12-10");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		System.out.println(test);
		System.out.println(test.compareTo(date));

	}

	public static Boolean compareDate(String date) throws BusinessException {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy,HH:mm:ss");
		Date dateActuelle = new Date(System.currentTimeMillis());
		Date dateChoisie = null;
		try {
			dateChoisie = formatter.parse(date + ",23:59:59");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		System.out.println(dateChoisie);
		System.out.println(dateActuelle);
		System.out.println(dateChoisie.compareTo(dateActuelle));
		if (dateChoisie.compareTo(dateActuelle) >= 0) {
			return true;
		}
		return false;
	}

}
