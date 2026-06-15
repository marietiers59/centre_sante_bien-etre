package fr.univartois.butinfo.sae.cabinet.controller;

import java.time.LocalDate;
import java.util.List;

import fr.univartois.butinfo.sae.cabinet.modele.domaine.RendezVous;
import fr.univartois.butinfo.sae.cabinet.modele.service.GestionRendezVous;
import javafx.fxml.FXML;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;

/**
 * Contrôleur JavaFX de la vue {@code gestionRDV-view.fxml}.
 *
 * <p>Lorsque l'utilisateur sélectionne une date dans le {@link DatePicker}, le contrôleur
 * interroge le service {@link GestionRendezVous} et affiche, dans la partie droite du
 * {@code SplitPane}, une {@link ListView} par rendez-vous trouvé ce jour-là. Toutes ces
 * listes sont empilées verticalement dans un {@code VBox} encapsulé dans un
 * {@code ScrollPane}, ce qui permet de les faire défiler sans limite.</p>
 */
public class RDVController {

    /** Hauteur en pixels d'une ligne dans une {@link ListView} (valeur par défaut JavaFX). */
    private static final int HAUTEUR_LIGNE = 24;

    /** Marge verticale interne appliquée à chaque {@link ListView} pour éviter la barre de défilement. */
    private static final int MARGE_LISTE = 4;

    /** Sélecteur de date permettant de filtrer les rendez-vous. */
    @FXML
    private DatePicker datePicker;

    /** Sélecteur de couleur (usage futur : mise en évidence des rendez-vous). */
    @FXML
    private ColorPicker colorPicker;

    /**
     * Conteneur vertical dans lequel une {@link ListView} est insérée pour chaque
     * rendez-vous correspondant à la date sélectionnée.
     */
    @FXML
    private VBox conteneurRDV;

    /** Service métier de gestion des rendez-vous. */
    private GestionRendezVous gestionRdv;

    /**
     * Initialise le contrôleur après le chargement du fichier FXML.
     *
     * <p>Crée une instance locale de {@link GestionRendezVous} (remplaçable via
     * {@link #setGestionRendezVous(GestionRendezVous)}) et branche un écouteur sur la
     * propriété {@code value} du {@link DatePicker} afin de rafraîchir l'affichage à
     * chaque changement de date.</p>
     */
    @FXML
    void initialize() {
        assert datePicker  != null : "fx:id=\"datePicker\" non injecté — vérifiez gestionRDV-view.fxml";
        assert colorPicker != null : "fx:id=\"colorPicker\" non injecté — vérifiez gestionRDV-view.fxml";
        assert conteneurRDV != null : "fx:id=\"conteneurRDV\" non injecté — vérifiez gestionRDV-view.fxml";

        gestionRdv = new GestionRendezVous();

        datePicker.valueProperty().addListener((obs, ancienneDate, nouvelleDate) -> {
            if (nouvelleDate != null) {
                afficherRDVPourDate(nouvelleDate);
            }
        });
    }

    /**
     * Injecte le service de gestion des rendez-vous partagé par l'application.
     *
     * <p>À appeler depuis la classe {@code Application} JavaFX après le chargement du FXML,
     * afin que le contrôleur travaille sur la même liste de rendez-vous que le reste de
     * l'application. Si une date est déjà sélectionnée, l'affichage est immédiatement
     * rafraîchi.</p>
     *
     * @param gestionRdv le service à utiliser, ne doit pas être {@code null}
     */
    public void setGestionRendezVous(GestionRendezVous gestionRdv) {
        this.gestionRdv = gestionRdv;
        LocalDate dateSelectionnee = datePicker.getValue();
        if (dateSelectionnee != null) {
            afficherRDVPourDate(dateSelectionnee);
        }
    }

    /**
     * Vide le conteneur puis insère une {@link ListView} par rendez-vous planifié à la
     * date indiquée.
     *
     * <p>Si aucun rendez-vous n'est trouvé, un {@link Label} informatif est affiché à la
     * place.</p>
     *
     * @param date la date sélectionnée dans le {@link DatePicker}
     */
    private void afficherRDVPourDate(LocalDate date) {
        conteneurRDV.getChildren().clear();

        List<RendezVous> rdvDuJour = gestionRdv.getRendezVousParDate(date);

        if (rdvDuJour.isEmpty()) {
            conteneurRDV.getChildren().add(new Label("Aucun rendez-vous pour le " + date));
            return;
        }

        for (RendezVous rdv : rdvDuJour) {
            conteneurRDV.getChildren().add(creerListeRDV(rdv));
        }
    }

    /**
     * Construit une {@link ListView} affichant les informations complètes d'un rendez-vous.
     *
     * <p>La hauteur de la liste est calculée dynamiquement en fonction du nombre
     * d'éléments, de façon à ce que la liste s'affiche entièrement sans barre de défilement
     * interne. C'est le {@code ScrollPane} parent qui assure le défilement global.</p>
     *
     * @param rdv le rendez-vous dont les informations doivent être affichées
     * @return une {@link ListView} peuplée et dimensionnée pour ce rendez-vous
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
}
