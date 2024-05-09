package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PersonnelDAO extends DAO<Personnel> {
	
	private static final String TABLE = "personnel";
	private static final String CLE_PRIMAIRE = "id_personnel";

	private static final String NOM_PERSONNEL = "nom";
	private static final String PRENOM_PERSONNEL = "prenom";
	private static final String ADRESSE = "adresse";
	private static final String TEL = "tel";
	private static final String MAIL = "role";
	
	
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

			String requete = "INSERT INTO "+TABLE+" ("+NOM_PERSONNEL+","+PRENOM_PERSONNEL+" , "+ADRESSE+
							" , "+TEL+" , "+MAIL+") VALUES (?, ?, ?, ?, ?)";
			PreparedStatement pst = Connexion.getInstance().prepareStatement(requete, Statement.RETURN_GENERATED_KEYS);
			
			pst.setString(1, personnel.getNom());
			pst.setString(2, personnel.getPrenom());
			pst.setString(3, personnel.getAdresse());
			pst.setString(4, personnel.getTel());
			pst.setString(5, personnel.getEmail());
			
			pst.executeUpdate();

			
			ResultSet rs = pst.getGeneratedKeys();
			if (rs.next()) {
				personnel.setid_Personnel(rs.getInt(1));
			}
			donnees.put(personnel.getIdPersonnel(), personnel);

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
			int id = personnel.getIdPersonnel();
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
		String mail =personnel.getEmail();
		int id = personnel.getIdPersonnel();

		try {
			String requete = "UPDATE "+TABLE+" SET "+NOM_PERSONNEL+" = ?, "+PRENOM_PERSONNEL+" = ?, "
					+ADRESSE+" = ?, "+TEL+" = ?, "+MAIL+" = ?= ?"+"WHERE "+CLE_PRIMAIRE+" = ?";
			PreparedStatement pst = Connexion.getInstance().prepareStatement(requete) ;
			pst.setString(1,nom) ; 
			pst.setString(2,prenom) ;
			pst.setString(3,adresse) ;
			pst.setString(4,tel) ;
			pst.setString(5,mail) ;
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
	            String mail = rs.getString(MAIL);
	            // Create a Personnel object
	            personnel = new Personnel(nom, prenom, adresse, tel, mail);
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

}
