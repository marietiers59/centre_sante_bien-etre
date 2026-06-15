package fr.univartois.butinfo.sae.cabinet;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;

import fr.univartois.butinfo.sae.cabinet.controller.RDVController;
import fr.univartois.butinfo.sae.cabinet.enumeration.Specialite;
import fr.univartois.butinfo.sae.cabinet.modele.domaine.Medecin;
import fr.univartois.butinfo.sae.cabinet.modele.domaine.Patient;
import fr.univartois.butinfo.sae.cabinet.modele.service.GestionRendezVous;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Classe principale de l'application JavaFX pour la gestion des rendez-vous.
 *
 * <p>Initialise un jeu de données de test (patients, médecins et rendez-vous),
 * charge la vue {@code gestionRDV-view.fxml}, injecte le service dans le contrôleur
 * puis affiche la fenêtre principale.</p>
 *
 * <p>Pour tester le défilement entre plusieurs fiches, deux rendez-vous sont planifiés
 * le même jour (le {@value #DATE_TEST_MULTI} à {@value #HEURE_RDV1} et
 * {@value #HEURE_RDV2}) : sélectionnez cette date dans le DatePicker pour voir
 * les deux {@code ListView} empilées.</p>
 *
 * @author Marie Tiers
 * @version 1.0
 */
public class RDVApplication extends Application {

    /** Date commune à deux rendez-vous de test (format ISO : {@code 2026-06-15}). */
    private static final String DATE_TEST_MULTI = "2026-06-15";

    /** Heure du premier rendez-vous de test. */
    private static final String HEURE_RDV1 = "09:00";

    /** Heure du deuxième rendez-vous de test. */
    private static final String HEURE_RDV2 = "10:30";

    /**
     * Point d'entrée JavaFX. Construit le modèle, charge la vue FXML,
     * injecte le service dans le contrôleur et affiche la fenêtre.
     *
     * @param stage la fenêtre principale fournie par JavaFX
     * @throws IOException si le fichier FXML est introuvable ou mal formé
     */
    @Override
    public void start(Stage stage) throws IOException {

        // 1. Initialisation des données de test

        Patient p1 = new Patient("Leblanc", "Paul", "0612345678", "p.leblanc@mail.fr",
                "12 rue des Lilas, Arras", LocalDate.of(1985, 3, 22), "185036562012345", 0.50);
        Patient p2 = new Patient("Moreau", "Claire", "0687654321", "c.moreau@mail.fr",
                "8 avenue Foch, Lens", LocalDate.of(1995, 7, 15), "295076210054321");
        Patient p3 = new Patient("Petit", "Antoine", "0655443322", "a.petit@mail.fr",
                "3 impasse du Moulin, Douai", LocalDate.of(2010, 11, 5), "210116210011122", 0.60);

        Medecin dr1 = new Medecin("Dupont", "Jean", "0312345678", "j.dupont@cabinet.fr",
                "ORD-001", Specialite.GENERALISTE, 30.0);
        Medecin dr2 = new Medecin("Martin", "Sophie", "0398765432", "s.martin@cabinet.fr",
                "ORD-002", Specialite.CARDIOLOGUE, 50.0);

        GestionRendezVous gestionRdv = new GestionRendezVous();

        // Deux RDV le même jour pour tester le défilement entre fiches
        gestionRdv.prendreRendezVous(p1, dr1,
                LocalDateTime.of(2026, 6, 15, 9, 0), 30, "Consultation de routine");
        gestionRdv.prendreRendezVous(p2, dr2,
                LocalDateTime.of(2026, 6, 15, 10, 30), 45, "Bilan cardiaque");

        // Un RDV sur une autre date
        gestionRdv.prendreRendezVous(p3, dr1,
                LocalDateTime.of(2026, 6, 20, 14, 0), 20, "Vaccination enfant");

        // 2. Chargement du fichier FXML de la vue
        FXMLLoader fxmlLoader = new FXMLLoader(
                getClass().getClassLoader().getResource("view/gestionRDV-view.fxml"));
        Parent root = fxmlLoader.load();

        // 3. Récupération du contrôleur et injection du service de gestion
        RDVController controller = fxmlLoader.getController();
        controller.setGestionRendezVous(gestionRdv);

        // 4. Création de la scène et affichage de la fenêtre principale
        Scene scene = new Scene(root, 730, 546);
        stage.setTitle("L'Éveil des Sens - Gestion des Rendez-vous");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Point d'entrée Java. Lance l'application JavaFX.
     *
     * @param args arguments de la ligne de commande (non utilisés)
     */
    public static void main(String[] args) {
        launch();
    }
}
