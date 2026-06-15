package fr.univartois.butinfo.sae.cabinet.modele.service;

import fr.univartois.butinfo.sae.cabinet.Main;

import fr.univartois.butinfo.sae.cabinet.modele.domaine.Medicament;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Service de gestion des médicaments disponibles dans le système.
 */
public class GestionMedicaments {
	
	private static final Logger logger = LoggerFactory.getLogger(GestionMedicaments.class.getSimpleName());


    private final ObservableList<Medicament> medicaments;

    /**
     * Constructeur : initialise la liste vide.
     */
    public GestionMedicaments() {
        this.medicaments = FXCollections.observableArrayList();
    }

    // -------------------------
    // CRUD
    // -------------------------

    /**
     * Ajoute un médicament s'il n'existe pas déjà (même nom + principe actif).
     */
    public void ajouterMedicament(Medicament medicament) {
        if (medicaments.contains(medicament)) {
            logger.error("ERREUR : Ce médicament existe déjà.");
            return;
        }
        medicaments.add(medicament);
        logger.info("Médicament ajouté : " + medicament.getNom());
    }

    /**
     * Recherche un médicament par son ID.
     */
    public Medicament rechercherParId(int id) {
        for (Medicament m : medicaments) {
            if (m.getId() == id) return m;
        }
        return null;
    }

    /**
     * Recherche des médicaments dont le nom contient le terme donné.
     */
    public List<Medicament> rechercherParNom(String terme) {
        List<Medicament> resultats = new ArrayList<>();
        String termeLower = terme.toLowerCase();
        for (Medicament m : medicaments) {
            if (m.getNom().toLowerCase().contains(termeLower)) {
                resultats.add(m);
            }
        }
        return resultats;
    }


    /**
     * Supprime un médicament par son ID.
     */
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

    /**
     * Retourne la liste complète des médicaments.
     */
    public List<Medicament> listerTousLesMedicaments() {
        return new ArrayList<>(medicaments);
    }

    /**
     * Retourne le nombre total de médicaments.
     */
    public int getNombreMedicaments() {
        return medicaments.size();
    }


}
