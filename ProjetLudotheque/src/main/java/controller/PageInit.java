package controller;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public abstract class PageInit {
	
	@FXML
	private AnchorPane childWindow;

    protected void setAnchors() {
        
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
}
