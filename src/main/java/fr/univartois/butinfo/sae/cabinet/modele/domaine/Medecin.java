package fr.univartois.butinfo.sae.cabinet.modele.domaine;
import fr.univartois.butinfo.sae.cabinet.enumeration.Specialite;

/**
 * Classe représentant un médecin du cabinet. Hérite de la classe abstraite Personne.
 */
public class Medecin extends Personne {

  /** Numéro d'identification au Conseil de l'Ordre des Médecins, immuable. */
  private final String numeroOrdre;

  /** Spécialité médicale du médecin. */
  private Specialite specialite;

  /** Tarif horaire de base pour une consultation, en euros. */
  private double tarifHoraire;

  /**
   * Constructeur complet d'un Médecin.
   *
   * @param nom          le nom de famille
   * @param prenom       le prénom
   * @param telephone    le numéro de téléphone
   * @param email        l'adresse e-mail
   * @param numeroOrdre  le numéro d'ordre professionnel
   * @param specialite   la spécialité médicale
   * @param tarifHoraire le tarif horaire en euros
   */
  public Medecin(String nom, String prenom, String telephone, String email,
      String numeroOrdre, Specialite specialite, double tarifHoraire) {
    super(nom, prenom, telephone, email);
    this.numeroOrdre = numeroOrdre;
    this.specialite = specialite;
    this.tarifHoraire = tarifHoraire;
  }

  /**
   * Constructeur simplifié avec un tarif horaire par défaut de 30 €.
   *
   * @param nom         le nom de famille
   * @param prenom      le prénom
   * @param telephone   le numéro de téléphone
   * @param email       l'adresse e-mail
   * @param numeroOrdre le numéro d'ordre professionnel
   * @param specialite  la spécialité médicale
   */
  public Medecin(String nom, String prenom, String telephone, String email,
      String numeroOrdre, Specialite specialite) {
    this(nom, prenom, telephone, email, numeroOrdre, specialite, 30.00);
  }

  // -------------------------
  // Getters et Setters
  // -------------------------

  /**
   * Retourne le numéro d'ordre professionnel du médecin.
   *
   * @return le numéro d'ordre
   */
  public String getNumeroOrdre() {
    return numeroOrdre;
  }

  /**
   * Retourne la spécialité médicale du médecin.
   *
   * @return la spécialité
   */
  public Specialite getSpecialite() {
    return specialite;
  }

  /**
   * Modifie la spécialité médicale du médecin.
   *
   * @param specialite la nouvelle spécialité
   */
  public void setSpecialite(Specialite specialite) {
    this.specialite = specialite;
  }

  /**
   * Retourne le tarif horaire du médecin en euros.
   *
   * @return le tarif horaire
   */
  public double getTarifHoraire() {
    return tarifHoraire;
  }

  /**
   * Modifie le tarif horaire du médecin.
   *
   * @param tarifHoraire le nouveau tarif horaire en euros
   */
  public void setTarifHoraire(double tarifHoraire) {
    this.tarifHoraire = tarifHoraire;
  }

  /**
   * Indique si deux médecins sont égaux (même identifiant et même numéro d'ordre).
   *
   * @param obj l'objet à comparer
   * @return {@code true} si les deux médecins sont identiques
   */
  @Override
  public boolean equals(Object obj) {
    if (!super.equals(obj)) {
      return false;
    }
    Medecin autre = (Medecin) obj;
    return this.numeroOrdre.equals(autre.numeroOrdre);
  }

  /**
   * Retourne le code de hachage basé sur le numéro d'ordre.
   *
   * @return le code de hachage
   */
  @Override
  public int hashCode() {
    return numeroOrdre.hashCode();
  }

  /**
   * Retourne une représentation textuelle du médecin.
   *
   * @return le médecin formaté
   */
  @Override
  public String toString() {
    return "Médecin  { " + super.toString() +
        " | Spécialité: " + specialite.getLibelle() +
        " | N° Ordre: " + numeroOrdre +
        " | tarif: " + tarifHoraire + " }";
  }
}
