package fr.univartois.butinfo.sae.cabinet.modele;

import java.time.LocalDate;
import java.time.Period;

/**
 * Classe représentant un patient du cabinet médical.
 * Hérite de la classe abstraite Personne.
 */
public class Patient extends Personne {

    /** Adresse postale du patient. */
    private String adresse;

    /** Date de naissance du patient. */
    private LocalDate dateNaissance;

    /** Numéro de sécurité sociale, immuable une fois défini. */
    private final String numeroSecu;

    /** Taux de remboursement par la sécurité sociale (valeur entre 0 et 1). */
    private double tauxRemboursement;

    /**
     * Constructeur complet d'un Patient.
     *
     * @param nom               le nom de famille
     * @param prenom            le prénom
     * @param telephone         le numéro de téléphone
     * @param email             l'adresse e-mail
     * @param adresse           l'adresse postale
     * @param dateNaissance     la date de naissance
     * @param numeroSecu        le numéro de sécurité sociale
     * @param tauxRemboursement le taux de remboursement (entre 0 et 1)
     */
    public Patient(String nom, String prenom, String telephone, String email,
                   String adresse, LocalDate dateNaissance, String numeroSecu, double tauxRemboursement) {
        super(nom, prenom, telephone, email);
        this.adresse = adresse;
        this.dateNaissance = dateNaissance;
        this.numeroSecu = numeroSecu;
        this.tauxRemboursement = tauxRemboursement;
    }

    /**
     * Constructeur simplifié avec un taux de remboursement par défaut de 75 %.
     *
     * @param nom           le nom de famille
     * @param prenom        le prénom
     * @param telephone     le numéro de téléphone
     * @param email         l'adresse e-mail
     * @param adresse       l'adresse postale
     * @param dateNaissance la date de naissance
     * @param numeroSecu    le numéro de sécurité sociale
     */
    public Patient(String nom, String prenom, String telephone, String email,
                   String adresse, LocalDate dateNaissance, String numeroSecu) {
        this(nom, prenom, telephone, email, adresse, dateNaissance, numeroSecu, .75);
    }

    // -------------------------
    // Getters et Setters
    // -------------------------

    /**
     * Retourne l'adresse postale du patient.
     *
     * @return l'adresse
     */
    public String getAdresse() {
        return adresse;
    }

    /**
     * Modifie l'adresse postale du patient.
     *
     * @param adresse la nouvelle adresse
     */
    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    /**
     * Retourne la date de naissance du patient.
     *
     * @return la date de naissance
     */
    public LocalDate getDateNaissance() {
        return dateNaissance;
    }

    /**
     * Modifie la date de naissance du patient.
     *
     * @param dateNaissance la nouvelle date de naissance
     */
    public void setDateNaissance(LocalDate dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    /**
     * Retourne le numéro de sécurité sociale du patient.
     * Ce champ est immuable une fois le patient créé.
     *
     * @return le numéro de sécurité sociale
     */
    public String getNumeroSecu() {
        return numeroSecu;
    }

    /**
     * Retourne le taux de remboursement du patient.
     *
     * @return le taux de remboursement (entre 0 et 1)
     */
    public double getTauxRemboursement() {
        return tauxRemboursement;
    }

    /**
     * Modifie le taux de remboursement du patient.
     *
     * @param tauxRemboursement le nouveau taux (entre 0 et 1)
     */
    public void setTauxRemboursement(double tauxRemboursement) {
        this.tauxRemboursement = tauxRemboursement;
    }

    /**
     * Calcule et retourne l'âge actuel du patient en années.
     *
     * @return l'âge en années
     */
    public int getAge() {
        return Period.between(dateNaissance, LocalDate.now()).getYears();
    }

    /**
     * Indique si deux patients sont égaux (même identifiant et même numéro de sécurité sociale).
     *
     * @param obj l'objet à comparer
     * @return {@code true} si les deux patients sont identiques
     */
    @Override
    public boolean equals(Object obj) {
        if (!super.equals(obj)) return false;
        Patient autre = (Patient) obj;
        return this.numeroSecu.equals(autre.numeroSecu);
    }

    /**
     * Retourne le code de hachage basé sur l'identifiant et le numéro de sécurité sociale.
     *
     * @return le code de hachage
     */
    @Override
    public int hashCode() {
        return super.hashCode() * 31 + numeroSecu.hashCode();
    }

    /**
     * Retourne une représentation textuelle du patient.
     *
     * @return le patient formaté
     */
    @Override
    public String toString() {
        return "Patient  { " + super.toString() +
               " | Naissance: " + dateNaissance +
               " (" + getAge() + " ans)" +
               " | Adresse: " + adresse +
               " | Sécu: " + numeroSecu +
               " | Taux: " + tauxRemboursement + " }";
    }
}
