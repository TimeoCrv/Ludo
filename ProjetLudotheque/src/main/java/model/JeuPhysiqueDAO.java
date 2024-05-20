package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class JeuPhysiqueDAO extends DAO<JeuPhysique>{
	private static final String TABLE = "jeuPhysique";
	private static final String CLE_PRIMAIRE = "id_jeu_physique";
	
	private static final String ETAT = "etat";
	
	private static JeuPhysiqueDAO instance=null;
	
	public static JeuPhysiqueDAO getInstance() {
		if(instance==null) {
			instance = new JeuPhysiqueDAO();
		}
		return instance;
	}
	
	private JeuPhysiqueDAO() {
		super();
	}
	
	public boolean create(JeuPhysique jeuPhysique) {
		boolean succes=true;
		try {
			
			String requete = "INSERT INTO "+TABLE+" ("+ETAT+") VALUES (?)";
			PreparedStatement pst = Connexion.getInstance().prepareStatement(requete, Statement.RETURN_GENERATED_KEYS);
			pst.setString(1, jeuPhysique.getEtat());		
			pst.executeUpdate();
			
			ResultSet rs = pst.getGeneratedKeys();
			if (rs.next()) {
				jeuPhysique.setIdJeuPhysique(rs.getInt(1));
			}
			donnees.put(jeuPhysique.getIdJeuPhysique(), jeuPhysique);

		} catch (SQLException e) {
			succes=false;
			e.printStackTrace();
		}

		return succes;
	}

	@Override
	public boolean delete(JeuPhysique jeuPhysique) {
		boolean succes = true;
		try {
			int id = jeuPhysique.getIdJeuPhysique();
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
	public boolean update(JeuPhysique jeuPhysique) {
		boolean succes=true;
		
		String etat = jeuPhysique.getEtat();
		
		try {
			String requete = "UPDATE "+TABLE+" SET "+ETAT+" = ?" + " WHERE "+CLE_PRIMAIRE+" = ?";
			PreparedStatement pst = Connexion.getInstance().prepareStatement(requete) ;
			pst.setString(1,etat);
		} catch (SQLException e) {
			succes = false;
			e.printStackTrace();
		} 
		return succes;	
	}

	@Override
	public JeuPhysique read(int id_jeu_physique) {
		JeuPhysique jeuPhysique = null;
		
		System.out.println("recherché dans la BD");
		try {
			String requete = "SELECT * FROM "+TABLE+" WHERE "+CLE_PRIMAIRE+" = "+id_jeu_physique;
			ResultSet rs = Connexion.executeQuery(requete);
			rs.next();
			String etat = rs.getString(ETAT);
			jeuPhysique = new JeuPhysique (id_jeu_physique, etat);
			donnees.put(id_jeu_physique, jeuPhysique);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return jeuPhysique;
	}
	
	public List<JeuPhysique> readTable() {
		List<JeuPhysique> rep = new ArrayList<JeuPhysique>();
		JeuPhysique ad = null;
		try{
			String requete = "SELECT "+CLE_PRIMAIRE+" FROM "+TABLE;
			ResultSet res = Connexion.executeQuery(requete) ;
			while(res.next()){
				int id = res.getInt(1);
				ad = JeuPhysiqueDAO.getInstance().read(id);
				rep.add(ad);
			}
		}
		catch(SQLException e){
			System.out.println("Echec de la tentative d'interrogation Select * : " + e.getMessage()) ;
		}
		return rep;
	}

}


