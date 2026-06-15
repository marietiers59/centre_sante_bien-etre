package fr.univartois.butinfo.sae.cabinet.modele;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe représentant une ordonnance médicale. Une ordonnance est émise par un médecin pour un
 * patient et contient une liste de lignes de prescription.
 */
public class Ordonnance {

  /** Identifiant unique de l'ordonnance, généré automatiquement. */
  private final int id;

  /** Médecin ayant émis l'ordonnance. */
  private final Medecin medecin;

  /** Patient destinataire de l'ordonnance. */
  private final Patient patient;

  /** Date d'émission de l'ordonnance (initialisée à la date du jour). */
  private final LocalDate dateEmission;

  /** Date limite de validité de l'ordonnance. */
  private LocalDate dateValidite;

  /** Liste des lignes de prescription de l'ordonnance. */
  private final List<LignePrescription> prescriptions;

  /** Remarques ou instructions complémentaires du médecin. */
  private String remarques;

  /** Compteur statique pour générer des identifiants uniques. */
  private static int compteurId = 1;

  /**
   * Constructeur complet d'une Ordonnance.
   * La date d'émission est fixée à la date du jour et la liste des prescriptions est vide.
   *
   * @param medecin      le médecin émetteur
   * @param patient      le patient destinataire
   * @param dateValidite la date limite de validité
   * @param remarques    les remarques complémentaires
   */
  public Ordonnance(Medecin medecin, Patient patient, LocalDate dateValidite, String remarques) {
    this.id = compteurId++;
    this.medecin = medecin;
    this.patient = patient;
    this.dateEmission = LocalDate.now();
    this.dateValidite = dateValidite;
    this.prescriptions = new ArrayList<>();
    this.remarques = remarques;
  }

  /**
   * Constructeur simplifié : validité à 3 mois par défaut, aucune remarque.
   *
   * @param medecin le médecin émetteur
   * @param patient le patient destinataire
   */
  public Ordonnance(Medecin medecin, Patient patient) {
    this(medecin, patient, LocalDate.now().plusMonths(3), "");
  }

  // -------------------------
  // Gestion des prescriptions
  // -------------------------

  /**
   * Ajoute une ligne de prescription à l'ordonnance.
   *
   * @param ligne la ligne de prescription à ajouter
   */
  public void ajouterPrescription(LignePrescription ligne) {
    prescriptions.add(ligne);
  }

  /**
   * Supprime la ligne de prescription correspondant au médicament donné.
   *
   * @param medicament le médicament dont la prescription doit être supprimée
   * @return {@code true} si une prescription a été supprimée, {@code false} sinon
   */
  public boolean supprimerPrescription(Medicament medicament) {
    return prescriptions.removeIf(l -> l.getMedicament().equals(medicament));
  }

  // -------------------------
  // Getters et Setters
  // -------------------------

  /**
   * Retourne l'identifiant unique de l'ordonnance.
   *
   * @return l'identifiant
   */
  public int getId() {
    return id;
  }

  /**
   * Retourne le médecin ayant émis l'ordonnance.
   *
   * @return le médecin
   */
  public Medecin getMedecin() {
    return medecin;
  }

  /**
   * Retourne le patient destinataire de l'ordonnance.
   *
   * @return le patient
   */
  public Patient getPatient() {
    return patient;
  }

  /**
   * Retourne la date d'émission de l'ordonnance.
   *
   * @return la date d'émission
   */
  public LocalDate getDateEmission() {
    return dateEmission;
  }

  /**
   * Retourne la date de fin de validité de l'ordonnance.
   *
   * @return la date de validité
   */
  public LocalDate getDateValidite() {
    return dateValidite;
  }

  /**
   * Modifie la date de fin de validité de l'ordonnance.
   *
   * @param dateValidite la nouvelle date de validité
   */
  public void setDateValidite(LocalDate dateValidite) {
    this.dateValidite = dateValidite;
  }

  /**
   * Retourne une copie défensive de la liste des prescriptions.
   *
   * @return la liste des lignes de prescription
   */
  public List<LignePrescription> getPrescriptions() {
    return new ArrayList<>(prescriptions);
  }

  /**
   * Retourne les remarques complémentaires du médecin.
   *
   * @return les remarques
   */
  public String getRemarques() {
    return remarques;
  }

  /**
   * Modifie les remarques complémentaires.
   *
   * @param remarques les nouvelles remarques
   */
  public void setRemarques(String remarques) {
    this.remarques = remarques;
  }

  /**
   * Indique si l'ordonnance est encore valide à la date du jour.
   *
   * @return {@code true} si l'ordonnance est valide, {@code false} sinon
   */
  public boolean estValide() {
    return !LocalDate.now().isAfter(dateValidite);
  }

  /**
   * Indique si deux ordonnances sont égales (même identifiant).
   *
   * @param obj l'objet à comparer
   * @return {@code true} si les deux ordonnances ont le même identifiant
   */
  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null || getClass() != obj.getClass()) {
      return false;
    }
    Ordonnance o = (Ordonnance) obj;
    return id == o.id;
  }

  /**
   * Retourne le code de hachage basé sur l'identifiant.
   *
   * @return le code de hachage
   */
  @Override
  public int hashCode() {
    return Integer.hashCode(id);
  }

  /**
   * Calcul du prix total de l'ordonnance en fonction des prescriptions.
   * <p>
   * Le prix total de l'ordonnance est fonction :
   * <ul>
   *   <li>du prix de chaque médicament prescrit</li>
   *   <li>du taux de remboursement du patient.</li>
   * </ul>
   *
   * @return le prix total de l'ordonnance à la charge du patient
   */
  public double getPrixTotal() {
	    double totalBrut = 0.0;

	    for (int i = 0; i < prescriptions.size(); i++) {
	        LignePrescription lp = prescriptions.get(i);
	        totalBrut += lp.getMedicament().getPrix() * lp.getQuantite();
	    }
	    return totalBrut * (1 - patient.getTauxRemboursement());
	}
  
  /**
   * Retourne une représentation textuelle détaillée de l'ordonnance.
   *
   * @return l'ordonnance formatée
   */
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("=== ORDONNANCE N°").append(id).append(" ===\n");
    sb.append("  Médecin  : Dr ").append(medecin.getNomComplet())
        .append(" (").append(medecin.getSpecialite().getLibelle()).append(")\n");
    sb.append("  Patient  : ").append(patient.getNomComplet()).append("\n");
    sb.append("  Émise le : ").append(dateEmission).append("\n");
    sb.append("  Valide jusqu'au : ").append(dateValidite).append("\n");
    sb.append("  Médicaments prescrits :\n");
    for (LignePrescription lp : prescriptions) {
      sb.append(lp.toString()).append("\n");
    }
    sb.append("  Prix total estimé : ").append(String.format("%.2f €", getPrixTotal())).append("\n");
    if (!remarques.isEmpty()) {
      sb.append("  Remarques : ").append(remarques).append("\n");
    }
    return sb.toString();
  }
}
