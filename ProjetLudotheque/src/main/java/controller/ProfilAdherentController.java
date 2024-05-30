package controller;

import java.text.SimpleDateFormat;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.Adherent;
import model.AdherentDAO;
import model.Connexion;
import utils.PasswordManager;
import utils.SessionManager;
import utils.ValidatorManager;

public class ProfilAdherentController extends PageInit {
	

		private Adherent adherent;

		@FXML
		private Label dateField;
		@FXML
		private Label nameField;
		@FXML
		private Label addressField;
		@FXML
		private Label telField;
		@FXML
		private Label emailField;
		@FXML
		private HBox changeEmail;
		@FXML
		private TextField newEmail;
		@FXML
		private TextField confirmEmail;
		@FXML
		private VBox changeMDP;
		@FXML
		private PasswordField oldMDP;
		@FXML
		private PasswordField newMDP;
		@FXML
		private PasswordField confirmMDP;
		
		@FXML
		public void initialize() {
			if (SessionManager.getCurrentUser()!=null && MainController.isAdherent()) {
				Adherent adherent = AdherentDAO.getInstance().read(SessionManager.getCurrentUser().getId());
				setAdherent(adherent);
			}
			setAnchors();
			changeEmail.setVisible(false);
			changeEmail.setManaged(false);
			changeMDP.setVisible(false);
			changeMDP.setManaged(false);
		}

		public void setAdherent(Adherent adherent) {
			this.adherent = adherent;
			if (this.adherent != null) {
				initializeFields();
			}
		}

		private void initializeFields() {
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
			String dateInscription = formatter.format(this.adherent.getDateInscription());
			dateField.setText("Date d'inscription : "+dateInscription);
			nameField.setText("Nom : " + this.adherent.getNom() + " - Prénom : " + this.adherent.getPrenom());
			addressField.setText("Adresse : " + this.adherent.getAdresse());
			telField.setText("Tél : " +  this.adherent.getTel());
			emailField.setText("E-mail : " + SessionManager.getCurrentUser().getEmail());
		}

		@FXML
		public void modifierEmail(ActionEvent event) {
			changeEmail.setVisible(!changeEmail.isVisible());
			changeEmail.setManaged(changeEmail.isVisible());
			changeMDP.setVisible(false);
			changeMDP.setManaged(false);
		}
		
		@FXML
		public void modifierMotDePasse(ActionEvent event) {
			changeEmail.setVisible(false);
			changeEmail.setManaged(false);
			changeMDP.setVisible(!changeMDP.isVisible());
			changeMDP.setManaged(changeMDP.isVisible());
		}

		@FXML
		public void validerEmail(ActionEvent event) {
			try {
				String newEmailSaisi = newEmail.getText();
				String confirmEmailSaisi = confirmEmail.getText();
				if(!newEmailSaisi.isBlank() && !confirmEmailSaisi.isBlank()) {
					if(!ValidatorManager.isValidEmail(newEmailSaisi)) {
						afficherMessage("E-mail invalide");
					} else if (ValidatorManager.isAdherentEmailInBD(newEmailSaisi)) {
						afficherMessage("Cet e-mail est déjà utilisé");
					} else if (!newEmailSaisi.matches(confirmEmailSaisi)) {
						afficherMessage("Les e-mails ne correspondent pas.");
					} else {
						this.adherent.setEmail(newEmailSaisi);
						
						Connexion.getInstance();
						
						AdherentDAO.getInstance().update(this.adherent);
						
						afficherMessage("Votre E-mail a été modifiée avec succès");
						SessionManager.getCurrentUser().setEmail(newEmailSaisi);
						loadOtherFXML("ProfilAdherent");
					}
				} else {
					afficherMessage("Veuillez remplir tous les champs");
				}
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		@FXML
		public void validerMDP(ActionEvent event) {
			try {
				String userMdp = SessionManager.getCurrentUser().getPassword();
				String userSalt = SessionManager.getCurrentUser().getSalt();
				
				String oldMdpSaisi = oldMDP.getText();
				String newMdpSaisi = newMDP.getText();
				String confirmMdpSaisi = confirmMDP.getText();
				
			
				
				if(!oldMdpSaisi.isBlank() && !newMdpSaisi.isBlank() && !confirmMdpSaisi.isBlank()) {
					if(!PasswordManager.verifyUserPassword(oldMdpSaisi, userMdp, userSalt)) {
						afficherMessage("Mot de passe incorrect.");
					}  else if (!newMdpSaisi.matches(confirmMdpSaisi)) {
						afficherMessage("Les nouveaux mots de passe ne correspondent pas.");
					} else {
						
						String salt = PasswordManager.getSaltvalue(30);
						String hashedPassword = PasswordManager.generateSecurePassword(newMdpSaisi, salt);
						
						this.adherent.setPassword(hashedPassword);
						this.adherent.setSalt(salt);
						
						Connexion.getInstance();
						
						AdherentDAO.getInstance().update(this.adherent);
						
						afficherMessage("Votre mot de passe a été modifiée avec succès");
						loadOtherFXML("ProfilAdherent");
					}
				} else {
					afficherMessage("Veuillez remplir tous les champs");
				}
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		
}