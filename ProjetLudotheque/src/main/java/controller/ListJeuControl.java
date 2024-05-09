package controller;

import java.util.List;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.util.Callback;
import model.Jeu;
import model.JeuDAO;

public class ListJeuControl extends PageInit {
	
	@FXML
	private TableView<Jeu> jeuList;
	@FXML
	private TableColumn<Jeu, String> nomJeu;
	@FXML
	private TableColumn<Jeu, String> descriptifJeu;
	@FXML
	private TableColumn<Jeu, String> editeurJeu;
	@FXML
	private TableColumn<Jeu, Integer> disponibleJeu;
	@FXML
	private TableColumn<Jeu, Integer> nombreJeu;
	
	private ObservableList<Jeu> jeuData = FXCollections.observableArrayList();
	
	public ListJeuControl() {
		super();
		this.jeuData = getJeuDataJeu();
		
	}
	
	public ObservableList<Jeu> getJeuData() {
		return jeuData;
	}
	
	
	@FXML
	private void start() {
		System.out.println("oui");
		nomJeu.setCellValueFactory(new Callback<CellDataFeatures<Jeu, String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<Jeu, String> cellData) {
				return new SimpleStringProperty(cellData.getValue().getNom());
			}
		});
		descriptifJeu.setCellValueFactory(new Callback<CellDataFeatures<Jeu, String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<Jeu, String> cellData) {
				return new SimpleStringProperty(cellData.getValue().getDescriptif());
			}
		});
		
		editeurJeu.setCellValueFactory(new Callback<CellDataFeatures<Jeu, String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<Jeu, String> cellData) {
				return new SimpleStringProperty(cellData.getValue().getEditeur());
			}
		});
		
		disponibleJeu.setCellValueFactory(new Callback<CellDataFeatures<Jeu, Integer>, ObservableValue<Integer>>() {
		    @Override
		    public ObservableValue<Integer> call(CellDataFeatures<Jeu, Integer> cellData) {
		        return new SimpleObjectProperty<>(cellData.getValue().getDisponible());
		    }
		});
		
		nombreJeu.setCellValueFactory(new Callback<CellDataFeatures<Jeu, Integer>, ObservableValue<Integer>>() {
		    @Override
		    public ObservableValue<Integer> call(CellDataFeatures<Jeu, Integer> cellData) {
		        return new SimpleObjectProperty<>(cellData.getValue().getNombre());
		    }
		});
		
		
		
		jeuList.setItems(this.getJeuData());
	}
	
	public ObservableList<Jeu> getJeuDataJeu() {
		jeuData = FXCollections.observableArrayList();
		List<Jeu> lesJeux = JeuDAO.getInstance().readTable();
		for (Jeu jeu : lesJeux) {
			jeuData.add(jeu);
		}
		return jeuData;
	}
	
	@FXML
	private Jeu getIndex() {
		return jeuList.getSelectionModel().getSelectedItem();
	}
	
	@FXML
	private void supprimerUnJeu() {
		JeuDAO.getInstance().delete(getIndex());
	}

	
}
