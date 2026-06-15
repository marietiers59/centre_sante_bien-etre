package fr.univartois.butinfo.sae.cabinet.service;

import fr.univartois.butinfo.sae.cabinet.enumeration.Specialite;
import fr.univartois.butinfo.sae.cabinet.modele.Medecin;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

/**
 * Classe de tests unitaires pour le service {@link GestionMedecins}.
 * Elle valide le bon fonctionnement des opérations de gestion des médecins, notamment
 * l'ajout sans doublons, la suppression et les différents critères de recherche.
 * * @author Loan Bilcke
 * @version 1.0
 */
@ExtendWith(MockitoExtension.class)
class GestionMedecinsTest {

    /**
     * Le service de gestion des médecins à tester.
     */
    private GestionMedecins gestionMedecins;

    /**
     * Doublure de test (Mock) représentant un médecin pour isoler les tests de la logique métier.
     */
    @Mock
    private Medecin mockMedecin;

    /**
     * Initialise l'environnement de test avant l'exécution de chaque méthode.
     * Instancie un nouveau service de gestion de médecins vierge.
     */
    @BeforeEach
    void setUp() {
        gestionMedecins = new GestionMedecins();
    }

    /**
     * Vérifie qu'un médecin est correctement ajouté à la liste et que le nombre 
     * total de médecins est incrémenté en conséquence.
     */
    @Test
    void testAjouterEtNombreMedecins() {
        when(mockMedecin.getNumeroOrdre()).thenReturn("ORD-001");
        
        gestionMedecins.ajouterMedecin(mockMedecin);
        
        assertThat(gestionMedecins.getNombreMedecins()).isEqualTo(1);
        assertThat(gestionMedecins.listerTousLesMedecins()).contains(mockMedecin);
    }

    /**
     * Vérifie qu'il est impossible d'ajouter deux fois un médecin possédant 
     * le même numéro d'ordre au sein du service.
     */
    @Test
    void testAjouterMedecinEnDoubleEchoue() {
        when(mockMedecin.getNumeroOrdre()).thenReturn("ORD-001");
        
        gestionMedecins.ajouterMedecin(mockMedecin);
        gestionMedecins.ajouterMedecin(mockMedecin); // Tentative d'ajout en double
        
        assertThat(gestionMedecins.getNombreMedecins()).isEqualTo(1);
    }

    /**
     * Vérifie que la recherche par numéro d'ordre renvoie le bon médecin s'il existe,
     * ou retourne {@code null} s'il n'est pas répertorié.
     */
    @Test
    void testRechercherParNumeroOrdre() {
        when(mockMedecin.getNumeroOrdre()).thenReturn("ORD-001");
        gestionMedecins.ajouterMedecin(mockMedecin);
        
        Medecin trouve = gestionMedecins.rechercherParNumeroOrdre("ORD-001");
        assertThat(trouve).isEqualTo(mockMedecin);
        
        Medecin introuvable = gestionMedecins.rechercherParNumeroOrdre("ORD-999");
        assertThat(introuvable).isNull();
    }

    /**
     * Vérifie qu'un médecin existant est correctement supprimé de la liste 
     * à l'aide de son identifiant unique.
     */
    @Test
    void testSupprimerMedecin() {
        when(mockMedecin.getId()).thenReturn(1);
        when(mockMedecin.getNumeroOrdre()).thenReturn("ORD-001");
        
        gestionMedecins.ajouterMedecin(mockMedecin);
        boolean supprime = gestionMedecins.supprimerMedecin(1);
        
        assertThat(supprime).isTrue();
        assertThat(gestionMedecins.getNombreMedecins()).isZero();
    }

    /**
     * Vérifie que la recherche par spécialité médicale renvoie bien la liste des 
     * médecins concernés et qu'une spécialité non représentée retourne une liste vide.
     */
    @Test
    void testRechercherParSpecialite() {
        when(mockMedecin.getNumeroOrdre()).thenReturn("ORD-001");
        when(mockMedecin.getSpecialite()).thenReturn(Specialite.GENERALISTE);
        
        gestionMedecins.ajouterMedecin(mockMedecin);
        
        List<Medecin> resultats = gestionMedecins.rechercherParSpecialite(Specialite.GENERALISTE);
        assertThat(resultats).contains(mockMedecin);
        
        List<Medecin> vide = gestionMedecins.rechercherParSpecialite(Specialite.CARDIOLOGUE);
        assertThat(vide).isEmpty();
    }
}