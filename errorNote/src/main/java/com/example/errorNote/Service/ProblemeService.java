package com.example.errorNote.Service;

import com.example.errorNote.Modele.EtatProbleme;
import com.example.errorNote.Modele.Probleme;
import com.example.errorNote.Modele.Utilisateur;

import java.util.List;

public interface ProblemeService {
    //================METHODE PERMETTANT DE CREER UN PROBLEME=========================
    Probleme CreerProbleme(Probleme probleme);

    //================METHODE PERMETTANT DE RECUPERER L'IDENTIFIANT D'UN PROBLEME=========================
    Probleme RecupererParIdProbleme(Long idProbleme);


    //================METHODE PERMETTANT DE RECHERCHER UN PROBLEME PAR TITRE=========================
    List<Probleme> Rechercher(String titreProbleme);

    //================METHODE PERMETTANT DE MODIFIER UN PROBLEME=========================
    Probleme modifier(Long idProbleme, Probleme probleme);

    //================METHODE PERMETTANT DE CHANGER L'ETAT D'UN PROBLEME=========================
    public String changerEtatProbleme(Long idProbleme,Long idEtat);

    //================METHODE PERMETTANT DE SUPPRIMER UN PROBLEME=========================
    String SupprimerProbleme(Long idProbleme);

    //================METHODE PERMETTANT D'AFFICHER LA LISTE DES PROBLEME=========================
    List<Probleme> AfficherTousLesProblemes1();
}
