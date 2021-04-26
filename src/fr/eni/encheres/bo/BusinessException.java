package fr.eni.encheres.bo;

import java.util.ArrayList;
import java.util.List;

public class BusinessException extends Exception {
	private static final long serialVersionUID = 1L;
	
	private List<Integer> listeErreur;
	
	private String message;

	public BusinessException() {
		super();
		this.listeErreur=new ArrayList<>();
	}
	
	public BusinessException(String message) {
		super();
		this.message = message;
	}

	public void ajouterErreur(int code)
	{
		if(!this.listeErreur.contains(code))
		{
			this.listeErreur.add(code);
		}
	}
	
	public boolean hasErreurs()
	{
		return this.listeErreur.size()>0;
	}
	
	public List<Integer> getlisteErreur()
	{
		return this.listeErreur;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}