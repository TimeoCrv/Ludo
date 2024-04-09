package model;

<<<<<<< HEAD
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PersonnelDAO extends DAO<Personnel> {
	
	private static final String TABLE_PERSONNEL = "personnel";

	private static final String NOM_PERSONNEL = "nom";
	private static final String PRENOM_PERSONNEL = "prenom";
	private static final String ADRESSE = "adresse";
	private static final String TEL = "tel";
	//private static final Boolean IS_ADMIN=false;
	
	
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
			
			/*pstForProfil.setInt(1, adherent.getIdProfil());
			pstForProfil.setString(2, adherent.getNom());
			pstForProfil.setString(3, adherent.getPrenom());
			pstForProfil.setString(4, adherent.getTel());
			pstForProfil.setString(5, adherent.getAdresse());
			
			String requete = "INSERT INTO "+TABLE+" ("+NOM_PERSONNEL+","+PRENOM_PERSONNEL+","+ADRESSE+
							" , "+TEL+") VALUES (?, ?, ?, ?)";
			PreparedStatement pst = Connexion.getInstance().prepareStatement(requete, Statement.RETURN_GENERATED_KEYS);
			
			pst.setString(1, personnel.getNom());
			pst.setString(2, personnel.getPrenom());
			pst.setString(3, personnel.getAdresse());
			pst.setString(4, personnel.getTel());
			//pst.setBoolean(5, personnel.getIsAdmin());*/
			
			
			//pst.executeUpdate();

			
			ResultSet rs = pst.getGeneratedKeys();
			if (rs.next()) {
				personnel.setIdutilisateur(rs.getInt(1));
			}
			donnees.put(personnel.getIdutilisateur(), personnel);

		} catch (SQLException e) {
			succes=false;
			e.printStackTrace();
		}

		return succes;
	}

	@Override
	public boolean delete(Personnel personnel) {
		boolean succes = true;
		try {
			int id = personnel.getId();
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
	public boolean update(Personnel personnel) {
		boolean succes=true;

		String nom =personnel.getNom();
		String prenom =personnel.getPrenom();
		String adresse =personnel.getAdresse();
		String tel =personnel.getTel();
		int id = personnel.getIdPersonnel();
		int isAdmin = personnel.getIsAdmin();

		try {
			String requete = "UPDATE "+TABLE+" SET "+NOM_PERSONNEL+" = ?, "+PRENOM_PERSONNEL+" = ?, "
					+ADRESSE+" = ?, "+TEL+" = ?,"+IS_ADMIN+"= ?"+"WHERE "+CLE_PRIMAIRE+" = ?";
			PreparedStatement pst = Connexion.getInstance().prepareStatement(requete) ;
			pst.setString(1,nom) ; 
			pst.setString(2,prenom) ;
			pst.setString(3,adresse) ;
			pst.setString(4,tel) ;
			pst.executeUpdate() ;
			donnees.put(id, personnel);
		} catch (SQLException e) {
			succes = false;
			e.printStackTrace();
		} 
		return succes;	
	}

	public Personnel read(int id) {
	    Personnel personnel = null;
	    
	    System.out.println("recherché dans la BD");
	    try {
	        String requete = "SELECT * FROM " + TABLE + " WHERE " + CLE_PRIMAIRE + " = ?";
	        PreparedStatement statement = Connexion.getInstance().prepareStatement(requete);
	        statement.setInt(1, id);
	        ResultSet rs = statement.executeQuery();
	        
	        // Check if a row is returned
	        if (rs.next()) {
	            // Retrieve data from the ResultSet
	            String nom = rs.getString(NOM_PERSONNEL);
	            String prenom = rs.getString(PRENOM_PERSONNEL);
	            String adresse = rs.getString(ADRESSE);
	            String tel = rs.getString(TEL);
	            // Create a Personnel object
	            personnel = new Personnel(nom, prenom, adresse, tel);
	            donnees.put(id, personnel);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    
	    return personnel;
	}

	public void afficheSelectPersonnel() {
		System.out.println("--- Liste Personnel ---");
		String clauseWhere = null;
		Connexion.afficheSelectEtoile("Personnel", clauseWhere);

	}

	public void affichePersonnel(int id) {
		System.out.println("--- Liste Personnel ---");
		String clauseWhere = CLE_PRIMAIRE + " = " + id;
		Connexion.afficheSelectEtoile("Personnel", clauseWhere);

	}
	
	public List<Personnel> readTable() {
		List<Personnel> rep = new ArrayList<Personnel>();
		Personnel ad = null;
		try{
			String requete = "SELECT "+CLE_PRIMAIRE+" FROM "+TABLE;
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
=======
public class PersonnelDAO {
>>>>>>> 51bfa59 (mise a jour)

}
