package fr.univartois.butinfo.sae.cabinet.utils;

import fr.univartois.butinfo.sae.cabinet.enumeration.Specialite;

import fr.univartois.butinfo.sae.cabinet.enumeration.StatutRendezVous;
import fr.univartois.butinfo.sae.cabinet.modele.domaine.Medecin;
import fr.univartois.butinfo.sae.cabinet.modele.service.GestionMedecins;
import fr.univartois.butinfo.sae.cabinet.modele.service.GestionMedicaments;
import fr.univartois.butinfo.sae.cabinet.modele.service.GestionOrdonnances;
import fr.univartois.butinfo.sae.cabinet.modele.service.GestionPatients;
import fr.univartois.butinfo.sae.cabinet.modele.service.GestionRendezVous;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Module de statistiques du cabinet médical.
 * Agrège des informations issues des différents services pour produire un tableau de bord.
 */
public class Statistiques {
	
	
	/**
     * Logger pour suivre les activités de recherche et consigner les avertissements.
     */
	private static final Logger logger = LoggerFactory.getLogger(Statistiques.class);
	/**
     * Service de gestion des patients utilisé pour les statistiques.
     */
    private final GestionPatients gestionPatients;
    /**
     * Service de gestion des medecins utilisé pour les statistiques.
     */
    private final GestionMedecins gestionMedecins;
    /**
     * Service de gestion des medicaments utilisé pour les statistiques.
     */
    private final GestionMedicaments gestionMedicaments;
    /**
     * Service de gestion des rendez vous utilisé pour les statistiques.
     */
    private final GestionRendezVous gestionRendezVous;
    /**
     * Service de gestion des ordonnances utilisé pour les statistiques.
     */
    private final GestionOrdonnances gestionOrdonnances;

    /**
     * Constructeur : injecte tous les services nécessaires.
     */
    public Statistiques(GestionPatients gestionPatients,
                        GestionMedecins gestionMedecins,
                        GestionMedicaments gestionMedicaments,
                        GestionRendezVous gestionRendezVous,
                        GestionOrdonnances gestionOrdonnances) {
        this.gestionPatients = gestionPatients;
        this.gestionMedecins = gestionMedecins;
        this.gestionMedicaments = gestionMedicaments;
        this.gestionRendezVous = gestionRendezVous;
        this.gestionOrdonnances = gestionOrdonnances;
    }

    /**
     * Retourne un résumé complet des statistiques du cabinet sous forme de chaîne.
     *
     * @return les statistiques formatées
     */
    public String getStatistiques() {
        StringBuilder sb = new StringBuilder();

        sb.append("\n╔══════════════════════════════════════════╗\n");
        sb.append("║        TABLEAU DE BORD - CABINET MÉDICAL  ║\n");
        sb.append("╚══════════════════════════════════════════╝\n");

        sb.append("\nCHIFFRES GLOBAUX :\n");
        sb.append("  • Patients enregistrés   : ").append(gestionPatients.getNombrePatients()).append("\n");
        sb.append("  • Medecins enregistrés   : ").append(gestionMedecins.getNombreMedecins()).append("\n");
        sb.append("  • Medicaments référencés : ").append(gestionMedicaments.getNombreMedicaments()).append("\n");
        sb.append("  • Rendez-vous total      : ").append(gestionRendezVous.getNombreRendezVous()).append("\n");
        sb.append("  • Ordonnances émises     : ").append(gestionOrdonnances.getNombreOrdonnances()).append("\n");

        sb.append("\nRENDEZ-VOUS PAR STATUT :\n");
        for (StatutRendezVous statut : StatutRendezVous.values()) {
            int nb = gestionRendezVous.getRendezVousParStatut(statut).size();
            sb.append("  • ").append(statut.getLibelle()).append(" : ").append(nb).append("\n");
        }

        sb.append("\nMEDECINS PAR SPECIALITE :\n");
        for (Specialite sp : Specialite.values()) {
            int nb = gestionMedecins.rechercherParSpecialite(sp).size();
            if (nb > 0) {
                sb.append("  • ").append(sp.getLibelle()).append(" : ").append(nb).append("\n");
            }
        }

        sb.append("\nMEDECINS PAR NOMBRE DE RENDEZ-VOUS :\n");
        for (Medecin m : gestionMedecins.listerTousLesMedecins()) {
            int nbRdv = gestionRendezVous.getRendezVousMedecin(m).size();
            int nbOrdo = gestionOrdonnances.getOrdonnancesMedecin(m).size();
            sb.append("  • Dr ").append(m.getNomComplet())
              .append(" (").append(m.getSpecialite().getLibelle()).append(")")
              .append(" -> ").append(nbRdv).append(" RDV, ").append(nbOrdo).append(" ordonnances\n");
        }

        sb.append("\nORDONNANCES VALIDES (en cours) : ")
          .append(gestionOrdonnances.getOrdonnancesValides().size()).append("\n");

        sb.append("══════════════════════════════════════════════\n");

        return sb.toString();
    }

    // -------------------------
    // Méthodes utilitaires
    // -------------------------

    /** Retourne le nombre de rendez-vous pour un statut donné. */
    public int getNombreRendezVousParStatut(StatutRendezVous statut) {
        return gestionRendezVous.getRendezVousParStatut(statut).size();
    }

    /** Retourne le médecin ayant le plus de rendez-vous (ou null si aucun). */
    public Medecin getMedecinLePlusActif() {
        Medecin medecinMax = null;
        int max = 0;
        for (Medecin m : gestionMedecins.listerTousLesMedecins()) {
            int nb = gestionRendezVous.getRendezVousMedecin(m).size();
            if (nb > max) {
                max = nb;
                medecinMax = m;
            }
        }
        return medecinMax;
    }

    @Override
    public String toString() {
        return "Statistiques { " +
               "patients=" + gestionPatients.getNombrePatients() +
               ", médecins=" + gestionMedecins.getNombreMedecins() +
               ", médicaments=" + gestionMedicaments.getNombreMedicaments() +
               ", RDV=" + gestionRendezVous.getNombreRendezVous() +
               ", ordonnances=" + gestionOrdonnances.getNombreOrdonnances() + " }";
    }
}
