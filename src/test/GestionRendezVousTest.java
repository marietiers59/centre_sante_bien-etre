package fr.univartois.butinfo.sae.cabinet.service;

import fr.univartois.butinfo.sae.cabinet.enumeration.StatutRendezVous;
import fr.univartois.butinfo.sae.cabinet.modele.Medecin;
import fr.univartois.butinfo.sae.cabinet.modele.Patient;
import fr.univartois.butinfo.sae.cabinet.modele.RendezVous;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

/**
 * Classe de tests unitaires pour le service {@link GestionRendezVous}.
 * Elle valide la logique de prise de rendez-vous, de gestion des disponibilités 
 * des médecins et du suivi des différents statuts.
 * * @author Loan Bilcke
 * @version 1.0
 */
@ExtendWith(MockitoExtension.class)
class GestionRendezVousTest {

    /**
     * Le service de gestion des rendez-vous à tester.
     */
    private GestionRendezVous gestionRdv;

    /**
     * Doublure de test (Mock) représentant un patient.
     */
    @Mock
    private Patient mock