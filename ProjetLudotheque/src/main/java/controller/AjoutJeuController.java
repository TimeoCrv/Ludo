package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import model.Connexion;
import model.Jeu;
import model.JeuDAO;

public class AjoutJeuController extends PageInit{

	@FXML
	private TextField ajoutNom;
	
	@FXML
	private TextField ajoutJMax;
	
	@FXML
	private TextField ajoutJMin;
	
	@FXML
	private TextField ajoutAnnee;
	
	@FXML
	private TextField ajoutAgeMin;
	
	@FXML
	private TextField ajoutDureeMin;
	
	@FXML
	private TextField ajoutEditeur;
	
	@FXML
	private CheckBox ajoutDisponible;
	
	@FXML
	private TextField ajoutNombre;
	
	@FXML
	private TextArea ajoutDescriptif;
	
	public void initialize() {
		setAnchors();
	}
	
	@FXML
	public void ajouterJeu(ActionEvent event) {

	    try {
	        String nomSaisi = ajoutNom.getText();
	        int joueursMaxSaisi = Integer.parseInt(ajoutJMax.getText());
	        int joueursMinSaisi = Integer.parseInt(ajoutJMin.getText());
	        int anneeSaisi = Integer.parseInt(ajoutAnnee.getText());
	        int ageMinSaisi = Integer.parseInt(ajoutAgeMin.getText());
	        int dureeMinSaisi = Integer.parseInt(ajoutDureeMin.getText());
	        String descriptifSaisi = ajoutDescriptif.getText();
	        String editeurSaisi = ajoutEditeur.getText();
	        boolean isDisponible = ajoutDisponible.isSelected();
	        int nombreSaisi = Integer.parseInt(ajoutNombre.getText());

	        if (!nomSaisi.isBlank() && joueursMaxSaisi > 0 && joueursMinSaisi > 0 && anneeSaisi > 0
	                && ageMinSaisi > 0 && dureeMinSaisi > 0 && !descriptifSaisi.isBlank()
	                && !editeurSaisi.isBlank() && nombreSaisi >= 0) {
	            boolean confirmation = demanderConfirmation("Ajouter le jeu ?");
	            if (confirmation) {
	                Connexion.getInstance();
	                Jeu jeuCree = new Jeu(nomSaisi, joueursMaxSaisi, joueursMinSaisi, anneeSaisi, ageMinSaisi, dureeMinSaisi, descriptifSaisi, editeurSaisi, isDisponible, nombreSaisi);
	                JeuDAO.getInstance().create(jeuCree);

	                afficherMessage("Jeu ajouté avec succès");
	                loadOtherFXML("ListeJeux");
	            }
	        } else {
	            afficherMessage("Veuillez remplir tous les champs");
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}


	
	@FXML
	private void annulerCreationJeu() {
	    try {
	        loadOtherFXML("ListeJeux");
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
}

