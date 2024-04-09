package model;

public class PersonneAssocie {

	private int idPersonneAssocie;
	private String nom;
	private String prenom;
	private String tel;
	private String email;
	private String adresse;
	private String numeroCarteIdentite;
	private int idAdherent;



	
	
	public PersonneAssocie(int idPersonneAssocie, String nom, String prenom, String tel, String email, String adresse,
			String numeroCarteIdentite, int idAdherent) {
		super();
		this.idPersonneAssocie = idPersonneAssocie;
		this.nom = nom;
		this.prenom = prenom;
		this.tel = tel;
		this.email = email;
		this.adresse = adresse;
		this.numeroCarteIdentite = numeroCarteIdentite;
		this.idAdherent = idAdherent;
	}

	public PersonneAssocie(String nom, String prenom, String adresse, String tel,String email,
			String numeroCarteIdentite, int idAdherent) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.adresse = adresse;
		this.tel = tel;
		this.numeroCarteIdentite = numeroCarteIdentite;
		this.idAdherent = idAdherent;
		
	}

	public int getIdPersonneAssocie() {
		return idPersonneAssocie;
	}



	public void setIdPersonneAssocie(int idPersonneAssocie) {
		this.idPersonneAssocie = idPersonneAssocie;
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



	public String getNumeroCarteIdentite() {
		return numeroCarteIdentite;
	}



	public void setNumeroCarteIdentite(String numeroCarteIdentite) {
		this.numeroCarteIdentite = numeroCarteIdentite;
	}


	public int getIdAdherent() {
		return idAdherent;
	}



	public void setIdAdherent(int idAdherent) {
		this.idAdherent = idAdherent;
	}

	@Override
	public String toString() {
		return "PersonnesAssocie [idPersonneAssocie=" + idPersonneAssocie + ", nom=" + nom + ", prenom=" + prenom
				+ ", idAdherent=" + idAdherent + "]";
	}

}