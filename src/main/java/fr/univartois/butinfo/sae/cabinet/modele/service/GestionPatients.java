package fr.univartois.butinfo.sae.cabinet.modele.service;

import fr.univartois.butinfo.sae.cabinet.modele.domaine.Patient;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Façade de gestion des patients.
 * Cette classe centralise les opérations CRUD sur les patients du cabinet médical.
 */
public class GestionPatients {

    // Liste interne des patients enregistrés
    /** Logger utilisé pour tracer les actions du service. */
    private static final Logger LOGGER = LoggerFactory.getLogger(GestionPatients.class);

    /** Liste observable des patients, utilisable directement par JavaFX. */
    private final ObservableList<Patient> patients;

    /**
     * Crée un service de gestion des patients avec une liste vide.
     */
    public GestionPatients() {
        this.patients = FXCollections.observableArrayList();
    }

    /**
     * Ajoute un patient si son numéro de sécurité sociale n'existe pas déjà.
     */
    public boolean ajouterPatient(Patient patient) {
        if (patient == null) {
            LOGGER.error("Impossible d'ajouter un patient null.");
            return false;
        }

        if (rechercherParNumeroSecu(patient.getNumeroSecu()) != null) {
            LOGGER.error("Un patient avec le numéro de sécurité sociale {} existe déjà.",
                    patient.getNumeroSecu());
            return false;
        }

        patients.add(patient);
        LOGGER.info("Patient ajouté : {}", patient.getNomComplet());
        return true;
    }

    /**
     * Recherche un patient par son identifiant.
     */
    public Patient rechercherParId(int id) {
        for (Patient patient : patients) {
            if (patient.getId() == id) {
                return patient;
            }
        }
        return null;
    }

    /**
     * Recherche un patient par son numéro de sécurité sociale.
     */
    public Patient rechercherParNumeroSecu(String numeroSecu) {
        if (numeroSecu == null) return null;

        for (Patient patient : patients) {
            if (patient.getNumeroSecu().equalsIgnoreCase(numeroSecu)) {
                return patient;
            }
        }
        return null;
    }

    /**
     * Recherche les patients dont le nom ou le prénom contient le terme donné.
     */
    public List<Patient> rechercherParNom(String terme) {
        List<Patient> resultats = new ArrayList<>();
        if (terme == null) return resultats;

        String termeLower = terme.toLowerCase();
        for (Patient patient : patients) {
            if (patient.getNom().toLowerCase().contains(termeLower)
                    || patient.getPrenom().toLowerCase().contains(termeLower)) {
                resultats.add(patient);
            }
        }
        return resultats;
    }

    /**
     * Met à jour les informations d'un patient.
     */
    public boolean mettreAJourPatient(int id, String nouveauNom, String nouveauPrenom,
                                      String nouveauTel, String nouveauEmail,
                                      String nouvelleAdresse, double nouveauTauxRemboursement) {
        Patient patient = rechercherParId(id);
        if (patient == null) {
            LOGGER.error("Patient ID={} introuvable.", id);
            return false;
        }

        patient.setNom(nouveauNom);
        patient.setPrenom(nouveauPrenom);
        patient.setTelephone(nouveauTel);
        patient.setEmail(nouveauEmail);
        patient.setAdresse(nouvelleAdresse);
        patient.setTauxRemboursement(nouveauTauxRemboursement);
        LOGGER.info("Patient mis à jour : {}", patient.getNomComplet());
        return true;
    }

    /**
     * Supprime un patient grâce à son identifiant.
     */
    public boolean supprimerPatient(int id) {
        Patient patient = rechercherParId(id);
        if (patient == null) {
            LOGGER.error("Patient ID={} introuvable.", id);
            return false;
        }

        patients.remove(patient);
        LOGGER.info("Patient supprimé : {}", patient.getNomComplet());
        return true;
    }

    /**
     * Retourne une copie de la liste des patients.
     */
    public List<Patient> listerTousLesPatients() {
        return new ArrayList<>(patients);
    }

    /**
     * Retourne la liste observable des patients pour JavaFX.
     */
    public ObservableList<Patient> getPatients() {
        return patients;
    }

    /**
     * Retourne le nombre total de patients.
     */
    public int getNombrePatients() {
        return patients.size();
    }
}