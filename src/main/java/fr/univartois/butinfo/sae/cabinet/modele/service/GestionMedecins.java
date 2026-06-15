package fr.univartois.butinfo.sae.cabinet.modele.service;

import fr.univartois.butinfo.sae.cabinet.enumeration.Specialite;
import fr.univartois.butinfo.sae.cabinet.modele.domaine.Medecin;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Façade permettant de gérer les médecins du cabinet.
 */
public class GestionMedecins {

    private static final Logger logger = LoggerFactory.getLogger(GestionMedecins.class);

    private final ObservableList<Medecin> medecins;

    public GestionMedecins() {
        this.medecins = FXCollections.observableArrayList();
    }

    public void ajouterMedecin(Medecin medecin) {
        if (rechercherParNumeroOrdre(medecin.getNumeroOrdre()) != null) {
            logger.info("ERREUR : Un médecin avec ce numéro d'ordre existe déjà.");
            return;
        }
        medecins.add(medecin);
        logger.info("Médecin ajouté : Dr " + medecin.getNomComplet());
    }

    public Medecin rechercherParId(int id) {
        for (Medecin m : medecins) {
            if (m.getId() == id) {
                return m;
            }
        }
        return null;
    }

    public Medecin rechercherParNumeroOrdre(String numeroOrdre) {
        for (Medecin m : medecins) {
            if (m.getNumeroOrdre().equalsIgnoreCase(numeroOrdre)) {
                return m;
            }
        }
        return null;
    }

    public ObservableList<Medecin> rechercherParNom(String terme) {
        ObservableList<Medecin> resultats = FXCollections.observableArrayList();
        String termeLower = terme.toLowerCase();

        for (Medecin m : medecins) {
            if (m.getNom().toLowerCase().contains(termeLower)
                    || m.getPrenom().toLowerCase().contains(termeLower)) {
                resultats.add(m);
            }
        }

        return resultats;
    }

    public ObservableList<Medecin> rechercherParSpecialite(Specialite specialite) {
        ObservableList<Medecin> resultats = FXCollections.observableArrayList();

        for (Medecin m : medecins) {
            if (m.getSpecialite() == specialite) {
                resultats.add(m);
            }
        }

        return resultats;
    }

    public void mettreAJourMedecin(int id, String nouveauNom, String nouveauPrenom,
            double nouveauTarifConsultation, String nouveauTel, String nouveauEmail,
            Specialite nouvelleSpecialite) {

        Medecin m = rechercherParId(id);

        if (m == null) {
            logger.error("ERREUR : Médecin ID=" + id + " introuvable.");
            return;
        }

        m.setNom(nouveauNom);
        m.setPrenom(nouveauPrenom);
        m.setTarifHoraire(nouveauTarifConsultation);
        m.setTelephone(nouveauTel);
        m.setEmail(nouveauEmail);
        m.setSpecialite(nouvelleSpecialite);

        logger.info("Médecin mis à jour : Dr " + m.getNomComplet());
    }

    public boolean supprimerMedecin(int id) {
        Medecin m = rechercherParId(id);

        if (m == null) {
            logger.error("ERREUR : Médecin ID=" + id + " introuvable.");
            return false;
        }

        medecins.remove(m);
        logger.info("Médecin supprimé : Dr " + m.getNomComplet());
        return true;
    }

    public ObservableList<Medecin> listerTousLesMedecins() {
        return medecins;
    }

    public ObservableList<Medecin> getMedecins() {
        return medecins;
    }

    public int getNombreMedecins() {
        return medecins.size();
    }

}