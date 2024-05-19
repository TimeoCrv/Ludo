package controller;

import java.io.IOException;
import java.util.List;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.util.Callback;
import model.Personnel;
import model.PersonnelDAO;

public class ListePersonnelController extends PageInit {

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
	@FXML
    private TableColumn<Personnel, Boolean> isAdmin;
	
	
	private ObservableList<Personnel> personnelData = FXCollections.observableArrayList();
	
	public ListePersonnelController() {
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
		
		isAdmin.setCellValueFactory(new Callback<CellDataFeatures<Personnel, Boolean>, ObservableValue<Boolean>>() {
            @Override
            public ObservableValue<Boolean> call(CellDataFeatures<Personnel, Boolean> cellData) {
                SimpleBooleanProperty booleanProperty = new SimpleBooleanProperty(cellData.getValue().isAdmin());
                return booleanProperty.asObject();
            }
        });
		
		/*adresse.setCellValueFactory(new Callback<CellDataFeatures<Personnel, String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<Personnel, String> cellData) {
				return new SimpleStringProperty(cellData.getValue().getAdresse());
			}
		});*/
		
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
		private Personnel getPersonnel() {
			return personnelList.getSelectionModel().getSelectedItem();
		}
	
	@FXML
	private void supprimerPersonnel() {
		try {
			boolean confirmation = demanderConfirmation("Supprimer le membre du personnel ?");
			if(confirmation) {
			PersonnelDAO.getInstance().delete(getPersonnel());
			loadOtherFXML("ListePersonnel");
			afficherMessage("Membre du personnel supprimé avec succès");
		}}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
    @FXML
    public void toUpdatePersonnel(ActionEvent event) {
        try {
            Personnel personnel = getPersonnel();
           // if (adherent != null && SessionManager.getCurrentUser() != null && (MainController.isAdmin() || MainController.isPersonnel())) {
                loadUpdatePersonnelFXML("UpdatePersonnel", personnel);
            //}
        } catch (IOException e) {
            e.printStackTrace();
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
