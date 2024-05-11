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

			String nomSaisi = nom.getText();
			String prenomSaisi = prenom.getText();
			String telephoneSaisi = telephone.getText();
			String adresseSaisi = adresse.getText();
			String emailSaisi = email.getText();
			String noCNISaisi = noCNI.getText();
			String observationsSaisi = observations.getText();

			String passwordToHash = "sio";
			String salt = PasswordManager.getSaltvalue(30);
			
			String hashedPassword = PasswordManager.generateSecurePassword(passwordToHash, salt);
			

			if(!nomSaisi.isBlank() && !prenomSaisi.isBlank() && !telephoneSaisi.isBlank()
					&& !adresseSaisi.isBlank() && !emailSaisi.isBlank() && !noCNISaisi.isBlank()) {
				boolean confirmation = demanderConfirmation("Ajouter l'adhérent ?");
				if(confirmation) {
					Connexion.getInstance();
					Adherent adherentCree = new Adherent(nomSaisi, prenomSaisi, telephoneSaisi, adresseSaisi, emailSaisi,
							noCNISaisi, 40, observationsSaisi, hashedPassword, salt);
					AdherentDAO.getInstance().create(adherentCree);
					System.out.println(hashedPassword);
					
					System.out.println(adherentCree);
					System.out.println(AdherentDAO.getInstance().read(adherentCree.getIdProfil()));
					
					afficherMessage("Adhérent ajouté avec succès");
					loadOtherFXML("monCompte");
				}
			} else {
				afficherMessage("Veuillez remplir tous les champs");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	@FXML
	public void allerVersConnexion(ActionEvent event) {
	  
		try {
			
			//test update
			
			Connexion.getInstance();
			Adherent adherent = AdherentDAO.getInstance().read(3);
			System.out.println(adherent);
			
			adherent.setEmail("a");
			adherent.setCaution(30);
			
			System.out.println(adherent);
			AdherentDAO.getInstance().update(adherent);
			
			System.out.println(AdherentDAO.getInstance().read(3));
			
			
			
//			closePopup();
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
