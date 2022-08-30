package com.example.errorNote.Service;

import com.example.errorNote.Modele.EtatProbleme;


public interface EtatService {

    //================METHODE PERMETTANT DE RECUPERER L'IDENTIFIANT DE L'ETAT D'UN PROBLEME=========================
    EtatProbleme RecupererParIdEtat(Long idEtat);

    //================METHODE PERMETTANT D'AJOUTER LES ETATS =========================
    EtatProbleme AjouterEtat(EtatProbleme etatProbleme);

}
