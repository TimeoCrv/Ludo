package controller;


import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.text.Font;
import javafx.stage.Stage;

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
    }
  
    public static void main(String[] args) {
        launch(args);
    }
   
}
