package controller;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javafx.beans.property.SimpleStringProperty;
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
import model.Emprunt;
import model.EmpruntDAO;

public class HistoriqueEmpruntController extends PageInit {

	@FXML
	private TextField searchField;
	@FXML
	private TableView<Emprunt> empruntList;
	@FXML
	private TableColumn<Emprunt, String> nomAdherent;
	@FXML
	private TableColumn<Emprunt, String> prenomAdherent;
	@FXML
	private TableColumn<Emprunt, String> nomJeu;
	@FXML
	private TableColumn<Emprunt, String> dateEmprunt;
	@FXML
	private TableColumn<Emprunt, String> dateRendu;

	private ObservableList<Emprunt> empruntData = FXCollections.observableArrayList();

	public HistoriqueEmpruntController() {
		super();
		this.empruntData = getEmpruntDataEmprunt();

	}

	public ObservableList<Emprunt> getEmpruntData() {
		return empruntData;
	}

	@FXML
	private void initialize() {

		setAnchors();

		nomAdherent.setCellValueFactory(new Callback<CellDataFeatures<Emprunt, String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<Emprunt, String> cellData) {
				return new SimpleStringProperty(cellData.getValue().getAdherent().getNom());
			}
		});
		prenomAdherent.setCellValueFactory(new Callback<CellDataFeatures<Emprunt, String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<Emprunt, String> cellData) {
				return new SimpleStringProperty(cellData.getValue().getAdherent().getPrenom());
			}
		});

		nomJeu.setCellValueFactory(new Callback<CellDataFeatures<Emprunt, String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<Emprunt, String> cellData) {
				return new SimpleStringProperty(cellData.getValue().getJeu().getNom());
			}
		});
		dateEmprunt.setCellValueFactory(new Callback<CellDataFeatures<Emprunt, String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<Emprunt, String> cellData) {
				Timestamp empruntDate = cellData.getValue().getDateEmprunt();
				LocalDateTime localDateTime = empruntDate.toLocalDateTime();
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
				String formattedInscription = localDateTime.format(formatter);
				return new SimpleStringProperty(formattedInscription);
			}
		});

		dateRendu
				.setCellValueFactory(new Callback<CellDataFeatures<Emprunt, String>, ObservableValue<String>>() {
					@Override
					public ObservableValue<String> call(CellDataFeatures<Emprunt, String> cellData) {
						Timestamp inscription = cellData.getValue().getDateARendre();
						LocalDateTime localDateTime = inscription.toLocalDateTime();
						DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
						String formattedInscription = localDateTime.format(formatter);
						return new SimpleStringProperty(formattedInscription);
					}
				});

		empruntList.setItems(this.getEmpruntData());
		
		searchField.textProperty().addListener((observable, oldValue, newValue) -> filterEmprunt(newValue));
	}

	public ObservableList<Emprunt> getEmpruntDataEmprunt() {
		empruntData = FXCollections.observableArrayList();
		List<Emprunt> lesEmprunts = EmpruntDAO.getInstance().readFullHisto();
		for (Emprunt emprunt : lesEmprunts) {
			empruntData.add(emprunt);
		}
		return empruntData;
	}

	@FXML
	public void toAddAdherent(ActionEvent event) {
//		try {
//			loadOtherFXML("AjoutAdherent");
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
	}

//	private Adherent getAdherent() {
//		return adherentList.getSelectionModel().getSelectedItem();
//	}

	@FXML
	private void deleteAdherent() {
//		if (getAdherent() != null) {
//			try {
//				boolean confirmation = demanderConfirmation("Supprimer l'adhérent ?");
//				if (confirmation) {
//					AdherentDAO.getInstance().delete(getAdherent());
//					afficherMessage("Adhérent supprimé avec succès");
//					loadOtherFXML("ListeAdherents");
//				}
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		} else {
//			afficherMessage("Sélectionnez dans la liste l'adhérent à supprimer.");
//		}
	}

	@FXML
	public void toUpdateAdherent(ActionEvent event) {
//		if (getAdherent() != null) {
//			try {
//				Adherent adherent = getAdherent();
////			if (adherent != null && SessionManager.getCurrentUser() != null
////					&& (MainController.isAdmin() || MainController.isPersonnel())) {
//				loadUpdateAdherentFXML("UpdateAdherent", adherent);
////			}
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		} else {
//			afficherMessage("Sélectionnez dans la liste l'adhérent à modifier.");
//		}
	}
	
	public void filterEmprunt(String searchText) {
		ObservableList<Emprunt> filteredList = FXCollections.observableArrayList();
		if (searchText == null || searchText.isEmpty()) {
			filteredList.setAll(empruntData);
		} else {
			String lowerCaseSearchText = searchText.toLowerCase();
			for (Emprunt emprunt : empruntData) {
				if (emprunt.getAdherent().getNom().toLowerCase().contains(lowerCaseSearchText) ||
						emprunt.getAdherent().getPrenom().toLowerCase().contains(lowerCaseSearchText) ||
						emprunt.getJeu().getNom().toLowerCase().contains(lowerCaseSearchText)) {
					filteredList.add(emprunt);
				}
			}
		}
		empruntList.setItems(filteredList);
	}

}
