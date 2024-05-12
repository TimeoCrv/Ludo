package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import model.Connexion;
import model.Personnel;
import model.PersonnelDAO;
import utils.PasswordManager;


public class  testAjoutPersonnelControl extends PageInit {
	
	@FXML
	private TextField nom;

	@FXML
	private TextField prenom;
	
	@FXML
	private TextField email;

	@FXML
	private TextField adresse;

	@FXML
	private TextField telephone;
	
	@FXML
	private TextField isAdmin;
	
	

	
	@FXML
	public void ajouterPersonnel(ActionEvent event) {

		try {

			String nomSaisi = nom.getText();
			String prenomSaisi = prenom.getText();
			String adresseSaisi = adresse.getText();
			String emailSaisi = email.getText();
			String telephoneSaisi = telephone.getText();
			// TODO need to figure out how to use isAdmin
			//String isAdminSaisi = isAdmin.getText();
		
			String passwordToHash = "sio";
			String salt = PasswordManager.getSaltvalue(30);
			
			String hashedPassword = PasswordManager.generateSecurePassword(passwordToHash, salt);
			
			if(!nomSaisi.isBlank() && !prenomSaisi.isBlank() && !telephoneSaisi.isBlank()
					&& !adresseSaisi.isBlank() && !emailSaisi.isBlank()) {
				boolean confirmation = demanderConfirmation("Ajouter le membre du personnel	 ?");
				if(confirmation) {
					Connexion.getInstance();
					Personnel personnelCree = new Personnel(nomSaisi, prenomSaisi, telephoneSaisi, adresseSaisi, emailSaisi,
							 hashedPassword, salt);
					PersonnelDAO.getInstance().create(personnelCree);
					System.out.println(hashedPassword);
					
					System.out.println(personnelCree);
					System.out.println(PersonnelDAO.getInstance().read(personnelCree.getId_personnel()));
					
					afficherMessage("Membre du personnel ajouté avec succès");
					loadOtherFXML("testListePersonnel");


		} else {
			afficherMessage("Veuillez remplir tous les champs");
		}

			}}catch (Exception e) {
		e.printStackTrace();
	}
}
	
		@FXML
		public void allerVersConnexion(ActionEvent event) {
		  
			try {
				
				//test update
				
				Connexion.getInstance();
				Personnel personnel = PersonnelDAO.getInstance().read(3);
				System.out.println(personnel);
				
				personnel.setEmail("a");
				
				System.out.println(personnel);
				PersonnelDAO.getInstance().update(personnel);
				
				System.out.println(PersonnelDAO.getInstance().read(3));
				
				
				
//				closePopup();
//	            // Charger la scène accueil.fxml
//	            FXMLLoader loader = new FXMLLoader(getClass().getResource("../ihm/testConnexionAdherent.fxml"));
//	            Parent accueilRoot = loader.load();
//	            
//	            // Récupérer la fenêtre principale (stage)
//	            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow(); // Récupérer la scène du bouton
//	            
//	            stage.setScene(new Scene(accueilRoot));
//	            stage.setTitle("Connexion");
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
		}
	    
	}
	