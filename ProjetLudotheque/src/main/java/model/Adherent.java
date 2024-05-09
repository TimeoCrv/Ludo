package model;

import java.sql.Timestamp;

public class Adherent {

		private int numero;
		private String nom;
		private String prenom;
		private String adresse;
		private String tel;
		private String mail;
		private Timestamp dateAdhesion;
		private Timestamp dateFinAdhesion;
		private boolean actif;
		private float caution;
		private String observations;
		private String password;
		private String salt;
		
		public Adherent(String nom, String prenom, String adresse, String tel,String mail,
						float caution, String observations, String password, String salt) {
			super();
			this.nom = nom;
			this.prenom = prenom;
			this.adresse = adresse;
			this.tel = tel;
			this.mail = mail;
			this.caution = caution;
			this.observations = observations;
			this.password = password;
			this.salt = salt;
}
		
		public Adherent(int numero, String nom, String prenom, String adresse, String tel, String mail,
						Timestamp dateAdhesion, Timestamp dateFinAdhesion, 
						boolean actif, float caution, String observations) {
			super();
			this.numero = numero;
			this.nom = nom;
			this.prenom = prenom;
			this.adresse = adresse;
			this.tel = tel;
			this.mail = mail;
			this.dateAdhesion = dateAdhesion;
			this.dateFinAdhesion = dateFinAdhesion;
			this.actif = actif;
			this.caution = caution;
			this.observations = observations;
		}
		

		public int getNumero() {
			return numero;
		}

		public void setNumero(int numero) {
			this.numero = numero;
		}

		public String getNom() {
			return nom;
		}

		public void setNom(String nom) {
			this.nom = nom;
		}

		public String getPrenom() {
			return prenom;
		}

		public void setPrenom(String prenom) {
			this.prenom = prenom;
		}

		public String getAdresse() {
			return adresse;
		}

		public void setAdresse(String adresse) {
			this.adresse = adresse;
		}

		public String getTel() {
			return tel;
		}

		public void setTel(String tel) {
			this.tel = tel;
		}

		public String getMail() {
			return mail;
		}

		public void setMail(String mail) {
			this.mail = mail;
		}

		public Timestamp getDateAdhesion() {
			return dateAdhesion;
		}

		public void setDateAdhesion(Timestamp dateAdhesion) {
			this.dateAdhesion = dateAdhesion;
		}

		public Timestamp getDateFinAdhesion() {
			return dateFinAdhesion;
		}

		public void setDateFinAdhesion(Timestamp dateFinAdhesion) {
			this.dateFinAdhesion = dateFinAdhesion;
		}

		public boolean isActif() {
			return actif;
		}

		public void setActif(boolean actif) {
			this.actif = actif;
		}

		public float getCaution() {
			return caution;
		}

		public void setCaution(float caution) {
			this.caution = caution;
		}

		public String getObservations() {
			return observations;
		}

		public void setObservations(String observations) {
			this.observations = observations;
		}
		
		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}
		
		public String getSalt() {
			return salt;
		}

		public void setSalt(String salt) {
			this.salt = salt;
		}

		@Override
		public String toString() {
			return "Adhérent [numero = " + numero + ", nom = " + nom + ", prenom = " + prenom + "]";
		}

}
