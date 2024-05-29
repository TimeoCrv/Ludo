package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import model.Connexion;
import model.Jeu;
import model.JeuDAO;
import model.Personnel;
import model.PersonnelDAO;
import model.User;
import utils.SessionManager;

public class UpdateJeuController extends PageInit{
	
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
	private CheckBox modifDisponible;
	
	@FXML
	private TextField modifNombre;
	
	@FXML
	private TextArea modifDescriptif;

	
	@FXML
	public void initialize() {
		setAnchors();
//		User user = SessionManager.getCurrentUser();
//		int id = SessionManager.getCurrentUser().getId();      //exemple de code pour récupérer la donnée de la personne connecté 
//		System.out.println(user.getRole());
//		if (user.getRole().matches("admin")) {
//			Personnel p = PersonnelDAO.getInstance().read(id);
//			System.out.println(p.getAdresse());
//			p.setAdresse("déménage");
//			PersonnelDAO.getInstance().update(p);
//			System.out.println(p.getAdresse());
//		}
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
	    modifDisponible.setSelected(this.jeu.getDisponible());
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
	        boolean disponibleSaisi = modifDisponible.isSelected();
	        int nombreSaisi = Integer.parseInt(modifNombre.getText());

	        if (!nomSaisi.isBlank() && joueursMaxSaisi > 0 && joueursMinSaisi > 0 && anneeSaisi > 0
	                && ageMinSaisi > 0 && dureeMinSaisi > 0 && !descriptifSaisi.isBlank() && !editeurSaisi.isBlank()
	                && nombreSaisi >= 0) {
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
	                loadOtherFXML("ListeJeux");
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
	        loadOtherFXML("ListeJeux");
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
}
