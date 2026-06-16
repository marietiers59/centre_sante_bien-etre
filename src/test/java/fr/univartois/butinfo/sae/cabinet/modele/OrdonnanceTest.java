package fr.univartois.butinfo.sae.cabinet.modele;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.within;
import static org.mockito.Mockito.when;

import fr.univartois.butinfo.sae.cabinet.enumeration.Specialite;
import java.time.LocalDate;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class OrdonnanceTest {

  @Mock
  private Patient patient;

  @Test
  void testPrixTotalOrdonnanceVide() {
    Medecin medecin = new Medecin(
        "Dupont", "Jean", "0102030405", "jean@mail.fr",
        "ORD123", Specialite.GENERALISTE);

    when(patient.getTauxRemboursement()).thenReturn(0.70);

    Ordonnance ordonnance = new Ordonnance(medecin, patient);

    assertThat(ordonnance.getPrixTotal()).isCloseTo(0.0, within(0.01));
  }

  @Test
  void testPrixTotalAvecUnePrescription() {
    Medecin medecin = new Medecin(
        "Dupont", "Jean", "0102030405", "jean@mail.fr",
        "ORD123", Specialite.GENERALISTE);

    when(patient.getTauxRemboursement()).thenReturn(0.70);

    Medicament doliprane = new Medicament("Doliprane", "Antidouleur", 5.0);
    LignePrescription ligne = new LignePrescription(
        doliprane, 2, "Matin et soir", 5, "Après le repas");

    Ordonnance ordonnance = new Ordonnance(medecin, patient);
    ordonnance.ajouterPrescription(ligne);

    assertThat(ordonnance.getPrixTotal()).isCloseTo(3.0, within(0.01));
  }

  @Test
  void testPrixTotalAvecPlusieursPrescriptions() {
    Medecin medecin = new Medecin(
        "Dupont", "Jean", "0102030405", "jean@mail.fr",
        "ORD123", Specialite.GENERALISTE);

    when(patient.getTauxRemboursement()).thenReturn(0.50);

    Medicament medicament1 = new Medicament("Doliprane", "Antidouleur", 5.0);
    Medicament medicament2 = new Medicament("Sirop", "Toux", 8.0);

    Ordonnance ordonnance = new Ordonnance(medecin, patient);
    ordonnance.ajouterPrescription(new LignePrescription(
        medicament1, 2, "Matin", 5, "Après repas"));
    ordonnance.ajouterPrescription(new LignePrescription(
        medicament2, 1, "Soir", 3, "Avant dormir"));

    assertThat(ordonnance.getPrixTotal()).isCloseTo(9.0, within(0.01));
  }

  @Test
  void testAjouterPrescription() {
    Medecin medecin = new Medecin(
        "Dupont", "Jean", "0102030405", "jean@mail.fr",
        "ORD123", Specialite.GENERALISTE);

    Ordonnance ordonnance = new Ordonnance(medecin, patient);
    Medicament medicament = new Medicament("Doliprane", "Antidouleur", 5.0);
    LignePrescription ligne = new LignePrescription(
        medicament, 1, "Matin", 5, "Aucune");

    ordonnance.ajouterPrescription(ligne);

    assertThat(ordonnance.getPrescriptions()).hasSize(1);
  }

  @Test
  void testSupprimerPrescription() {
    Medecin medecin = new Medecin(
        "Dupont", "Jean", "0102030405", "jean@mail.fr",
        "ORD123", Specialite.GENERALISTE);

    Ordonnance ordonnance = new Ordonnance(medecin, patient);
    Medicament medicament = new Medicament("Doliprane", "Antidouleur", 5.0);
    LignePrescription ligne = new LignePrescription(
        medicament, 1, "Matin", 5, "Aucune");

    ordonnance.ajouterPrescription(ligne);
    boolean resultat = ordonnance.supprimerPrescription(medicament);

    assertThat(resultat).isTrue();
    assertThat(ordonnance.getPrescriptions()).isEmpty();
  }

  @Test
  void testOrdonnanceValide() {
    Medecin medecin = new Medecin(
        "Dupont", "Jean", "0102030405", "jean@mail.fr",
        "ORD123", Specialite.GENERALISTE);

    Ordonnance ordonnance = new Ordonnance(
        medecin, patient, LocalDate.now().plusDays(10), "RAS");

    assertThat(ordonnance.estValide()).isTrue();
  }

  @Test
  void testOrdonnanceNonValide() {
    Medecin medecin = new Medecin(
        "Dupont", "Jean", "0102030405", "jean@mail.fr",
        "ORD123", Specialite.GENERALISTE);

    Ordonnance ordonnance = new Ordonnance(
        medecin, patient, LocalDate.now().minusDays(1), "Expirée");

    assertThat(ordonnance.estValide()).isFalse();
  }
}