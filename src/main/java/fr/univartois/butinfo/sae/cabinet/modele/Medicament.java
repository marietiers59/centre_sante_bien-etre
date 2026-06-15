package fr.univartois.butinfo.sae.cabinet.modele;

/**
 * Classe représentant un médicament disponible dans le système.
 */
public class Medicament {

  /** Identifiant unique du médicament, généré automatiquement. */
  private final int id;

  /** Nom commercial du médicament. */
  private String nom;

  /** Description ou indication thérapeutique du médicament. */
  private String description;

  /** Prix unitaire du médicament en euros. */
  private double prix;

  /** Compteur statique pour générer des identifiants uniques. */
  private static int compteurId = 1;

  /**
   * Constructeur d'un Médicament.
   *
   * @param nom         le nom commercial
   * @param description la description ou indication thérapeutique
   * @param prix        le prix unitaire en euros
   */
  public Medicament(String nom, String description, double prix) {
    this.id = compteurId++;
    this.nom = nom;
    this.description = description;
    this.prix = prix;
  }

  // -------------------------
  // Getters et Setters
  // -------------------------

  /**
   * Retourne l'identifiant unique du médicament.
   *
   * @return l'identifiant
   */
  public int getId() {
    return id;
  }

  /**
   * Retourne le nom commercial du médicament.
   *
   * @return le nom
   */
  public String getNom() {
    return nom;
  }

  /**
   * Modifie le nom commercial du médicament.
   *
   * @param nom le nouveau nom
   */
  public void setNom(String nom) {
    this.nom = nom;
  }

  /**
   * Retourne la description ou indication thérapeutique du médicament.
   *
   * @return la description
   */
  public String getDescription() {
    return description;
  }

  /**
   * Modifie la description du médicament.
   *
   * @param description la nouvelle description
   */
  public void setDescription(String description) {
    this.description = description;
  }

  /**
   * Retourne le prix unitaire du médicament en euros.
   *
   * @return le prix
   */
  public double getPrix() {
    return prix;
  }

  /**
   * Modifie le prix unitaire du médicament.
   *
   * @param prix le nouveau prix en euros
   */
  public void setPrix(double prix) {
    this.prix = prix;
  }

  /**
   * Indique si deux médicaments sont égaux (même nom et même prix).
   *
   * @param obj l'objet à comparer
   * @return {@code true} si les deux médicaments sont identiques
   */
  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null || getClass() != obj.getClass()) {
      return false;
    }
    Medicament autre = (Medicament) obj;
    return this.nom.equalsIgnoreCase(autre.nom) &&
        this.prix == autre.prix;
  }

  /**
   * Retourne le code de hachage basé sur le nom et le prix.
   *
   * @return le code de hachage
   */
  @Override
  public int hashCode() {
    int result = nom.toLowerCase().hashCode();
    result = 31 * result + Double.hashCode(prix);
    return result;
  }

  /**
   * Retourne une représentation textuelle du médicament.
   *
   * @return le médicament formaté
   */
  @Override
  public String toString() {
    return "Médicament { [ID=" + id + "] " + nom +
        " | Prix: " + prix +
        " | " + description + " }";
  }
}
