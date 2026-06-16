package fr.univartois.butinfo.sae.cabinet;

import fr.univartois.butinfo.sae.cabinet.controller.MedecinsController;
import fr.univartois.butinfo.sae.cabinet.enumeration.Specialite;
import fr.univartois.butinfo.sae.cabinet.modele.domaine.Medecin;
import fr.univartois.butinfo.sae.cabinet.modele.service.GestionMedecins;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MedecinsApplication extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        FXMLLoader loader =
                new FXMLLoader(getClass().getResource("/medecins-view.fxml"));

        BorderPane root = loader.load();

        MedecinsController controller = loader.getController();

        GestionMedecins gestionMedecins = new GestionMedecins();

        gestionMedecins.ajouterMedecin(
                new Medecin(
                        "Dupont",
                        "Jean",
                        "0600000001",
                        "jean.dupont@mail.fr",
                        "ORD001",
                        Specialite.GENERALISTE,
                        50));

        gestionMedecins.ajouterMedecin(
                new Medecin(
                        "Martin",
                        "Sophie",
                        "0600000002",
                        "sophie.martin@mail.fr",
                        "ORD002",
                        Specialite.CARDIOLOGUE,
                        80));

        gestionMedecins.ajouterMedecin(
                new Medecin(
                        "Bernard",
                        "Paul",
                        "0600000003",
                        "paul.bernard@mail.fr",
                        "ORD003",
                        Specialite.DERMATOLOGUE,
                        70));

        controller.setGestionMedecins(gestionMedecins);

        Scene scene = new Scene(root);
        controller.setStageEtScene(stage, scene);

        stage.setTitle("Gestion des médecins");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}