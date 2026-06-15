package fr.univartois.butinfo.sae.cabinet.modele.service;

import fr.univartois.butinfo.sae.cabinet.modele.domaine.LignePrescription;
import fr.univartois.butinfo.sae.cabinet.modele.domaine.Medecin;
import fr.univartois.butinfo.sae.cabinet.modele.domaine.Medicament;
import fr.univartois.butinfo.sae.cabinet.modele.domaine.Ordonnance;
import fr.univartois.butinfo.sae.cabinet.modele.domaine.Patient;
import java.time.LocalDate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Façade permettant de gérer les ordonnances médicales.
 */
public class GestionOrdonnances {

    private final ObservableList<Ordonnance> ordonnances;

    public GestionOrdonnances() {
        this.ordonnances = FXCollections.observableArrayList();
    }

    public Ordonnance creerOrdonnance(Medecin medecin, Patient patient,
            LocalDate dateValidite, String remarques) {

        Ordonnance ordo = new Ordonnance(medecin, patient, dateValidite, remarques);
        ordonnances.add(ordo);

        System.out.println("Ordonnance créée (ID=" + ordo.getId() + ") pour "
                + patient.getNomComplet() + " par Dr " + medecin.getNomComplet());

        return ordo;
    }

    public void ajouterPrescription(int ordonnanceId, Medicament medicament, int quantite,
            String posologie, int dureeJours, String instructions) {

        Ordonnance ordo = rechercherParId(ordonnanceId);

        if (ordo == null) {
            System.out.println("ERREUR : Ordonnance ID=" + ordonnanceId + " introuvable.");
            return;
        }

        LignePrescription ligne = new LignePrescription(
                medicament, quantite, posologie, dureeJours, instructions);

        ordo.ajouterPrescription(ligne);

        System.out.println("Prescription ajoutée : " + medicament.getNom()
                + " → ordonnance ID=" + ordonnanceId);
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