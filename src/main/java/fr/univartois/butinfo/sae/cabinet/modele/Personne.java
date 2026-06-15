package fr.univartois.butinfo.sae.cabinet.modele;

/**
 * Classe abstraite représentant une personne (base commune pour Patient et Medecin).
 * Applique le principe d'encapsulation et sert de superclasse pour l'héritage.
 */
public abstract class Personne {

    /** Identifiant unique de la personne, généré automatiquement. */
    private final int id;

    /** Nom de famille. */
    protected String nom;

    /** Prénom. */
    protected String prenom;

    /** Numéro de téléphone. */
    protected String telephone;

    /** Adresse e-mail. */
    protected String email;

    /** Compteur statique pour générer des identifiants uniques. */
    private static int compteurId = 1;

    /**
     * Constructeur d'une Personne.
     *
     * @param nom       le nom de famille
     * @param prenom    le prénom
     * @param telephone le numéro de téléphone
     * @param email     l'adresse e-mail
     */
    public Personne(String nom, String prenom, String telephone, String email) {
        this.id = compteurId++;
        this.nom = nom;
        this.prenom = prenom;
        this.telephone = telephone;
        this.email = email;
    }

    // -------------------------
    // Getters et Setters
    // -------------------------

    /**
     * Retourne l'identifiant unique de la personne.
     *
     * @return l'identifiant
     */
    public int getId() {
        return id;
    }

    /**
     * Retourne le nom de famille.
     *
     * @return le nom
     */
    public String getNom() {
        return nom;
    }

    /**
     * Modifie le nom de famille.
     *
     * @param nom le nouveau nom
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Retourne le prénom.
     *
     * @return le prénom
     */
    public String getPrenom() {
        return prenom;
    }

    /**
     * Modifie le prénom.
     *
     * @param prenom le nouveau prénom
     */
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    /**
     * Retourne le numéro de téléphone.
     *
     * @return le téléphone
     */
    public String getTelephone() {
        return telephone;
    }

    /**
     * Modifie le numéro de téléphone.
     *
     * @param telephone le nouveau numéro
     */
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    /**
     * Retourne l'adresse e-mail.
     *
     * @return l'e-mail
     */
    public String getEmail() {
        return email;
    }

    /**
     * Modifie l'adresse e-mail.
     *
     * @param email la nouvelle adresse e-mail
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Retourne le nom complet (prénom suivi du nom).
     *
     * @return le nom complet
     */
    public String getNomComplet() {
        return prenom + " " + nom;
    }

    /**
     * Indique si deux personnes sont égales (même identifiant).
     *
     * @param obj l'objet à comparer
     * @return {@code true} si les deux personnes ont le même identifiant
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Personne autre = (Personne) obj;
        return this.id == autre.id;
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
     * Retourne une représentation textuelle de la personne.
     *
     * @return la personne formatée
     */
    @Override
    public String toString() {
        return "[ID=" + id + "] " + prenom + " " + nom +
               " | Tél: " + telephone +
               " | Email: " + email;
    }
}
