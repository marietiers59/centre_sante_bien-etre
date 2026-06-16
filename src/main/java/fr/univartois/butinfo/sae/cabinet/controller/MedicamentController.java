package fr.univartois.butinfo.sae.cabinet.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.util.Optional;

import fr.univartois.butinfo.sae.cabinet.modele.domaine.Medicament;
import fr.univartois.butinfo.sae.cabinet.modele.service.GestionMedicaments;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextInputDialog;
import javafx.stage.Stage;

public class MedicamentController {

	    @FXML
	    private Button ajouter;

	    @FXML
	    private Label idLabel;

	    @FXML
	    private Label nomLabel;

	    @FXML
	    private Label prixLabel;

	    @FXML
	    private Label descriptionLabel;

	    @FXML
	    private ListView<Medicament> medicamentListView;
	    
	    @FXML
	    private Button supprimer;

	    private GestionMedicaments gestionMedicaments;

	    /**
	     * Stocke l'instance du modèle, lie la ListView aux données, configure le format d'affichage
	     * des cellules et met en place le listener de sélection.
	     *
	     * @param gestionMedicaments Le service de gestion des médicaments.
	     */
	    public void setModel(GestionMedicaments gestionMedicaments) {
	        this.gestionMedicaments = gestionMedicaments;

	     // 1. Liaison de la ListView avec la liste de médicaments
	        medicamentListView.setItems(this.gestionMedicaments.getListeMedicaments());
	        medicamentListView.setCellFactory(list -> new ListCell<>() {
	            @Override
	            protected void updateItem(Medicament medicament, boolean empty) {
	                super.updateItem(medicament, empty);
	                if (empty || (medicament == null)) {
	                    setText(null);
	                } else {
	                    setText(medicament.getNom() + " (" + String.format("%.2f", medicament.getPrix()) + " €)");
	                }
	            }
	        });

	        medicamentListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
	            afficherDetailsMedicament(newValue);
	        });
	    }

	    /**
	     * Met à jour les composants textuels de la vue avec les informations du médicament sélectionné.
	     *
	     * @param medicament Le médicament sélectionné, ou null si la sélection est vide.
	     */
	    private void afficherDetailsMedicament(Medicament medicament) {
	        if (medicament == null) {
	            idLabel.setText("");
	            nomLabel.setText("");
	            prixLabel.setText("");
	            descriptionLabel.setText("");
	        } else {
	            idLabel.setText("ID : " + medicament.getId());
	            nomLabel.setText("Nom : " + medicament.getNom());
	            prixLabel.setText("Prix : " + String.format("%.2f €", medicament.getPrix()));
	            descriptionLabel.setText("Description : " + medicament.getDescription());
	        }
	    }
	    
	    /**
	     * Méthode liée au bouton Ajouter. 
	     * Ouvre des boîtes de dialogue successives pour saisir les informations du nouveau médicament.
	     */
	    @FXML
	    private void ajouterMedicament() {
	        // 1. Saisie du Nom
	        TextInputDialog dialogNom = new TextInputDialog();
	        dialogNom.setTitle("Nouveau médicament");
	        dialogNom.setHeaderText("Ajout d'un produit thérapeutique");
	        dialogNom.setContentText("Entrez le nom du médicament :");
	        Optional<String> resultatNom = dialogNom.showAndWait();
	        if (resultatNom.isEmpty() || resultatNom.get().trim().isEmpty()) return;

	        // 2. Saisie de la Description
	        TextInputDialog dialogDesc = new TextInputDialog();
	        dialogDesc.setTitle("Nouveau médicament");
	        dialogDesc.setHeaderText("Description thérapeutique");
	        dialogDesc.setContentText("Entrez sa description :");
	        Optional<String> resultatDesc = dialogDesc.showAndWait();
	        if (resultatDesc.isEmpty() || resultatDesc.get().trim().isEmpty()) return;

	        // 3. Saisie du Prix
	        TextInputDialog dialogPrix = new TextInputDialog("0.00");
	        dialogPrix.setTitle("Nouveau médicament");
	        dialogPrix.setHeaderText("Tarification");
	        dialogPrix.setContentText("Entrez le prix unitaire (€) :");
	        Optional<String> resultatPrix = dialogPrix.showAndWait();
	        if (resultatPrix.isEmpty()) return;

	        try {
	            double prix = Double.parseDouble(resultatPrix.get().replace(',', '.'));
	            
	            // Création et insertion via le service métier
	            Medicament nouveau = new Medicament(resultatNom.get().trim(), resultatDesc.get().trim(), prix);
	            gestionMedicaments.ajouterMedicament(nouveau);
	            
	            // Auto-sélection du médicament nouvellement créé
	            medicamentListView.getSelectionModel().select(nouveau);

	        } catch (NumberFormatException e) {
	            // En cas de saisie invalide pour le prix
	            System.err.println("Erreur : Le prix saisi n'est pas un nombre valide.");
	        }
	    }

	    /**
	     * Méthode pour supprimer le médicament sélectionné dans la ListView.
	     */
	    @FXML
	    private void supprimerMedicament() {
	        // Récupération de l'élément sélectionné
	        Medicament selection = medicamentListView.getSelectionModel().getSelectedItem();
	        
	        if (selection != null) {
	            // Suppression via le service d'administration
	            gestionMedicaments.supprimerMedicament(selection.getId());
	            
	            // Nettoyage de la sélection et des labels
	            medicamentListView.getSelectionModel().clearSelection();
	            afficherDetailsMedicament(null);
	        }
	    }
	    
	    private Scene sceneAccueil;

	    public void setSceneAccueil(Scene sceneAccueil) {
	        this.sceneAccueil = sceneAccueil;
	    }

	    /**
	     * Méthode à lier à un nouveau bouton "Retour"
	     */
	    @FXML
	    private void retournerAccueil(ActionEvent event) {
	        if (sceneAccueil != null) {
	            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
	            stage.setScene(sceneAccueil);
	        }
	    }
}
