package ludo;

import java.sql.Timestamp;

public class Adherent {

		private int numero;
		private String nom;
		private String prenom;
		private String adresse;
		private int cp;
		private String ville;
		private String tel;
		private String mail;
		private Timestamp dateAdhesion;
		private Timestamp dateFinAdhesion;
		private float caution;
		
		public Adherent(String nom, String prenom, String adresse,
				int cp,String ville,String tel,String mail, float caution) {
			super();
			this.nom = nom;
			this.prenom = prenom;
			this.adresse = adresse;
			this.cp = cp;
			this.ville = ville;
			this.tel = tel;
			this.mail = mail;
			this.caution = caution;
}
		
		public Adherent(int numero, String nom, String prenom, String adresse,
						int cp,String ville,String tel,String mail,Timestamp dateAdhesion,
						Timestamp dateFinAdhesion,float caution) {
			super();
			this.numero = numero;
			this.nom = nom;
			this.prenom = prenom;
			this.adresse = adresse;
			this.cp = cp;
			this.ville = ville;
			this.tel = tel;
			this.mail = mail;
			this.dateAdhesion = dateAdhesion;
			this.dateFinAdhesion = dateFinAdhesion;
			this.caution = caution;
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



		public int getCp() {
			return cp;
		}



		public void setCp(int cp) {
			this.cp = cp;
		}



		public String getVille() {
			return ville;
		}



		public void setVille(String ville) {
			this.ville = ville;
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



		public float getCaution() {
			return caution;
		}



		public void setCaution(float caution) {
			this.caution = caution;
		}



		@Override
		public String toString() {
			return "Adhérent [numero = " + numero + ", nom = " + nom + ", prenom = " + prenom + "]";
		}

}
