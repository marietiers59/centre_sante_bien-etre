package fr.univartois.butinfo.sae.cabinet.service;

import static org.assertj.core.api.Assertions.assertThat;


import fr.univartois.butinfo.sae.cabinet.modele.domaine.Patient;
import fr.univartois.butinfo.sae.cabinet.modele.service.GestionPatients;

import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.Test;

class GestionPatientsTest {

	private Patient creerPatient() {
		  return new Patient(
		      "Martin",
		      "Paul",
		      "0606060606",
		      "paul@mail.fr",
		      "Adresse test",
		      LocalDate.of(2000, 1, 1),
		      "1234567890123",
		      0.70
		  );
		}

  @Test
  void testAjouterPatient() {
    GestionPatients gestion = new GestionPatients();
    Patient patient = creerPatient();

    gestion.ajouterPatient(patient);

    assertThat(gestion.getNombrePatients()).isEqualTo(1);
    assertThat(gestion.listerTousLesPatients()).contains(patient);
  }

  @Test
  void testAjouterPatientDoublonNumeroSecu() {
    GestionPatients gestion = new GestionPatients();
    Patient patient1 = creerPatient();
    Patient patient2 = creerPatient();

    gestion.ajouterPatient(patient1);
    gestion.ajouterPatient(patient2);

    assertThat(gestion.getNombrePatients()).isEqualTo(1);
  }

  @Test
  void testRechercherParId() {
    GestionPatients gestion = new GestionPatients();
    Patient patient = creerPatient();

    gestion.ajouterPatient(patient);

    assertThat(gestion.rechercherParId(patient.getId())).isEqualTo(patient);
  }

  @Test
  void testRechercherParIdInexistant() {
    GestionPatients gestion = new GestionPatients();

    assertThat(gestion.rechercherParId(999)).isNull();
  }

  @Test
  void testRechercherParNumeroSecu() {
    GestionPatients gestion = new GestionPatients();
    Patient patient = creerPatient();

    gestion.ajouterPatient(patient);

    assertThat(gestion.rechercherParNumeroSecu("1234567890123")).isEqualTo(patient);
  }

  @Test
  void testRechercherParNom() {
    GestionPatients gestion = new GestionPatients();
    Patient patient = creerPatient();

    gestion.ajouterPatient(patient);

    List<Patient> resultats = gestion.rechercherParNom("mar");

    assertThat(resultats).hasSize(1);
    assertThat(resultats).contains(patient);
  }

  @Test
  void testMettreAJourPatient() {
    GestionPatients gestion = new GestionPatients();
    Patient patient = creerPatient();

    gestion.ajouterPatient(patient);

    gestion.mettreAJourPatient(
        patient.getId(),
        "Durand",
        "Lucas",
        "0707070707",
        "lucas@mail.fr",
        "Nouvelle adresse",
        0.80
    );

    assertThat(patient.getNom()).isEqualTo("Durand");
    assertThat(patient.getPrenom()).isEqualTo("Lucas");
    assertThat(patient.getTelephone()).isEqualTo("0707070707");
    assertThat(patient.getEmail()).isEqualTo("lucas@mail.fr");
    assertThat(patient.getAdresse()).isEqualTo("Nouvelle adresse");
    assertThat(patient.getTauxRemboursement()).isEqualTo(0.80);
  }

  @Test
  void testSupprimerPatient() {
    GestionPatients gestion = new GestionPatients();
    Patient patient = creerPatient();

    gestion.ajouterPatient(patient);
    gestion.supprimerPatient(patient.getId());

    assertThat(gestion.getNombrePatients()).isZero();
  }

  @Test
  void testSupprimerPatientInexistant() {
    GestionPatients gestion = new GestionPatients();

    gestion.supprimerPatient(999);

    assertThat(gestion.getNombrePatients()).isZero();
  }
}