package fr.univartois.butinfo.sae.cabinet.service;

import fr.univartois.butinfo.sae.cabinet.modele.Patient;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


/**
 * Façade de gestion des patients.
 * Cette classe centralise les opérations CRUD sur les patients du cabinet médical.
 */
public class GestionPatients {

    /** Logger utilisé pour tracer les actions du service. */
    private static final Logger LOGGER = LogManager.getLogger(GestionPatients.class);

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
     *
     * @param patient le patient à ajouter
     * @return true si le patient a été ajouté, false sinon
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
     *
     * @param id l'identifiant du patient
     * @return le patient trouvé, ou null si aucun patient ne correspond
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
     *
     * @param numeroSecu le numéro de sécurité sociale recherché
     * @return le patient trouvé, ou null si aucun patient ne correspond
     */
    public Patient rechercherParNumeroSecu(String numeroSecu) {
        if (numeroSecu == null) {
            return null;
        }

        for (Patient patient : patients) {
            if (patient.getNumeroSecu().equalsIgnoreCase(numeroSecu)) {
                return patient;
            }
        }
        return null;
    }

    /**
     * Recherche les patients dont le nom ou le prénom contient le terme donné.
     *
     * @param terme le texte à rechercher
     * @return la liste des patients trouvés
     */
    public List<Patient> rechercherParNom(String terme) {
        List<Patient> resultats = new ArrayList<>();
        if (terme == null) {
            return resultats;
        }

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
     * Met à jour les informations d'un patient, sauf son identifiant et son numéro de sécurité sociale.
     *
     * @param id l'identifiant du patient à modifier
     * @param nouveauNom le nouveau nom
     * @param nouveauPrenom le nouveau prénom
     * @param nouveauTel le nouveau numéro de téléphone
     * @param nouveauEmail le nouvel email
     * @param nouvelleAdresse la nouvelle adresse
     * @param nouveauTauxRemboursement le nouveau taux de remboursement
     * @return true si le patient a été modifié, false sinon
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
     *
     * @param id l'identifiant du patient à supprimer
     * @return true si le patient a été supprimé, false sinon
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
     *
     * @return une copie de la liste des patients
     */
    public List<Patient> listerTousLesPatients() {
        return new ArrayList<>(patients);
    }

    /**
     * Retourne la liste observable des patients pour JavaFX.
     *
     * @return la liste observable des patients
     */
    public ObservableList<Patient> getPatients() {
        return patients;
    }

    /**
     * Retourne le nombre total de patients.
     *
     * @return le nombre de patients
     */
    public int getNombrePatients() {
        return patients.size();
    }
}
