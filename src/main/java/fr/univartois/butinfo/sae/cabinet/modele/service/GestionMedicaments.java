package fr.univartois.butinfo.sae.cabinet.modele.service;

import fr.univartois.butinfo.sae.cabinet.modele.domaine.Medicament;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Façade permettant de gérer les médicaments disponibles.
 */
public class GestionMedicaments {

    private static final Logger logger = LoggerFactory.getLogger(GestionMedicaments.class);

    private final ObservableList<Medicament> medicaments;

    public GestionMedicaments() {
        this.medicaments = FXCollections.observableArrayList();
    }

    public void ajouterMedicament(Medicament medicament) {
        if (medicaments.contains(medicament)) {
            logger.error("ERREUR : Ce médicament existe déjà.");
            return;
        }

        medicaments.add(medicament);
        logger.info("Médicament ajouté : " + medicament.getNom());
    }

    public Medicament rechercherParId(int id) {
        for (Medicament m : medicaments) {
            if (m.getId() == id) {
                return m;
            }
        }
        return null;
    }

    public ObservableList<Medicament> rechercherParNom(String terme) {
        ObservableList<Medicament> resultats = FXCollections.observableArrayList();
        String termeLower = terme.toLowerCase();

        for (Medicament m : medicaments) {
            if (m.getNom().toLowerCase().contains(termeLower)) {
                resultats.add(m);
            }
        }

        return resultats;
    }

    public boolean supprimerMedicament(int id) {
        Medicament m = rechercherParId(id);

        if (m == null) {
            logger.error("ERREUR : Médicament ID=" + id + " introuvable.");
            return false;
        }

        medicaments.remove(m);
        logger.info("Médicament supprimé : " + m.getNom());
        return true;
    }

    public ObservableList<Medicament> listerTousLesMedicaments() {
        return medicaments;
    }

    public ObservableList<Medicament> getMedicaments() {
        return medicaments;
    }

    public int getNombreMedicaments() {
        return medicaments.size();
    }

}