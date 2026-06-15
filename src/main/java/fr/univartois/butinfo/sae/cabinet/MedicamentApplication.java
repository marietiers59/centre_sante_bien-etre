package fr.univartois.butinfo.sae.cabinet;

import fr.univartois.butinfo.sae.cabinet.controller.MedicamentControlleur;
import fr.univartois.butinfo.sae.cabinet.modele.domaine.Medicament;
import fr.univartois.butinfo.sae.cabinet.modele.service.GestionMedicaments;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Classe principale de l'application JavaFX pour la gestion des médicaments.
 * @author Loan Bilcke
 * @version 1.0
 */
public class MedicamentApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        // 1. Initialisation du modèle avec des données initiales de test
        GestionMedicaments gestionMedicaments = new GestionMedicaments();
        gestionMedicaments.ajouterMedicament(new Medicament("Doliprane 1000mg", "Soulage douleur et fièvre. Contient paracétamol.", 2.10));
        gestionMedicaments.ajouterMedicament(new Medicament("Amoxicilline 500mg", "Antibiotique à large spectre.", 4.35));
        gestionMedicaments.ajouterMedicament(new Medicament("Spasfon", "Antispasmodique pour les douleurs abdominales.", 3.20));

        // 2. Chargement du fichier FXML de la vue
     // Syntaxe avec le ClassLoader (chemin sans le slash '/' au début)
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("view/medicaments-view.fxml"));
        Parent root = fxmlLoader.load();

        // 3. Récupération du contrôleur et injection du service de gestion
        MedicamentControlleur controller = fxmlLoader.getController();
        controller.setModel(gestionMedicaments);

        // 4. Création de la scène et affichage de la fenêtre principale
        Scene scene = new Scene(root, 800, 600);
        stage.setTitle("L'Éveil des Sources - Gestion des Médicaments");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}