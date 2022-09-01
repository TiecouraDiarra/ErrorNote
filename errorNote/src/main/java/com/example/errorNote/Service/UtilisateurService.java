package com.example.errorNote.Service;

import com.example.errorNote.Modele.Probleme;
import com.example.errorNote.Modele.Utilisateur;

import java.util.List;

public interface UtilisateurService {

    //================METHODE PERMETTANT D'AJOUTER UN UTILISATEUR=========================
    Utilisateur AjouterUtilisateur(Utilisateur utilisateur);

    //================METHODE PERMETTANT DE RECUPERER L'IDENTIFIANT D'UN UTILISATEUR=========================
    Utilisateur RecupererParId(Long idUtilisateur);

    //================METHODE PERMETTANT DE SUPPRIMER UN UTILISATEUR=========================
    String Supprimer(Long idUtilisateur);

    //================METHODE PERMETTANT DE MODIFIER UN UTILISATEUR=========================
    Utilisateur modifier(Long idUtilisateur, Utilisateur region);


    //================METHODE PERMETTANT DE RECUPERER L'EMAIL D'UN UTILISATEUR=========================
    Utilisateur TrouverParEmail(String emailUtilisateur);

    //================METHODE PERMETTANT D'AFFICHER TOUS LES UTILISATEURS ET ADMINS=========================
    List<Utilisateur> AfficherTousLesUtilisateurs();

    //================METHODE PERMETTANT D'AFFICHER TOUS LES ADMINISTRATEURS=========================
    List<Utilisateur> AfficherTousLesAdmin();

    //================METHODE PERMETTANT D'AFFICHER TOUS LES UTILISATEURS SIMPLES=========================
    List<Utilisateur> AfficherTousLesUserSimple();


}
