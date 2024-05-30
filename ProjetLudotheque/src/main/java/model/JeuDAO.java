package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

	public class JeuDAO extends DAO<Jeu> {
		
		public static final String TABLE = "jeu";
		public static final String CLE_PRIMAIRE = "id_jeu";

		private static final String NOM_JEU = "nom";
		private static final String NOMBRE_JOUEURS_MAX = "nombre_joueurs_max";
		private static final String NOMBRE_JOUEURS_MIN = "nombre_joueurs_min";
		private static final String ANNEE= "annee";
		private static final String AGE_MIN = "age_min";
		private static final String DUREE_MIN = "duree_min";
		private static final String DESCRIPTIF = "descriptif";
		private static final String EDITEUR = "editeur";
		private static final String DISPONIBLE = "disponible";
		private static final String NOMBRE = "nombre";
		
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
		    boolean succes = true;
		    try {
		        String requete = "INSERT INTO " + TABLE + " (" + NOM_JEU + ", " + NOMBRE_JOUEURS_MAX + ", " + NOMBRE_JOUEURS_MIN +
		                ", " + ANNEE + ", " + AGE_MIN + ", " + DUREE_MIN + ", " + DESCRIPTIF +
		                ", " + EDITEUR + ", " + DISPONIBLE + ", " + NOMBRE + ") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		        PreparedStatement pst = Connexion.getInstance().prepareStatement(requete, Statement.RETURN_GENERATED_KEYS);
		        pst.setString(1, jeu.getNom());
		        pst.setInt(2, jeu.getNombreJoueursMax());
		        pst.setInt(3, jeu.getNombreJoueursMin());
		        pst.setInt(4, jeu.getAnnee());
		        pst.setInt(5, jeu.getAgeMin());
		        pst.setInt(6, jeu.getDureeMin()); // Index 6 pour la durée minimale
		        pst.setString(7, jeu.getDescriptif());
		        pst.setString(8, jeu.getEditeur()); // Correction de l'index pour le nom de l'éditeur
		        pst.setBoolean(9, jeu.getDisponible()); // Correction de l'index pour la disponibilité
		        pst.setInt(10, jeu.getNombre());

		        pst.executeUpdate();

		        ResultSet rs = pst.getGeneratedKeys();
		        if (rs.next()) {
		            jeu.setIdJeu(rs.getInt(1));
		        }

		        donnees.put(jeu.getIdJeu(), jeu);

		    } catch (SQLException e) {
		        succes = false;
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
		    boolean succes = true;

		    // Retrieve fields from the 'jeu' object
		    String nom = jeu.getNom();
		    int nombreJoueursMax = jeu.getNombreJoueursMax();
		    int nombreJoueursMin = jeu.getNombreJoueursMin();
		    int annee = jeu.getAnnee();
		    int ageMin = jeu.getAgeMin();
		    int dureeMin = jeu.getDureeMin();
		    String descriptif = jeu.getDescriptif();
		    String editeur = jeu.getEditeur();
		    boolean disponible = jeu.getDisponible();
		    int nombre = jeu.getNombre();
		    int id = jeu.getIdJeu(); // Assume this method retrieves the game ID

		    // Establish connection
		    Connection connexion = Connexion.getInstance();
		    
		    try {
		        // Disable auto-commit
		        connexion.setAutoCommit(false);
		        
		        // Prepare the SQL update query
		        String requete = "UPDATE " + TABLE + " SET " 
		            + NOM_JEU + " = ?, " 
		            + NOMBRE_JOUEURS_MAX + " = ?, " 
		            + NOMBRE_JOUEURS_MIN + " = ?, " 
		            + ANNEE + " = ?, " 
		            + AGE_MIN + " = ?, " 
		            + DUREE_MIN + " = ?, "
		            + DESCRIPTIF + " = ?, " 
		            + EDITEUR + " = ?, " 
		            + DISPONIBLE + " = ?, " 
		            + NOMBRE + " = ? "
		            + "WHERE " + CLE_PRIMAIRE + " = ?";

		        // Create a PreparedStatement
		        PreparedStatement pst = connexion.prepareStatement(requete);
		        
		        // Set parameters
		        pst.setString(1, nom);
		        pst.setInt(2, nombreJoueursMax);
		        pst.setInt(3, nombreJoueursMin);
		        pst.setInt(4, annee);
		        pst.setInt(5, ageMin);
		        pst.setInt(6, dureeMin);
		        pst.setString(7, descriptif);
		        pst.setString(8, editeur);
		        pst.setBoolean(9, disponible);
		        pst.setInt(10, nombre);
		        pst.setInt(11, id); // Bind the ID value at the end
		        
		        // Execute the update
		        pst.executeUpdate();
		        
		        // Commit the transaction
		        connexion.commit();
		    } catch (SQLException e) {
		        try {
		            // Rollback in case of error
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
		public Jeu read(int idJeu) {
			Jeu jeu = null;
			
//			System.out.println("recherché dans la BD");
			try {

				String requete = "SELECT * FROM "+TABLE+" WHERE "+CLE_PRIMAIRE+" = "+idJeu;
				ResultSet rs = Connexion.executeQuery(requete);
				rs.next();
				String nom = rs.getString(NOM_JEU);
				int nombreJoueursMax = rs.getInt(NOMBRE_JOUEURS_MAX);
				int nombreJoueursMin = rs.getInt(NOMBRE_JOUEURS_MIN);
				int annee = rs.getInt(ANNEE);
				int ageMin = rs.getInt(AGE_MIN);
				int dureeMin = rs.getInt(DUREE_MIN);
				String descriptif = rs.getString(DESCRIPTIF);
				String editeur = rs.getString(EDITEUR);
				boolean disponible = rs.getBoolean(DISPONIBLE);
				int nombre = rs.getInt(NOMBRE);
				jeu = new Jeu (idJeu, nom, nombreJoueursMax, nombreJoueursMin, annee, ageMin, dureeMin, descriptif, editeur, disponible, nombre);
				donnees.put(idJeu, jeu);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			return jeu;
		}
		
		public List<Jeu> readTable() {
			List<Jeu> rep = new ArrayList<Jeu>();
			Jeu ad = null;
			try{
				String requete = "SELECT "+CLE_PRIMAIRE+" FROM "+TABLE;
				ResultSet res = Connexion.executeQuery(requete) ;
				while(res.next()){
					int id = res.getInt(1);
					ad = JeuDAO.getInstance().read(id);
					rep.add(ad);
				}
//				for(Jeu jeu : rep) {
//					System.out.println(jeu.getNom());
//				}
			}
			catch(SQLException e){
				System.out.println("Echec de la tentative d'interrogation Select * : " + e.getMessage()) ;
			}
			return rep;
		}
		
		
}
