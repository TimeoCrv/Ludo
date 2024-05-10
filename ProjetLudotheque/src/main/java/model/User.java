package model;

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
