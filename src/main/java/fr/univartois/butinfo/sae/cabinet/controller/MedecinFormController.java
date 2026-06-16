package fr.univartois.butinfo.sae.cabinet.controller;

import fr.univartois.butinfo.sae.cabinet.enumeration.Specialite;
import fr.univartois.butinfo.sae.cabinet.modele.domaine.Medecin;
import fr.univartois.butinfo.sae.cabinet.modele.service.GestionMedecins;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.Scene;

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
    private ComboBox<Specialite> specialiteComboBox; // Aligné sur fx:id="specialiteComboBox"

    private GestionMedecins gestionMedecins;
    private Stage stage;
    private Scene sceneListe;
    private ListView<Medecin> listeMedecins;
    private Medecin medecinAModifier;

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

    @FXML
    public void initialize() {
        // Remplit la ComboBox avec les valeurs de l'énumération Specialite
        specialiteComboBox.getItems().setAll(Specialite.values());
    }

    public void setMedecinAModifier(Medecin medecin) {
        this.medecinAModifier = medecin;
        
        if (medecin != null) {
            nomField.setText(medecin.getNom());
            prenomField.setText(medecin.getPrenom());
            telephoneField.setText(medecin.getTelephone());
            emailField.setText(medecin.getEmail());
            numeroOrdreField.setText(medecin.getNumeroOrdre());
            numeroOrdreField.setDisable(true); // Empêche de modifier le numéro d'ordre unique
            tarifField.setText(String.valueOf(medecin.getTarifHoraire()));
            specialiteComboBox.setValue(medecin.getSpecialite());
        }
    }

    /**
     * Action du bouton "Valider" - Aligné sur onAction="#valider"
     */
    @FXML
    private void valider(ActionEvent event) {
        String nom = nomField.getText();
        String prenom = prenomField.getText();
        String tel = telephoneField.getText();
        String email = emailField.getText();
        String numOrdre = numeroOrdreField.getText();
        Specialite spec = specialiteComboBox.getValue();
        double tarif = Double.parseDouble(tarifField.getText());

        if (medecinAModifier == null) {
            // Création d'un nouveau médecin
            Medecin nouveau = new Medecin(nom, prenom, tel, email, numOrdre, spec, tarif);
            gestionMedecins.ajouterMedecin(nouveau);
        } else {
            // Modification du médecin existant
            gestionMedecins.mettreAJourMedecin(medecinAModifier.getId(), nom, prenom, tarif, tel, email, spec);
        }

        // Met à jour l'affichage de la liste
        listeMedecins.refresh();

        // Retour à la liste
        stage.setScene(sceneListe);
    }

    /**
     * Action du bouton "Annuler" - Aligné sur onAction="#annuler"
     */
    @FXML
    private void annuler(ActionEvent event) {
        stage.setScene(sceneListe);
    }
}