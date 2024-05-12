package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import model.Connexion;
import model.Personnel;
import model.PersonnelDAO;
import utils.PassEncTech4;


public class  testAjoutPersonnelControl extends PageInit {
	
	@FXML
	private TextField nom;

	@FXML
	private TextField prenom;

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
			String telephoneSaisi = telephone.getText();
			//String isAdminText = isAdmin.getText();
			
			//boolean isAdminSaisi = Boolean.parseBoolean(isAdminText);
		
			//String passwordToHash = "sio";
			//String salt = PassEncTech4.getSaltvalue(30);
			
			//String hashedPassword = PassEncTech4.generateSecurePassword(passwordToHash, salt);
			

			Connexion.getInstance();
			Personnel personnelCree = new Personnel(nomSaisi, prenomSaisi, adresseSaisi, telephoneSaisi);
			PersonnelDAO.getInstance().create(personnelCree);
			Connexion.fermer();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	@FXML
	public void allerVersConnexion(ActionEvent event) {
	  
		try {
			closePopup();
//            // Charger la scène accueil.fxml
//            FXMLLoader loader = new FXMLLoader(getClass().getResource("../ihm/testConnexionAdherent.fxml"));
//            Parent accueilRoot = loader.load();
//            
//            // Récupérer la fenêtre principale (stage)
//            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow(); // Récupérer la scène du bouton
//            
//            stage.setScene(new Scene(accueilRoot));
//            stage.setTitle("Connexion");
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
    
}


