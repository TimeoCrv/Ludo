package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import model.Adherent;
import model.AdherentDAO;
import model.Connexion;
import utils.PasswordManager;
import utils.ValidatorManager;

public class AjoutAdherentController extends PageInit{

	@FXML
	private TextField nom;

	@FXML
	private TextField prenom;

	@FXML
	private TextField telephone;
	
	@FXML
	private TextField adresse;

	@FXML
	private TextField email;
	
	@FXML
	private TextField noCNI;

	@FXML
	private TextArea observations;
	
	public void initialize() {
		setAnchors();
	}
	
	@FXML
	public void ajouterAdherent(ActionEvent event) {

		try {

			String emailSaisi = email.getText();
			if(!ValidatorManager.isValidEmail(emailSaisi)) {
				afficherMessage("E-mail invalide");
			} else if (ValidatorManager.isAdherentEmailInBD(emailSaisi)) {
				afficherMessage("Cet e-mail est déjà utilisé");
			} else {
				String nomSaisi = nom.getText();
				String prenomSaisi = prenom.getText();
				String telephoneSaisi = telephone.getText();
				String adresseSaisi = adresse.getText();
				String noCNISaisi = noCNI.getText();
				String observationsSaisi = observations.getText();
				
				String passwordToHash = "sio"; // Mot de passe par défaut à la cration d'un adhérent
				String salt = PasswordManager.getSaltvalue(30); //Longueur du grain de sel arbitraire
				
				String hashedPassword = PasswordManager.generateSecurePassword(passwordToHash, salt);
				
				
				if(!nomSaisi.isBlank() && !prenomSaisi.isBlank() && !telephoneSaisi.isBlank()
						&& !adresseSaisi.isBlank() && !emailSaisi.isBlank() && !noCNISaisi.isBlank()) {
					boolean confirmation = demanderConfirmation("Ajouter l'adhérent ?");
					if(confirmation) {
						Connexion.getInstance();
						Adherent adherentCree = new Adherent(nomSaisi, prenomSaisi, telephoneSaisi, adresseSaisi, emailSaisi,
								noCNISaisi, 40, observationsSaisi, hashedPassword, salt);
						AdherentDAO.getInstance().create(adherentCree);
						
						afficherMessage("Adhérent ajouté avec succès");
						loadOtherFXML("ListeAdherents");
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
	public void backListeAdherents(ActionEvent event) {
	  
		try {
			loadOtherFXML("ListeAdherents");
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
    
}
