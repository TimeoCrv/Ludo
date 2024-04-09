package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PersonneAssocieDAO extends DAO<PersonneAssocie> {

		private static final String TABLE = "personnes_associes";
		private static final String CLE_PRIMAIRE = "id_personnes_associes";

		private static final String NOM = "nom";
		private static final String PRENOM = "prenom";
		private static final String ADRESSE = "adresse";
		private static final String TEL = "tel";
		private static final String MAIL = "email";
		private static final String NO_CNI = "numero_carte_identite";
		private static final String CLE_ETRANGERE ="id_adherent";


		
		private static PersonneAssocieDAO instance=null;

		public static PersonneAssocieDAO getInstance(){
			if (instance==null){
				instance = new PersonneAssocieDAO();
			}
			return instance;
		}

		private PersonneAssocieDAO() {
			super();
		}


		@Override
		public boolean create(PersonneAssocie personneAssocie) {
			boolean succes=true;
			try {

				String requete = "INSERT INTO "+TABLE+" ("+NOM+","+PRENOM+" , "+ADRESSE+
								" , "+TEL+" , "+MAIL+", "+NO_CNI+") VALUES (?, ?, ?,?, ?, ?)";
				PreparedStatement pst = Connexion.getInstance().prepareStatement(requete, Statement.RETURN_GENERATED_KEYS);
				// on pose un String en param�tre 1 -1er '?'- et ce String est le nom de l'avion
				pst.setString(1, personneAssocie.getNom());
				pst.setString(2, personneAssocie.getPrenom());
				pst.setString(3, personneAssocie.getAdresse());
				pst.setString(4, personneAssocie.getTel());
				pst.setString(5, personneAssocie.getEmail());
				pst.setString(6, personneAssocie.getNumeroCarteIdentite());
				
				pst.executeUpdate();

				//R�cup�rer la cl� qui a �t� g�n�r�e et la pousser dans l'objet initial
				ResultSet rs = pst.getGeneratedKeys();
				if (rs.next()) {
					personneAssocie.setIdPersonneAssocie(rs.getInt(1));
				}
				donnees.put(personneAssocie.getIdPersonneAssocie(), personneAssocie);

			} catch (SQLException e) {
				succes=false;
				e.printStackTrace();
			}

			return succes;
		}

		@Override
		public boolean delete(PersonneAssocie personneAssocie) {
			boolean succes = true;
			try {
				int id = personneAssocie.getIdPersonneAssocie();
				String requete = "DELETE FROM "+TABLE+" WHERE "+CLE_PRIMAIRE+" = ?";
				PreparedStatement pst = Connexion.getInstance().prepareStatement(requete);
				pst.setInt(1, id);
				pst.executeUpdate();
				donnees.remove(id);
			} catch (SQLException e) {
				succes=false;
				e.printStackTrace();
			}
			return succes;
		}

		@Override
		public boolean update(PersonneAssocie personneAssocie) {
			boolean succes=true;

			String nom =personneAssocie.getNom();
			String prenom =personneAssocie.getPrenom();
			String adresse =personneAssocie.getAdresse();
			String tel =personneAssocie.getTel();
			String mail =personneAssocie.getEmail();
			String cni =personneAssocie.getNumeroCarteIdentite();
			int id = personneAssocie.getIdPersonneAssocie();

			try {
				String requete = "UPDATE "+TABLE+" SET "+NOM+" = ?, "+PRENOM+" = ?, "
						+ADRESSE+" = ?, "+TEL+" = ?, "+MAIL+" = ?, "+NO_CNI+" = ?"+"WHERE "+CLE_ETRANGERE+" = ?";
				PreparedStatement pst = Connexion.getInstance().prepareStatement(requete) ;
				pst.setString(1,nom) ; 
				pst.setString(2,prenom) ;
				pst.setString(3,adresse) ;
				pst.setString(3, cni);
				pst.setString(4,tel) ;
				pst.setString(5,mail) ;
				pst.executeUpdate() ;
				donnees.put(id, personneAssocie);
			} catch (SQLException e) {
				succes = false;
				e.printStackTrace();
			} 
			return succes;	
		}

		@Override
		public PersonneAssocie read(int id) {
			PersonneAssocie personneAssocie = null;
			
			System.out.println("recherché dans la BD");
			try {

				String requete = "SELECT * FROM "+TABLE+" WHERE "+CLE_PRIMAIRE+" = "+id;
				ResultSet rs = Connexion.executeQuery(requete);
				rs.next();
				String nom = rs.getString(NOM);
				String prenom = rs.getString(PRENOM);
				String adresse = rs.getString(ADRESSE);
				String tel = rs.getString(TEL);
				String mail = rs.getString(MAIL);
				String cni = rs.getString(NO_CNI);
				Integer id_adherent = rs.getInt(CLE_ETRANGERE);
				personneAssocie = new PersonneAssocie (nom, prenom, adresse, tel, mail, cni, id_adherent );
				donnees.put(id, personneAssocie);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			return personneAssocie;
		}

		public void afficheSelectPersonneAssocie() {
			System.out.println("--- Liste Personne Associe ---");
			String clauseWhere = null;
			Connexion.afficheSelectEtoile("Personne Associe", clauseWhere);

		}

		public void affichePersonneAssocie(int id) {
			System.out.println("--- Liste Personne Associe ---");
			String clauseWhere = CLE_PRIMAIRE + " = " + id;
			Connexion.afficheSelectEtoile("Personne Associe", clauseWhere);

		}
		
		public List<PersonneAssocie> readTable() {
			List<PersonneAssocie> rep = new ArrayList<PersonneAssocie>();
			PersonneAssocie ad = null;
			try{
				String requete = "SELECT "+CLE_PRIMAIRE+" FROM "+TABLE;
				ResultSet res = Connexion.executeQuery(requete) ;
				while(res.next()){
					int id = res.getInt(1);
					ad = PersonneAssocieDAO.getInstance().read(id);
					rep.add(ad);
				}
			}
			catch(SQLException e){
				System.out.println("Echec de la tentative d'interrogation Select * : " + e.getMessage()) ;
			}
			return rep;
		}


}
