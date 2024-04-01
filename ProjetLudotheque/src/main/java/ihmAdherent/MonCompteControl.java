package ihmAdherent;

import java.util.List;

import ihm.IhmControl;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.util.Callback;
import ludo.Adherent;
import modele.dao.AdherentDAO;

public class MonCompteControl extends IhmControl{
	
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
	
	public MonCompteControl() {
		super();
		this.adherentData = getAdherentDataAdherent();
		
	}
	
	public ObservableList<Adherent> getAdherentData() {
		return adherentData;
	}
	
	@FXML
	private void initialize() {
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
				return new SimpleStringProperty(cellData.getValue().getMail());
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
		List<Adherent> lesAdherents = AdherentDAO.getInstance().readTable();
		for (Adherent adherent : lesAdherents) {
			adherentData.add(adherent);
		}
		return adherentData;
	}

}

