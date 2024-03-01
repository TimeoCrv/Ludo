package modele.dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import ludo.Adherent;

public class AdherentDAO extends DAO<Adherent> {

	private static final String TABLE = "Adherent";
	private static final String CLE_PRIMAIRE = "id_adherent";

	private static final String NOM_ADHERENT = "nom_adherent";
	private static final String PRENOM_ADHERENT = "prenom_adherent";
	private static final String ADRESSE = "adresse";
	private static final String CP = "cp";
	private static final String VILLE = "ville";
	private static final String TEL = "tel";
	private static final String MAIL = "mail";
	private static final String DATE_ADHESION = "date_adhesion";
	private static final String DATE_FIN_ADHESION = "date_fin_adhesion";
	private static final String CAUTION = "caution";

	
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
							" , "+CP+" , "+VILLE+" , "+TEL+" , "+MAIL+" , "+DATE_ADHESION+" , "+DATE_FIN_ADHESION+
							" , "+CAUTION+") VALUES (?, ?, ?,?, ?, ?,?, ?, ?, ?)";
			PreparedStatement pst = Connexion.getInstance().prepareStatement(requete, Statement.RETURN_GENERATED_KEYS);
			// on pose un String en param�tre 1 -1er '?'- et ce String est le nom de l'avion
			pst.setString(1, adherent.getNom());
			pst.setString(2, adherent.getPrenom());
			pst.setString(3, adherent.getAdresse());
			pst.setInt(4, adherent.getCp());
			pst.setString(5, adherent.getVille());
			pst.setString(6, adherent.getTel());
			pst.setString(7, adherent.getMail());
			pst.setObject(8, adherent.getDateAdhesion());
			pst.setObject(9, adherent.getDateFinAdhesion());
			pst.setFloat(10, adherent.getCaution());
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
		int cp = adherent.getCp();
		String ville =adherent.getVille();
		String tel =adherent.getTel();
		String mail =adherent.getMail();
		Date dateAdhesion =adherent.getDateAdhesion();
		Date dateFinAdhesion =adherent.getDateFinAdhesion();
		float caution =adherent.getCaution();
		int id = adherent.getNumero();

		try {
			String requete = "UPDATE "+TABLE+" SET "+NOM_ADHERENT+" = ?, "+PRENOM_ADHERENT+" = ?, "
							+ADRESSE+" = ?, "+CP+" = ?, "+VILLE+" = ?, "+TEL+" = ?, "+MAIL+" = ?, "
							+DATE_ADHESION+" = ?, "+DATE_FIN_ADHESION+" = ?, "+CAUTION+" = ?"
									+ "WHERE "+CLE_PRIMAIRE+" = ?";
			PreparedStatement pst = Connexion.getInstance().prepareStatement(requete) ;
			pst.setString(1,nom) ; 
			pst.setString(2,prenom) ;
			pst.setString(3,adresse) ;
			pst.setInt(4, cp) ;
			pst.setString(5,ville) ;
			pst.setString(6,tel) ;
			pst.setString(7,mail) ;
			pst.setObject(8,dateAdhesion) ;
			pst.setObject(9,dateFinAdhesion) ;
			pst.setFloat(10, caution) ;
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
		if (donnees.containsKey(id)) {
			System.out.println("récupéré");
			adherent=donnees.get(id);
		}
		else {
			System.out.println("recherché dans la BD");
			try {

				String requete = "SELECT * FROM "+TABLE+" WHERE "+CLE_PRIMAIRE+" = "+id;
				ResultSet rs = Connexion.executeQuery(requete);
				rs.next();
				String nom = rs.getString(NOM_ADHERENT);
				String prenom = rs.getString(PRENOM_ADHERENT);
				String adresse = rs.getString(ADRESSE);
				int cp = rs.getInt(CP);
				String ville = rs.getString(VILLE);
				String tel = rs.getString(TEL);
				String mail = rs.getString(MAIL);
				Date dateAdhesion = (Date) rs.getObject(DATE_ADHESION);
				Date dateFinAdhesion = (Date) rs.getObject(DATE_FIN_ADHESION);
				float caution = rs.getFloat(CAUTION);
				adherent = new Adherent (id, nom, prenom, adresse, cp, ville, tel, mail, dateAdhesion,
										dateFinAdhesion, caution);
				donnees.put(id, adherent);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return adherent;
	}

	public void afficheSelectEtoileAdherent() {
		System.out.println("--- Liste adhérent ---");
		String clauseWhere = null;
		Connexion.afficheSelectEtoile("Adherent", clauseWhere);

//		System.out.println("--- Avion contraint par Vol --- ");
//		clauseWhere = CLE_PRIMAIRE+" IN (SELECT "+CLE_PRIMAIRE+" From Vol)";
//		Connexion.afficheSelectEtoile("Avion", clauseWhere);

	}



}
