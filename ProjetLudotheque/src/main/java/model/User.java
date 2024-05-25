package model;


// Classe créée pour faciliter la création de session
// Besoin de l'ID pour identifier l'utilisateur et le rôle pour les droits sur l'application
// Possibilité d'être étendue à d'autres fonctions de l'application si besoin

public class User {
	
	private int id;
	private String email;
	private String password;
	private String salt;
	private String role;
	
	public User(int id, String email, String password, String salt, String role) {
		this.id = id;
		this.email = email;
		this.password = password;
		this.salt = salt;
		this.role = role;
	}
	
	public int getId() {
		return this.id;
	}
	
	public String getEmail() {
		return this.email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}

	public String getRole() {
		return this.role;
	}
	
	public String getPassword() {
		return this.password;
	}
	
	public String getSalt() {
		return this.salt;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", role=" + role + "]";
	}


}

