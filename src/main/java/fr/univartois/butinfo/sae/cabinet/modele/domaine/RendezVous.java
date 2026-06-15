package fr.univartois.butinfo.sae.cabinet.modele.domaine;

import fr.univartois.butinfo.sae.cabinet.enumeration.StatutRendezVous;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Classe représentant un rendez-vous entre un patient et un médecin.
 */
public class RendezVous {

  /** Identifiant unique du rendez-vous, généré automatiquement. */
  private final int id;

  /** Patient concerné par le rendez-vous. */
  private final Patient patient;

  /** Médecin qui reçoit le patient. */
  private final Medecin medecin;

  /** Date et heure du rendez-vous. */
  private LocalDateTime dateHeure;

  /** Durée du rendez-vous en minutes. */
  private int dureeMinutes;

  /** Statut courant du rendez-vous. */
  private StatutRendezVous statut;

  /** Motif de la consultation. */
  private String motif;

  /** Compteur statique pour générer des identifiants uniques. */
  private static int compteurId = 1;

  /** Format d'affichage de la date et de l'heure. */
  private static final DateTimeFormatter FORMATTER =
      DateTimeFormatter.ofPattern("dd/MM/yyyy à HH:mm");

  /**
   * Constructeur complet d'un RendezVous. Le statut est initialisé à PLANIFIE.
   *
   * @param patient      le patient
   * @param medecin      le médecin
   * @param dateHeure    la date et l'heure du rendez-vous
   * @param dureeMinutes la durée en minutes
   * @param motif        le motif de la consultation
   */
  public RendezVous(Patient patient, Medecin medecin, LocalDateTime dateHeure,
      int dureeMinutes, String motif) {
    this.id = compteurId++;
    this.patient = patient;
    this.medecin = medecin;
    this.dateHeure = dateHeure;
    this.dureeMinutes = dureeMinutes;
    this.statut = StatutRendezVous.PLANIFIE;
    this.motif = motif;
  }

  /**
   * Constructeur simplifié avec une durée par défaut de 30 minutes.
   *
   * @param patient   le patient
   * @param medecin   le médecin
   * @param dateHeure la date et l'heure du rendez-vous
   * @param motif     le motif de la consultation
   */
  public RendezVous(Patient patient, Medecin medecin, LocalDateTime dateHeure, String motif) {
    this(patient, medecin, dateHeure, 30, motif);
  }

  // -------------------------
  // Actions métier
  // -------------------------

  /**
   * Confirme le rendez-vous : passe le statut à CONFIRME si le statut est PLANIFIE.
   */
  public void confirmer() {
    if (this.statut == StatutRendezVous.PLANIFIE) {
      this.statut = StatutRendezVous.CONFIRME;
    }
  }

  /**
   * Annule le rendez-vous : passe le statut à ANNULE si le rendez-vous n'est pas terminé.
   */
  public void annuler() {
    if (this.statut != StatutRendezVous.TERMINE) {
      this.statut = StatutRendezVous.ANNULE;
    }
  }

  /**
   * Marque le rendez-vous comme terminé : passe le statut à TERMINE.
   */
  public void terminer() {
    this.statut = StatutRendezVous.TERMINE;
  }

  // -------------------------
  // Getters et Setters
  // -------------------------

  /**
   * Retourne l'identifiant unique du rendez-vous.
   *
   * @return l'identifiant
   */
  public int getId() {
    return id;
  }

  /**
   * Retourne le patient concerné par le rendez-vous.
   *
   * @return le patient
   */
  public Patient getPatient() {
    return patient;
  }

  /**
   * Retourne le médecin qui reçoit le patient.
   *
   * @return le médecin
   */
  public Medecin getMedecin() {
    return medecin;
  }

  /**
   * Retourne la date et l'heure du rendez-vous.
   *
   * @return la date et l'heure
   */
  public LocalDateTime getDateHeure() {
    return dateHeure;
  }

  /**
   * Modifie la date et l'heure du rendez-vous.
   *
   * @param dateHeure la nouvelle date et heure
   */
  public void setDateHeure(LocalDateTime dateHeure) {
    this.dateHeure = dateHeure;
  }

  /**
   * Retourne la durée du rendez-vous en minutes.
   *
   * @return la durée en minutes
   */
  public int getDureeMinutes() {
    return dureeMinutes;
  }

  /**
   * Modifie la durée du rendez-vous.
   *
   * @param dureeMinutes la nouvelle durée en minutes
   */
  public void setDureeMinutes(int dureeMinutes) {
    this.dureeMinutes = dureeMinutes;
  }

  /**
   * Retourne le statut courant du rendez-vous.
   *
   * @return le statut
   */
  public StatutRendezVous getStatut() {
    return statut;
  }

  /**
   * Modifie le statut du rendez-vous.
   *
   * @param statut le nouveau statut
   */
  public void setStatut(StatutRendezVous statut) {
    this.statut = statut;
  }

  /**
   * Retourne le motif de la consultation.
   *
   * @return le motif
   */
  public String getMotif() {
    return motif;
  }

  /**
   * Modifie le motif de la consultation.
   *
   * @param motif le nouveau motif
   */
  public void setMotif(String motif) {
    this.motif = motif;
  }

  /**
   * Calcule le prix de la consultation.
   * <p>
   * Le prix de la consultation est fonction :
   * <ul>
   *   <li>du tarif horaire du médecin</li>
   *   <li>du nombre de minutes de consultation</li>
   *   <li>du taux de remboursement du patient</li>
   * </ul>
   *
   * @return le prix de la consultation à la charge du patient
   */
  public double prixConsultation() {
    double coutBrut = (medecin.getTarifHoraire() * dureeMinutes) / 60.0;
    double tauxRemboursement = patient.getTauxRemboursement();
    return coutBrut * (1 - tauxRemboursement);
  }

  /**
   * Retourne le code de hachage basé sur le patient, le médecin et la date/heure.
   *
   * @return le code de hachage
   */
  @Override
  public int hashCode() {
    return patient.hashCode() * 31 + medecin.hashCode() * 17 + dateHeure.hashCode();
  }

  /**
   * Indique si deux rendez-vous sont égaux (même patient, même médecin, même date/heure).
   *
   * @param obj l'objet à comparer
   * @return {@code true} si les deux rendez-vous sont identiques
   */
  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null || getClass() != obj.getClass()) {
      return false;
    }
    RendezVous autre = (RendezVous) obj;
    return this.patient.equals(autre.patient) &&
        this.medecin.equals(autre.medecin) &&
        this.dateHeure.equals(autre.dateHeure);
  }

  /**
   * Retourne une représentation textuelle du rendez-vous.
   *
   * @return le rendez-vous formaté
   */
  @Override
  public String toString() {
    return "RDV      { [ID=" + id + "] " +
        patient.getNomComplet() + " avec Dr " + medecin.getNomComplet() +
        " | " + dateHeure.format(FORMATTER) +
        " (" + dureeMinutes + " min)" +
        " | Motif: " + motif +
        " | Statut: " + statut.getLibelle() +
        " | Coût : " + prixConsultation() + " }";
  }
}
