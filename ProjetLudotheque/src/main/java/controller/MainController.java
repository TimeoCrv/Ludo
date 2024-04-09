package controller;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class MainController extends Application {

	private static Scene scene;
	
	private String currentView = "listeJeu";
	
	@FXML
    private Label accountButton;
	
    @Override
    public void start(Stage stage) throws IOException {
    	Parent root = loadFXML("mainLayout");
    	scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
        
        loadViewInContainer(currentView);
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainController.class.getResource("/ihm/" + fxml + ".fxml"));
        return fxmlLoader.load();
    }
    
    public static void loadViewInContainer(String fxml) throws IOException {
        StackPane mainContent = (StackPane) scene.getRoot().lookup("#StackPane");
            
        FXMLLoader fxmlLoader = new FXMLLoader(MainController.class.getResource("/ihm/" + fxml + ".fxml"));
        Node otherContent = fxmlLoader.load();
            
        mainContent.getChildren().clear();
        mainContent.getChildren().add(otherContent);
    }

    public static void loadViewInOverlay(String fxml) throws IOException {
        StackPane mainContent = (StackPane) scene.getRoot().lookup("#StackPane");
            
        FXMLLoader fxmlLoader = new FXMLLoader(MainController.class.getResource("/ihm/" + fxml + ".fxml"));
        Node otherContent = fxmlLoader.load();
            
        mainContent.getChildren().add(otherContent);
        
    }
    
    public static void main(String[] args) {
        launch(args);
    }
    
    @FXML
    private void handleConnexionButton() {
    	try {
    		currentView = "connexion";
    		
			MainController.loadViewInOverlay(currentView);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    @FXML
    private void closeOverlay() {
    	StackPane mainContent = (StackPane) scene.getRoot().lookup("#StackPane");
    	mainContent.getChildren().clear();
    }
}
