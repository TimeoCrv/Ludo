package model;

import java.sql.Timestamp;

public class Emprunt {
	
		private int idEmprunt;
		private int idProfil;
		private int idJeuPhysique;
		private Adherent adherent;
		private Jeu jeu;
		private Timestamp dateEmprunt;
		private Timestamp dateARendre;
		
		public Emprunt(int idEmprunt, int idProfil, int idJeuPhysique, Adherent adherent, Jeu jeu, Timestamp dateEmprunt, Timestamp dateARendre) {
			super();
			
			this.idEmprunt = idEmprunt;
			this.idProfil = idProfil;
			this.idJeuPhysique = idJeuPhysique;
			this.adherent = adherent;
			this.jeu=jeu;
			this.dateEmprunt = dateEmprunt;
			this.dateARendre = dateARendre;
		}

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

		public Adherent getAdherent() {
			return adherent;
		}

		public void setAdherent(Adherent adherent) {
			this.adherent = adherent;
		}

		public Jeu getJeu() {
			return jeu;
		}

		public void setJeu(Jeu jeu) {
			this.jeu = jeu;
		}
		
}
