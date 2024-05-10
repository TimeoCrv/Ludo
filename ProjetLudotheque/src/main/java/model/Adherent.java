package model;

import java.sql.Timestamp;

public class Adherent {

		private int idProfil;
		private String nom;
		private String prenom;
		private String tel;
		private String adresse;
		private String email;
		private Timestamp dateInscription;
		private Timestamp dateInscriptionFin;
		private String noCNI;
		private boolean actif;
		private float caution;
		private String observations;
		private String password;
		private String salt;
		private String role = "adherent";
		
		//Constructeur pour Create
		public Adherent(String nom, String prenom, String tel, String adresse, String email, String noCNI,
				float caution, String observations, String password, String salt) {
			super();
			this.nom = nom;
			this.prenom = prenom;
			this.tel = tel;
			this.adresse = adresse;
			this.email = email;
			this.noCNI = noCNI;
			this.observations = observations;
			this.password = password;
			this.salt = salt;
		}

		//Constructeur pour Read et Update
		public Adherent(int idProfil, String nom, String prenom, String tel, String adresse, String email,
				Timestamp dateInscription, Timestamp dateInscriptionFin, String noCNI, boolean actif, float caution,
				String observations, String password, String salt, String role) {
			super();
			this.idProfil = idProfil;
			this.nom = nom;
			this.prenom = prenom;
			this.tel = tel;
			this.adresse = adresse;
			this.email = email;
			this.dateInscription = dateInscription;
			this.dateInscriptionFin = dateInscriptionFin;
			this.noCNI = noCNI;
			this.actif = actif;
			this.caution = caution;
			this.observations = observations;
			this.password = password;
			this.salt = salt;
			this.role = role;
		}


		public int getIdProfil() {
			return idProfil;
		}

		public void setIdProfil(int idProfil) {
			this.idProfil = idProfil;
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

		public String getTel() {
			return tel;
		}

		public void setTel(String tel) {
			this.tel = tel;
		}

		public String getAdresse() {
			return adresse;
		}

		public void setAdresse(String adresse) {
			this.adresse = adresse;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public Timestamp getDateInscription() {
			return dateInscription;
		}

		public void setDateInscription(Timestamp dateInscription) {
			this.dateInscription = dateInscription;
		}

		public Timestamp getDateInscriptionFin() {
			return dateInscriptionFin;
		}

		public void setDateInscriptionFin(Timestamp dateInscriptionFin) {
			this.dateInscriptionFin = dateInscriptionFin;
		}

		public String getNoCNI() {
			return noCNI;
		}

		public void setNoCNI(String noCNI) {
			this.noCNI = noCNI;
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

		public String getRole() {
			return role;
		}

		public void setRole(String role) {
			this.role = role;
		}

		@Override
		public String toString() {
			return "Adherent [idProfil=" + idProfil + ", nom=" + nom + ", prenom=" + prenom + "]";
		}

}
