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



    //================DEBUT DE LA METHODE PERMETTANT DE CREER UN COMMENTAIRE=========================
    @Override
    public Commentaire AjoutCommentaire(Commentaire contenuCommentaire) {
        return repoCommentaire.save(contenuCommentaire);
    }
    //================FIN DE LA METHODE PERMETTANT DE CREER UN COMMENTAIRE=========================
}
