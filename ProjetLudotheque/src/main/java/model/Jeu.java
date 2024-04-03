package model;

public class Jeu {

	private int idJeu;
	private String nom;
	private int nombreJoueursMax;
	private int nombreJoueursMin;
	private int annee;
	private int ageMin;
	private int ageMax;
	private int dureeMin;
	private int dureeMax;
	private String descriptif;
	private String editeur;
	private int disponible;
	private String etat;
	
	
	public Jeu(int idJeu, String nom, int nombreJoueursMax, int nombreJoueursMin, int annee, int ageMin, int ageMax, int dureeMin, int dureeMax, String descriptif, String editeur, int disponible, String etat) {
		super();
		
		this.idJeu = idJeu;
		this.nom = nom;
		this.nombreJoueursMax = nombreJoueursMax;
		this.nombreJoueursMin = nombreJoueursMin;
		this.annee = annee;
		this.ageMin = ageMin;
		this.ageMax = ageMax;
		this.dureeMin= dureeMin;
		this.dureeMax = dureeMax;
		this.descriptif = descriptif;
		this.editeur = editeur;
		this.disponible = disponible;
		this.etat = etat;
	}
	
	public Jeu(String nom, int nombreJoueursMax, int nombreJoueursMin, int annee, int ageMin, int ageMax, int dureeMin, int dureeMax, String descriptif, String editeur, int disponible, String etat) {
		super();
		
		this.nom = nom;
		this.nombreJoueursMax = nombreJoueursMax;
		this.nombreJoueursMin = nombreJoueursMin;
		this.annee = annee;
		this.ageMin = ageMin;
		this.ageMax = ageMax;
		this.dureeMin= dureeMin;
		this.dureeMax = dureeMax;
		this.descriptif = descriptif;
		this.editeur = editeur;
		this.disponible = disponible;
		this.etat = etat;
	}


	public int getIdJeu() {
		return idJeu;
	}


	public void setIdJeu(int idJeu) {
		this.idJeu = idJeu;
	}


	public String getNom() {
		return nom;
	}


	public void setNom(String nom) {
		this.nom = nom;
	}


	public int getNombreJoueursMax() {
		return nombreJoueursMax;
	}


	public void setNombreJoueursMax(int nombreJoueursMax) {
		this.nombreJoueursMax = nombreJoueursMax;
	}


	public int getNombreJoueursMin() {
		return nombreJoueursMin;
	}


	public void setNombreJoueursMin(int nombreJoueursMin) {
		this.nombreJoueursMin = nombreJoueursMin;
	}


	public int getAnnee() {
		return annee;
	}


	public void setAnnee(int annee) {
		this.annee = annee;
	}


	public int getAgeMin() {
		return ageMin;
	}


	public void setAgeMin(int ageMin) {
		this.ageMin = ageMin;
	}


	public int getAgeMax() {
		return ageMax;
	}


	public void setAgeMax(int ageMax) {
		this.ageMax = ageMax;
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


	public String getDescriptif() {
		return descriptif;
	}


	public void setDescriptif(String descriptif) {
		this.descriptif = descriptif;
	}


	public String getEditeur() {
		return editeur;
	}


	public void setEditeur(String editeur) {
		this.editeur = editeur;
	}


	public int getDisponible() {
		return disponible;
	}


	public void setDisponible(int disponible) {
		this.disponible = disponible;
	}


	public String getEtat() {
		return etat;
	}


	public void setEtat(String etat) {
		this.etat = etat;
	}


	

	
	
	
	
}
