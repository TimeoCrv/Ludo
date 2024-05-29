package model;

import java.sql.Timestamp;

public class Emprunt {
private int idProfil;
private int idJeuPhysique;
private Timestamp dateEmprunt;
private Timestamp dateRendre;

//constructeur pour create
public Emprunt(int idProfil, int idJeuPhysique, Timestamp dateEmprunt, Timestamp dateRendre) {
	super();
	this.idProfil = idProfil;
	this.idJeuPhysique = idJeuPhysique;
	this.dateEmprunt = dateEmprunt;
	this.dateRendre = dateRendre;
}

public int getIdProfil() {
	return idProfil;
}

public void setIdProfil(int idProfil) {
	this.idProfil = idProfil;
}

public int getIdJeuPhysique() {
	return idJeuPhysique;
}

public void setIdJeuPhysique(int idJeuPhysique) {
	this.idJeuPhysique = idJeuPhysique;
}

public Timestamp getDateEmprunt() {
	return dateEmprunt;
}

public void setDateEmprunt(Timestamp dateEmprunt) {
	this.dateEmprunt = dateEmprunt;
}

public Timestamp getDateRendre() {
	return dateRendre;
}

public void setDateRendre(Timestamp dateRendre) {
	this.dateRendre = dateRendre;
}



}
