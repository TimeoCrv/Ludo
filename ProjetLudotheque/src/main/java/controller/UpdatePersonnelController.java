package controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import model.Connexion;
import model.Personnel;
import utils.ValidatorManager;
import model.PersonnelDAO;


public class UpdatePersonnelController extends PageInit {

	private Personnel personnel;

	@FXML
	private TextField nom;

	@FXML
	private TextField prenom;
	
	@FXML
	private TextField email;

	@FXML
	private TextField telephone;

	@FXML
	private TextField adresse;
	
	@FXML
    private CheckBox isAdmin;


	@FXML
	public void initialize() {
		setAnchors();
	}

	public void setPersonnel(Personnel personnel) {
		this.personnel = personnel;
		if (this.personnel != null) {
			initializeFields();
		}
	}

	private void initializeFields() {
		nom.setText(this.personnel.getNom());
		prenom.setText(this.personnel.getPrenom());
		email.setText(this.personnel.getEmail());
		telephone.setText(this.personnel.getTel());
		adresse.setText(this.personnel.getAdresse());
		isAdmin.setSelected(this.personnel.isAdmin());
	}

	@FXML
	public void validateUpdate(ActionEvent event) {

		try {

			String emailSaisi = email.getText();
			if (!ValidatorManager.isValidEmail(emailSaisi)) {
				afficherMessage("E-mail invalide");
			} else if (ValidatorManager.isPersonnelEmailInBD(emailSaisi)
					&& !emailSaisi.equals(this.personnel.getEmail())) {
				afficherMessage("Cet e-mail est déjà utilisé");
			} else {
				String nomSaisi = nom.getText();
				String prenomSaisi = prenom.getText();
				String telephoneSaisi = telephone.getText();
				String adresseSaisi = adresse.getText();
				boolean isAdminSaisi = isAdmin.isSelected();
				String role = isAdminSaisi ? "admin":"personnel";

				if (!nomSaisi.isBlank() && !prenomSaisi.isBlank() && !telephoneSaisi.isBlank()
						&& !adresseSaisi.isBlank() && !emailSaisi.isBlank()) {
					boolean confirmation = demanderConfirmation("Modifier le membre du personnel ?");
					if (confirmation) {
						this.personnel.setNom(nomSaisi);
						this.personnel.setPrenom(prenomSaisi);
						this.personnel.setEmail(emailSaisi);
						this.personnel.setTel(telephoneSaisi);
						this.personnel.setAdresse(adresseSaisi);
						this.personnel.setAdmin(isAdminSaisi);
						this.personnel.setRole(role);

						Connexion.getInstance();

						PersonnelDAO.getInstance().update(this.personnel);

						afficherMessage("Membre du personnel modifié avec succès");
						loadOtherFXML("ListePersonnel");
					}
				} else {
					afficherMessage("Veuillez remplir tous les champs");
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	public void backListePersonnel(ActionEvent event) {
	  
		try {
			loadOtherFXML("ListePersonnel");
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
}