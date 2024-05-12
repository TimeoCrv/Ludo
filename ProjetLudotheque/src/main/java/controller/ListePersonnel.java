package controller;

import java.io.IOException;
import java.util.List;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.util.Callback;
import model.Personnel;
import model.PersonnelDAO;

public class ListePersonnel extends PageInit {

	@FXML
	private TableView<Personnel> personnelList;
	@FXML
	private TableColumn<Personnel, String> nomPersonnel;
	@FXML
	private TableColumn<Personnel, String> prenomPersonnel;
	@FXML
	private TableColumn<Personnel, String> emailPersonnel;
	@FXML
	private TableColumn<Personnel, String> tel;
	
	
	private ObservableList<Personnel> personnelData = FXCollections.observableArrayList();
	
	public ListePersonnel() {
		super();
		this.personnelData = getPersonnelDataPersonnel();
		
	}
	
	public ObservableList<Personnel> getPersonnelData() {
		return personnelData;
	}
	
	@FXML
	private void initialize() {
		
		setAnchors();
		
		nomPersonnel.setCellValueFactory(new Callback<CellDataFeatures<Personnel, String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<Personnel, String> cellData) {
				return new SimpleStringProperty(cellData.getValue().getNom());
			}
		});
		prenomPersonnel.setCellValueFactory(new Callback<CellDataFeatures<Personnel, String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<Personnel, String> cellData) {
				return new SimpleStringProperty(cellData.getValue().getPrenom());
			}
		});
		
		emailPersonnel.setCellValueFactory(new Callback<CellDataFeatures<Personnel, String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<Personnel, String> cellData) {
				return new SimpleStringProperty(cellData.getValue().getEmail());
			}
		});
		tel.setCellValueFactory(new Callback<CellDataFeatures<Personnel, String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<Personnel, String> cellData) {
				return new SimpleStringProperty(cellData.getValue().getTel());
			}
		});
		
		personnelList.setItems(this.getPersonnelData());
	}
	
	public ObservableList<Personnel> getPersonnelDataPersonnel() {
		personnelData = FXCollections.observableArrayList();
		List<Personnel> lePersonnel = PersonnelDAO.getInstance().readAllPersonnel();
		for (Personnel personnel : lePersonnel) {
			personnelData.add(personnel);
		}
		return personnelData;
	}
	
	
		@FXML
		private Personnel getIndex() {
			return personnelList.getSelectionModel().getSelectedItem();
		}
	
	@FXML
	private void supprimerPersonnel() {
		PersonnelDAO.getInstance().delete(getIndex());
	}
	@FXML
	private void modifierPersonnel(){
	    try {

	        loadOtherFXML("modifierPersonnel");

	        
	
	    } catch (IOException e) {
	        e.printStackTrace();
	        // Handle the exception
	    }
	}
	
	
	

	public void loadAutrePage() {
		try {
			loadOtherFXML("testConnexionAdherent");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
