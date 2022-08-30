package com.example.errorNote.Service;

import com.example.errorNote.Modele.Commentaire;
import com.example.errorNote.Modele.Probleme;
import com.example.errorNote.Modele.Solution;

public interface CommentaireService {
    //Commentaire AjouterCommentaire(Commentaire commentaire);


    //================METHODE PERMETTANT DE CREER UN COMMENTAIRE=========================
    Commentaire AjoutCommentaire(Commentaire contenuCommentaire);

        //================METHODE PERMETTANT DE MODIFIER UN COMMENTAIRE=========================
    Commentaire modifier(Long idCommentaire, Commentaire commentaire);

    //================METHODE PERMETTANT DE SUPPRIMER UN COMMENTAIRE=========================
    String SupprimerCommentaire(Long idCommentaire);
}
