package fr.univartois.butinfo.sae.cabinet.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import fr.univartois.butinfo.sae.cabinet.modele.domaine.Medicament;
import fr.univartois.butinfo.sae.cabinet.modele.service.GestionMedicaments;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;

public class MedicamentControlleur {

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

}
