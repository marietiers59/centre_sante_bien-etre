package fr.univartois.butinfo.sae.cabinet.utils;

import fr.univartois.butinfo.sae.cabinet.enumeration.Specialite;
import fr.univartois.butinfo.sae.cabinet.modele.Medecin;
import fr.univartois.butinfo.sae.cabinet.modele.Patient;
import fr.univartois.butinfo.sae.cabinet.service.GestionMedecins;
import fr.univartois.butinfo.sae.cabinet.service.GestionPatients;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Module de recherche transversale pour patients et médecins.
 * Regroupe les fonctionnalités de recherche avancée.
 */
public class Recherche {

	private static final Logger logger = LoggerFactory.getLogger(Recherche.class);
    private final GestionPatients gestionPatients;
    private final GestionMedecins gestionMedecins;

    /**
     * Constructeur : injecte les services nécessaires.
     */
    public Recherche(GestionPatients gestionPatients, GestionMedecins gestionMedecins) {
        this.gestionPatients = gestionPatients;
        this.gestionMedecins = gestionMedecins;
    }

    // -------------------------
    // Recherche patients
    // -------------------------

    /**
     * Recherche des patients par nom ou prénom.
     *
     * @param terme le nom ou prénom à rechercher
     * @return la liste des patients correspondants, vide si aucun résultat
     */
    public List<Patient> rechercherPatientParNom(String terme) {
        List<Patient> resultats = gestionPatients.rechercherParNom(terme);
        if (resultats.isEmpty()) {
            logger.warn("Aucun patient trouve pour le terme : {}", terme);
        }
        return resultats;
    }

    /**
     * Recherche un patient par son numéro de sécurité sociale.
     *
     * @param numeroSecu le numéro de sécurité sociale
     * @return le patient trouvé, ou {@code null} si aucun résultat
     */
    public Patient rechercherPatientParSecu(String numeroSecu) {
        Patient p = gestionPatients.rechercherParNumeroSecu(numeroSecu);
        if (p == null) {
            logger.warn("Aucun patient trouve pour le numero de secu : {}", numeroSecu);
        }
        return p;
    }

    // -------------------------
    // Recherche médecins
    // -------------------------

    /**
     * Recherche des médecins par nom ou prénom.
     *
     * @param terme le nom ou prénom à rechercher
     * @return la liste des médecins correspondants, vide si aucun résultat
     */
    public List<Medecin> rechercherMedecinParNom(String terme) {
        List<Medecin> resultats = gestionMedecins.rechercherParNom(terme);
        if (resultats.isEmpty()) {
            logger.warn("Aucun medecin trouve pour le terme : {}", terme);
        }
        return resultats;
    }

    /**
     * Recherche des médecins par spécialité.
     *
     * @param specialite la spécialité à rechercher
     * @return la liste des médecins correspondants, vide si aucun résultat
     */
    public List<Medecin> rechercherMedecinParSpecialite(Specialite specialite) {
        List<Medecin> resultats = gestionMedecins.rechercherParSpecialite(specialite);
        if (resultats.isEmpty()) {
            logger.warn("Aucun medecin trouve pour la specialite : {}", specialite.getLibelle());
        }
        return resultats;
    }

    /**
     * Recherche un médecin par son numéro d'ordre.
     *
     * @param numeroOrdre le numéro d'ordre
     * @return le médecin trouvé, ou {@code null} si aucun résultat
     */
    public Medecin rechercherMedecinParOrdre(String numeroOrdre) {
        Medecin m = gestionMedecins.rechercherParNumeroOrdre(numeroOrdre);
        if (m == null) {
            logger.warn("Aucun medecin trouve pour le numero d'ordre : {}", numeroOrdre);
        }
        return m;
    }

    @Override
    public String toString() {
        return "Module Recherche { patients=" + gestionPatients.getNombrePatients() +
               ", médecins=" + gestionMedecins.getNombreMedecins() + " }";
    }
}
