package controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.AdherentDAO;
import model.Connexion;
import utils.PassEncTech4;

public class testConnexionAdherentControl extends PageInit {

	@FXML
	private TextField loginEmail;

	@FXML
	private PasswordField loginPassword;
	
	public void initialize() {
		setAnchors();
	}

	@FXML
	public void userConnexion(ActionEvent event) {

		String emailSaisi = loginEmail.getText();
		String motDePasseSaisi = loginPassword.getText();

		if (authenticate(emailSaisi, motDePasseSaisi)) {

			try {
			
				loadModal("testAjoutAdherent", "test pop up");
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("Email ou mot de passe incorrect.");
		}
	}

	private static boolean authenticate(String email, String password) {
		boolean connexionOk = false;


	        try {
	        	Connexion.getInstance();
	    		int idAdherent = AdherentDAO.getInstance().getIdByEmail(email);
	    		String storedPassword = AdherentDAO.getInstance().getPasswordById(idAdherent);
	    		String storedSalt = AdherentDAO.getInstance().getSaltById(idAdherent);
	    		
	    		System.out.println(storedPassword);
	            connexionOk = PassEncTech4.verifyUserPassword(password, storedPassword, storedSalt);
	            Connexion.fermer();

			} catch (Exception e) {
				e.printStackTrace();
			}
	    
		return connexionOk;
	}
	
}
