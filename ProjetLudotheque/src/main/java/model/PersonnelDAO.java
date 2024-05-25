package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PersonnelDAO extends DAO<Personnel> {
	//Table utilisateur
	private static final String TABLE_UTILISATEUR = "utilisateur";
	private static final String ID_UTILISATEUR = "id_utilisateur";
	private static final String EMAIL = "email";
	private static final String MOT_DE_PASSE = "password";
	private static final String SALT = "salt";
	private static final String ROLE = "role";

	//Table personnel
	private static final String TABLE_PERSONNEL = "personnel";
	private static final String ID_PERSONNEL = "id_personnel";
	private static final String NOM = "nom";
	private static final String PRENOM = "prenom";
	private static final String ADRESSE = "adresse";
	private static final String TEL = "tel";
	private static final String IS_ADMIN="isAdmin";

	private static PersonnelDAO instance=null;

	public static PersonnelDAO getInstance(){
		if (instance==null){
			instance = new PersonnelDAO();
		}
		return instance;
	}

	private PersonnelDAO() {
		super();
	}


	@Override
	public boolean create(Personnel personnel) {
		boolean succes=true;

		Connection connexion = Connexion.getInstance();
		try {

			connexion.setAutoCommit(false);

			String requeteUtilisateur = "INSERT INTO "+TABLE_UTILISATEUR+" ("+EMAIL+" , "+MOT_DE_PASSE+
					" , "+SALT+" , "+ROLE+") VALUES (?, ?, ?, ?)";

			//Ajout dans la table utilisateur
			PreparedStatement pstForUser = Connexion.getInstance().prepareStatement(requeteUtilisateur, Statement.RETURN_GENERATED_KEYS);

			pstForUser.setString(1, personnel.getEmail());
			pstForUser.setString(2, personnel.getPassword());
			pstForUser.setString(3, personnel.getSalt());
			pstForUser.setObject(4, personnel.getRole());

			pstForUser.executeUpdate();

			ResultSet rs = pstForUser.getGeneratedKeys();
			if (rs.next()) {
				personnel.setId_personnel(rs.getInt(1));
			}

			donnees.put(personnel.getId_personnel(), personnel);


			//Ajout dans la table personnel
			String requeteProfil = "INSERT INTO "+TABLE_PERSONNEL+" ("+ID_PERSONNEL+","+NOM+","+PRENOM+", "+TEL+", "+ADRESSE+
					" , "+IS_ADMIN+") VALUES (?, ?, ?, ?, ?,?)";

			PreparedStatement pstForPersonnel = connexion.prepareStatement(requeteProfil, Statement.RETURN_GENERATED_KEYS);

			pstForPersonnel.setInt(1, personnel.getId_personnel());
			pstForPersonnel.setString(2, personnel.getNom());
			pstForPersonnel.setString(3, personnel.getPrenom());
			pstForPersonnel.setString(4, personnel.getTel());
			pstForPersonnel.setString(5, personnel.getAdresse());
			pstForPersonnel.setBoolean(6, personnel.isAdmin());
			
			pstForPersonnel.executeUpdate();
			donnees.put(personnel.getId_personnel(), personnel);
			
			connexion.commit();


	} catch (SQLException e) {
		try {
			connexion.rollback();
			System.out.println("Create annulé");
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		succes=false;
		e.printStackTrace();
	}

	return succes;
}

	@Override
	public boolean delete(Personnel personnel) {
		boolean succes = true;
		Connection connexion = Connexion.getInstance();
		
		try {
			connexion.setAutoCommit(false);
			int id = personnel.getId_personnel();
			
			String requeteForUser = "DELETE FROM "+TABLE_PERSONNEL+" WHERE "+ID_PERSONNEL+" = ?";
			PreparedStatement pstForUser = connexion.prepareStatement(requeteForUser);
			pstForUser.setInt(1, id);
			pstForUser.executeUpdate();
			
			String requeteForPersonnel = "DELETE FROM "+TABLE_UTILISATEUR+" WHERE "+ID_UTILISATEUR+" = ?";
			PreparedStatement pstForPersonnel = connexion.prepareStatement(requeteForPersonnel);
			pstForPersonnel.setInt(1, id);
			pstForPersonnel.executeUpdate();
			
			donnees.remove(id);
			
			connexion.commit();
		} catch (SQLException e) {
			try {
				connexion.rollback();
				System.out.println("Delete annulé");
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			succes=false;
			e.printStackTrace();
		}
		return succes;
	}
	

	@Override
	public boolean update(Personnel personnel) {
		
		boolean succes=true;

int id = personnel.getId_personnel();
		
		String email = personnel.getEmail();
		String mdp = personnel.getPassword();
		String salt = personnel.getSalt();
		String role = personnel.getRole();

		String nom =personnel.getNom();
		String prenom =personnel.getPrenom();
		String tel =personnel.getTel();
		String adresse =personnel.getAdresse();
		Boolean isAdmin =personnel.isAdmin();
		
		Connection connexion = Connexion.getInstance();
		
		try {
			
			connexion.setAutoCommit(false);
			
			String requeteUtilisateur = "UPDATE "+TABLE_UTILISATEUR+" SET "+EMAIL+" = ?, "+MOT_DE_PASSE+" = ?, "
					+SALT+" = ?, "+ROLE+" = ?"+" WHERE "+ID_UTILISATEUR+" = ?";
			PreparedStatement pstForUser = connexion.prepareStatement(requeteUtilisateur) ;
			pstForUser.setString(1,email) ; 
			pstForUser.setString(2,mdp) ;
			pstForUser.setString(3,salt) ;
			pstForUser.setString(4,role) ;
			pstForUser.setInt(5,id) ;
			pstForUser.executeUpdate() ;
//			donnees.put(id, personnel);
			
			
			String requetePersonnel = "UPDATE "+TABLE_PERSONNEL+" SET "+NOM+" = ?, "+PRENOM+" = ?, "
							+TEL+" = ?, "+ADRESSE+" = ?, "+IS_ADMIN+" = ?"+" WHERE "+ID_PERSONNEL+" = ?";
			PreparedStatement pstForPersonnel = connexion.prepareStatement(requetePersonnel) ;
			pstForPersonnel.setString(1,nom) ; 
			pstForPersonnel.setString(2,prenom) ;
			pstForPersonnel.setString(3,tel) ;
			pstForPersonnel.setString(4,adresse) ;
			pstForPersonnel.setBoolean(5,isAdmin) ;
			pstForPersonnel.setInt(6, id);
			pstForPersonnel.executeUpdate() ;
//			donnees.put(id, adherent);
			
			connexion.commit();
		} catch (SQLException e) {
			try {
				connexion.rollback();
				System.out.println("Update annulé");
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			succes = false;
			e.printStackTrace();
		} 
		return succes;	
	}
	
	public Personnel read(int id) {
		Personnel personnel = null;
			
//			System.out.println("recherché dans la BD");
			try {

				String requete = "SELECT * FROM "+TABLE_UTILISATEUR+" JOIN "+TABLE_PERSONNEL+
						" ON "+TABLE_UTILISATEUR+"."+ID_UTILISATEUR+" = "+TABLE_PERSONNEL+"."+ID_PERSONNEL+
						" WHERE "+ID_UTILISATEUR+" = "+id;
				ResultSet rs = Connexion.executeQuery(requete);
				rs.next();
				String nom = rs.getString(NOM);
				String prenom = rs.getString(PRENOM);
				String email = rs.getString(EMAIL);
				String tel = rs.getString(TEL);
				String adresse = rs.getString(ADRESSE);
				Boolean admin = rs.getBoolean(IS_ADMIN);
				String password = rs.getString(MOT_DE_PASSE);
				String salt = rs.getString(SALT);
				String role = rs.getString(ROLE);
				personnel = new Personnel(id, nom, prenom, email, tel, adresse,   
										admin,password, salt, role);
//				donnees.put(id, personnel);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			return personnel;
		}

	
	
	public List<Personnel> readAllPersonnel() {
		List<Personnel> rep = new ArrayList<Personnel>();
		Personnel ad = null;
		try{
			String requete = "SELECT "+ID_PERSONNEL+" FROM "+TABLE_PERSONNEL;
			ResultSet res = Connexion.executeQuery(requete) ;
			while(res.next()){
				int id = res.getInt(1);
				ad = PersonnelDAO.getInstance().read(id);
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