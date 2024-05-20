package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import model.Connexion;
import model.Jeu;
import model.JeuDAO;

public class ModifierJeuController extends PageInit{
	
	private Jeu jeu;
	
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

	
	@FXML
	public void initialize() {
		setAnchors();
	}
	
	public void setJeu(Jeu jeu) {
		this.jeu = jeu;
		if (this.jeu != null) {
			initializeFields();
		}
	}
	
	private void initializeFields() {
	    modifNom.setText(this.jeu.getNom());
	    modifJMax.setText(Integer.toString(this.jeu.getNombreJoueursMax()));
	    modifJMin.setText(Integer.toString(this.jeu.getNombreJoueursMin()));
	    modifAnnee.setText(Integer.toString(this.jeu.getAnnee()));
	    modifAgeMin.setText(Integer.toString(this.jeu.getAgeMin()));
	    modifDureeMin.setText(Integer.toString(this.jeu.getDureeMin()));
	    modifEditeur.setText(this.jeu.getEditeur());
	    modifDisponible.setText(Integer.toString(this.jeu.getDisponible()));
	    modifNombre.setText(Integer.toString(this.jeu.getNombre()));
	    modifDescriptif.setText(this.jeu.getDescriptif());
	}

	@FXML
	public void validateUpdate(ActionEvent event) {
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

	        if (!nomSaisi.isBlank() && joueursMaxSaisi > 0 && joueursMinSaisi > 0 && anneeSaisi > 0
	                && ageMinSaisi > 0 && dureeMinSaisi > 0 && !descriptifSaisi.isBlank() && !editeurSaisi.isBlank()
	                && disponibleSaisi >= 0 && nombreSaisi > 0) {
	            boolean confirmation = demanderConfirmation("Modifier le jeu ?");
	            if (confirmation) {
	                this.jeu.setNom(nomSaisi);
	                this.jeu.setNombreJoueursMax(joueursMaxSaisi);
	                this.jeu.setNombreJoueursMin(joueursMinSaisi);
	                this.jeu.setAnnee(anneeSaisi);
	                this.jeu.setAgeMin(ageMinSaisi);
	                this.jeu.setDureeMin(dureeMinSaisi);
	                this.jeu.setDescriptif(descriptifSaisi);
	                this.jeu.setEditeur(editeurSaisi);
	                this.jeu.setDisponible(disponibleSaisi);
	                this.jeu.setNombre(nombreSaisi);

	                Connexion.getInstance();

	                JeuDAO.getInstance().update(this.jeu);

	                afficherMessage("Jeu modifié avec succès");
	                loadOtherFXML("listejeuadmin");
	            }
	        } else {
	            afficherMessage("Veuillez remplir tous les champs");
	        }

	    } catch (NumberFormatException e) {
	        afficherMessage("Veuillez entrer des valeurs valides pour les champs numériques");
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
