package controller;

import java.util.List;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.util.Callback;
import model.PersonneAssocie;
import model.PersonneAssocieDAO;

public class PersonneAssocieControl extends PageInit{
	
	@FXML
	private TableView<PersonneAssocie> personneAssocieList;
	@FXML
	private TableColumn<PersonneAssocie, String> nomPersonneAssocie;
	@FXML
	private TableColumn<PersonneAssocie, String> prenomPersonneAssocie;
	@FXML
	private TableColumn<PersonneAssocie, String> emailPersonneAssocie;
	@FXML
	private TableColumn<PersonneAssocie, String> tel;
	@FXML
    private TextField idAdherent;
	
	
	private ObservableList<PersonneAssocie> PersonneAssocieData = FXCollections.observableArrayList();
	
	public PersonneAssocieControl(String searchText) {
		super();
		this.PersonneAssocieData = getPersonneAssocieDataPersonneAssocie(searchText);
		
	}
	 public PersonneAssocieControl() {
	        super();
	        this.PersonneAssocieData = FXCollections.observableArrayList();
	    }
	
	public ObservableList<PersonneAssocie> getPersonneAssocieData() {
		return PersonneAssocieData;
	}
	
	@FXML
	public void initialize() {
		nomPersonneAssocie.setCellValueFactory(new Callback<CellDataFeatures<PersonneAssocie, String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<PersonneAssocie, String> cellData) {
				return new SimpleStringProperty(cellData.getValue().getNom());
			}
		});
		prenomPersonneAssocie.setCellValueFactory(new Callback<CellDataFeatures<PersonneAssocie, String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<PersonneAssocie, String> cellData) {
				return new SimpleStringProperty(cellData.getValue().getPrenom());
			}
		});
		
		emailPersonneAssocie.setCellValueFactory(new Callback<CellDataFeatures<PersonneAssocie, String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<PersonneAssocie, String> cellData) {
				return new SimpleStringProperty(cellData.getValue().getEmail());
			}
		});
		tel.setCellValueFactory(new Callback<CellDataFeatures<PersonneAssocie, String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<PersonneAssocie, String> cellData) {
				return new SimpleStringProperty(cellData.getValue().getTel());
			}
		});
		
		personneAssocieList.setItems(this.getPersonneAssocieData());
	}
	
	public ObservableList<PersonneAssocie> getPersonneAssocieDataPersonneAssocie(String searchText) {
		PersonneAssocieData = FXCollections.observableArrayList();
		List<PersonneAssocie> lesPersonnesAssocies = PersonneAssocieDAO.getInstance().readTable();
		for (PersonneAssocie personneAssocie : lesPersonnesAssocies) {
			PersonneAssocieData.add(personneAssocie);
		}
		return PersonneAssocieData;
	}
	
	/*@FXML
	private void rechercherPersonneAssocieClicked() {
	    String searchText = idAdherent.getText();
	    ObservableList<PersonneAssocie> filteredData = getPersonneAssocieDataPersonneAssocie(searchText);
	    personneAssocieList.setItems(filteredData);
	}*/
	

}

