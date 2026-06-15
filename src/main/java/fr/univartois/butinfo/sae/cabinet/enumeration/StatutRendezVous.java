package fr.univartois.butinfo.sae.cabinet.enumeration;

/**
 * Énumération des statuts possibles d'un rendez-vous.
 */
public enum StatutRendezVous {
	/**
     * Statut planifié.
     */
    PLANIFIE("Planifié"),
    /**
     * Statut confirmé.
     */
    CONFIRME("Confirmé"),
    /**
     * Statut annulé.
     */
    ANNULE("Annulé"),
    /**
     * Statut terminé.
     */
    TERMINE("Terminé");
	
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
    StatutRendezVous(String libelle) {
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
