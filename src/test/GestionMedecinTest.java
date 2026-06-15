package fr.univartois.butinfo.sae.cabinet.service;

import fr.univartois.butinfo.sae.cabinet.enumeration.Specialite;
import fr.univartois.butinfo.sae.cabinet.modele.domaine.Medecin;
import fr.univartois.butinfo.sae.cabinet.modele.service.GestionMedecins;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

/**
 * Tests unitaires pour la classe GestionMedecins.
 */
@ExtendWith(MockitoExtension.class)
class GestionMedecinsTest {

    private GestionMedecins gestionMedecins;

    @Mock
    private Medecin mockMedecin;

    @BeforeEach
    void setUp() {
        gestionMedecins = new GestionMedecins();
    }

    @Test
    void testAjouterEtNombreMedecins() {
        when(mockMedecin.getNumeroOrdre()).thenReturn("ORD-001");

        gestionMedecins.ajouterMedecin(mockMedecin);

        assertThat(gestionMedecins.getNombreMedecins()).isEqualTo(1);
        assertThat(gestionMedecins.listerTousLesMedecins()).contains(mockMedecin);
    }

    @Test
    void testAjouterMedecinEnDoubleEchoue() {
        when(mockMedecin.getNumeroOrdre()).thenReturn("ORD-001");

        gestionMedecins.ajouterMedecin(mockMedecin);
        gestionMedecins.ajouterMedecin(mockMedecin);

        assertThat(gestionMedecins.getNombreMedecins()).isEqualTo(1);
    }

    @Test
    void testRechercherParNumeroOrdre() {
        when(mockMedecin.getNumeroOrdre()).thenReturn("ORD-001");

        gestionMedecins.ajouterMedecin(mockMedecin);

        Medecin trouve = gestionMedecins.rechercherParNumeroOrdre("ORD-001");
        Medecin introuvable = gestionMedecins.rechercherParNumeroOrdre("ORD-999");

        assertThat(trouve).isEqualTo(mockMedecin);
        assertThat(introuvable).isNull();
    }

    @Test
    void testSupprimerMedecin() {
        when(mockMedecin.getId()).thenReturn(1);
        when(mockMedecin.getNumeroOrdre()).thenReturn("ORD-001");

        gestionMedecins.ajouterMedecin(mockMedecin);
        boolean supprime = gestionMedecins.supprimerMedecin(1);

        assertThat(supprime).isTrue();
        assertThat(gestionMedecins.getNombreMedecins()).isZero();
    }

    @Test
    void testSupprimerMedecinInexistant() {
        boolean supprime = gestionMedecins.supprimerMedecin(999);

        assertThat(supprime).isFalse();
    }

    @Test
    void testRechercherParSpecialite() {
        when(mockMedecin.getNumeroOrdre()).thenReturn("ORD-001");
        when(mockMedecin.getSpecialite()).thenReturn(Specialite.GENERALISTE);

        gestionMedecins.ajouterMedecin(mockMedecin);

        ObservableList<Medecin> resultats =
                gestionMedecins.rechercherParSpecialite(Specialite.GENERALISTE);

        ObservableList<Medecin> vide =
                gestionMedecins.rechercherParSpecialite(Specialite.CARDIOLOGUE);

        assertThat(resultats).contains(mockMedecin);
        assertThat(vide).isEmpty();
    }

    @Test
    void testRechercherParNom() {
        when(mockMedecin.getNumeroOrdre()).thenReturn("ORD-001");
        when(mockMedecin.getNom()).thenReturn("Dupont");
        when(mockMedecin.getPrenom()).thenReturn("Jean");

        gestionMedecins.ajouterMedecin(mockMedecin);

        ObservableList<Medecin> resultats = gestionMedecins.rechercherParNom("dup");

        assertThat(resultats).contains(mockMedecin);
    }

    @Test
    void testRechercherParId() {
        when(mockMedecin.getId()).thenReturn(1);
        when(mockMedecin.getNumeroOrdre()).thenReturn("ORD-001");

        gestionMedecins.ajouterMedecin(mockMedecin);

        Medecin trouve = gestionMedecins.rechercherParId(1);
        Medecin introuvable = gestionMedecins.rechercherParId(999);

        assertThat(trouve).isEqualTo(mockMedecin);
        assertThat(introuvable).isNull();
    }
}