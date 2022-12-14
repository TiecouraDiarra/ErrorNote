package com.example.errorNote.Service;

import com.example.errorNote.Modele.Commentaire;

public interface CommentaireService {
    //Commentaire AjouterCommentaire(Commentaire commentaire);


    //================METHODE PERMETTANT DE CREER UN COMMENTAIRE=========================
    Commentaire AjoutCommentaire(Commentaire contenuCommentaire);

        //================METHODE PERMETTANT DE MODIFIER UN COMMENTAIRE=========================
    Commentaire modifier(Long idCommentaire, Commentaire commentaire);

    //================METHODE PERMETTANT DE SUPPRIMER UN COMMENTAIRE=========================
    String SupprimerCommentaire(Long idCommentaire);

    //================METHODE PERMETTANT DE RECUPERER L'IDENTIFIANT D'UN PROBLEME=========================
    Commentaire RecupererParIdCommentaire(Long idCom);
}
