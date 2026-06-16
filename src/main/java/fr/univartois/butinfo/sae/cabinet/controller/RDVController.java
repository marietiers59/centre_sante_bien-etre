package fr.univartois.butinfo.sae.cabinet.controller;

import java.time.LocalDate;
import java.util.List;

import fr.univartois.butinfo.sae.cabinet.modele.domaine.RendezVous;
import fr.univartois.butinfo.sae.cabinet.modele.service.GestionMedecins;
import fr.univartois.butinfo.sae.cabinet.modele.service.GestionPatients;
import fr.univartois.butinfo.sae.cabinet.modele.service.GestionRendezVous;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Contrôleur JavaFX de la vue {@code gestionRDV-view.fxml} fusionné et adapté.
 */
public class RDVController {

    private static final int HAUTEUR_LIGNE = 24;
    private static final int MARGE_LISTE = 4;

    @FXML private DatePicker datePicker;
    @FXML private ColorPicker colorPicker;
    
    /** * Le conteneur vertical présent dans ton FXML pour empiler les rendez-vous.
     * (Vérifie bien que son fx:id est "conteneurRDV" dans SceneBuilder)
     */
    @FXML private VBox conteneurRDV;

    private GestionRendezVous gestionRendezVous;
    private Scene sceneAccueil;

    @FXML
    void initialize() {
        assert datePicker  != null : "fx:id=\"datePicker\" non injecté — vérifiez gestionRDV-view.fxml";
        assert colorPicker != null : "fx:id=\"colorPicker\" non injecté — vérifiez gestionRDV-view.fxml";
        assert conteneurRDV != null : "fx:id=\"conteneurRDV\" non injecté — vérifiez gestionRDV-view.fxml";

        // Écouteur sur le changement de date pour rafraîchir l'affichage
        datePicker.valueProperty().addListener((obs, ancienneDate, nouvelleDate) -> {
            if (nouvelleDate != null) {
                afficherRDVPourDate(nouvelleDate);
            }
        });
    }

    /**
     * Reçoit les services depuis l'AccueilController ou l'Application.
     */
    public void setModel(GestionRendezVous gestionRendezVous, 
                         GestionPatients gestionPatients, 
                         GestionMedecins gestionMedecins) { 
        this.gestionRendezVous = gestionRendezVous; 

        // Si une date est déjà sélectionnée au moment du chargement, on affiche les rendez-vous
        LocalDate dateSelectionnee = datePicker.getValue();
        if (dateSelectionnee != null) {
            afficherRDVPourDate(dateSelectionnee);
        }
    } 

    /**
     * Mémorise la scène d'accueil pour le bouton Retour.
     */
    public void setSceneAccueil(Scene sceneAccueil) {
        this.sceneAccueil = sceneAccueil;
    }

    /**
     * Action associée au bouton de retour vers le menu principal.
     */
    @FXML
    private void retournerAccueil(ActionEvent event) {
        if (sceneAccueil != null) {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(sceneAccueil);
        }
    }

    /**
     * Vide le conteneur puis insère une ListView par rendez-vous planifié à la date indiquée.
     */
    private void afficherRDVPourDate(LocalDate date) {
        conteneurRDV.getChildren().clear();

        if (gestionRendezVous == null) return;

        List<RendezVous> rdvDuJour = gestionRendezVous.getRendezVousParDate(date);

        if (rdvDuJour.isEmpty()) {
            conteneurRDV.getChildren().add(new Label("Aucun rendez-vous pour le " + date));
            return;
        }

        for (RendezVous rdv : rdvDuJour) {
            conteneurRDV.getChildren().add(creerListeRDV(rdv));
        }
    }

    /**
     * Construit une ListView affichant les informations complètes d'un rendez-vous.
     */
    private ListView<String> creerListeRDV(RendezVous rdv) {
        ListView<String> liste = new ListView<>();

        liste.getItems().addAll(
            "── Patient ──────────────────────────────────",
            "Identifiant      : " + rdv.getPatient().getId(),
            "Nom              : " + rdv.getPatient().getNom(),
            "Prénom           : " + rdv.getPatient().getPrenom(),
            "Date de naissance: " + rdv.getPatient().getDateNaissance(),
            "── Médecin ──────────────────────────────────",
            "Identifiant      : " + rdv.getMedecin().getId(),
            "Nom              : " + rdv.getMedecin().getNom(),
            "Prénom           : " + rdv.getMedecin().getPrenom(),
            "Spécialité       : " + rdv.getMedecin().getSpecialite().getLibelle(),
            "── Rendez-vous ──────────────────────────────",
            "Heure            : " + rdv.getDateHeure().toLocalTime(),
            "Durée            : " + rdv.getDureeMinutes() + " min",
            "Motif            : " + rdv.getMotif(),
            "Statut           : " + rdv.getStatut().getLibelle()
        );

        liste.setPrefHeight((double) liste.getItems().size() * HAUTEUR_LIGNE + MARGE_LISTE);

        return liste;
    }
    
    public void setGestionRendezVous(GestionRendezVous gestionRendezVous) {
        this.gestionRendezVous = gestionRendezVous;
    }
}