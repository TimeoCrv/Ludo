package controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import model.AdherentDAO;
import model.Connexion;
import utils.PasswordManager;

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
	            connexionOk = PasswordManager.verifyUserPassword(password, storedPassword, storedSalt);

			} catch (Exception e) {
				e.printStackTrace();
			}
	    
		return connexionOk;
	}
	
}
