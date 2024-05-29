package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import model.Connexion;
import model.Personnel;
import model.PersonnelDAO;
import utils.PasswordManager;
import utils.ValidatorManager;

public class AjoutPersonnelController extends PageInit {

	@FXML
	private TextField nom;

	@FXML
	private TextField prenom;

	@FXML
	private TextField email;

	@FXML
	private TextField telephone;

	@FXML
	private TextField adresse;

	@FXML
	private CheckBox isAdmin;

	@FXML
	public void initialize() {
		setAnchors();
	}
	
	@FXML
	public void ajouterPersonnel(ActionEvent event) {

		try {
			String emailSaisi = email.getText();
			if (!ValidatorManager.isValidEmail(emailSaisi)) {
				afficherMessage("E-mail invalide");
			} else if (ValidatorManager.isAdherentEmailInBD(emailSaisi)) {
				afficherMessage("Cet e-mail est déjà utilisé");
			} else {
				String nomSaisi = nom.getText();
				String prenomSaisi = prenom.getText();
				String telephoneSaisi = telephone.getText();
				String adresseSaisi = adresse.getText();

				// TODO need to figure out how to use isAdmin
				boolean isAdminSaisi = isAdmin.isSelected();
				String role = "";
//				if(isAdminSaisi = isAdmin.isSelected()) {
//					role = "admin";
//					
//				}else {
//					role="personnel";
//					}
				
				role = isAdminSaisi ? "admin" : "personnel";

				String passwordToHash = "sio";
				String salt = PasswordManager.getSaltvalue(30);

				String hashedPassword = PasswordManager.generateSecurePassword(passwordToHash, salt);

				if (!nomSaisi.isBlank() && !prenomSaisi.isBlank() && !emailSaisi.isBlank() && !telephoneSaisi.isBlank()
						&& !adresseSaisi.isBlank()) {
					boolean confirmation = demanderConfirmation("Ajouter le membre du personnel	 ?");
					if (confirmation) {
						Connexion.getInstance();
						Personnel personnelCree = new Personnel(nomSaisi, prenomSaisi, emailSaisi, telephoneSaisi,
								adresseSaisi, isAdminSaisi, hashedPassword, salt, role);
						PersonnelDAO.getInstance().create(personnelCree);
						System.out.println(hashedPassword);

						System.out.println(personnelCree);
						System.out.println(PersonnelDAO.getInstance().read(personnelCree.getId_personnel()));

						afficherMessage("Membre du personnel ajouté avec succès");
						loadOtherFXML("ListePersonnel");
					}
				} else {
					afficherMessage("Veuillez remplir tous les champs");
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@FXML
	public void backListePersonnel(ActionEvent event) {
	  
		try {
			loadOtherFXML("ListePersonnel");
        } catch (Exception e) {
            e.printStackTrace();
        }
	}

}
