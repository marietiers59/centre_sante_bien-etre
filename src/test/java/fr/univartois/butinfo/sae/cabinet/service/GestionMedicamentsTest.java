package fr.univartois.butinfo.sae.cabinet.service;

import static org.assertj.core.api.Assertions.assertThat;

import fr.univartois.butinfo.sae.cabinet.modele.Medicament;
import java.util.List;
import org.junit.jupiter.api.Test;

class GestionMedicamentsTest {

  @Test
  void testAjouterMedicament() {
    GestionMedicaments gestion = new GestionMedicaments();
    Medicament medicament = new Medicament("Doliprane", "Paracetamol", 5.0);

    gestion.ajouterMedicament(medicament);

    assertThat(gestion.getNombreMedicaments()).isEqualTo(1);
    assertThat(gestion.listerTousLesMedicaments()).contains(medicament);
  }

  @Test
  void testAjouterMedicamentDejaExistant() {
    GestionMedicaments gestion = new GestionMedicaments();
    Medicament medicament = new Medicament("Doliprane", "Paracetamol", 5.0);

    gestion.ajouterMedicament(medicament);
    gestion.ajouterMedicament(medicament);

    assertThat(gestion.getNombreMedicaments()).isEqualTo(1);
  }

  @Test
  void testRechercherParId() {
    GestionMedicaments gestion = new GestionMedicaments();
    Medicament medicament = new Medicament("Doliprane", "Paracetamol", 5.0);

    gestion.ajouterMedicament(medicament);

    assertThat(gestion.rechercherParId(medicament.getId())).isEqualTo(medicament);
  }

  @Test
  void testRechercherParIdInexistant() {
    GestionMedicaments gestion = new GestionMedicaments();

    assertThat(gestion.rechercherParId(999)).isNull();
  }

  @Test
  void testRechercherParNom() {
    GestionMedicaments gestion = new GestionMedicaments();
    Medicament medicament1 = new Medicament("Doliprane", "Paracetamol", 5.0);
    Medicament medicament2 = new Medicament("Sirop", "Toux", 8.0);

    gestion.ajouterMedicament(medicament1);
    gestion.ajouterMedicament(medicament2);

    List<Medicament> resultats = gestion.rechercherParNom("doli");

    assertThat(resultats).hasSize(1);
    assertThat(resultats).contains(medicament1);
  }

  @Test
  void testSupprimerMedicament() {
    GestionMedicaments gestion = new GestionMedicaments();
    Medicament medicament = new Medicament("Doliprane", "Paracetamol", 5.0);

    gestion.ajouterMedicament(medicament);

    boolean resultat = gestion.supprimerMedicament(medicament.getId());

    assertThat(resultat).isTrue();
    assertThat(gestion.getNombreMedicaments()).isZero();
  }

  @Test
  void testSupprimerMedicamentInexistant() {
    GestionMedicaments gestion = new GestionMedicaments();

    boolean resultat = gestion.supprimerMedicament(999);

    assertThat(resultat).isFalse();
  }
}