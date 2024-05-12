package model;

public class Personnel {
	
	private int idUtilisateur;
	private String nom;
	private String prenom;
	private String tel;
	private String adresse;
	private boolean isAdmin;
	
	
	
	public Personnel(String nom, String prenom, String tel, String adresse) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.tel = tel;
		this.adresse = adresse;
		//this.isAdmin = isAdmin;
		
	}
	
	
	
	public Personnel(int idUtilisateur,String nom, String prenom, String tel, String adresse) {
		super();
		this.idUtilisateur=idUtilisateur;
		this.nom = nom;
		this.prenom = prenom;
		this.tel = tel;
		this.adresse = adresse;
	}


	public int getIdutilisateur() {
		return idUtilisateur;
	}



	public void setIdutilisateur(int idUtilisateur) {
		this.idUtilisateur = idUtilisateur;
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

	public String getAdresse() {
		return adresse;
	}



	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}



	public boolean getIsAdmin() {
		return isAdmin;
	}



	public void setIsAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

}

	