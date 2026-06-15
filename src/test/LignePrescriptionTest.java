package fr.univartois.butinfo.sae.cabinet.modele;

/**
 * Classe représentant une ligne de prescription dans une ordonnance médicale.
 * Elle associe un médicament spécifique à une quantité, une posologie, une durée 
 * de traitement et des instructions particulières pour le patient.
 * * @author Loan Bilcke
 * @version 1.0
 */
public class LignePrescription {

  /**
   * Le médicament associé à cette ligne de prescription.
   */
  private final Medicament medicament;

  /**
   * La quantité de boîtes ou d'unités de médicament à délivrer.
   */
  private int quantite;

  /**
   * La posologie décrivant la manière de prendre le médicament (ex: "1 comprimé matin et soir").
   */
  private String posologie;

  /**
   * La durée totale du traitement exprimée en jours.
   */
  private int dureeTraitement;

  /**
   * Les consignes ou instructions particulières destinées au patient.
   */
  private String instructions;

  /**
   * Constructeur complet permettant d'initialiser une ligne de prescription avec l'ensemble de ses détails.
   *
   * @param medicament      Le médicament prescrit.
   * @param quantite        La quantité à délivrer.
   * @param posologie       La posologie du traitement.
   * @param dureeTraitement La durée du traitement en jours.
   * @param instructions    Les consignes particulières pour le patient.
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
   * Constructeur simplifié initialisant une ligne de prescription avec une quantité par défaut de 1 
   * et une mention d'absence d'instructions particulières.
   *
   * @param medicament      Le médicament prescrit.
   * @param posologie       La posologie du traitement.
   * @param dureeTraitement La durée du traitement en jours.
   */
  public LignePrescription(Medicament medicament, String posologie, int dureeTraitement) {
    this(medicament, 1, posologie, dureeTraitement, "Aucune instruction particulière");
  }

  /**
   * Retourne le médicament de la prescription.
   *
   * @return Le médicament prescrit.
   */
  public Medicament getMedicament() {
    return medicament;
  }

  /**
   * Retourne la posologie de la prescription.
   *
   * @return La description de la posologie.
   */
  public String getPosologie() {
    return posologie;
  }

  /**
   * Modifie la posologie de la prescription.
   *
   * @param posologie La nouvelle posologie.
   */
  public void setPosologie(String posologie) {
    this.posologie = posologie;
  }

  /**
   * Retourne la durée du traitement.
   *
   * @return La durée en jours.
   */
  public int getDureeTraitement() {
    return dureeTraitement;
  }

  /**
   * Modifie la durée du traitement.
   *
   * @param dureeTraitement La nouvelle durée en jours.
   */
  public void setDureeTraitement(int dureeTraitement) {
    this.dureeTraitement = dureeTraitement;
  }

  /**
   * Retourne les instructions particulières de la prescription.
   *
   * @return Les consignes textuelles.
   */
  public String getInstructions() {
    return instructions;
  }

  /**
   * Modifie les instructions particulières de la prescription.
   *
   * @param instructions Les nouvelles consignes particulières.
   */
  public void setInstructions(String instructions) {
    this.instructions = instructions;
  }

  /**
   * Retourne la quantité de médicaments à délivrer.
   *
   * @return La quantité (nombre d'unités/boîtes).
   */
  public int getQuantite() {
    return quantite;
  }

  /**
   * Modifie la quantité de médicaments à délivrer.
   *
   * @param quantite La nouvelle quantité.
   */
  public void setQuantite(int quantite) {
    this.quantite = quantite;
  }

  /**
   * Renvoie une représentation textuelle de la ligne de prescription, affichant ses caractéristiques principales.
   *
   * @return Une chaîne de caractères décrivant la ligne de prescription.
   */
  @Override
  public String toString() {
    return "  → " + medicament.getNom() +
        " | Quantité: " + quantite +
        " | Posologie: " + posologie +
        " | Durée: " + dureeTrait