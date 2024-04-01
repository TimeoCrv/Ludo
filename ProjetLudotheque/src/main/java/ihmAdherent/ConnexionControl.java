package ihmAdherent;

import java.io.IOException;

import ihm.IhmControl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;

public class ConnexionControl extends IhmControl{
	
	@FXML
    private PasswordField passwordField;

//	public ConnexionControl() {  	
//	}
//
//	@FXML
//	private void initialize() {
//	}
//
//	public void setMainApp(AdherentGUI adherentApp) {
//		super.setMainApp(adherentApp); 
//	}

	@FXML
	public void userConnexion(ActionEvent event) {
		
		String motDePasseSaisi = passwordField.getText(); 
	    
	    String motDePasseAttendu = "sio";
	    if (motDePasseSaisi.equals(motDePasseAttendu)) {
	  
		try {
            // Charger la scène accueil.fxml
            FXMLLoader loader = new FXMLLoader(getClass().getResource("accueilAdherent.fxml"));
            Parent accueilRoot = loader.load();
            
            // Récupérer la fenêtre principale (stage)
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow(); // Récupérer la scène du bouton
            
            stage.setScene(new Scene(accueilRoot));
            stage.setTitle("Accueil");
        } catch (IOException e) {
            e.printStackTrace();
        }
	    } else {
	        System.out.println("mdp erroné");
	    }
	}

}

