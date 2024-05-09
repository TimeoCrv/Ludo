package model;

import java.sql.Timestamp;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class AdherentDAO extends DAO<Adherent> {

	private static final String TABLE = "adherent";
	private static final String CLE_PRIMAIRE = "id_adherent";

	private static final String NOM_ADHERENT = "nom";
	private static final String PRENOM_ADHERENT = "prenom";
	private static final String ADRESSE = "adresse";
	private static final String TEL = "tel";
	private static final String MAIL = "email";
	private static final String DATE_ADHESION = "date_inscription";
	private static final String DATE_FIN_ADHESION = "date_inscription_fin";
	private static final String ACTIF = "actif";
	private static final String CAUTION = "caution";
	private static final String OBSERVATIONS = "observations";
	private static final String MOT_DE_PASSE = "password";
	private static final String SALT = "salt";

	
	private static AdherentDAO instance=null;

	public static AdherentDAO getInstance(){
		if (instance==null){
			instance = new AdherentDAO();
		}
		return instance;
	}

	private AdherentDAO() {
		super();
	}


	@Override
	public boolean create(Adherent adherent) {
		boolean succes=true;
		try {

			String requete = "INSERT INTO "+TABLE+" ("+NOM_ADHERENT+","+PRENOM_ADHERENT+" , "+ADRESSE+
							" , "+TEL+" , "+MAIL+" , "+DATE_ADHESION+" , "+DATE_FIN_ADHESION+
							" , "+ACTIF+" , "+CAUTION+" , "+OBSERVATIONS+" , "+MOT_DE_PASSE+
							" , "+SALT+") VALUES (?, ?, ?,?, ?, ?,?, ?, ?, ?, ?, ?)";
			PreparedStatement pst = Connexion.getInstance().prepareStatement(requete, Statement.RETURN_GENERATED_KEYS);
			// on pose un String en param�tre 1 -1er '?'- et ce String est le nom de l'avion
			pst.setString(1, adherent.getNom());
			pst.setString(2, adherent.getPrenom());
			pst.setString(3, adherent.getAdresse());
			pst.setString(4, adherent.getTel());
			pst.setString(5, adherent.getMail());
			
			Date startDate = new Date();			
			pst.setTimestamp(6, new Timestamp(startDate.getTime()));
			
            Calendar cal = Calendar.getInstance();
            cal.setTime(startDate);
            cal.add(Calendar.YEAR, 1);
            Date endDate = cal.getTime();
			pst.setTimestamp(7, new Timestamp(endDate.getTime()));
			
			pst.setBoolean(8, true);
			pst.setFloat(9, adherent.getCaution());
			
			String observations = (adherent.getObservations() != null) ? adherent.getObservations() : "";
			
			pst.setString(10, observations);
			pst.setString(11, adherent.getPassword());
			pst.setString(12, adherent.getSalt());
			// on ex�cute la mise � jour
			pst.executeUpdate();

			//R�cup�rer la cl� qui a �t� g�n�r�e et la pousser dans l'objet initial
			ResultSet rs = pst.getGeneratedKeys();
			if (rs.next()) {
				adherent.setNumero(rs.getInt(1));
			}
			donnees.put(adherent.getNumero(), adherent);

		} catch (SQLException e) {
			succes=false;
			e.printStackTrace();
		}

		return succes;
	}

	@Override
	public boolean delete(Adherent adherent) {
		boolean succes = true;
		try {
			int id = adherent.getNumero();
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
	public boolean update(Adherent adherent) {
		boolean succes=true;

		String nom =adherent.getNom();
		String prenom =adherent.getPrenom();
		String adresse =adherent.getAdresse();
		String tel =adherent.getTel();
		String mail =adherent.getMail();
		Timestamp dateAdhesion =adherent.getDateAdhesion();
		Timestamp dateFinAdhesion =adherent.getDateFinAdhesion();
		Boolean actif =adherent.isActif();
		float caution =adherent.getCaution();
		String observations =adherent.getObservations();
		int id = adherent.getNumero();

		try {
			String requete = "UPDATE "+TABLE+" SET "+NOM_ADHERENT+" = ?, "+PRENOM_ADHERENT+" = ?, "
							+ADRESSE+" = ?, "+TEL+" = ?, "+MAIL+" = ?, "+DATE_ADHESION+" = ?, "
							+DATE_FIN_ADHESION+" = ?, "+ACTIF+" = ?"+CAUTION+" = ?"
							+OBSERVATIONS+" = ?"+"WHERE "+CLE_PRIMAIRE+" = ?";
			PreparedStatement pst = Connexion.getInstance().prepareStatement(requete) ;
			pst.setString(1,nom) ; 
			pst.setString(2,prenom) ;
			pst.setString(3,adresse) ;
			pst.setString(4,tel) ;
			pst.setString(5,mail) ;
			pst.setTimestamp(6,dateAdhesion) ;
			pst.setTimestamp(7,dateFinAdhesion) ;
			pst.setBoolean(8,actif) ;
			pst.setFloat(9, caution) ;
			pst.setString(10,observations) ;
			pst.executeUpdate() ;
			donnees.put(id, adherent);
		} catch (SQLException e) {
			succes = false;
			e.printStackTrace();
		} 
		return succes;	
	}

	@Override
	public Adherent read(int id) {
		Adherent adherent = null;
		
		System.out.println("recherché dans la BD");
		try {

			String requete = "SELECT * FROM "+TABLE+" WHERE "+CLE_PRIMAIRE+" = "+id;
			ResultSet rs = Connexion.executeQuery(requete);
			rs.next();
			String nom = rs.getString(NOM_ADHERENT);
			String prenom = rs.getString(PRENOM_ADHERENT);
			String adresse = rs.getString(ADRESSE);
			String tel = rs.getString(TEL);
			String mail = rs.getString(MAIL);
			Timestamp dateAdhesion = rs.getTimestamp(DATE_ADHESION);
			Timestamp dateFinAdhesion = rs.getTimestamp(DATE_FIN_ADHESION);
			Boolean actif = rs.getBoolean(ACTIF);
			float caution = rs.getFloat(CAUTION);
			String observations = rs.getString(OBSERVATIONS);
			adherent = new Adherent (id, nom, prenom, adresse, tel, mail, dateAdhesion,
									dateFinAdhesion, actif, caution, observations);
			donnees.put(id, adherent);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return adherent;
	}

	public void afficheSelectEtoileAdherent() {
		System.out.println("--- Liste adhérent ---");
		String clauseWhere = null;
		Connexion.afficheSelectEtoile("Adherent", clauseWhere);

	}

	public void afficheAdherent(int id) {
		System.out.println("--- Liste adhérent ---");
		String clauseWhere = CLE_PRIMAIRE + " = " + id;
		Connexion.afficheSelectEtoile("Adherent", clauseWhere);

	}
	
	public List<Adherent> readTable() {
		List<Adherent> rep = new ArrayList<Adherent>();
		Adherent ad = null;
		try{
			String requete = "SELECT "+CLE_PRIMAIRE+" FROM "+TABLE;
			ResultSet res = Connexion.executeQuery(requete) ;
			while(res.next()){
				int id = res.getInt(1);
				ad = AdherentDAO.getInstance().read(id);
				rep.add(ad);
			}
		}
		catch(SQLException e){
			System.out.println("Echec de la tentative d'interrogation Select * : " + e.getMessage()) ;
		}
		return rep;
	}
	
	public String getPasswordById(int id) {
		String password = null;
		try{
			// Requête SQL pour récupérer le mot de passe par ID
            String requete = "SELECT "+MOT_DE_PASSE+" FROM "+TABLE+" WHERE "+CLE_PRIMAIRE+" = ?";
            PreparedStatement pst = Connexion.getInstance().prepareStatement(requete) ;
            pst.setInt(1, id);

            // Exécution de la requête
            ResultSet resultSet = pst.executeQuery();

            // Récupération du mot de passe s'il existe
            if (resultSet.next()) {
                password = resultSet.getString(MOT_DE_PASSE);
            }
		}
		catch(SQLException e){
			System.out.println("Echec de la tentative d'interrogation Select * : " + e.getMessage()) ;
		}
		return password;
		
	}
	
	public String getSaltById(int id) {
		String password = null;
		try{
            String requete = "SELECT "+SALT+" FROM "+TABLE+" WHERE "+CLE_PRIMAIRE+" = ?";
            PreparedStatement pst = Connexion.getInstance().prepareStatement(requete) ;
            pst.setInt(1, id);

            // Exécution de la requête
            ResultSet resultSet = pst.executeQuery();

            // Récupération du mot de passe s'il existe
            if (resultSet.next()) {
                password = resultSet.getString(SALT);
            }
		}
		catch(SQLException e){
			System.out.println("Echec de la tentative d'interrogation Select * : " + e.getMessage()) ;
		}
		return password;
		
	}
	
	public String getEmailById(int id) {
		String email = null;
		try{
			// Requête SQL pour récupérer le mot de passe par ID
            String requete = "SELECT "+MAIL+" FROM "+TABLE+" WHERE "+CLE_PRIMAIRE+" = ?";
            PreparedStatement pst = Connexion.getInstance().prepareStatement(requete) ;
            pst.setInt(1, id);

            // Exécution de la requête
            ResultSet resultSet = pst.executeQuery();

            // Récupération du mot de passe s'il existe
            if (resultSet.next()) {
                email = resultSet.getString(MAIL);
            }
		}
		catch(SQLException e){
			System.out.println("Echec de la tentative d'interrogation Select * : " + e.getMessage()) ;
		}
		return email;
		
	}
	
	public int getIdByEmail(String email) {
		int id = 0;
		try{
			// Requête SQL pour récupérer le mot de passe par ID
            String requete = "SELECT "+CLE_PRIMAIRE+" FROM "+TABLE+" WHERE "+MAIL+" = ?";
            PreparedStatement pst = Connexion.getInstance().prepareStatement(requete) ;
            pst.setString(1, email);

            // Exécution de la requête
            ResultSet resultSet = pst.executeQuery();

            // Récupération du mot de passe s'il existe
            if (resultSet.next()) {
                id = resultSet.getInt(CLE_PRIMAIRE);
            }
		}
		catch(SQLException e){
			System.out.println("Echec de la tentative d'interrogation Select * : " + e.getMessage()) ;
		}
		return id;
		
	}

}
