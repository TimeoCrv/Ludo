package controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.util.Callback;
import model.Adherent;
import model.AdherentDAO;

public class ListeAdherentsController extends PageInit {

	@FXML
	private TextField searchField;
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
	@FXML
	private TableColumn<Adherent, String> dateInscription;

	private ObservableList<Adherent> adherentData = FXCollections.observableArrayList();

	public ListeAdherentsController() {
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

		dateInscription
				.setCellValueFactory(new Callback<CellDataFeatures<Adherent, String>, ObservableValue<String>>() {
					@Override
					public ObservableValue<String> call(CellDataFeatures<Adherent, String> cellData) {
						Timestamp inscription = cellData.getValue().getDateInscription();
						LocalDateTime localDateTime = inscription.toLocalDateTime();
						DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
						String formattedInscription = localDateTime.format(formatter);
						return new SimpleStringProperty(formattedInscription);
					}
				});

		adherentList.setItems(this.getAdherentData());

		//Champs de recherche qui filtre par rapport au nom
		searchField.textProperty().addListener((observable, oldValue, newValue) -> filterAdherent(newValue));

		// Même fonction 
//		searchField.textProperty().addListener(new ChangeListener<String>() {
//			@Override
//			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
//				filterAdherent(newValue);
//			}
//		});
	}

	public ObservableList<Adherent> getAdherentDataAdherent() {
		adherentData = FXCollections.observableArrayList();
		List<Adherent> lesAdherents = AdherentDAO.getInstance().readAllAdherent();
		for (Adherent adherent : lesAdherents) {
			adherentData.add(adherent);
		}
		return adherentData;
	}

	@FXML
	public void toAddAdherent(ActionEvent event) {
		try {
			loadOtherFXML("AjoutAdherent");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private Adherent getAdherent() {
		return adherentList.getSelectionModel().getSelectedItem();
	}

	@FXML
	private void deleteAdherent() {
		if (getAdherent() != null) {
			try {
				boolean confirmation = demanderConfirmation("Supprimer l'adhérent ?");
				if (confirmation) {
					AdherentDAO.getInstance().delete(getAdherent());
					afficherMessage("Adhérent supprimé avec succès");
					loadOtherFXML("ListeAdherents");
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			afficherMessage("Sélectionnez dans la liste l'adhérent à supprimer.");
		}
	}

	@FXML
	public void toUpdateAdherent(ActionEvent event) {
		if (getAdherent() != null) {
			try {
				Adherent adherent = getAdherent();
//			if (adherent != null && SessionManager.getCurrentUser() != null
//					&& (MainController.isAdmin() || MainController.isPersonnel())) {
				loadUpdateAdherentFXML("UpdateAdherent", adherent);
//			}
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			afficherMessage("Sélectionnez dans la liste l'adhérent à modifier.");
		}
	}

	public void filterAdherent(String searchText) {
		ObservableList<Adherent> filteredList = FXCollections.observableArrayList();
		if (searchText == null || searchText.isEmpty()) {
			filteredList.setAll(adherentData);
		} else {
			String lowerCaseSearchText = searchText.toLowerCase();
			for (Adherent adherent : adherentData) {
				if (adherent.getNom().toLowerCase().contains(lowerCaseSearchText)) {
					filteredList.add(adherent);
				}
			}
		}
		adherentList.setItems(filteredList);
	}
}