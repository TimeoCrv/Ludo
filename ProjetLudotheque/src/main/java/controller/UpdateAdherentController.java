package controller;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import model.Adherent;
import model.AdherentDAO;
import model.Connexion;
import utils.ValidatorManager;

public class UpdateAdherentController extends PageInit {

	private Adherent adherent;

	@FXML
	private TextField nom;

	@FXML
	private TextField prenom;

	@FXML
	private TextField telephone;

	@FXML
	private TextField adresse;

	@FXML
	private TextField email;

	@FXML
	private TextField noCNI;

	@FXML
	private TextArea observations;

	@FXML
	public void initialize() {
		setAnchors();
	}

	public void setAdherent(Adherent adherent) {
		this.adherent = adherent;
		if (this.adherent != null) {
			initializeFields();
		}
	}

	private void initializeFields() {
		nom.setText(this.adherent.getNom());
		prenom.setText(this.adherent.getPrenom());
		telephone.setText(this.adherent.getTel());
		adresse.setText(this.adherent.getAdresse());
		email.setText(this.adherent.getEmail());
		noCNI.setText(this.adherent.getNoCNI());
		observations.setText(this.adherent.getObservations());
	}

	@FXML
	public void validateUpdate(ActionEvent event) {

		try {

			String emailSaisi = email.getText();
			if (!ValidatorManager.isValidEmail(emailSaisi)) {
				afficherMessage("E-mail invalide");
			} else if (ValidatorManager.isAdherentEmailInBD(emailSaisi)
					&& !emailSaisi.equals(this.adherent.getEmail())) {
				afficherMessage("Cet e-mail est déjà utilisé");
			} else {
				String nomSaisi = nom.getText();
				String prenomSaisi = prenom.getText();
				String telephoneSaisi = telephone.getText();
				String adresseSaisi = adresse.getText();
				String noCNISaisi = noCNI.getText();
				String observationsSaisi = observations.getText();

				if (!nomSaisi.isBlank() && !prenomSaisi.isBlank() && !telephoneSaisi.isBlank()
						&& !adresseSaisi.isBlank() && !emailSaisi.isBlank() && !noCNISaisi.isBlank()) {
					boolean confirmation = demanderConfirmation("Modifier l'adhérent ?");
					if (confirmation) {
						this.adherent.setNom(nomSaisi);
						this.adherent.setPrenom(prenomSaisi);
						this.adherent.setAdresse(adresseSaisi);
						this.adherent.setTel(telephoneSaisi);
						this.adherent.setEmail(emailSaisi);
						this.adherent.setNoCNI(noCNISaisi);
						this.adherent.setObservations(observationsSaisi);

						Connexion.getInstance();

						AdherentDAO.getInstance().update(this.adherent);

						afficherMessage("Adhérent modifié avec succès");
						loadOtherFXML("ListeAdherents");
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
	public void renewSubscription(ActionEvent event) {

		try {

			// Calcul des dates d'inscription
			Date startDate = new Date();
			Calendar cal = Calendar.getInstance();
			cal.setTime(startDate);
			cal.add(Calendar.YEAR, 1);
			Date endDate = cal.getTime();

			boolean confirmation = demanderConfirmation("Confirmer le renouvellement de l'adhésion ?");
			if (confirmation) {
				this.adherent.setDateInscription(new Timestamp(startDate.getTime()));
				this.adherent.setDateInscriptionFin(new Timestamp(endDate.getTime()));

				Connexion.getInstance();

				AdherentDAO.getInstance().update(this.adherent);

				afficherMessage("Adhésion renouvelé avec succès");
				loadOtherFXML("ListeAdherents");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
