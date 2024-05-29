package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

public  class EmpruntDAO extends DAO<Emprunt> {

	//table emprunt 
	private static final String TABLE="emprunt";
	private static final String idProfil = "id_profil";
	private static final String idJeuPhysique = "id_jeu_physique";
	private static final String dateEmprunte = "date_emprunte";
	private static final String dateRendre = "date_a_rendre";

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
		boolean success = true;
		Connection connexion = Connexion.getInstance();

		try {
			connexion.setAutoCommit(false);
			String requete = "INSERT INTO " + TABLE + " (" + idJeuPhysique + "," + idProfil + "" + dateEmprunte + ", " + dateRendre + ") VALUES (?, ?,?,?)";
			PreparedStatement pst = Connexion.getInstance().prepareStatement(requete, Statement.RETURN_GENERATED_KEYS);
			pst.setInt(1, emprunt.getIdJeuPhysique());
			pst.setInt(2, emprunt.getIdProfil());

			Date startDate = new Date();			
			Calendar cal = Calendar.getInstance();
			cal.setTime(startDate);
			cal.add(Calendar.MONTH, 1);
			Date endDate = cal.getTime();
			pst.setTimestamp(3, new Timestamp(startDate.getTime()));
			pst.setTimestamp(4, new Timestamp(endDate.getTime()));

			pst.executeUpdate();

			/*ResultSet rs = pst.getGeneratedKeys();
			if (rs.next()) {
				emprunt.setIdJeu(rs.getInt(1));
			}

			donnees.put(jeu.getIdJeu(), jeu);*/


		}catch(SQLException e){
		}return success;
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
