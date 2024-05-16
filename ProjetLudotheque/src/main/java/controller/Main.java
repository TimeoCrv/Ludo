package controller;


import java.io.IOException;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import utils.SessionManager;

public class Main extends Application {

	private static Scene scene;
	
	@FXML
    private Label accountButton;
	
    @Override
    public void start(Stage stage) throws IOException {
    	Image icone = new Image(getClass().getResourceAsStream("/ihm/Img/BettonLudique-64.png"));
    	Font.loadFont(getClass().getResourceAsStream("/ihm/css/Englebert-Regular.ttf"), 12);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ihm/mainLayout.fxml"));
        Parent root = loader.load();

        MainController mainController = loader.getController();

        mainController.setMain(this);

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("BETTON LUDIQUE");
        stage.getIcons().add(icone);
        stage.show();
        
        // Fermeture par la croix
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			@Override
			public void handle(WindowEvent event) {
				event.consume();
				closeProperly();
			}
        });
        
    }
    
    private void closeProperly() {
    	if (SessionManager.getCurrentUser()!=null) SessionManager.closeSession();
		Platform.exit();
		System.exit(0);
	}
  
    public static void main(String[] args) {
        launch(args);
    }
   
}
