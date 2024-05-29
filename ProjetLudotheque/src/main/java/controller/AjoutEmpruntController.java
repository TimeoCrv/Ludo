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
import javafx.util.Callback;
import model.Adherent;
import model.AdherentDAO;
import model.Jeu;
import model.JeuDAO;


public class AjoutEmpruntController extends PageInit {
	
	@FXML
	private TableView<Adherent> adherentList;
	@FXML
	private TableColumn<Adherent, String> nomAdherent;
	
	@FXML
	private TableView<Jeu> jeuList;
	@FXML
	private TableColumn<Jeu, String> nomJeu;
	
	private ObservableList<Adherent> adherentData = FXCollections.observableArrayList();
	private ObservableList<Jeu> jeuData = FXCollections.observableArrayList();

	public AjoutEmpruntController() {
		super();
		this.adherentData = getAdherentDataAdherent();
		this.jeuData= getJeuDataJeu();

	}

	public ObservableList<Adherent> getAdherentData() {
		return adherentData;
	}
	
	public ObservableList<Jeu> getJeuData() {
		return jeuData;
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
		
		nomJeu.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Jeu, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Jeu, String> cellData) {
                return new SimpleStringProperty(cellData.getValue().getNom());
            }
        });
		

		/*
		 * adresse.setCellValueFactory(new Callback<CellDataFeatures<Personnel, String>,
		 * ObservableValue<String>>() {
		 * 
		 * @Override public ObservableValue<String> call(CellDataFeatures<Personnel,
		 * String> cellData) { return new
		 * SimpleStringProperty(cellData.getValue().getAdresse()); } });
		 */

		adherentList.setItems(this.getAdherentData());
		jeuList.setItems(this.getJeuData());
	}
	
	public ObservableList<Adherent> getAdherentDataAdherent() {
		adherentData = FXCollections.observableArrayList();
		List<Adherent> lesAdherent = AdherentDAO.getInstance().readAllAdherent();
		for (Adherent adherent : lesAdherent) {
			adherentData.add(adherent);
		}
		return adherentData;
	}
	
	public ObservableList<Jeu> getJeuDataJeu() {
		jeuData = FXCollections.observableArrayList();
		List<Jeu> lesJeux = JeuDAO.getInstance().readTable();
		for (Jeu jeu : lesJeux) {
			jeuData.add(jeu);
		}
		return jeuData;
	}
}
