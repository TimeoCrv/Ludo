package model;

public class Jeu {

	private int id;
	private String nom;
	private int ageMin;
	private int nombreJoueursMini;
	private int annee;
	private int dureeMin;
	private int dureeMax;
	private int complexite;
	private String descriptif;
	private int disponible;
	private int etatInitiale;
	private int etatActuel;
	
	public Jeu(int id, String nom, int ageMin, int nombreJoueurMini, int annee, int dureeMin, int dureeMax, int complexite, String descriptif, int disponible, int etatInitiale, int etatActuel, int nombreJoueursMini) {
		super();
		
		this.id = id;
		this.nom = nom;
		this.ageMin = ageMin;
		this.nombreJoueursMini = nombreJoueursMini;
		this.annee = annee;
		this.dureeMin = dureeMin;
		this.dureeMax = dureeMax;
		this.complexite = complexite;
		this.descriptif = descriptif;
		this.disponible = disponible;
		this.etatInitiale = etatInitiale;
		this.etatActuel = etatActuel;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public int getAgeMin() {
		return ageMin;
	}

	public void setAgeMin(int ageMin) {
		this.ageMin = ageMin;
	}

	public int getNombreJoueursMini() {
		return nombreJoueursMini;
	}

	public void setNombreJoueursMini(int nombreJoueursMini) {
		this.nombreJoueursMini = nombreJoueursMini;
	}

	public int getAnnee() {
		return annee;
	}

	public void setAnnee(int annee) {
		this.annee = annee;
	}

	public int getDureeMin() {
		return dureeMin;
	}

	public void setDureeMin(int dureeMin) {
		this.dureeMin = dureeMin;
	}

	public int getDureeMax() {
		return dureeMax;
	}

	public void setDureeMax(int dureeMax) {
		this.dureeMax = dureeMax;
	}

	public int getComplexite() {
		return complexite;
	}

	public void setComplexite(int complexite) {
		this.complexite = complexite;
	}

	public String getDescriptif() {
		return descriptif;
	}

	public void setDescriptif(String descriptif) {
		this.descriptif = descriptif;
	}

	public int getDisponible() {
		return disponible;
	}

	public void setDisponible(int disponible) {
		this.disponible = disponible;
	}

	public int getEtatInitiale() {
		return etatInitiale;
	}

	public void setEtatInitiale(int etatInitiale) {
		this.etatInitiale = etatInitiale;
	}

	public int getEtatActuel() {
		return etatActuel;
	}

	public void setEtatActuel(int etatActuel) {
		this.etatActuel = etatActuel;
	}
	
	
	
}
