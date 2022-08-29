package com.example.errorNote.Service;

import com.example.errorNote.Modele.Utilisateur;

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
    //Utilisateur TrouverParEmail(String emailUtilisateur);

}
