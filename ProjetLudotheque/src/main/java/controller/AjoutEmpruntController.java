package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import model.Connexion;
import model.Emprunt;
import model.EmpruntDAO;
import model.Jeu;
import model.Personnel;
import model.User;
import utils.SessionManager;

public class AjoutEmpruntController extends PageInit {
	
	private User user;
	private Jeu jeu;
	
	@FXML
	private TextField id_jeu_physique;

	@FXML
	private TextField id_profil;
	
	
	
	
	@FXML
	public void initialize() {
		setAnchors();
	}
	
	public void setJeu(User user) {
		this.user = user;
		if (this.user != null) {
			initializeFields();
		}
	}

	private void initializeFields() {
		nom.setText(this.personnel.getNom());
		prenom.setText(this.personnel.getPrenom());
		email.setText(this.personnel.getEmail());
		telephone.setText(this.personnel.getTel());
		adresse.setText(this.personnel.getAdresse());
		isAdmin.setSelected(this.personnel.isAdmin());
	}
	
	private void initializeFields() {
		id_jeu_physique.setPromptText(this.jeu.getIdJeu();)
		id_profil.setText(SessionManager.getCurrentUser().getId());
	}

	@FXML
	public void emprunterUnJeu(ActionEvent event) {
	    String idJeuPhysiqueText = id_jeu_physique.getText();
	    String idProfilText = id_profil.getText();

	    try {
	        // Input validation
	        if (idJeuPhysiqueText.isEmpty() || idProfilText.isEmpty()) {
	            afficherMessage("Veuillez remplir tous les champs");
	            return;
	        }

	        int idJeuPhysiqueSaisi = Integer.parseInt(idJeuPhysiqueText);
	        int idProfilSaisi = Integer.parseInt(idProfilText);

	        boolean confirmation = demanderConfirmation("Ajouter le emprunt ?");
	        if (confirmation) {
	            Connexion.getInstance();
	            Emprunt empruntCree = new Emprunt(idJeuPhysiqueSaisi, idProfilSaisi);
	            EmpruntDAO.getInstance().create(empruntCree);

	            afficherMessage("Jeu emprunté avec succès");
	            loadOtherFXML("ListeJeux");
	        }
	    } catch (NumberFormatException e) {
	        afficherMessage("Les identifiants doivent être des nombres");
	        e.printStackTrace();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}



	public void setJeu(Jeu jeu2) {
		// TODO Auto-generated method stub
		
	}


	
	

}
