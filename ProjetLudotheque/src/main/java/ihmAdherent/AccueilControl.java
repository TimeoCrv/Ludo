package ihmAdherent;

import java.io.IOException;

import ihm.IhmControl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AccueilControl extends IhmControl{

	@FXML
	public void monCompte(ActionEvent event) {
	  
		try {
            // Charger la scène accueil.fxml
            FXMLLoader loader = new FXMLLoader(getClass().getResource("monCompte.fxml"));
            Parent accueilRoot = loader.load();
            
            // Récupérer la fenêtre principale (stage)
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow(); // Récupérer la scène du bouton
            
            stage.setScene(new Scene(accueilRoot));
            stage.setTitle("Mon Compte");
        } catch (IOException e) {
            e.printStackTrace();
        }
	}

}

