package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

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
    public void initialize() {
        // Masquer certains boutons et labels au démarrage de l'application
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
    
    public void setMain(Main main) {
        this.main = main;
    }
    
    @FXML
    void handleButtonClick(ActionEvent event) {
    	Button sourceButton = (Button) event.getSource();
        String buttonName = sourceButton.getText();

        // Réagir à l'événement de clic de bouton ici
        System.out.println("Le bouton " + buttonName + " a été cliqué !");
    }
    
    @FXML
    public void onBtnConnexionClicked() {
    	btnConnexion.setVisible(!btnConnexion.isVisible());
        btnConnexion.setManaged(btnConnexion.isVisible());
        
        btnDeconnexion.setVisible(!btnDeconnexion.isVisible());
        btnDeconnexion.setManaged(btnDeconnexion.isVisible());
        
        btnProfil.setVisible(!btnProfil.isVisible());
        btnProfil.setManaged(btnProfil.isVisible());
        
        btnHistorique.setVisible(!btnHistorique.isVisible());
        btnHistorique.setManaged(btnHistorique.isVisible());
    }
}