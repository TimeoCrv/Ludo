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
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;
import model.Adherent;
import model.AdherentDAO;

public class ListeAdherents extends PageInit {

	@FXML
	private TableView<Adherent> adherentList;
	@FXML
	private TableColumn<Adherent, String> nomAdherent;
	@FXML
	private TableColumn<Adherent, String> prenomAdherent;
	@FXML
	private TableColumn<Adherent, String> emailAdherent;
	@FXML
	private TableColumn<Adherent, String> tel;
	
	private ObservableList<Adherent> adherentData = FXCollections.observableArrayList();
	
	public ListeAdherents() {
		super();
		this.adherentData = getAdherentDataAdherent();
		
	}
	
	public ObservableList<Adherent> getAdherentData() {
		return adherentData;
	}
	
	@FXML
	private void initialize() {
		
		setAnchors();
		
		nomAdherent.setCellValueFactory(new Callback<CellDataFeatures<Adherent, String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<Adherent, String> cellData) {
				return new SimpleStringProperty(cellData.getValue().getNom());
			}
		});
		prenomAdherent.setCellValueFactory(new Callback<CellDataFeatures<Adherent, String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<Adherent, String> cellData) {
				return new SimpleStringProperty(cellData.getValue().getPrenom());
			}
		});
		
		emailAdherent.setCellValueFactory(new Callback<CellDataFeatures<Adherent, String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<Adherent, String> cellData) {
				return new SimpleStringProperty(cellData.getValue().getEmail());
			}
		});
		tel.setCellValueFactory(new Callback<CellDataFeatures<Adherent, String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<Adherent, String> cellData) {
				return new SimpleStringProperty(cellData.getValue().getTel());
			}
		});
		
		adherentList.setItems(this.getAdherentData());
	}
	
	public ObservableList<Adherent> getAdherentDataAdherent() {
		adherentData = FXCollections.observableArrayList();
		List<Adherent> lesAdherents = AdherentDAO.getInstance().readAllAdherent();
		for (Adherent adherent : lesAdherents) {
			adherentData.add(adherent);
		}
		return adherentData;
	}
	
	
	//TEST
	

	public void loadAutrePage() {
		try {
			loadOtherFXML("testConnexionAdherent");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}