package fr.univartois.butinfo.sae.cabinet.controleur;

import fr.univartois.butinfo.sae.cabinet.modele.domaine.Medecin;
import fr.univartois.butinfo.sae.cabinet.modele.service.GestionMedecins;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

public class MedecinsController {

    @FXML
    private ListView<Medecin> listeMedecins;

    @FXML
    private Label nomLabel;

    @FXML
    private Label prenomLabel;

    @FXML
    private Label specialiteLabel;

    @FXML
    private Label telephoneLabel;

    @FXML
    private Label emailLabel;

    @FXML
    private Label tarifLabel;

    @FXML
    private Button ajouterButton;

    @FXML
    private Button modifierButton;

    @FXML
    private Button supprimerButton;

    private GestionMedecins gestionMedecins;

    private Stage stage;

    private Scene sceneListe;

    public void setStageEtScene(Stage stage, Scene sceneListe) {
        this.stage = stage;
        this.sceneListe = sceneListe;
    }

    public void setGestionMedecins(GestionMedecins gestionMedecins) {
        this.gestionMedecins = gestionMedecins;

        listeMedecins.setItems(gestionMedecins.listerTousLesMedecins());

        listeMedecins.setCellFactory(list -> new ListCell<>() {
            @Override
            protected void updateItem(Medecin medecin, boolean empty) {
                super.updateItem(medecin, empty);

                if (empty || medecin == null) {
                    setText(null);
                } else {
                    setText("Dr " + medecin.getNom() + " " + medecin.getPrenom());
                }
            }
        });
    }

    @FXML
    private void initialize() {
        listeMedecins.getSelectionModel()
                .selectedItemProperty()
                .addListener((observable, ancienMedecin, nouveauMedecin) -> {
                    afficherDetails(nouveauMedecin);
                });
    }

    private void afficherDetails(Medecin medecin) {
        if (medecin == null) {
            nomLabel.setText("");
            prenomLabel.setText("");
            specialiteLabel.setText("");
            telephoneLabel.setText("");
            emailLabel.setText("");
            tarifLabel.setText("");
            return;
        }

        nomLabel.setText(medecin.getNom());
        prenomLabel.setText(medecin.getPrenom());
        specialiteLabel.setText(medecin.getSpecialite().getLibelle());
        telephoneLabel.setText(medecin.getTelephone());
        emailLabel.setText(medecin.getEmail());
        tarifLabel.setText(medecin.getTarifHoraire() + " €");
    }

    @FXML
    private void supprimerMedecin() {
        Medecin medecin = listeMedecins.getSelectionModel().getSelectedItem();

        if (medecin != null) {
            gestionMedecins.supprimerMedecin(medecin.getId());
            afficherDetails(null);
        }
    }

    @FXML
    private void ajouterMedecin() throws IOException {
        ouvrirFormulaire(null);
    }

    @FXML
    private void modifierMedecin() throws IOException {
        Medecin medecin = listeMedecins.getSelectionModel().getSelectedItem();

        if (medecin != null) {
            ouvrirFormulaire(medecin);
        }
    }

    private void ouvrirFormulaire(Medecin medecin) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/medecin-form.fxml"));
        Scene sceneFormulaire = new Scene(loader.load());

        MedecinFormController controller = loader.getController();
        controller.setGestionMedecins(gestionMedecins);
        controller.setStageEtScene(stage, sceneListe);
        controller.setListeMedecins(listeMedecins);
        controller.setMedecinAModifier(medecin);

        stage.setScene(sceneFormulaire);
    }

}