package model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Emprunt {
	
		private int idEmprunt;
		private int idProfil;
		private int idJeuPhysique;
		private Date dateEmprunt;
		private Date dateARendre;
		private String nom;
		

		public Emprunt(String nom, Date dateEmprunte, Date dateARendre) {
			super();
			this.setNom(nom);
			this.dateEmprunt = dateEmprunte;
			this.dateARendre = dateARendre;
		}
		
		public Emprunt(int idProfil, int idJeuPhysique) {
			super();
			
			this.idProfil = idProfil;
			this.idJeuPhysique = idJeuPhysique;
		}


		public int getIdJeuPhysique() {
			return idJeuPhysique;
		}


		public void setIdJeuPhysique(int idJeuPhysique) {
			this.idJeuPhysique = idJeuPhysique;
		}


		public Date getDateEmprunt() {
			return dateEmprunt;
		}


		public void setDateEmprunt(Date dateEmprunt) {
			this.dateEmprunt = dateEmprunt;
		}


		public Date getDateARendre() {
			return dateARendre;
		}


		public void setDateARendre(Date dateARendre) {
			this.dateARendre = dateARendre;
		}

		
		public int getIdProfil() {
			return idProfil;
		}


		public void setIdProfil(int idProfil) {
			this.idProfil = idProfil;
		}


		public int getIdEmprunt() {
			return idEmprunt;
		}


		public void setIdEmprunt(int idEmprunt) {
			this.idEmprunt = idEmprunt;
		}

		public String getNom() {
			return nom;
		}

		public void setNom(String nom) {
			this.nom = nom;
		}
		
		
}
