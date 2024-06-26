package controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import model.User;
import model.UserDAO;
import utils.PasswordManager;
import utils.SessionManager;

public class ConnexionFormController extends PageInit {
	
	public static SessionManager sessionManager;

	@FXML
	private TextField loginEmail;

	@FXML
	private PasswordField loginPassword;
	
	@FXML
	private Label errorMessage;
	
	public void initialize() {
		setAnchors();
	}

	@FXML
	public void userConnexion(ActionEvent event) {

		String emailSaisi = loginEmail.getText();
		String motDePasseSaisi = loginPassword.getText();

		if (PasswordManager.authenticate(emailSaisi, motDePasseSaisi)) {

			try {
				
				int idUser = UserDAO.getInstance().getIdByEmail(emailSaisi);
				User user = UserDAO.getInstance().read(idUser);
				
				sessionManager = SessionManager.getInstance();
				sessionManager.createSession(user);
				
				loadOtherFXML("ListeJeux");
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			errorMessage.setVisible(true);
			errorMessage.setManaged(true);
		}
	}


	
}
