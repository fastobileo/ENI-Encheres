package fr.eni.encheres.bo;

public class Retrait {

	private int id;
	private String rue;
	private String code_postal;
	private String ville;
	
	public Retrait(int id, String rue, String code_postal, String ville) {
		super();
		this.id = id;
		this.rue = rue;
		this.code_postal = code_postal;
		this.ville = ville;
	}

	public Retrait() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRue() {
		return rue;
	}

	public void setRue(String rue) {
		this.rue = rue;
	}

	public String getCode_postal() {
		return code_postal;
	}

	public void setCode_postal(String code_postal) {
		this.code_postal = code_postal;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}
}
