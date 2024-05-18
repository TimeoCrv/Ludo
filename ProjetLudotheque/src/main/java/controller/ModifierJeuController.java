package controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import model.Jeu;
import model.JeuDAO;

public class ModifierJeuController extends PageInit{
	
	@FXML
	private TextField modifNom;
	
	@FXML
	private TextField modifJMax;
	
	@FXML
	private TextField modifJMin;
	
	@FXML
	private TextField modifAnnee;
	
	@FXML
	private TextField modifAgeMin;
	
	@FXML
	private TextField modifDureeMin;
	
	@FXML
	private TextField modifEditeur;
	
	@FXML
	private TextField modifDisponible;
	
	@FXML
	private TextField modifNombre;
	
	@FXML
	private TextArea modifDescriptif;

	
	public void initData(Jeu jeu) {
        modifNom.setText(jeu.getNom());
        modifJMax.setText(String.valueOf(jeu.getNombreJoueursMax()));
        modifJMin.setText(String.valueOf(jeu.getNombreJoueursMin()));
        modifAnnee.setText(String.valueOf(jeu.getAnnee()));
        modifAgeMin.setText(String.valueOf(jeu.getAgeMin()));
        modifDureeMin.setText(String.valueOf(jeu.getDureeMin()));
        modifDescriptif.setText(jeu.getDescriptif());
        modifEditeur.setText(jeu.getEditeur());
        modifDisponible.setText(String.valueOf(jeu.getDisponible()));
        modifNombre.setText(String.valueOf(jeu.getNombre()));
    }
	
	@FXML
	private void modifierUnJeu() {
		try {
			String nomSaisi = modifNom.getText();
			int joueursMaxSaisi = Integer.parseInt(modifJMax.getText());
			int joueursMinSaisi = Integer.parseInt(modifJMin.getText());
			int anneeSaisi = Integer.parseInt(modifAnnee.getText());
			int ageMinSaisi = Integer.parseInt(modifAgeMin.getText());
			int dureeMinSaisi = Integer.parseInt(modifDureeMin.getText());
			String descriptifSaisi = modifDescriptif.getText();
			String editeurSaisi = modifEditeur.getText();
			int disponibleSaisi = Integer.parseInt(modifDisponible.getText());
			int nombreSaisi = Integer.parseInt(modifNombre.getText());
			
			Jeu jeuModif = new Jeu(nomSaisi, joueursMaxSaisi, joueursMinSaisi, anneeSaisi, ageMinSaisi, dureeMinSaisi, editeurSaisi, descriptifSaisi, disponibleSaisi, nombreSaisi);
			
			JeuDAO.getInstance().update(jeuModif);
			
			loadOtherFXML("listejeuadmin");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	private void annulerModificationJeu() {
	    try {
	        loadOtherFXML("listejeuadmin");
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
}
