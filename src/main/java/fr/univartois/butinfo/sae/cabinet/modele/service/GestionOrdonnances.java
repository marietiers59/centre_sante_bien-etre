package fr.univartois.butinfo.sae.cabinet.modele.service;

import fr.univartois.butinfo.sae.cabinet.modele.domaine.LignePrescription;
import fr.univartois.butinfo.sae.cabinet.modele.domaine.Medecin;
import fr.univartois.butinfo.sae.cabinet.modele.domaine.Medicament;
import fr.univartois.butinfo.sae.cabinet.modele.domaine.Ordonnance;
import fr.univartois.butinfo.sae.cabinet.modele.domaine.Patient;
import java.time.LocalDate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Service de gestion des ordonnances médicales.
 */
public class GestionOrdonnances {

    private static final Logger LOGGER = LoggerFactory.getLogger(GestionOrdonnances.class);

    private final ObservableList<Ordonnance> ordonnances;

    public GestionOrdonnances() {
        this.ordonnances = FXCollections.observableArrayList();
    }

    // -------------------------
    // Gestion des ordonnances
    // -------------------------

    /**
     * Crée une nouvelle ordonnance vide (sans prescriptions) et l'enregistre.
     */
    public Ordonnance creerOrdonnance(Medecin medecin, Patient patient,
            LocalDate dateValidite, String remarques) {
        Ordonnance ordo = new Ordonnance(medecin, patient, dateValidite, remarques);
        ordonnances.add(ordo);
        LOGGER.info("Ordonnance créée (ID=" + ordo.getId() + ") pour " +
                patient.getNomComplet() + " par Dr " + medecin.getNomComplet());
        return ordo;
    }

    public void ajouterPrescription(int ordonnanceId, Medicament medicament, int quantite,
                                     String posologie, int dureeJours, String instructions) {
        Ordonnance ordo = rechercherParId(ordonnanceId);

        if (ordo == null) {
            LOGGER.error("ERREUR : Ordonnance ID=" + ordonnanceId + " introuvable.");
            return;
        }

        LignePrescription ligne = new LignePrescription(
                medicament, quantite, posologie, dureeJours, instructions);
        ordo.ajouterPrescription(ligne);
        LOGGER.info("Prescription ajoutée : " + medicament.getNom() +
                " → ordonnance ID=" + ordonnanceId);
    }

    public Ordonnance rechercherParId(int id) {
        for (Ordonnance o : ordonnances) {
            if (o.getId() == id) {
                return o;
            }
        }
        return null;
    }

    public ObservableList<Ordonnance> getOrdonnancesPatient(Patient patient) {
        ObservableList<Ordonnance> resultats = FXCollections.observableArrayList();

        for (Ordonnance o : ordonnances) {
            if (o.getPatient().equals(patient)) {
                resultats.add(o);
            }
        }

        return resultats;
    }

    public ObservableList<Ordonnance> getOrdonnancesMedecin(Medecin medecin) {
        ObservableList<Ordonnance> resultats = FXCollections.observableArrayList();

        for (Ordonnance o : ordonnances) {
            if (o.getMedecin().equals(medecin)) {
                resultats.add(o);
            }
        }

        return resultats;
    }

    public ObservableList<Ordonnance> getOrdonnancesValides() {
        ObservableList<Ordonnance> resultats = FXCollections.observableArrayList();

        for (Ordonnance o : ordonnances) {
            if (o.estValide()) {
                resultats.add(o);
            }
        }

        return resultats;
    }

    public ObservableList<Ordonnance> listerToutesLesOrdonnances() {
        return ordonnances;
    }

    public ObservableList<Ordonnance> getOrdonnances() {
        return ordonnances;
    }

    public int getNombreOrdonnances() {
        return ordonnances.size();
    }
}