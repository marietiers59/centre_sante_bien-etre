package fr.univartois.butinfo.sae.cabinet.controller;

import fr.univartois.butinfo.sae.cabinet.modele.service.CabinetFacade;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class AccueilController {

    private CabinetFacade facade;
    private Scene sceneAccueil;

    public void setModel(CabinetFacade facade, Scene sceneAccueil) {
        this.facade = facade;
        this.sceneAccueil = sceneAccueil;
    }

    @FXML
    private void ouvrirGestionMedicaments(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/view/medicaments-view.fxml")
            );
            if (loader.getLocation() == null) {
                System.err.println("ERREUR : /view/medicaments-view.fxml introuvable !");
                return;
            }
            Parent root = loader.load();
            MedicamentController controller = loader.getController();
            controller.setModel(facade.getGestionMedicaments());
            controller.setSceneAccueil(sceneAccueil);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root, 800, 600));

        } catch (IOException e) {
            System.err.println("Erreur chargement médicaments : " + e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    private void ouvrirGestionMedecins(ActionEvent event) {
        try {
            // 1. Chargement du fichier FXML (Vérifie bien qu'il est dans src/main/resources/view/medecins-view.fxml)
            FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/view/medecins-view.fxml")
            );
            if (loader.getLocation() == null) {
                System.err.println("ERREUR : /view/medecins-view.fxml introuvable !");
                return;
            }
            Parent root = loader.load();
            
            // 2. Configuration du contrôleur de la vue médecin
            MedecinsController controller = loader.getController();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            
            // On injecte le gestionnaire et la scène d'accueil pour le bouton Retour
            controller.setGestionMedecins(facade.getGestionMedecins());
            controller.setStageEtScene(stage, sceneAccueil);

            // 3. Affichage de la nouvelle scène
            stage.setScene(new Scene(root, 800, 600));

        } catch (IOException e) {
            System.err.println("Erreur chargement médecins : " + e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    private void ouvrirGestionRendezVous(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/view/gestionRDV-view.fxml")
            );
            if (loader.getLocation() == null) {
                System.err.println("ERREUR : /view/gestionRDV-view.fxml introuvable !");
                return;
            }
            Parent root = loader.load();
            
            // Récupération et injection complète des services dans le RDVController fusionné
            RDVController controller = loader.getController();
            controller.setModel(
                facade.getGestionRendezVous(),
                facade.getGestionPatients(),
                facade.getGestionMedecins()
            );
            controller.setSceneAccueil(sceneAccueil);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root, 800, 600));

        } catch (IOException e) {
            System.err.println("Erreur chargement RDV : " + e.getMessage());
            e.printStackTrace();
        }
    }
}