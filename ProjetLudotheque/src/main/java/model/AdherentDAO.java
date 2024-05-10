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
	
	//Table utilisateur
	private static final String TABLE_UTILISATEUR = "utilisateur";
	private static final String ID_UTILISATEUR = "id_utilisateur";
	private static final String EMAIL = "email";
	private static final String MOT_DE_PASSE = "password";
	private static final String SALT = "salt";
	private static final String ROLE = "role";

	//Table profil (adhérent)
	private static final String TABLE_PROFIL = "profil";
	private static final String ID_PROFIL = "id_profil";
	private static final String NOM = "nom";
	private static final String PRENOM = "prenom";
	private static final String TEL = "tel";
	private static final String ADRESSE = "adresse";
	private static final String DATE_INSCRIPTION = "date_inscription";
	private static final String DATE_INSCRIPTION_FIN = "date_inscription_fin";
	private static final String NO_CNI = "numero_carte_identite";
	private static final String ACTIF = "actif";
	private static final String CAUTION = "caution";
	private static final String OBSERVATIONS = "observations";

	
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
			
			String requeteUtilisateur = "INSERT INTO "+TABLE_UTILISATEUR+" ("+EMAIL+" , "+MOT_DE_PASSE+
					" , "+SALT+" , "+ROLE+") VALUES (?, ?, ?, ?)";

			//Ajout dans la table utilisateur
			PreparedStatement pstForUser = Connexion.getInstance().prepareStatement(requeteUtilisateur, Statement.RETURN_GENERATED_KEYS);
			
			pstForUser.setString(1, adherent.getEmail());
			pstForUser.setString(2, adherent.getPassword());
			pstForUser.setString(3, adherent.getSalt());
			pstForUser.setObject(4, adherent.getRole());
			
			pstForUser.executeUpdate();

			ResultSet rs = pstForUser.getGeneratedKeys();
			if (rs.next()) {
				adherent.setIdProfil(rs.getInt(1));
			}
			
			donnees.put(adherent.getIdProfil(), adherent);
			
			
			//Ajout dans la table profil
			String requeteProfil = "INSERT INTO "+TABLE_PROFIL+" ("+ID_PROFIL+","+NOM+","+PRENOM+" , "+TEL+" , "+ADRESSE+
					" , "+DATE_INSCRIPTION+" , "+DATE_INSCRIPTION_FIN+" , "+NO_CNI+" , "+ACTIF+" , "+CAUTION+
					" , "+OBSERVATIONS+") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			
			PreparedStatement pstForProfil = Connexion.getInstance().prepareStatement(requeteProfil, Statement.RETURN_GENERATED_KEYS);
			
			pstForProfil.setInt(1, adherent.getIdProfil());
			pstForProfil.setString(2, adherent.getNom());
			pstForProfil.setString(3, adherent.getPrenom());
			pstForProfil.setString(4, adherent.getTel());
			pstForProfil.setString(5, adherent.getAdresse());
			
			//Calcul des dates d'inscription
			Date startDate = new Date();			
			Calendar cal = Calendar.getInstance();
			cal.setTime(startDate);
			cal.add(Calendar.YEAR, 1);
			Date endDate = cal.getTime();
			
			pstForProfil.setTimestamp(6, new Timestamp(startDate.getTime()));
			pstForProfil.setTimestamp(7, new Timestamp(endDate.getTime()));
			pstForProfil.setString(8, adherent.getNoCNI());
			pstForProfil.setBoolean(9, true);
			pstForProfil.setDouble(10, adherent.getCaution());
			
			String observations = (adherent.getObservations() != null) ? adherent.getObservations() : "";
			pstForProfil.setString(11, observations);

			pstForProfil.executeUpdate();
			donnees.put(adherent.getIdProfil(), adherent);

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
			int id = adherent.getIdProfil();
			
			String requeteForUser = "DELETE FROM "+TABLE_UTILISATEUR+" WHERE "+ID_UTILISATEUR+" = ?";
			PreparedStatement pstForUser = Connexion.getInstance().prepareStatement(requeteForUser);
			pstForUser.setInt(1, id);
			pstForUser.executeUpdate();
			
			String requeteForProfil = "DELETE FROM "+TABLE_PROFIL+" WHERE "+ID_PROFIL+" = ?";
			PreparedStatement pstForProfil = Connexion.getInstance().prepareStatement(requeteForProfil);
			pstForProfil.setInt(1, id);
			pstForProfil.executeUpdate();
			
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
		
		int id = adherent.getIdProfil();
		
		String email = adherent.getEmail();
		String mdp = adherent.getPassword();
		String salt = adherent.getSalt();
		String role = adherent.getRole();

		String nom =adherent.getNom();
		String prenom =adherent.getPrenom();
		String tel =adherent.getTel();
		String adresse =adherent.getAdresse();
		Timestamp dateInscription =adherent.getDateInscription();
		Timestamp dateFinInscription =adherent.getDateInscriptionFin();
		String noCNI = adherent.getNoCNI();
		Boolean actif =adherent.isActif();
		double caution =adherent.getCaution();
		String observations =adherent.getObservations();
		
		System.out.println("adherentupdate");
		System.out.println(adherent);

		try {
			String requeteUtilisateur = "UPDATE "+TABLE_UTILISATEUR+" SET "+EMAIL+" = ?, "+MOT_DE_PASSE+" = ?, "
					+SALT+" = ?, "+ROLE+" = ?"+" WHERE "+ID_UTILISATEUR+" = ?";
			PreparedStatement pstForUser = Connexion.getInstance().prepareStatement(requeteUtilisateur) ;
			pstForUser.setString(1,email) ; 
			pstForUser.setString(2,mdp) ;
			pstForUser.setString(3,salt) ;
			pstForUser.setString(4,role) ;
			pstForUser.setInt(5,id) ;
			pstForUser.executeUpdate() ;
			donnees.put(id, adherent);
			
			
			String requeteProfil = "UPDATE "+TABLE_PROFIL+" SET "+NOM+" = ?, "+PRENOM+" = ?, "
							+TEL+" = ?, "+ADRESSE+" = ?, "+DATE_INSCRIPTION+" = ?, "
							+DATE_INSCRIPTION_FIN+" = ?, "+NO_CNI+" = ?, "+ACTIF+" = ?, "+CAUTION+" = ?, "
							+OBSERVATIONS+" = ?"+" WHERE "+ID_PROFIL+" = ?";
			PreparedStatement pstForProfil = Connexion.getInstance().prepareStatement(requeteProfil) ;
			pstForProfil.setString(1,nom) ; 
			pstForProfil.setString(2,prenom) ;
			pstForProfil.setString(3,tel) ;
			pstForProfil.setString(4,adresse) ;
			pstForProfil.setTimestamp(5,dateInscription) ;
			pstForProfil.setTimestamp(6,dateFinInscription) ;
			pstForProfil.setString(7, noCNI);
			pstForProfil.setBoolean(8,actif) ;
			pstForProfil.setDouble(9, caution) ;
			pstForProfil.setString(10,observations) ;
			pstForProfil.setInt(11, id);
			pstForProfil.executeUpdate() ;
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

			String requete = "SELECT * FROM "+TABLE_UTILISATEUR+" JOIN "+TABLE_PROFIL+
					" ON "+TABLE_UTILISATEUR+"."+ID_UTILISATEUR+" = "+TABLE_PROFIL+"."+ID_PROFIL+
					" WHERE "+ID_UTILISATEUR+" = "+id;
			ResultSet rs = Connexion.executeQuery(requete);
			rs.next();
			String nom = rs.getString(NOM);
			String prenom = rs.getString(PRENOM);
			String tel = rs.getString(TEL);
			String adresse = rs.getString(ADRESSE);
			String email = rs.getString(EMAIL);
			Timestamp dateInscription = rs.getTimestamp(DATE_INSCRIPTION);
			Timestamp dateInscriptionFin = rs.getTimestamp(DATE_INSCRIPTION_FIN);
			String noCNI = rs.getString(NO_CNI);
			Boolean actif = rs.getBoolean(ACTIF);
			float caution = rs.getFloat(CAUTION);
			String observations = rs.getString(OBSERVATIONS);
			String password = rs.getString(MOT_DE_PASSE);
			String salt = rs.getString(SALT);
			String role = rs.getString(ROLE);
			adherent = new Adherent (id, nom, prenom, tel, adresse, email, dateInscription,
									dateInscriptionFin, noCNI, actif, caution, observations,
									password, salt, role);
			donnees.put(id, adherent);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return adherent;
	}

