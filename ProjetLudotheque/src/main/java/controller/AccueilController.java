package controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class AccueilController extends PageInit {

	@FXML
	private ImageView logo;

	public void initialize() {
		setAnchors();

		Image image = new Image(getClass().getResourceAsStream("/ihm/Img/BettonLudique-64.png"));

		logo.setImage(image);
	}

	@FXML
	public void toConnexion(ActionEvent event) {
		try {
			loadOtherFXML("ConnexionForm");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@FXML
	public void toGameList(ActionEvent event) {
		try {
			// Remplacer par liste jeu
			loadOtherFXML("ListeJeux");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
