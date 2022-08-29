package com.example.errorNote.Service;

import com.example.errorNote.Modele.Commentaire;
import com.example.errorNote.Repository.RepoCommentaire;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CommentaireImpl implements CommentaireService{

    //================LA DEPENDANCE DU REPOSITORY D'UN COMMENTAIRE================================
    @Autowired
    private RepoCommentaire repoCommentaire;
    /*@Override
    public Commentaire AjouterCommentaire(Commentaire commentaire) {

        return repoCommentaire.save(commentaire);
    }*/

    //================DEBUT DE LA METHODE PERMETTANT DE CREER UN COMMENTAIRE=========================
    @Override
    public String AjouterCommentaireAutre(String contComAutre, Long idSolution, Long idUtilisateur) {
        repoCommentaire.ajoutCommAutreUser(contComAutre,idSolution,idUtilisateur);
        return "Commentaire Ajouté avec succès";
    }
    //================FIN DE LA METHODE PERMETTANT DE CREER UN COMMENTAIRE=========================
}
