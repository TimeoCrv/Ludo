package controller;

import java.util.Date;
import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Emprunt;

public class  HistoriqueEmprunteController extends PageInit {
	
	@FXML
    private TableView<Emprunt> empruntList;

    @FXML
    private TableColumn<Emprunt, String> nom;

    @FXML
    private TableColumn<Emprunt, Date> dateEmprunter;

    @FXML
    private TableColumn<Emprunt, Date> dateRendre;

    @FXML
    public void initialize() {
        nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        dateEmprunter.setCellValueFactory(new PropertyValueFactory<>("dateEmprunt"));
        dateRendre.setCellValueFactory(new PropertyValueFactory<>("dateARendre"));
    }

    public void setEmprunts(List<Emprunt> emprunts) {
        empruntList.getItems().setAll(emprunts);
    }
}