//	public void afficheSelectEtoileAdherent() {
//		System.out.println("--- Liste adhérent ---");
//		String clauseWhere = null;
//		Connexion.afficheSelectEtoile("Adherent", clauseWhere);
//
//	}
//
//	public void afficheAdherent(int id) {
//		System.out.println("--- Liste adhérent ---");
//		String clauseWhere = CLE_PRIMAIRE + " = " + id;
//		Connexion.afficheSelectEtoile("Adherent", clauseWhere);
//
//	}
	
	public List<Adherent> readAllAdherent() {
		List<Adherent> rep = new ArrayList<Adherent>();
		Adherent ad = null;
		try{
			String requete = "SELECT "+ID_PROFIL+" FROM "+TABLE_PROFIL;
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
            String requete = "SELECT "+MOT_DE_PASSE+" FROM "+TABLE_UTILISATEUR+" WHERE "+ID_UTILISATEUR+" = ?";
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
            String requete = "SELECT "+SALT+" FROM "+TABLE_UTILISATEUR+" WHERE "+ID_UTILISATEUR+" = ?";
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
            String requete = "SELECT "+EMAIL+" FROM "+TABLE_UTILISATEUR+" WHERE "+ID_UTILISATEUR+" = ?";
            PreparedStatement pst = Connexion.getInstance().prepareStatement(requete) ;
            pst.setInt(1, id);

            // Exécution de la requête
            ResultSet resultSet = pst.executeQuery();

            // Récupération du mot de passe s'il existe
            if (resultSet.next()) {
                email = resultSet.getString(EMAIL);
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
            String requete = "SELECT "+ID_UTILISATEUR+" FROM "+TABLE_UTILISATEUR+" WHERE "+EMAIL+" = ?";
            PreparedStatement pst = Connexion.getInstance().prepareStatement(requete) ;
            pst.setString(1, email);

            // Exécution de la requête
            ResultSet resultSet = pst.executeQuery();

            // Récupération du mot de passe s'il existe
            if (resultSet.next()) {
                id = resultSet.getInt(ID_UTILISATEUR);
            }
		}
		catch(SQLException e){
			System.out.println("Echec de la tentative d'interrogation Select * : " + e.getMessage()) ;
		}
		return id;
		
	}

}
