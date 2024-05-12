package controller;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Adherent;
import utils.SessionManager;

// Classe héritée par toutes les IHM
// Regroupe les fonctions communes à toutes

import utils.SessionManager;


public abstract class PageInit {


	@FXML
	private AnchorPane childWindow;

	protected void setAnchors() {

		SessionManager.startSessionTimer(); // équivalent session_start()

		AnchorPane.setTopAnchor(childWindow, 0.0);
		AnchorPane.setBottomAnchor(childWindow, 0.0);
		AnchorPane.setLeftAnchor(childWindow, 0.0);
		AnchorPane.setRightAnchor(childWindow, 0.0);

	}
	
	// Fonctions qui peuvent créer et fermer des modal à décommenter si besoin

    //protected void setAnchors() {
    	
    	//SessionManager.startSessionTimer();
        
       // AnchorPane.setTopAnchor(childWindow, 0.0);
       // AnchorPane.setBottomAnchor(childWindow, 0.0);
       // AnchorPane.setLeftAnchor(childWindow, 0.0);
       // AnchorPane.setRightAnchor(childWindow, 0.0);
        
   // }



//    protected void loadModal(String fxml, String title) throws IOException {
//    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/ihm/" + fxml + ".fxml"));
//        Parent popupRoot = loader.load();
//        Scene popupScene = new Scene(popupRoot);
//
//        // Création de la fenêtre de pop-up
//        Stage popupStage = new Stage();
//        popupStage.initModality(Modality.APPLICATION_MODAL);
//        popupStage.setTitle(title);
//        popupStage.setScene(popupScene);
//        popupStage.show();
//        
//    }

//	protected Object loadModal(String fxml, String title) throws IOException {
//		FXMLLoader loader = new FXMLLoader(getClass().getResource("/ihm/" + fxml + ".fxml"));
//		Parent popupRoot = loader.load();
//		Scene popupScene = new Scene(popupRoot);
//
//		// Création de la fenêtre de pop-up
//		Stage popupStage = new Stage();
//		popupStage.initModality(Modality.APPLICATION_MODAL);
//		popupStage.setTitle(title);
//		popupStage.setScene(popupScene);
//		popupStage.show();
//
//		// Retourne le contrôleur de la fenêtre pop-up
//		return loader.getController();
//	}
//
//	protected void closePopup() {
//		Stage stage = (Stage) childWindow.getScene().getWindow();
//		stage.close();
//	}
	
	
	
	// Fonctions no modal
	
	// Fonction de chargement d'une vue
	public void loadOtherFXML(String fxml) throws IOException {
		AnchorPane content = FXMLLoader.load(getClass().getResource("/ihm/" + fxml + ".fxml"));
		childWindow.getChildren().setAll(content);
	}
	
	// Fonction de chargement d'une vue qui nécessite un objet adhérent
	// (Besoin pour pouvoir aller sur la page de modification à partir de la liste des adhérents
	// en conservant les données de l'adhérent sélectionné)
	// Elle n'a pas sa place ici dans l'état actuel car elle ne sert qu'à UpdateAdherentController
	// En faisant implémenter une interface les controleurs de vue où il y a du update,
	// on pourrait tout rassembler ici et ce serait à mettre à jour avec les différents controleur de vue avec update
	public void loadUpdateAdherentFXML(String fxml, Adherent adherent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ihm/" + fxml + ".fxml"));
        Parent content = loader.load();
        Parent popupRoot = loader.load();
        Scene popupScene = new Scene(popupRoot);
        
        


        UpdateAdherentController updateController = loader.getController();
        updateController.setAdherent(adherent);


        childWindow.getChildren().setAll(content);
    }


	// Petits pop-up de confirmation
	// Pop-up confirmation d'action (actuellement utilisé comme ça ; utilisation étendue possible)
	protected boolean demanderConfirmation(String message) {
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setTitle("Confirmation");
		alert.setHeaderText(null);
		alert.setContentText(message);

		ButtonType confirmerButton = new ButtonType("Confirmer");
		ButtonType annulerButton = new ButtonType("Annuler");

		alert.getButtonTypes().setAll(confirmerButton, annulerButton);

		return alert.showAndWait().get() == confirmerButton;
	}

	// Pop-up d'information d'action effectué (actuellement utilisé comme ça ; utilisation étendue possible)
	protected void afficherMessage(String message) {
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("Information");
		alert.setHeaderText(null);
		alert.setContentText(message);
		alert.showAndWait();
	}}

        
