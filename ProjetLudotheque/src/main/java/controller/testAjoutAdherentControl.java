package controller;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Adherent;
import model.AdherentDAO;
import model.Connexion;
import utils.PassEncTech4;

public class testAjoutAdherentControl extends Application {

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

	@Override
	public void start(Stage primaryStage) throws Exception {
		// Charger le fichier FXML
		FXMLLoader loader = new FXMLLoader(getClass().getResource("../ihm/testAjoutAdherent.fxml"));
		Parent root = loader.load();

		// Créer une scène avec le contenu chargé depuis le FXML
		Scene scene = new Scene(root);

		// Définir la scène sur la fenêtre principale
		primaryStage.setScene(scene);
		primaryStage.setTitle("Connexion");
		primaryStage.show();
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
			String salt = PassEncTech4.getSaltvalue(30);
			
			String hashedPassword = PassEncTech4.generateSecurePassword(passwordToHash, salt);
			

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
            // Charger la scène accueil.fxml
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../ihm/testConnexionAdherent.fxml"));
            Parent accueilRoot = loader.load();
            
            // Récupérer la fenêtre principale (stage)
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow(); // Récupérer la scène du bouton
            
            stage.setScene(new Scene(accueilRoot));
            stage.setTitle("Connexion");
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
    
	public static void main(String[] args) {
		launch(args);
	}

}
