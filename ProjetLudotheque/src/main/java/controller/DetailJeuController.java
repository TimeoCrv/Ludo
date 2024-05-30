package controller;

import java.io.IOException;
import java.text.SimpleDateFormat;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.Adherent;
import model.AdherentDAO;
import model.Connexion;
import model.Emprunt;
import model.EmpruntDAO;
import model.Jeu;
import model.JeuDAO;
import utils.PasswordManager;
import utils.SessionManager;
import utils.ValidatorManager;

public class DetailJeuController extends PageInit {

	private Jeu jeu;

	@FXML
	private Label nomJeu;
	@FXML
	private Label editeur;
	@FXML
	private Label annee;
	@FXML
	private Label nbJoueurs;
	@FXML
	private Label ageMin;
	@FXML
	private Label descriptif;
	@FXML
	private Button emprunterJeu;
	@FXML
	private Button modifierJeu;
	@FXML
	private Button supprimerJeu;
	@FXML
	private Button backListJeu;

	@FXML
	public void initialize() {
		backListJeu.setManaged(true);
		modifierJeu.setVisible(false);
		modifierJeu.setManaged(false);
		supprimerJeu.setVisible(false);
		supprimerJeu.setManaged(false);
		emprunterJeu.setVisible(false);
		emprunterJeu.setManaged(false);

		if (SessionManager.getCurrentUser() != null && (MainController.isAdmin() || MainController.isPersonnel())) {
			modifierJeu.setVisible(true);
			modifierJeu.setManaged(true);
			supprimerJeu.setVisible(true);
			supprimerJeu.setManaged(true);
		} else if(SessionManager.getCurrentUser() != null && MainController.isAdherent()){
			emprunterJeu.setVisible(true);
			emprunterJeu.setManaged(true);
		}
	}

	public void setJeu(Jeu jeu) {
		this.jeu = jeu;
		if (this.jeu != null) {
			initializeFields();
		}
	}

	private void initializeFields() {
		nomJeu.setText(this.jeu.getNom());
		editeur.setText("Editeur : "+this.jeu.getEditeur());
		annee.setText("Année : "+Integer.toString(this.jeu.getAnnee()));
		nbJoueurs.setText("Nombre de joueurs : de "+Integer.toString(this.jeu.getNombreJoueursMin())+" à "+Integer.toString(this.jeu.getNombreJoueursMax())+" joueurs");
		ageMin.setText("Age minimum : "+Integer.toString(this.jeu.getAgeMin()));
	    descriptif.setText("Description : "+this.jeu.getDescriptif());
	}

	@FXML
	private void supprimerUnJeu() {
			try {
				boolean confirmation = demanderConfirmation("Supprimer le jeu ?");
				if (confirmation) {
					JeuDAO.getInstance().delete(this.jeu);
					afficherMessage("Jeu supprimé avec succès");
					loadOtherFXML("ListeJeux");
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
	}
	
	@FXML
	private void toEmprunterJeu() {
			try {
				boolean confirmation = demanderConfirmation("Emprunter le jeu ?");
				if (confirmation) {
					int idUser = SessionManager.getCurrentUser().getId();
					Adherent adherent = AdherentDAO.getInstance().read(idUser);
					Emprunt emprunt = new Emprunt(adherent.getIdProfil(),this.jeu.getIdJeu());
					EmpruntDAO.getInstance().create(emprunt);
					afficherMessage("Jeu emprunté avec succès");
					loadOtherFXML("ListeJeux");
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
	}

	@FXML
	public void toUpdateJeu(ActionEvent event) {
			try {
				if (jeu != null && SessionManager.getCurrentUser() != null
						&& (MainController.isAdmin() || MainController.isPersonnel())) {
					loadUpdateJeuFXML("UpdateJeu", this.jeu);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		
	}
	
	@FXML
	private void backListJeu() {
	    try {
	        loadOtherFXML("ListeJeux");
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}

}