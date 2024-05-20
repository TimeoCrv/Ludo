package model;

public class JeuPhysique {
	private int idJeuPhysique;
	private String etat;
	
	public JeuPhysique(int idJeuPhysique, String etat) {
		super();
		
		this.idJeuPhysique = idJeuPhysique;
		this.etat = etat;
	}
	
	public JeuPhysique(String etat) {
		super();
		
		this.etat = etat;
	}

	public int getIdJeuPhysique() {
		return idJeuPhysique;
	}

	public void setIdJeuPhysique(int idJeuPhysique) {
		this.idJeuPhysique = idJeuPhysique;
	}

	public String getEtat() {
		return etat;
	}

	public void setEtat(String etat) {
		this.etat = etat;
	}
	
	
}


