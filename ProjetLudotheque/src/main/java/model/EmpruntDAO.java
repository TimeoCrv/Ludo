package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

public class EmpruntDAO {
	
	private static final String TABLE_EMPRUNT = "emprunt";
	private static final String ID_EMPRUNT = "id_emprunt";
	private static final String ID_PROFIL = "id_profil";
	private static final String ID_JEU_PHYSIQUE = "id_jeu_physique";
	private static final String DATE_EMPRUNT = "date_emprunte";
	private static final String DATE_A_RENDRE = "date_a_rendre";
	
	private static EmpruntDAO instance = null;

	private EmpruntDAO() {
		// Private constructor to prevent instantiation
	}

	public static EmpruntDAO getInstance() {
		if (instance == null) {
			instance = new EmpruntDAO();
		}
		return instance;
	}

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
}
