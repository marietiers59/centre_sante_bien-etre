package fr.univartois.butinfo.sae.cabinet.modele.service;

import fr.univartois.butinfo.sae.cabinet.enumeration.StatutRendezVous;
import fr.univartois.butinfo.sae.cabinet.modele.domaine.Medecin;
import fr.univartois.butinfo.sae.cabinet.modele.domaine.Patient;
import fr.univartois.butinfo.sae.cabinet.modele.domaine.RendezVous;
import java.time.LocalDateTime;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Façade permettant de gérer les rendez-vous médicaux.
 */
public class GestionRendezVous {

    private static final Logger logger = LoggerFactory.getLogger(GestionRendezVous.class);

    private final ObservableList<RendezVous> rendezVous;

    public GestionRendezVous() {
        this.rendezVous = FXCollections.observableArrayList();
    }

    public RendezVous prendreRendezVous(Patient patient, Medecin medecin,
            LocalDateTime dateHeure, int dureeMinutes, String motif) {

        if (!estDisponible(medecin, dateHeure)) {
            logger.info("ERREUR : Le Dr " + medecin.getNomComplet()
                    + " n'est pas disponible à ce créneau.");
            return null;
        }

        RendezVous rdv = new RendezVous(patient, medecin, dateHeure, dureeMinutes, motif);
        rendezVous.add(rdv);

        logger.info("Rendez-vous créé : " + rdv);
        return rdv;
    }

    public void annulerRendezVous(int id) {
        RendezVous rdv = rechercherParId(id);

        if (rdv == null) {
            logger.info("ERREUR : Rendez-vous ID=" + id + " introuvable.");
            return;
        }

        rdv.annuler();
        logger.info("Rendez-vous annulé : ID=" + id);
    }

    public void confirmerRendezVous(int id) {
        RendezVous rdv = rechercherParId(id);

        if (rdv == null) {
            logger.info("ERREUR : Rendez-vous ID=" + id + " introuvable.");
            return;
        }

        rdv.confirmer();
        logger.info("Rendez-vous confirmé : ID=" + id);
    }

    public boolean terminerRendezVous(int id) {
        RendezVous rdv = rechercherParId(id);

        if (rdv == null) {
            logger.info("ERREUR : Rendez-vous ID=" + id + " introuvable.");
            return false;
        }

        rdv.terminer();
        logger.info("Rendez-vous terminé : ID=" + id);
        return true;
    }

    public RendezVous rechercherParId(int id) {
        for (RendezVous rdv : rendezVous) {
            if (rdv.getId() == id) {
                return rdv;
            }
        }
        return null;
    }

    public ObservableList<RendezVous> getRendezVousPatient(Patient patient) {
        ObservableList<RendezVous> resultats = FXCollections.observableArrayList();

        for (RendezVous rdv : rendezVous) {
            if (rdv.getPatient().equals(patient)) {
                resultats.add(rdv);
            }
        }

        return resultats;
    }

    public ObservableList<RendezVous> getRendezVousMedecin(Medecin medecin) {
        ObservableList<RendezVous> resultats = FXCollections.observableArrayList();

        for (RendezVous rdv : rendezVous) {
            if (rdv.getMedecin().equals(medecin)) {
                resultats.add(rdv);
            }
        }

        return resultats;
    }

    public ObservableList<RendezVous> getRendezVousParStatut(StatutRendezVous statut) {
        ObservableList<RendezVous> resultats = FXCollections.observableArrayList();

        for (RendezVous rdv : rendezVous) {
            if (rdv.getStatut() == statut) {
                resultats.add(rdv);
            }
        }

        return resultats;
    }

    public boolean estDisponible(Medecin medecin, LocalDateTime dateHeure) {
        for (RendezVous rdv : rendezVous) {
            if (rdv.getMedecin().equals(medecin)
                    && rdv.getDateHeure().equals(dateHeure)
                    && rdv.getStatut() != StatutRendezVous.ANNULE) {
                return false;
            }
        }
        return true;
    }

    public ObservableList<RendezVous> listerTousLesRendezVous() {
        return rendezVous;
    }

    public ObservableList<RendezVous> getRendezVous() {
        return rendezVous;
    }

    public int getNombreRendezVous() {
        return rendezVous.size();
    }

}