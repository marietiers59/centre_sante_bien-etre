package fr.univartois.butinfo.sae.cabinet.modele.service;

import fr.univartois.butinfo.sae.cabinet.modele.domaine.Patient;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class GestionPatients {

    private final ObservableList<Patient> patients;

    public GestionPatients() {
        this.patients = FXCollections.observableArrayList();
    }

    public void ajouterPatient(Patient patient) {
        if (rechercherParNumeroSecu(patient.getNumeroSecu()) != null) {
            return;
        }
        patients.add(patient);
    }

    public Patient rechercherParId(int id) {
        for (Patient p : patients) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }

    public Patient rechercherParNumeroSecu(String numeroSecu) {
        for (Patient p : patients) {
            if (p.getNumeroSecu().equalsIgnoreCase(numeroSecu)) {
                return p;
            }
        }
        return null;
    }

    public ObservableList<Patient> rechercherParNom(String terme) {
        ObservableList<Patient> resultats = FXCollections.observableArrayList();
        String termeLower = terme.toLowerCase();

        for (Patient p : patients) {
            if (p.getNom().toLowerCase().contains(termeLower)
                    || p.getPrenom().toLowerCase().contains(termeLower)) {
                resultats.add(p);
            }
        }

        return resultats;
    }

    public void mettreAJourPatient(int id, String nouveauNom, String nouveauPrenom,
            String nouveauTel, String nouveauEmail,
            String nouvelleAdresse, double nouveauTauxRemboursement) {
        Patient p = rechercherParId(id);

        if (p == null) {
            return;
        }

        p.setNom(nouveauNom);
        p.setPrenom(nouveauPrenom);
        p.setTelephone(nouveauTel);
        p.setEmail(nouveauEmail);
        p.setAdresse(nouvelleAdresse);
        p.setTauxRemboursement(nouveauTauxRemboursement);
    }

    public void supprimerPatient(int id) {
        Patient p = rechercherParId(id);

        if (p != null) {
            patients.remove(p);
        }
    }

    public ObservableList<Patient> listerTousLesPatients() {
        return patients;
    }

    public ObservableList<Patient> getPatients() {
        return patients;
    }

    public int getNombrePatients() {
        return patients.size();
    }
}