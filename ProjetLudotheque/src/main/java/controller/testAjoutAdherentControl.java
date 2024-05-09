package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import model.Adherent;
import model.AdherentDAO;
import model.Connexion;
import utils.PasswordManager;

public class testAjoutAdherentControl extends PageInit{

	@FXML
	private TextField nom;

	@FXML
	private TextField prenom;

	@FXML
	private TextField adresse;

	@FXML
	private TextField telephone;

	@FXML
	private TextField email;

	@FXML
	private TextArea observations;
	
	public void initialize() {
		setAnchors();
	}
	
	@FXML
	public void ajouterAdherent(ActionEvent event) {

		try {

			String nomSaisi = nom.getText();
			String prenomSaisi = prenom.getText();
			String adresseSaisi = adresse.getText();
			String telephoneSaisi = telephone.getText();
			String emailSaisi = email.getText();
			String observationsSaisi = observations.getText();

			String passwordToHash = "sio";
			String salt = PasswordManager.getSaltvalue(30);
			
			String hashedPassword = PasswordManager.generateSecurePassword(passwordToHash, salt);
			

			Connexion.getInstance();
			Adherent adherentCree = new Adherent(nomSaisi, prenomSaisi, adresseSaisi, telephoneSaisi, emailSaisi,
												40, observationsSaisi, hashedPassword, salt);
			AdherentDAO.getInstance().create(adherentCree);
			System.out.println(hashedPassword);
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
