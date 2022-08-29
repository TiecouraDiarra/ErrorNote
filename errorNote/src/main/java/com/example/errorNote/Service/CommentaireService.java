package com.example.errorNote.Service;

import com.example.errorNote.Modele.Commentaire;
import com.example.errorNote.Modele.Solution;

public interface CommentaireService {
    //Commentaire AjouterCommentaire(Commentaire commentaire);


    //================METHODE PERMETTANT DE CREER UN COMMENTAIRE=========================
    String AjouterCommentaireAutre(String contComAutre, Long idSolution, Long idUtilisateur);
}
