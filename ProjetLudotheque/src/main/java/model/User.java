package model;

// Classe créée pour faciliter la création de session
// Besoin de l'ID pour identifier l'utilisateur et le rôle pour les droits sur l'application
// Possibilité d'être étendue à d'autres fonctions de l'application si besoin

public class User {
	
	protected int id;
	protected String role;
	
	public User(int id, String role) {
		this.id = id;
		this.role = role;
	}
	
	public int getId() {
		return id;
	}

	public String getRole() {
		return role;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", role=" + role + "]";
	}

}
