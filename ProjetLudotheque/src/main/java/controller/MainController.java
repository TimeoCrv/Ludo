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
	
	
	// Atrribut timer qui sert dans la fonction startTimerToShowButtonsOrNot()
	// Sert pour l'instant à donner l'illusion d'une connexion et déconnexion instantanée
	// A voir pour rendre tout ça instantané par le création de fonction statique
	private Timer timer;

	@FXML
	public void initialize() {

		
		try {
			loadFXML("Accueil"); // Chargement d'une vue au lancement de l'application
		} catch (IOException e) {
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
			// Sorte de routeur des différents boutons du menu du côté
			switch (buttonName) {
			case "Connexion":
				loadFXML("ConnexionForm");
				break;
			// Historique : à but de test pour l'instant
			// Quand les emprunts seront implémentés, historique différent entre adhérent et personnel
			case "Historique":
				if (SessionManager.getCurrentUser() != null) {
					loadFXML("ListeAdherents");
				} else {
					loadFXML("ListeAdherents");
				}
				break;
			// Affichage des adhérents que si on est admin ou personnel
			case "Liste des adhérents":
				if (SessionManager.getCurrentUser() != null && (MainController.isAdmin() || MainController.isPersonnel())) {
					loadFXML("ListeAdherents");
				}
				break;
			case "Ajouter un Adhérent":
				if (SessionManager.getCurrentUser() != null && (MainController.isAdmin() || MainController.isPersonnel())){
					loadFXML("AjoutAdherent");
				}
				break;
			case "Ajouter un Employé":
				if (SessionManager.getCurrentUser() != null && MainController.isAdmin()) {
					loadFXML("AjoutPersonnel");
				}
				break;
			case "Liste des employés":
				if (SessionManager.getCurrentUser() != null && (MainController.isAdmin() || MainController.isPersonnel())) {
					loadFXML("ListePersonnel");
				}
				break;
			case "Tous les jeux":
				loadFXML("ListeJeux");
				break;
			case "Ajouter un jeu":
				loadFXML("AjoutJeu");
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

	
	// Fonction de chargement d'une vue par le routeur
	public void loadFXML(String fxml) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/ihm/" + fxml + ".fxml"));
		Parent autreContent = fxmlLoader.load();
		mainWindow.getChildren().setAll(autreContent);
		startTimerToShowButtonsOrNot();
	}

	// Fonctions qui gère l'affichage des boutons du menu principal suivant le rôle de l'utilisateur
	public void showButtons() {

		User user = SessionManager.getCurrentUser();

		if (user != null) {
			btnConnexion.setVisible(false);
			btnConnexion.setManaged(false);
			btnDeconnexion.setVisible(true);
			btnDeconnexion.setManaged(true);
			btnHistorique.setVisible(true);
			btnHistorique.setManaged(true);

			if (user.getRole().matches("adherent")) {

				btnProfil.setVisible(true);
				btnProfil.setManaged(true);

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

	// Fonction d'initialisation des boutons au lancement de l'application ou à la déconnexion
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
		btnAddEmploye.setManaged(false);}
	
	// Fonction qui donne l'illusion de la connexion et déconnexion instantanée
	// Elle vérifie si une session est créée avec un utilisateur défini pour lancer la fonction showButtons()
	// A améliorer pour éviter le timer
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
        }, 0, 1000);}
    

	// A la session créée, l'utilisateur étant défini, on vérifie son rôle pour divers affichages dans l'application
	// A ajouter isAdherent()
	// Ecriture en ternaire que je trouve plus simple lorsqu'on a des résultats de conditions if qui ne sont pas longues
	// A tester si SessionManager.getCurrentUser().getRole().matches(...) est suffisant
	public static boolean isPersonnel() {
		return SessionManager.getCurrentUser()!=null ? SessionManager.getCurrentUser().getRole().matches("personnel") : false;}



	public static boolean isAdmin() {
		return SessionManager.getCurrentUser()!=null ? SessionManager.getCurrentUser().getRole().matches("admin") : false;
	}
	
	public static boolean isAdherent() {
		return SessionManager.getCurrentUser()!=null ? SessionManager.getCurrentUser().getRole().matches("adherent") : false;
	}

}

