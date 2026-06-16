package fr.univartois.butinfo.sae.cabinet;

import fr.univartois.butinfo.sae.cabinet.controller.AccueilController;
import fr.univartois.butinfo.sae.cabinet.enumeration.Specialite;
import fr.univartois.butinfo.sae.cabinet.modele.domaine.Medecin;
import fr.univartois.butinfo.sae.cabinet.modele.domaine.Medicament;
import fr.univartois.butinfo.sae.cabinet.modele.domaine.Patient;
import fr.univartois.butinfo.sae.cabinet.modele.service.CabinetFacade;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class CabinetApplication extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        CabinetFacade facade = new CabinetFacade();

        // Médicaments
        facade.getGestionMedicaments().ajouterMedicament(new Medicament("Doliprane 1000mg", "Antalgique", 2.10));
        facade.getGestionMedicaments().ajouterMedicament(new Medicament("Amoxicilline 500mg", "Antibiotique", 4.35));

        // Patients
        facade.getGestionPatients().ajouterPatient(new Patient(
            "Dupont", "Jean",
            "0601020304", "jean.dupont@mail.com",
            "12 rue de la Paix, Lens",
            LocalDate.of(1985, 3, 12),
            "185036200101234"
        ));
        facade.getGestionPatients().ajouterPatient(new Patient(
            "Martin", "Sophie",
            "0607080910", "sophie.martin@mail.com",
            "8 avenue Foch, Arras",
            LocalDate.of(1992, 7, 24),
            "292076200598765"
        ));

        // Médecins
        facade.getGestionMedecins().ajouterMedecin(new Medecin(
            "Bernard", "Paul",
            "0320010203", "paul.bernard@cabinet.fr",
            "ORD-001",
            Specialite.GENERALISTE
        ));
        facade.getGestionMedecins().ajouterMedecin(new Medecin(
            "Leclerc", "Anne",
            "0320040506", "anne.leclerc@cabinet.fr",
            "ORD-002",
            Specialite.CARDIOLOGUE
        ));

        // Rendez-vous de test
        Patient p1 = facade.getGestionPatients().listerTousLesPatients().get(0);
        Patient p2 = facade.getGestionPatients().listerTousLesPatients().get(1);
        Medecin dr1 = facade.getGestionMedecins().listerTousLesMedecins().get(0);
        Medecin dr2 = facade.getGestionMedecins().listerTousLesMedecins().get(1);

        facade.getGestionRendezVous().prendreRendezVous(
            p1, dr1,
            LocalDateTime.of(2026, 6, 16, 9, 0),
            30, "Consultation de routine"
        );
        facade.getGestionRendezVous().prendreRendezVous(
            p2, dr2,
            LocalDateTime.of(2026, 6, 16, 10, 30),
            45, "Bilan cardiaque"
        );
        facade.getGestionRendezVous().prendreRendezVous(
            p1, dr2,
            LocalDateTime.of(2026, 6, 20, 14, 0),
            20, "Suivi cardiologique"
        );

        FXMLLoader fxmlLoader = new FXMLLoader(
            CabinetApplication.class.getResource("/view/accueil-view.fxml")
        );
        Parent root = fxmlLoader.load();

        Scene sceneAccueil = new Scene(root, 800, 600);
        AccueilController controleur = fxmlLoader.getController();
        controleur.setModel(facade, sceneAccueil);

        stage.setTitle("L'Éveil des Sources - Menu Principal");
        stage.setScene(sceneAccueil);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}