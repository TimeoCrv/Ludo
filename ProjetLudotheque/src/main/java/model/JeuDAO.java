package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

	public class JeuDAO extends DAO<Jeu> {
		
		private static final String TABLE = "Jeu";
		private static final String CLE_PRIMAIRE = "id_jeu";

		private static final String NOM_JEU = "nom_jeu";
		private static final String NOMBRE_JOUEURS_MAX = "nombre_joueurs_max";
		private static final String NOMBRE_JOUEURS_MIN = "nombre_joueurs_min";
		private static final String ANNEE= "annee";
		private static final String AGE_MIN = "age_min";
		private static final String AGE_MAX = "age_max";
		private static final String DUREE_MIN = "duree_min";
		private static final String DUREE_MAX = "duree_max";
		private static final String DESCRIPTIF = "descriptif";
		private static final String EDITEUR = "editeur";
		private static final String DISPONIBLE = "disponible";
		private static final String ETAT = "etat";
		
		
		private static JeuDAO instance=null;

		public static JeuDAO getInstance(){
			if (instance==null){
				instance = new JeuDAO();
			}
			return instance;
		}


		private JeuDAO() {
			super();
		}
		
		@Override
		public boolean create(Jeu jeu) {
			boolean succes=true;
			try {

				String requete = "INSERT INTO "+TABLE+" ("+NOM_JEU+","+NOMBRE_JOUEURS_MAX+" , "+NOMBRE_JOUEURS_MIN+
								" , "+ANNEE+" , "+AGE_MIN+" , "+AGE_MAX+" , "+DUREE_MIN+" , "+DUREE_MAX+" , "+DESCRIPTIF+
								" , "+EDITEUR+" , "+DISPONIBLE+" , "+ETAT+") VALUES (?, ?, ?,?, ?, ?,?, ?, ?, ?)";
				PreparedStatement pst = Connexion.getInstance().prepareStatement(requete, Statement.RETURN_GENERATED_KEYS);
				// on pose un String en param�tre 1 -1er '?'- et ce String est le nom de l'avion
				pst.setString(1, jeu.getNom());
				pst.setInt(2, jeu.getNombreJoueursMax());
				pst.setInt(3, jeu.getNombreJoueursMin());
				pst.setInt(4, jeu.getAnnee());
				pst.setInt(5, jeu.getAgeMin());
				pst.setInt(6, jeu.getAgeMax());
				pst.setInt(7, jeu.getDureeMin());
				pst.setInt(8, jeu.getDureeMax());
				pst.setString(9, jeu.getDescriptif());
				pst.setString(10, jeu.getEditeur());
				pst.setInt(11, jeu.getDisponible());
				pst.setString(12, jeu.getEtat());
				// on ex�cute la mise � jour
				pst.executeUpdate();

				//R�cup�rer la cl� qui a �t� g�n�r�e et la pousser dans l'objet initial
				ResultSet rs = pst.getGeneratedKeys();
				if (rs.next()) {
					jeu.setIdJeu(rs.getInt(1));
				}
				donnees.put(jeu.getIdJeu(), jeu);

			} catch (SQLException e) {
				succes=false;
				e.printStackTrace();
			}

			return succes;
		}
		
		@Override
		public boolean delete(Jeu jeu) {
			boolean succes = true;
			try {
				int id = jeu.getIdJeu();
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
		public boolean update(Jeu jeu) {
			boolean succes=true;

			String nom =jeu.getNom();
			int nombreJoueursMax =jeu.getNombreJoueursMax();
			int nombreJoueursMin =jeu.getNombreJoueursMin();
			int annee =jeu.getAnnee();
			int ageMin =jeu.getAgeMin();
			int ageMax =jeu.getAgeMax();
			int dureeMin =jeu.getDureeMin();
			int dureeMax =jeu.getDureeMax();
			String descriptif =jeu.getDescriptif();
			String editeur =jeu.getEditeur();
			int disponible =jeu.getDisponible();
			String etat =jeu.getEtat();

			try {
				String requete = "UPDATE "+TABLE+" SET "+NOM_JEU+" = ?, "+NOMBRE_JOUEURS_MAX+" = ?, "
								+NOMBRE_JOUEURS_MIN+" = ?, "+ANNEE+" = ?, "+AGE_MIN+" = ?, "+AGE_MAX+" = ?, "+DUREE_MIN+" = ?, "
								+DUREE_MAX+" = ?, "+DESCRIPTIF+" = ?, "+EDITEUR+" = ?, "+DISPONIBLE+" = ?, "+ETAT+" = ?"
										+ "WHERE "+CLE_PRIMAIRE+" = ?";
				PreparedStatement pst = Connexion.getInstance().prepareStatement(requete) ;
				pst.setString(1,nom) ; 
				pst.setInt(2,nombreJoueursMax) ;
				pst.setInt(3,nombreJoueursMin) ;
				pst.setInt(4, annee) ;
				pst.setInt(5,ageMin) ;
				pst.setInt(6,ageMax) ;
				pst.setInt(7,dureeMin) ;
				pst.setInt(8,dureeMax) ;
				pst.setString(9,descriptif) ;
				pst.setString(10, editeur) ;
				pst.setInt(11, disponible) ;
				pst.setString(12, etat) ;
				pst.executeUpdate() ;
				// TODO donnees.put(id, jeu);
			} catch (SQLException e) {
				succes = false;
				e.printStackTrace();
			} 
			return succes;	
		}

		@Override
		public Jeu read(int idJeu) {
			Jeu jeu = null;
			
			System.out.println("recherché dans la BD");
			try {

				String requete = "SELECT * FROM "+TABLE+" WHERE "+CLE_PRIMAIRE+" = "+idJeu;
				ResultSet rs = Connexion.executeQuery(requete);
				rs.next();
				String nom = rs.getString(NOM_JEU);
				int nombreJoueursMax = rs.getInt(NOMBRE_JOUEURS_MAX);
				int nombreJoueursMin = rs.getInt(NOMBRE_JOUEURS_MIN);
				int annee = rs.getInt(ANNEE);
				int ageMin = rs.getInt(AGE_MIN);
				int ageMax = rs.getInt(AGE_MAX);
				int dureeMin = rs.getInt(DUREE_MIN);
				int dureeMax = rs.getInt(DUREE_MAX);
				String descriptif = rs.getString(DESCRIPTIF);
				String editeur = rs.getString(EDITEUR);
				int disponible = rs.getInt(DISPONIBLE);
				String etat = rs.getString(ETAT);
				jeu = new Jeu (idJeu, nom, nombreJoueursMax, nombreJoueursMin, annee, ageMin, ageMax, dureeMin, dureeMax,
										descriptif, editeur, disponible, etat);
				donnees.put(idJeu, jeu);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			return jeu;
		}
}
