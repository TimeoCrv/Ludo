package controller;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.Connexion;
import model.Personnel;
import model.PersonnelDAO;
import utils.PasswordManager;
import utils.SessionManager;
import utils.ValidatorManager;

public class ProfilPersonnelController extends PageInit {
	

		private Personnel personnel;

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
			if (SessionManager.getCurrentUser()!=null && MainController.isPersonnel()) {
				Personnel personnel = PersonnelDAO.getInstance().read(SessionManager.getCurrentUser().getId());
				setPersonnel(personnel);
			}
			setAnchors();
			changeEmail.setVisible(false);
			changeEmail.setManaged(false);
			changeMDP.setVisible(false);
			changeMDP.setManaged(false);
		}

		public void setPersonnel(Personnel personnel) {
			this.personnel = personnel;
			if (this.personnel != null) {
				initializeFields();
			}
		}

		private void initializeFields() {
			nameField.setText("Nom : " + this.personnel.getNom() + " - Prénom : " + this.personnel.getPrenom());
			addressField.setText("Adresse : " + this.personnel.getAdresse());
			telField.setText("Tél : " +  this.personnel.getTel());
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
						this.personnel.setEmail(newEmailSaisi);
						
						Connexion.getInstance();
						
						PersonnelDAO.getInstance().update(this.personnel);
						
						afficherMessage("Votre E-mail a été modifiée avec succès");
						SessionManager.getCurrentUser().setEmail(newEmailSaisi);
						loadOtherFXML("ProfilPersonnel");
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
						
						this.personnel.setPassword(hashedPassword);
						this.personnel.setSalt(salt);
						
						Connexion.getInstance();
						
						PersonnelDAO.getInstance().update(this.personnel);
						
						afficherMessage("Votre mot de passe a été modifiée avec succès");
						loadOtherFXML("ProfilPersonnel");
					}
				} else {
					afficherMessage("Veuillez remplir tous les champs");
				}
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		
}