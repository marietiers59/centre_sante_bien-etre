package fr.univartois.butinfo.sae.cabinet.service;

import static org.assertj.core.api.Assertions.assertThat;


import fr.univartois.butinfo.sae.cabinet.enumeration.Specialite;
import fr.univartois.butinfo.sae.cabinet.modele.domaine.Medecin;
import fr.univartois.butinfo.sae.cabinet.modele.domaine.Medicament;
import fr.univartois.butinfo.sae.cabinet.modele.domaine.Ordonnance;
import fr.univartois.butinfo.sae.cabinet.modele.domaine.Patient;
import fr.univartois.butinfo.sae.cabinet.modele.service.GestionOrdonnances;

import java.time.LocalDate;
import org.junit.jupiter.api.Test;

class GestionOrdonnancesTest {

  @Test
  void testCreerOrdonnance() {
    GestionOrdonnances gestion = new GestionOrdonnances();

    Medecin medecin = new Medecin(
        "Dupont", "Jean", "0102030405",
        "jean@mail.fr", "ORD123",
        Specialite.GENERALISTE);

    Patient patient = new Patient(
    	    "Martin",
    	    "Paul",
    	    "0606060606",
    	    "paul@mail.fr",
    	    "1234567890123",
    	    LocalDate.of(2000, 1, 1),
    	    "Adresse test",
    	    0.70
    	);

    Ordonnance ordonnance = gestion.creerOrdonnance(
        medecin,
        patient,
        LocalDate.now().plusMonths(3),
        "RAS"
    );

    assertThat(ordonnance).isNotNull();
    assertThat(gestion.getNombreOrdonnances()).isEqualTo(1);
  }

  @Test
  void testRechercheParId() {
    GestionOrdonnances gestion = new GestionOrdonnances();

    Medecin medecin = new Medecin(
        "Dupont", "Jean", "0102030405",
        "jean@mail.fr", "ORD123",
        Specialite.GENERALISTE);

    Patient patient = new Patient(
    	    "Martin",
    	    "Paul",
    	    "0606060606",
    	    "paul@mail.fr",
    	    "1234567890123",
    	    LocalDate.of(2000, 1, 1),
    	    "Adresse test",
    	    0.70
    	);

    Ordonnance ordonnance = gestion.creerOrdonnance(
        medecin,
        patient,
        LocalDate.now().plusMonths(3),
        "RAS"
    );

    assertThat(
        gestion.rechercherParId(ordonnance.getId())
    ).isEqualTo(ordonnance);
  }

  @Test
  void testAjouterPrescription() {
    GestionOrdonnances gestion = new GestionOrdonnances();

    Medecin medecin = new Medecin(
        "Dupont", "Jean", "0102030405",
        "jean@mail.fr", "ORD123",
        Specialite.GENERALISTE);

    Patient patient = new Patient(
    	    "Martin",
    	    "Paul",
    	    "0606060606",
    	    "paul@mail.fr",
    	    "1234567890123",
    	    LocalDate.of(2000, 1, 1),
    	    "Adresse test",
    	    0.70
    	);

    Ordonnance ordonnance = gestion.creerOrdonnance(
        medecin,
        patient,
        LocalDate.now().plusMonths(3),
        "RAS"
    );

    Medicament medicament =
        new Medicament("Doliprane", "Paracetamol", 5.0);

    gestion.ajouterPrescription(
        ordonnance.getId(),
        medicament,
        2,
        "Matin et soir",
        5,
        "Après repas"
    );

    assertThat(
        ordonnance.getPrescriptions()
    ).hasSize(1);
  }

  @Test
  void testGetOrdonnancesValides() {
    GestionOrdonnances gestion = new GestionOrdonnances();

    Medecin medecin = new Medecin(
        "Dupont", "Jean", "0102030405",
        "jean@mail.fr", "ORD123",
        Specialite.GENERALISTE);

    Patient patient = new Patient(
    	    "Martin",
    	    "Paul",
    	    "0606060606",
    	    "paul@mail.fr",
    	    "1234567890123",
    	    LocalDate.of(2000, 1, 1),
    	    "Adresse test",
    	    0.70
    	);

    gestion.creerOrdonnance(
        medecin,
        patient,
        LocalDate.now().plusDays(10),
        "Valide"
    );

    assertThat(
        gestion.getOrdonnancesValides()
    ).hasSize(1);
  }

  @Test
  void testNombreOrdonnances() {
    GestionOrdonnances gestion = new GestionOrdonnances();

    assertThat(
        gestion.getNombreOrdonnances()
    ).isZero();
  }
}