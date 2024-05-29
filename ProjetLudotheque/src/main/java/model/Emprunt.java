package model;

import java.security.Timestamp;

public class Emprunt {
	
		private int idEmprunt;
		private int idProfil;
		private int idJeuPhysique;
		private Timestamp dateEmprunt;
		private Timestamp dateARendre;
		

		public Emprunt(int idEmprunt, int idProfil, int idJeuPhysique, Timestamp dateEmprunt, Timestamp dateARendre) {
			super();
			
			this.idEmprunt = idEmprunt;
			this.idProfil = idProfil;
			this.idJeuPhysique = idJeuPhysique;
			this.dateEmprunt = dateEmprunt;
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


		public Timestamp getDateEmprunt() {
			return dateEmprunt;
		}


		public void setDateEmprunt(Timestamp dateEmprunt) {
			this.dateEmprunt = dateEmprunt;
		}


		public Timestamp getDateARendre() {
			return dateARendre;
		}


		public void setDateARendre(Timestamp dateARendre) {
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
		
}
