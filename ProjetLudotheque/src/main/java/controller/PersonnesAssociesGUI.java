package controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class PersonnesAssociesGUI extends PageInit {


	public void start(Stage primaryStage) throws Exception {
		// Charger le fichier FXML
		FXMLLoader loader = new FXMLLoader(getClass().getResource("../ihm/personnel.fxml"));
		Parent root = loader.load();

		// Créer une scène avec le contenu chargé depuis le FXML
		Scene scene = new Scene(root);

		// Définir la scène sur la fenêtre principale
		primaryStage.setScene(scene);
		primaryStage.setTitle("Personnel");
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

}
