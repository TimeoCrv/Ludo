package controller;

import java.io.IOException;
import java.util.List;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.util.Callback;
import model.Jeu;
import model.JeuDAO;
import utils.SessionManager;

public class ListeJeuxController extends PageInit {

	@FXML
	private TableView<Jeu> jeuList;
	@FXML
	private TableColumn<Jeu, String> nomJeu;
	@FXML
	private TableColumn<Jeu, String> descriptifJeu;
	@FXML
	private TableColumn<Jeu, String> editeurJeu;
	@FXML
	private TableColumn<Jeu, Boolean> disponibleJeu;
	@FXML
	private TableColumn<Jeu, Integer> nombreJeu;
	@FXML
	private Button ajouterJeu;
	@FXML
	private Button modifierJeu;
	@FXML
	private Button supprimerJeu;

	private ObservableList<Jeu> jeuData = FXCollections.observableArrayList();

	public ListeJeuxController() {
		super();
		this.jeuData = getJeuDataJeu();

	}

	public ObservableList<Jeu> getJeuData() {
		return jeuData;
	}

	@FXML
	private void initialize() {
		setAnchors();

		if (SessionManager.getCurrentUser() != null && (MainController.isAdmin() || MainController.isPersonnel())) {
			ajouterJeu.setVisible(true);
			modifierJeu.setVisible(true);
			supprimerJeu.setVisible(true);
		} else {
			ajouterJeu.setVisible(false);
			modifierJeu.setVisible(false);
			supprimerJeu.setVisible(false);
		}

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

		disponibleJeu.setCellValueFactory(new Callback<CellDataFeatures<Jeu, Boolean>, ObservableValue<Boolean>>() {
			@Override
			public ObservableValue<Boolean> call(CellDataFeatures<Jeu, Boolean> cellData) {
				SimpleBooleanProperty booleanProperty = new SimpleBooleanProperty(cellData.getValue().getDisponible());
				return booleanProperty.asObject();
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
	private Jeu getJeu() {
		return jeuList.getSelectionModel().getSelectedItem();
	}

	@FXML
	private void supprimerUnJeu() {
		if (getJeu() != null) {
			try {
				boolean confirmation = demanderConfirmation("Supprimer le jeu ?");
				if (confirmation) {
					JeuDAO.getInstance().delete(getJeu());
					afficherMessage("Jeu supprimé avec succès");
					loadOtherFXML("ListeJeux");
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			afficherMessage("Sélectionnez dans la liste le jeu à supprimer.");
		}
	}

	@FXML
	public void toAddJeu(ActionEvent event) {
		try {
			if (SessionManager.getCurrentUser() != null && (MainController.isAdmin() || MainController.isPersonnel())) {
				loadOtherFXML("AjoutJeu");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	public void toUpdateJeu(ActionEvent event) {
		if (getJeu() != null) {
			try {
				Jeu jeu = getJeu();
				if (jeu != null && SessionManager.getCurrentUser() != null
						&& (MainController.isAdmin() || MainController.isPersonnel())) {
					loadUpdateJeuFXML("UpdateJeu", jeu);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			afficherMessage("Sélectionnez dans la liste le jeu à modifier.");
		}
	}
}
