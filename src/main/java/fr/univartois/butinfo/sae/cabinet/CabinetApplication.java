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

public class CabinetApplication extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        CabinetFacade facade = new CabinetFacade();

        // Médicaments
        facade.getGestionMedicaments().ajouterMedicament(new Medicament("Doliprane 1000mg", "Antalgique", 2.10));
        facade.getGestionMedicaments().ajouterMedicament(new Medicament("Amoxicilline 500mg", "Antibiotique", 4.35));

        // Patients — constructeur : (nom, prenom, telephone, email, adresse, dateNaissance, numeroSecu)
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

        // Médecins — constructeur : (nom, prenom, telephone, email, numeroOrdre, specialite)
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