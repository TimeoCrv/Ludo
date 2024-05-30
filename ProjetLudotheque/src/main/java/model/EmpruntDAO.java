package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class EmpruntDAO extends DAO<Emprunt>{
	
	private static final String TABLE_EMPRUNT = "emprunt";
	private static final String ID_EMPRUNT = "id_emprunt";
	private static final String ID_PROFIL = "id_profil";
	private static final String ID_JEU_PHYSIQUE = "id_jeu_physique";
	private static final String DATE_EMPRUNT = "date_emprunte";
	private static final String DATE_A_RENDRE = "date_a_rendre";
	
	private static EmpruntDAO instance = null;

	public static EmpruntDAO getInstance() {
		if (instance == null) {
			instance = new EmpruntDAO();
		}
		return instance;
	}
	
	private EmpruntDAO() {
		super();
	}

	@Override
	public boolean create(Emprunt emprunt) {
		boolean success = true;
		Connection connexion = Connexion.getInstance();

		try {
			String requeteProfil = "INSERT INTO " + TABLE_EMPRUNT + " (" + ID_PROFIL + ", " + ID_JEU_PHYSIQUE + ", " + DATE_EMPRUNT + ", " + DATE_A_RENDRE + ") VALUES (?, ?, ?, ?)";
			
			PreparedStatement pst = connexion.prepareStatement(requeteProfil, Statement.RETURN_GENERATED_KEYS);
			pst.setInt(1, emprunt.getIdProfil());
			pst.setInt(2, emprunt.getIdJeuPhysique());
			
			// Calculate dates
			Date startDate = new Date();
			Calendar cal = Calendar.getInstance();
			cal.setTime(startDate);
			cal.add(Calendar.MONTH, 1);
			Date endDate = cal.getTime();
			
			pst.setTimestamp(3, new Timestamp(startDate.getTime()));
			pst.setTimestamp(4, new Timestamp(endDate.getTime()));
			
			pst.executeUpdate();

			ResultSet rs = pst.getGeneratedKeys();
			if (rs.next()) {
				emprunt.setIdEmprunt(rs.getInt(1));
			}

		} catch (SQLException e) {
			success = false;
			e.printStackTrace();
		}

		return success;
	}

	@Override
	public boolean delete(Emprunt emprunt) {
		boolean succes = true;
		try {
			int id = emprunt.getIdEmprunt();
			String requete = "DELETE FROM "+TABLE_EMPRUNT+" WHERE "+ID_EMPRUNT+" = ?";
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
	public boolean update(Emprunt emprunt) {
		boolean succes = true;

	    int idEmprunt = emprunt.getIdEmprunt();
	    int idAdherent = emprunt.getIdProfil();
	    int idJeu = emprunt.getIdJeuPhysique();
	    Timestamp dateEmprunt = emprunt.getDateEmprunt();
	    Timestamp dateARendre = emprunt.getDateARendre();
	    
	    Connection connexion = Connexion.getInstance();
	    
	    try {
	        connexion.setAutoCommit(false);
	        
	        String requete = "UPDATE " + TABLE_EMPRUNT + " SET " 
	            + ID_PROFIL + " = ?, " 
	            + ID_JEU_PHYSIQUE + " = ?, " 
	            + DATE_EMPRUNT + " = ?, " 
	            + DATE_A_RENDRE + " = ?, " 
	            + "WHERE " + ID_EMPRUNT + " = ?";

	        PreparedStatement pst = connexion.prepareStatement(requete);
	        
	        pst.setInt(1, idAdherent);
	        pst.setInt(2, idJeu);
	        pst.setTimestamp(3, dateEmprunt);
	        pst.setTimestamp(4, dateARendre);
	        pst.setInt(5, idEmprunt);

	        pst.executeUpdate();
	        
	        connexion.commit();
	    } catch (SQLException e) {
	        try {
	            connexion.rollback();
	            System.out.println("Update annulé");
	        } catch (SQLException e1) {
	            e1.printStackTrace();
	        }
	        succes = false;
	        e.printStackTrace();
	    }
	    return succes;
	}

	@Override
	public Emprunt read(int id) {
		Emprunt emprunt = null;
		
//		System.out.println("recherché dans la BD");
		try {

			String requete = "SELECT * FROM "+TABLE_EMPRUNT+" WHERE "+ID_EMPRUNT+" = "+id;
			ResultSet rs = Connexion.executeQuery(requete);
			rs.next();
			int idAdherent = rs.getInt(ID_PROFIL);
			int idJeu = rs.getInt(ID_JEU_PHYSIQUE);
			Timestamp dateEmprunt = rs.getTimestamp(DATE_EMPRUNT);
			Timestamp dateARendre = rs.getTimestamp(DATE_A_RENDRE);
			emprunt = new Emprunt (id, idAdherent, idJeu, dateEmprunt, dateARendre);
			donnees.put(id, emprunt);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return emprunt;
	}
	
	public Emprunt readFullDetails(int id) {
		Emprunt emprunt = null;
		
//		System.out.println("recherché dans la BD");
		try {

			String requete = "SELECT * FROM "+TABLE_EMPRUNT+" WHERE "+ID_EMPRUNT+" = "+id;
			ResultSet rs = Connexion.executeQuery(requete);
			rs.next();
			int idAdherent = rs.getInt(ID_PROFIL);
			int idJeu = rs.getInt(ID_JEU_PHYSIQUE);
			Adherent adherent = AdherentDAO.getInstance().read(idAdherent);
			Jeu jeu = JeuDAO.getInstance().read(idJeu);
			Timestamp dateEmprunt = rs.getTimestamp(DATE_EMPRUNT);
			Timestamp dateARendre = rs.getTimestamp(DATE_A_RENDRE);
			emprunt = new Emprunt (id, idAdherent, idJeu, adherent, jeu, dateEmprunt, dateARendre);
			donnees.put(id, emprunt);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return emprunt;
	}
	
	public List<Emprunt> readTable() {
		List<Emprunt> rep = new ArrayList<Emprunt>();
		Emprunt emprunt = null;
		try{
			String requete = "SELECT "+ID_EMPRUNT+" FROM "+TABLE_EMPRUNT;
			ResultSet res = Connexion.executeQuery(requete) ;
			while(res.next()){
				int id = res.getInt(1);
				emprunt = EmpruntDAO.getInstance().read(id);
				rep.add(emprunt);
			}
//			for(Jeu jeu : rep) {
//				System.out.println(jeu.getNom());
//			}
		}
		catch(SQLException e){
			System.out.println("Echec de la tentative d'interrogation Select * : " + e.getMessage()) ;
		}
		return rep;
	}
	
	public List<Emprunt> readFullHisto() {
		List<Emprunt> rep = new ArrayList<Emprunt>();
		Emprunt emprunt = null;
		try{
			String requete = "SELECT "+ID_EMPRUNT+" FROM "+TABLE_EMPRUNT;
			ResultSet res = Connexion.executeQuery(requete) ;
			while(res.next()){
				int id = res.getInt(1);
				emprunt = EmpruntDAO.getInstance().readFullDetails(id);
				rep.add(emprunt);
			}
//			for(Jeu jeu : rep) {
//				System.out.println(jeu.getNom());
//			}
		}
		catch(SQLException e){
			System.out.println("Echec de la tentative d'interrogation Select * : " + e.getMessage()) ;
		}
		return rep;
	}
	
	public List<Emprunt> readFullHistoAdherent(int idAdherent) {
		List<Emprunt> rep = new ArrayList<Emprunt>();
		Emprunt emprunt = null;
		try{
			String requete = "SELECT "+ID_EMPRUNT+" FROM "+TABLE_EMPRUNT+" WHERE "+ID_PROFIL+"="+idAdherent;
			ResultSet res = Connexion.executeQuery(requete) ;
			while(res.next()){
				int id = res.getInt(1);
				emprunt = EmpruntDAO.getInstance().readFullDetails(id);
				rep.add(emprunt);
			}
//			for(Jeu jeu : rep) {
//				System.out.println(jeu.getNom());
//			}
		}
		catch(SQLException e){
			System.out.println("Echec de la tentative d'interrogation Select * : " + e.getMessage()) ;
		}
		return rep;
	}
}
