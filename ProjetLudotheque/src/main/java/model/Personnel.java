package model;

public class Personnel {
	
	private int id_personnel;
	private String nom;
	private String prenom;
	private String tel;
	private String email;
	private String adresse;
	private boolean isAdmin;
	
	private String password;
	private String salt;
	private String role = "";
	
	
	//Constructeur pour Create
	public Personnel(String nom, String prenom,String email, String tel, String adresse, boolean isAdmin, String password, String salt, String role) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.tel = tel;
		this.adresse = adresse;
		this.isAdmin = isAdmin;
		this.password = password;
		this.salt = salt;
		this.role=role;
		
	}
	
	//Constructeur pour update et read
	public Personnel(int id_personnel, String nom, String prenom,String email, String tel, String adresse, boolean isAdmin,String password,String salt, String role) {
		super();
		this.id_personnel=id_personnel;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.tel = tel;
		this.adresse = adresse;
		this.isAdmin = isAdmin;
		this.password = password;
		this.salt = salt;
		this.role = role;
	}



	public int getId_personnel() {
		return id_personnel;
	}



	public void setId_personnel(int id_personnel) {
		this.id_personnel = id_personnel;
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



	public boolean isAdmin() {
		return isAdmin;
	}



	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}



	public String getSalt() {
		return salt;
	}



	public void setSalt(String salt) {
		this.salt = salt;
	}



	public String getRole() {
		return role;
	}



	public void setRole(String role) {
		this.role = role;
	}


	
}