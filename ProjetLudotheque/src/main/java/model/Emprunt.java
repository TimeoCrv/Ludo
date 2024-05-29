package model;

import java.sql.Timestamp;

public class Emprunt {
	
	private int idJeuPhysique;
	private int idProfil;
	private Timestamp dateEmprunte;
	private Timestamp dateRendre;
	
	// create
	public Emprunt(int idJeuPhysique, int idProfil) {
		super();
		this.idJeuPhysique = idJeuPhysique;
		this.idProfil = idProfil;
	}


	public int getIdJeuPhysique() {
		return idJeuPhysique;
	}


	public void setIdJeuPhysique(int idJeuPhysique) {
		this.idJeuPhysique = idJeuPhysique;
	}


	public int getIdProfil() {
		return idProfil;
	}


	public void setIdProfil(int idProfil) {
		this.idProfil = idProfil;
	}


	public Timestamp getDateEmprunte() {
		return dateEmprunte;
	}


	public void setDateEmprunte(Timestamp dateEmprunte) {
		this.dateEmprunte = dateEmprunte;
	}


	public Timestamp getDateRendre() {
		return dateRendre;
	}


	public void setDateRendre(Timestamp dateRendre) {
		this.dateRendre = dateRendre;
	}
	
	
	
	
	
	
	
	

}
