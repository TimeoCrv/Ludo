package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Calendar;

public class EmpruntDAO extends DAO<Emprunt> {
	
	//TABLE Emprunt
	private static final String TABLE = "emprunt";
	private static final String ID_JEU_PHYSIQUE = "id_jeu_physique";
	private static final String ID_PROFIL = "id_profil";
	private static final String DATE_EMPRUNTE ="date_emprunte";
	private static final String DATE_RENDRE ="date_a_rendre";
	
	private static EmpruntDAO instance=null;

	public static EmpruntDAO getInstance(){
		if (instance==null){
			instance = new EmpruntDAO();
		}
		return instance;
	}
	
	private EmpruntDAO() {
		super();
	}

	@Override
	public boolean create(Emprunt emprunt) {
		boolean succes=true;
		
		Connection connexion = Connexion.getInstance();
		//PreparedStatement pst = null;
		try {
		
			//connexion.setAutoCommit(false);
			String requete = "INSERT INTO "+TABLE+" ("+ID_JEU_PHYSIQUE+" , "+ID_PROFIL+
					" , "+DATE_EMPRUNTE+" , "+DATE_RENDRE+") VALUES (?, ?, ?, ?)";
			
			PreparedStatement pst = connexion.prepareStatement(requete, Statement.RETURN_GENERATED_KEYS);
			
			pst.setInt(1, emprunt.getIdJeuPhysique());
			pst.setInt(2, emprunt.getIdProfil());
			
			java.util.Date startDate = new java.util.Date();            
	        Calendar cal = Calendar.getInstance();
	        cal.setTime(startDate);
	        cal.add(Calendar.MONTH, 1);
	        java.util.Date endDate = cal.getTime();

	        // Using java.sql.Date to ensure date format YYYY-MM-DD
	        pst.setDate(3, new java.sql.Date(startDate.getTime()));
	        pst.setDate(4, new java.sql.Date(endDate.getTime()));

			
			int rowsAffected = pst.executeUpdate();

	        if (rowsAffected > 0) {
	            succes = true;
	        }
			
		}catch(Exception e) {
			e.printStackTrace();
			
		}
		
		return succes;
	}

	@Override
	public boolean delete(Emprunt obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Emprunt obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Emprunt read(int id) {
		// TODO Auto-generated method stub
		return null;
	}
	

}
