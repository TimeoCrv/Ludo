package controller;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import model.Personnel;
import model.PersonnelDAO;

public class ModifierPersonnelController {
    @FXML
    private TextField nom;
    @FXML
    private TextField prenom;
    @FXML
    private TextField adresse;
    @FXML
    private TextField telephone;
    @FXML
    private TextField email;

    private Personnel personnelToUpdate;

    public void populateFields(Personnel personnel) {
        personnelToUpdate = personnel;
        nom.setText(personnel.getNom());
        prenom.setText(personnel.getPrenom());
        adresse.setText(personnel.getAdresse());
        telephone.setText(personnel.getTel());
        email.setText(personnel.getEmail());
    }

    @FXML
    private void modifierPersonnel() {
        // Retrieve modified details from the text fields
        String newNom = nom.getText();
        String newPrenom = prenom.getText();
        String newAdresse = adresse.getText();
        String newTelephone = telephone.getText();
        String newEmail = email.getText();

        // Update the personnel member with the modified details
        personnelToUpdate.setNom(newNom);
        personnelToUpdate.setPrenom(newPrenom);
        personnelToUpdate.setAdresse(newAdresse);
        personnelToUpdate.setTel(newTelephone);
        personnelToUpdate.setEmail(newEmail);

        // Call the update method in your PersonnelDAO to persist the changes
        boolean updateSuccess = PersonnelDAO.getInstance().update(personnelToUpdate);

        if (updateSuccess) {
            // Handle successful update
        } else {
            // Handle update failure
        }
    }
}