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
 * Module de recherche transversale permettant d'effectuer des recherches avancées
 * sur les patients et les médecins du cabinet.
 * Ce module centralise les accès aux services de gestion pour simplifier les requêtes métier.
 * @version 1.0
 */
public class Recherche {

    /**
     * Logger pour suivre les activités de recherche et consigner les avertissements.
     */
    private static final Logger logger = LoggerFactory.getLogger(Recherche.class);

    /**
     * Service de gestion des patients utilisé pour les recherches.
     */
    private final GestionPatients gestionPatients;

    /**
     * Service de gestion des médecins utilisé pour les recherches.
     */
    private final GestionMedecins gestionMedecins;

    /**
     * Constructeur injectant les services nécessaires aux opérations de recherche.
     *
     * @param gestionPatients Le service de gestion des patients.
     * @param gestionMedecins Le service de gestion des médecins.
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
     * @param terme le nom ou prénom à rechercher.
     * @return la liste des patients correspondants, vide si aucun résultat n'est trouvé.
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
     * @param numeroSecu le numéro de sécurité sociale à rechercher.
     * @return le patient trouvé, ou {@code null} si aucun résultat n'existe.
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
     * @param terme le nom ou prénom à rechercher.
     * @return la liste des médecins correspondants, vide si aucun résultat n'est trouvé.
     */
    public List<Medecin> rechercherMedecinParNom(String terme) {
        List<Medecin> resultats = gestionMedecins.rechercherParNom(terme);
        if (resultats.isEmpty()) {
            logger.warn("Aucun medecin trouve pour le terme : {}", terme);
        }
        return resultats;
    }

    /**
     * Recherche des médecins par spécialité médicale.
     *
     * @param specialite la spécialité à rechercher.
     * @return la liste des médecins correspondants, vide si aucun résultat n'est trouvé.
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
     * @param numeroOrdre le numéro d'ordre du médecin.
     * @return le médecin trouvé, ou {@code null} si aucun résultat n'existe.
     */
    public Medecin rechercherMedecinParOrdre(String numeroOrdre) {
        Medecin m = gestionMedecins.rechercherParNumeroOrdre(numeroOrdre);
        if (m == null) {
            logger.warn("Aucun medecin trouve pour le numero d'ordre : {}", numeroOrdre);
        }
        return m;
    }

    /**
     * Retourne une représentation textuelle du module de recherche, indiquant
     * les volumes de données actuels (patients et médecins).
     *
     * @return une chaîne décrivant l'état du module de recherche.
     */
    @Override
    public String toString() {
        return "Module Recherche { patients=" + gestionPatients.getNombrePatients() +
                ", médecins=" + gestionMedecins.getNombreMedecins() + " }";
    }
}