package controller;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import model.User;
import utils.SessionManager;

public class MainController {

	private Main main;

	@FXML
	private Button btnAddJeu;

	@FXML
	private Button btnConnexion;

	@FXML
	private Button btnDeconnexion;

	@FXML
	private Button btnHistorique;

	@FXML
	private Button btnListeJeu;

	@FXML
	private Button btnProfil;

	@FXML
	private Button btnRendreJeu;

	@FXML
	private Label labelAdmin;
	@FXML
	private Button btnListeAdherent;

	@FXML
	private Button btnAddAdherent;

	@FXML
	private Button btnListeEmploye;

	@FXML
	private Button btnAddEmploye;

	@FXML
	private AnchorPane mainWindow;
	
	private Timer timer;

	@FXML
	public void initialize() {

		try {
			loadFXML("Accueil");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Masquer certains boutons et labels au démarrage de l'application
		initializeButtons();
	}

	public void setMain(Main main) {
		this.main = main;
	}

	@FXML
	void handleButtonClick(ActionEvent event) {
		
		Button sourceButton = (Button) event.getSource();
		String buttonName = sourceButton.getText();

		try {
			switch (buttonName) {
			case "Connexion":
				loadFXML("ConnexionForm");
				break;
			case "Historique":
				if (SessionManager.getCurrentUser() != null) {
					loadFXML("ListeAdherents");
				} else {
					loadFXML("ListeAdherents");
				}
				break;
			case "Liste des adhérents":
				loadFXML("ListeAdherents");
				break;
			case "Ajouter un Adhérent":
				loadFXML("AjoutAdherent");
				break;
			case "Deconnexion":
				SessionManager.closeSession();
				loadFXML("Accueil");
				break;

			default:
				// code block
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Réagir à l'événement de clic de bouton ici
		System.out.println("Le bouton " + buttonName + " a été cliqué !");
	}

	public void loadFXML(String fxml) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/ihm/" + fxml + ".fxml"));
		Parent autreContent = fxmlLoader.load();
		mainWindow.getChildren().setAll(autreContent);
		startTimerToShowButtonsOrNot();
	}

	public void showButtons() {

		User user = SessionManager.getCurrentUser();

		if (user != null) {
			btnConnexion.setVisible(false);
			btnConnexion.setManaged(false);
			btnDeconnexion.setVisible(true);
			btnDeconnexion.setManaged(true);

			if (user.getRole().matches("adherent")) {

				btnProfil.setVisible(true);
				btnProfil.setManaged(true);
				btnHistorique.setVisible(true);
				btnHistorique.setManaged(true);

			} else {

				btnAddJeu.setVisible(true);
				btnAddJeu.setManaged(true);
				btnRendreJeu.setVisible(true);
				btnRendreJeu.setManaged(true);
				btnListeAdherent.setVisible(true);
				btnListeAdherent.setManaged(true);
				btnAddAdherent.setVisible(true);
				btnAddAdherent.setManaged(true);

				if (user.getRole().matches("admin")) {

					labelAdmin.setVisible(true);
					labelAdmin.setManaged(true);
					btnListeEmploye.setVisible(true);
					btnListeEmploye.setManaged(true);
					btnAddEmploye.setVisible(true);
					btnAddEmploye.setManaged(true);
				}

			}
		}

	}

	private void initializeButtons() {
		btnConnexion.setVisible(true);
		btnConnexion.setManaged(true);
		btnProfil.setVisible(false);
		btnProfil.setManaged(false);
		btnDeconnexion.setVisible(false);
		btnDeconnexion.setManaged(false);
		btnHistorique.setVisible(false);
		btnHistorique.setManaged(false);
		btnAddJeu.setVisible(false);
		btnAddJeu.setManaged(false);
		btnRendreJeu.setVisible(false);
		btnRendreJeu.setManaged(false);
		labelAdmin.setVisible(false);
		labelAdmin.setManaged(false);
		btnListeAdherent.setVisible(false);
		btnListeAdherent.setManaged(false);
		btnAddAdherent.setVisible(false);
		btnAddAdherent.setManaged(false);
		btnListeEmploye.setVisible(false);
		btnListeEmploye.setManaged(false);
		btnAddEmploye.setVisible(false);
		btnAddEmploye.setManaged(false);
	}
	
	private void startTimerToShowButtonsOrNot() {

        timer = new Timer();
        
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (SessionManager.getCurrentUser()!=null) {
                    showButtons();
                    timer.cancel();
                } else {
                	initializeButtons();
                }
            }
        }, 0, 1000);
    
	}

}