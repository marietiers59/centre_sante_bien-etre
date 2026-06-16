package fr.univartois.butinfo.sae.cabinet.modele.service;

public class CabinetFacade {

    private final GestionMedicaments gestionMedicaments;
    private final GestionMedecins gestionMedecins;
    private final GestionRendezVous gestionRendezVous;
    private final GestionPatients gestionPatients; 

    public CabinetFacade() {
        this.gestionMedicaments = new GestionMedicaments();
        this.gestionMedecins    = new GestionMedecins();
        this.gestionRendezVous  = new GestionRendezVous();
        this.gestionPatients    = new GestionPatients(); 
    }

    public GestionMedicaments getGestionMedicaments() { return gestionMedicaments; }
    public GestionMedecins    getGestionMedecins()    { return gestionMedecins; }
    public GestionRendezVous  getGestionRendezVous()  { return gestionRendezVous; }
    public GestionPatients    getGestionPatients()    { return gestionPatients; }
}