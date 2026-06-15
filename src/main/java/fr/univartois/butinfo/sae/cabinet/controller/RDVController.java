package fr.univartois.butinfo.sae.cabinet.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class RDVController {

	/**
	 * Sample Skeleton for 'gestionRDV-view.fxml' Controller Class
	 */


    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="colorPicker"
    private ColorPicker colorPicker; // Value injected by FXMLLoader

    @FXML // fx:id="datePicker"
    private DatePicker datePicker; // Value injected by FXMLLoader

    @FXML // fx:id="ddnPatient"
    private Label ddnPatient; // Value injected by FXMLLoader

    @FXML // fx:id="ficheRDV"
    private AnchorPane ficheRDV; // Value injected by FXMLLoader

    @FXML // fx:id="idMedecin"
    private Label idMedecin; // Value injected by FXMLLoader

    @FXML // fx:id="idPatient"
    private Label idPatient; // Value injected by FXMLLoader

    @FXML // fx:id="nomMedecin"
    private Label nomMedecin; // Value injected by FXMLLoader

    @FXML // fx:id="nomPatient"
    private Label nomPatient; // Value injected by FXMLLoader

    @FXML // fx:id="prenomMedecin"
    private Label prenomMedecin; // Value injected by FXMLLoader

    @FXML // fx:id="prenomPatient"
    private Label prenomPatient; // Value injected by FXMLLoader

    @FXML // fx:id="speMedecin"
    private Label speMedecin; // Value injected by FXMLLoader

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert colorPicker != null : "fx:id=\"colorPicker\" was not injected: check your FXML file 'gestionRDV-view.fxml'.";
        assert datePicker != null : "fx:id=\"datePicker\" was not injected: check your FXML file 'gestionRDV-view.fxml'.";
        assert ddnPatient != null : "fx:id=\"ddnPatient\" was not injected: check your FXML file 'gestionRDV-view.fxml'.";
        assert ficheRDV != null : "fx:id=\"ficheRDV\" was not injected: check your FXML file 'gestionRDV-view.fxml'.";
        assert idMedecin != null : "fx:id=\"idMedecin\" was not injected: check your FXML file 'gestionRDV-view.fxml'.";
        assert idPatient != null : "fx:id=\"idPatient\" was not injected: check your FXML file 'gestionRDV-view.fxml'.";
        assert nomMedecin != null : "fx:id=\"nomMedecin\" was not injected: check your FXML file 'gestionRDV-view.fxml'.";
        assert nomPatient != null : "fx:id=\"nomPatient\" was not injected: check your FXML file 'gestionRDV-view.fxml'.";
        assert prenomMedecin != null : "fx:id=\"prenomMedecin\" was not injected: check your FXML file 'gestionRDV-view.fxml'.";
        assert prenomPatient != null : "fx:id=\"prenomPatient\" was not injected: check your FXML file 'gestionRDV-view.fxml'.";
        assert speMedecin != null : "fx:id=\"speMedecin\" was not injected: check your FXML file 'gestionRDV-view.fxml'.";

    }




}
