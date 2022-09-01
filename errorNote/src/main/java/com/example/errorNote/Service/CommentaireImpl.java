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

    //================DEBUT DE LA METHODE PERMETTANT DE MODIFIER UN COMMENTAIRE=========================
    @Override
    public Commentaire modifier(Long idCommentaire, Commentaire commentaire) {
        return repoCommentaire.findById(idCommentaire)
                .map(p->{
                    p.setCommentaire(commentaire.getCommentaire());
                    return repoCommentaire.save(p);
                }).orElseThrow(() -> new RuntimeException("Commentaire non trouvé !"));
    }
    //================FIN DE LA METHODE PERMETTANT DE MODIFIER UN COMMENTAIRE=========================

    //================DEBUT DE LA METHODE PERMETTANT DE SUPPRIMER UN COMMENTAIRE=========================
    @Override
    public String SupprimerCommentaire(Long idCommentaire) {
        repoCommentaire.deleteById(idCommentaire);
        return "Commentaire suprimé avec succès";
    }
    //================FIN DE LA METHODE PERMETTANT DE SUPPRIMER UN COMMENTAIRE=========================

    //================DEBUT DE LA METHODE PERMETTANT DE RECUPERER L'IDENTIFIANT DU COMMENTAIRE=========================
    @Override
    public Commentaire RecupererParIdCommentaire(Long idCom) {
        try {
            return repoCommentaire.findById(idCom).get();
        }catch (Exception e){
            return null;
        }
    }
    //================FIN DE LA METHODE PERMETTANT DE RECUPERER L'IDENTIFIANT UN COMMENTAIRE=========================
}
