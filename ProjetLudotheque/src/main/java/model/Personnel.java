package model;

public class Personnel {
	
	private int idPersonnel;
	private String nom;
	private String prenom;
	private String tel;
	private String email;
	private String adresse;
	
	
	
	public Personnel(int idPersonnel, String nom, String prenom, String tel, String email, String adresse) {
		super();
		this.idPersonnel = idPersonnel;
		this.nom = nom;
		this.prenom = prenom;
		this.tel = tel;
		this.email = email;
		this.adresse = adresse;
	}
	
	
	
	public Personnel(String nom, String prenom, String tel, String email, String adresse) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.tel = tel;
		this.email = email;
		this.adresse = adresse;
	}



	public int getIdPersonnel() {
		return idPersonnel;
	}
	public void setid_Personnel(int id_Personnel) {
		this.idPersonnel = idPersonnel;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAdresse() {
		return adresse;
	}
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}


	
	

}
