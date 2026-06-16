package fr.univartois.butinfo.sae.cabinet.enumeration;

/**
 * Énumération représentant les différentes spécialités médicales disponibles 
 * au sein du cabinet médical.
 *
 * @version 1.0
 */
public enum Specialite {

    /**
     * Spécialité de médecine générale.
     */
    GENERALISTE("Médecin généraliste"),

    /**
     * Spécialité de cardiologie.
     */
    CARDIOLOGUE("Cardiologie"),

    /**
     * Spécialité de dermatologie.
     */
    DERMATOLOGUE("Dermatologie"),

    /**
     * Spécialité de gynécologie.
     */
    GYNECOLOGUE("Gynécologie"),

    /**
     * Spécialité de neurologie.
     */
    NEUROLOGUE("Neurologie"),

    /**
     * Spécialité de ophtalmologie.
     */
    OPHTALMOLOGUE("Ophtalmologie"),

    /**
     * Spécialité de pédiatrie.
     */
    PEDIATRE("Pédiatrie"),

    /**
     * Spécialité de psychiatrie.
     */
    PSYCHIATRE("Psychiatrie"),

    /**
     * Spécialité de radiologie.
     */
    RADIOLOGUE("Radiologie"),

    /**
     * Spécialité de médecine d'urgence.
     */
    URGENTISTE("Urgences");

    /**
     * Le libellé complet et lisible de la spécialité médicale.
     */
    private final String libelle;

    /**
     * Constructeur interne de l'énumération permettant d'associer un libellé 
     * à une spécialité.
     *
     * @param libelle Le nom complet et affichable de la spécialité.
     */
    Specialite(String libelle) {
        this.libelle = libelle;
    }

    /**
     * Retourne le libellé textuel de la spécialité médicale.
     *
     * @return Le nom complet de la spécialité sous forme de chaîne de caractères.
     */
    public String getLibelle() {
        return libelle;
    }

    /**
     * Renvoie la représentation textuelle de la spécialité, correspondant à son libellé.
     *
     * @return Une chaîne de caractères représentant la spécialité.
     */
    @Override
    public String toString() {
        return libelle;
    }
}