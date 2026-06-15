package fr.univartois.butinfo.sae.cabinet.controleur;

import fr.univartois.butinfo.sae.cabinet.enumeration.Specialite;
import fr.univartois.butinfo.sae.cabinet.modele.domaine.Medecin;
import fr.univartois.butinfo.sae.cabinet.modele.service.GestionMedecins;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class MedecinFormController {

    @FXML
    private TextField nomField;

    @FXML
    private TextField prenomField;

    @FXML
    private TextField telephoneField;

    @FXML
    private TextField emailField;

    @FXML
    private TextField numeroOrdreField;

    @FXML
    private TextField tarifField;

    @FXML
    private ComboBox<Specialite> specialiteComboBox;

    private GestionMedecins gestionMedecins;

    private Medecin medecinAModifier;

    private Stage stage;

    private Scene sceneListe;

    private ListView<Medecin> listeMedecins;

    @FXML
    private void initialize() {
        specialiteComboBox.getItems().addAll(Specialite.values());
    }

    public void setGestionMedecins(GestionMedecins gestionMedecins) {
        this.gestionMedecins = gestionMedecins;
    }

    public void setStageEtScene(Stage stage, Scene sceneListe) {
        this.stage = stage;
        this.sceneListe = sceneListe;
    }

    public void setListeMedecins(ListView<Medecin> listeMedecins) {
        this.listeMedecins = listeMedecins;
    }

    public void setMedecinAModifier(Medecin medecinAModifier) {
        this.medecinAModifier = medecinAModifier;

        if (medecinAModifier != null) {
            nomField.setText(medecinAModifier.getNom());
            prenomField.setText(medecinAModifier.getPrenom());
            telephoneField.setText(medecinAModifier.getTelephone());
            emailField.setText(medecinAModifier.getEmail());
            numeroOrdreField.setText(medecinAModifier.getNumeroOrdre());
            tarifField.setText(String.valueOf(medecinAModifier.getTarifHoraire()));
            specialiteComboBox.getSelectionModel().select(medecinAModifier.getSpecialite());

            numeroOrdreField.setDisable(true);
        }
    }

    @FXML
    private void valider() {
        String nom = nomField.getText();
        String prenom = prenomField.getText();
        String telephone = telephoneField.getText();
        String email = emailField.getText();
        String numeroOrdre = numeroOrdreField.getText();
        double tarif = Double.parseDouble(tarifField.getText());
        Specialite specialite = specialiteComboBox.getSelectionModel().getSelectedItem();

        if (medecinAModifier == null) {
            Medecin nouveauMedecin = new Medecin(
                    nom, prenom, telephone, email, numeroOrdre, specialite, tarif);

            gestionMedecins.ajouterMedecin(nouveauMedecin);
        } else {
            gestionMedecins.mettreAJourMedecin(
                    medecinAModifier.getId(),
                    nom,
                    prenom,
                    tarif,
                    telephone,
                    email,
                    specialite);

            listeMedecins.refresh();
        }

        stage.setScene(sceneListe);
    }

    @FXML
    private void annuler() {
        stage.setScene(sceneListe);
    }

}