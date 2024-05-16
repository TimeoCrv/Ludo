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
import utils.SessionManager;

public abstract class PageInit {

	@FXML
	private AnchorPane childWindow;

	protected void setAnchors() {

		SessionManager.startSessionTimer();

		AnchorPane.setTopAnchor(childWindow, 0.0);
		AnchorPane.setBottomAnchor(childWindow, 0.0);
		AnchorPane.setLeftAnchor(childWindow, 0.0);
		AnchorPane.setRightAnchor(childWindow, 0.0);

	}

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

	protected Object loadModal(String fxml, String title) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/ihm/" + fxml + ".fxml"));
		Parent popupRoot = loader.load();
		Scene popupScene = new Scene(popupRoot);

		// Création de la fenêtre de pop-up
		Stage popupStage = new Stage();
		popupStage.initModality(Modality.APPLICATION_MODAL);
		popupStage.setTitle(title);
		popupStage.setScene(popupScene);
		popupStage.show();

		// Retourne le contrôleur de la fenêtre pop-up
		return loader.getController();
	}

	public void loadOtherFXML(String fxml) throws IOException {
		AnchorPane content = FXMLLoader.load(getClass().getResource("/ihm/" + fxml + ".fxml"));
		childWindow.getChildren().setAll(content);
	}

	protected void closePopup() {
		Stage stage = (Stage) childWindow.getScene().getWindow();
		stage.close();
	}

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

	protected void afficherMessage(String message) {
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("Information");
		alert.setHeaderText(null);
		alert.setContentText(message);
		alert.showAndWait();
	}
}
