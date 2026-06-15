package fr.univartois.butinfo.sae.cabinet.modele;

/**
 * Représente une ligne de prescription dans une ordonnance.
 * Associe un médicament à sa posologie, sa quantité et sa durée de traitement.
 */
public class LignePrescription {

  /** Médicament prescrit. */
  private final Medicament medicament;

  /** Quantité de médicament prescrite. */
  private int quantite;

  /** Posologie, ex : "1 comprimé matin et soir". */
  private String posologie;

  /** Durée du traitement en jours. */
  private int dureeTraitement;

  /** Consignes particulières à respecter lors de la prise. */
  private String instructions;

  /**
   * Constructeur complet d'une ligne de prescription.
   *
   * @param medicament      le médicament prescrit
   * @param quantite        la quantité prescrite
   * @param posologie       la posologie (fréquence et mode de prise)
   * @param dureeTraitement la durée du traitement en jours
   * @param instructions    les consignes particulières
   */
  public LignePrescription(Medicament medicament, int quantite, String posologie,
      int dureeTraitement, String instructions) {
    this.medicament = medicament;
    this.quantite = quantite;
    this.posologie = posologie;
    this.dureeTraitement = dureeTraitement;
    this.instructions = instructions;
  }

  /**
   * Constructeur simplifié : quantité 1, aucune instruction particulière.
   *
   * @param medicament      le médicament prescrit
   * @param posologie       la posologie
   * @param dureeTraitement la durée du traitement en jours
   */
  public LignePrescription(Medicament medicament, String posologie, int dureeTraitement) {
    this(medicament, 1, posologie, dureeTraitement, "Aucune instruction particulière");
  }

  // -------------------------
  // Getters et Setters
  // -------------------------

  /**
   * Retourne le médicament prescrit.
   *
   * @return le médicament
   */
  public Medicament getMedicament() {
    return medicament;
  }

  /**
   * Retourne la posologie associée à cette prescription.
   *
   * @return la posologie
   */
  public String getPosologie() {
    return posologie;
  }

  /**
   * Modifie la posologie.
   *
   * @param posologie la nouvelle posologie
   */
  public void setPosologie(String posologie) {
    this.posologie = posologie;
  }

  /**
   * Retourne la durée du traitement en jours.
   *
   * @return la durée en jours
   */
  public int getDureeTraitement() {
    return dureeTraitement;
  }

  /**
   * Modifie la durée du traitement.
   *
   * @param dureeTraitement la nouvelle durée en jours
   */
  public void setDureeTraitement(int dureeTraitement) {
    this.dureeTraitement = dureeTraitement;
  }

  /**
   * Retourne les consignes particulières de la prescription.
   *
   * @return les instructions
   */
  public String getInstructions() {
    return instructions;
  }

  /**
   * Modifie les consignes particulières.
   *
   * @param instructions les nouvelles instructions
   */
  public void setInstructions(String instructions) {
    this.instructions = instructions;
  }

  /**
   * Retourne la quantité prescrite.
   *
   * @return la quantité
   */
  public int getQuantite() {
    return quantite;
  }

  /**
   * Modifie la quantité prescrite.
   *
   * @param quantite la nouvelle quantité
   */
  public void setQuantite(int quantite) {
    this.quantite = quantite;
  }

  /**
   * Retourne une représentation textuelle de la ligne de prescription.
   *
   * @return la ligne de prescription formatée
   */
  @Override
  public String toString() {
    return "  → " + medicament.getNom() +
        " | Quantité: " + quantite +
        " | Posologie: " + posologie +
        " | Durée: " + dureeTraitement + " jours" +
        " | Instructions: " + instructions;
  }
}
