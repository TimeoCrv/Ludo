package controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import model.Jeu;
import model.JeuDAO;

public class ajouterJeuController extends PageInit{

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
	private TextField ajoutDisponible;
	
	@FXML
	private TextField ajoutNombre;
	
	@FXML
	private TextArea ajoutDescriptif;
	
	@FXML
	private void ajouterUnJeu() {
		try {
			String nomSaisi = ajoutNom.getText();
			int joueursMaxSaisi = Integer.parseInt(ajoutJMax.getText());
			int joueursMinSaisi = Integer.parseInt(ajoutJMin.getText());
			int anneeSaisi = Integer.parseInt(ajoutAnnee.getText());
			int ageMinSaisi = Integer.parseInt(ajoutAgeMin.getText());
			int dureeMinSaisi = Integer.parseInt(ajoutDureeMin.getText());
			String descriptifSaisi = ajoutDescriptif.getText();
			String editeurSaisi = ajoutEditeur.getText();
			int disponibleSaisi = Integer.parseInt(ajoutDisponible.getText());
			int nombreSaisi = Integer.parseInt(ajoutNombre.getText());
			
			Jeu jeuCree = new Jeu(nomSaisi, joueursMaxSaisi, joueursMinSaisi, anneeSaisi, ageMinSaisi, dureeMinSaisi, editeurSaisi, descriptifSaisi, disponibleSaisi, nombreSaisi);
			
			JeuDAO.getInstance().create(jeuCree);
			
			loadOtherFXML("listejeuadmin");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	private void annulerCreationJeu() {
	    try {
	        loadOtherFXML("listejeuadmin");
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
}