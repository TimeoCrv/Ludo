package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO extends DAO<User>{
	
		private static final String TABLE_UTILISATEUR = "utilisateur";
		private static final String ID_UTILISATEUR = "id_utilisateur";
		private static final String EMAIL = "email";
		private static final String MOT_DE_PASSE = "password";
		private static final String SALT = "salt";
		private static final String ROLE = "role";
		
		private static UserDAO instance=null;

		public static UserDAO getInstance(){
			if (instance==null){
				instance = new UserDAO();
			}
			return instance;
		}

		private UserDAO() {
			super();
		}


		// User sert à la création de session actuellement :
		// Pas de besoin de create, delete et update
		
		@Override
		public boolean create(User user) {
			//No need
			return true;
		}

		@Override
		public boolean delete(User user) {
			//No need
			return true;
		}

		@Override
		public boolean update(User user) {
			//No need
			return true;	
		}

		@Override
		public User read(int id) {
			User user = null;
			
//			System.out.println("recherché dans la BD");
			try {

				String requete = "SELECT * FROM "+TABLE_UTILISATEUR+" WHERE "+ID_UTILISATEUR+" = "+id;
				ResultSet rs = Connexion.executeQuery(requete);
				rs.next();
				String role = rs.getString(ROLE);
				user = new User (id, role);
//				donnees.put(id, user);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			return user;
		}

		
		
		// Répétition des fonctions d'adhérent.
		// A voir la possibilité de extend user dans adhérent et personnel pour éviter la répétition
		public String getPasswordById(int id) {
			String password = null;
			try{

	            String requete = "SELECT "+MOT_DE_PASSE+" FROM "+TABLE_UTILISATEUR+" WHERE "+ID_UTILISATEUR+" = ?";
	            PreparedStatement pst = Connexion.getInstance().prepareStatement(requete) ;
	            pst.setInt(1, id);

	            ResultSet resultSet = pst.executeQuery();

	            if (resultSet.next()) {
	                password = resultSet.getString(MOT_DE_PASSE);
	            }
			}
			catch(SQLException e){
				System.out.println("Echec de la tentative d'interrogation Select * : " + e.getMessage()) ;
			}
			return password;
			
		}
		
		public String getSaltById(int id) {
			String password = null;
			try{
	            String requete = "SELECT "+SALT+" FROM "+TABLE_UTILISATEUR+" WHERE "+ID_UTILISATEUR+" = ?";
	            PreparedStatement pst = Connexion.getInstance().prepareStatement(requete) ;
	            pst.setInt(1, id);

	            ResultSet resultSet = pst.executeQuery();

	            if (resultSet.next()) {
	                password = resultSet.getString(SALT);
	            }
			}
			catch(SQLException e){
				System.out.println("Echec de la tentative d'interrogation Select * : " + e.getMessage()) ;
			}
			return password;
			
		}
		
		public String getEmailById(int id) {
			String email = null;
			try{

	            String requete = "SELECT "+EMAIL+" FROM "+TABLE_UTILISATEUR+" WHERE "+ID_UTILISATEUR+" = ?";
	            PreparedStatement pst = Connexion.getInstance().prepareStatement(requete) ;
	            pst.setInt(1, id);

	            ResultSet resultSet = pst.executeQuery();

	            if (resultSet.next()) {
	                email = resultSet.getString(EMAIL);
	            }
			}
			catch(SQLException e){
				System.out.println("Echec de la tentative d'interrogation Select * : " + e.getMessage()) ;
			}
			return email;
			
		}
		
		public int getIdByEmail(String email) {
			int id = 0;
			try{

	            String requete = "SELECT "+ID_UTILISATEUR+" FROM "+TABLE_UTILISATEUR+" WHERE "+EMAIL+" = ?";
	            PreparedStatement pst = Connexion.getInstance().prepareStatement(requete) ;
	            pst.setString(1, email);

	            ResultSet resultSet = pst.executeQuery();

	            if (resultSet.next()) {
	                id = resultSet.getInt(ID_UTILISATEUR);
	            }
			}
			catch(SQLException e){
				System.out.println("Echec de la tentative d'interrogation Select * : " + e.getMessage()) ;
			}
			return id;
			
		}

}
