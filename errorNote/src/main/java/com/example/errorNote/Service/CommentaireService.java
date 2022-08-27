package com.example.errorNote.Service;

import com.example.errorNote.Modele.Commentaire;
import com.example.errorNote.Modele.Solution;

public interface CommentaireService {
    Commentaire AjouterCommentaire(Commentaire commentaire);

    String AjouterCommentaireAutre(String contComAutre, Long idSolution, Long idUtilisateur);
}
