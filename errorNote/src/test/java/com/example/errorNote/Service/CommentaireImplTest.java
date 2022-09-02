package com.example.errorNote.Service;

import com.example.errorNote.Modele.Commentaire;
import com.example.errorNote.Modele.Role;
import com.example.errorNote.Modele.Solution;
import com.example.errorNote.Modele.Utilisateur;
import com.example.errorNote.Repository.RepoCommentaire;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class CommentaireImplTest {
    @Autowired
    CommentaireService commentaireService;
    @Autowired
    SolutionService solutionService;

    @Autowired
    UtilisateurService utilisateurService;
    @Autowired
    RoleService roleService;

    @Autowired
    RepoCommentaire repoCommentaire;

    @Test
    void ajoutCommentaire() {
        Commentaire commentaire = new Commentaire();
        Solution solution = solutionService.RecupererIdSolution(1L);
        Utilisateur user = utilisateurService.TrouverParEmail("tiec@gmail.com");
        if (user==null){
            System.out.println("Email Incorrect!");
        }
        else if (!user.getPassword().equals("admin")){
            System.out.println("Mot de passe incorrect!");
        }
        else {
            commentaire.setCommentaire("Bakary Bleni");
            commentaire.setDateCommentaire(new Date());
            commentaire.setSolution(solution);
            commentaire.setUtilisateur(user);

            if (commentaire.getCommentaire().isEmpty()){
                //System.out.println("Commentaire Ajouté avec succès");
                System.out.println("Commentaire Non ajouté");
            }else {
                commentaireService.AjoutCommentaire(commentaire);
                System.out.println("Commentaire Ajouté avec succès");
            }

        }
    }

    @Test
    void modifier() {
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setEmailUtilisateur("tiecou@gmail.com");
        Commentaire commentaire = new Commentaire();
        Utilisateur user = utilisateurService.TrouverParEmail(utilisateur.getEmailUtilisateur());
        //Long idCom = commentaire.getIdComm();
        //Commentaire commentaire1 = commentaireService.RecupererParIdCommentaire(4L);
        Role admin = roleService.getLibelleRolee("ADMIN");
        if (user==null){
            System.out.println("Email Incorrect!");
        }
        else if (!user.getPassword().equals("admin")){
            System.out.println("Mot de passe incorrect!");
        }else if (commentaire.getUtilisateur()==user || user.getRole()==admin ){
            commentaire.setDateCommentaire(new Date());
            commentaire.setCommentaire("Tiec");
            commentaireService.modifier(5L, commentaire);
            System.out.println("Commentaire modifié avec succès !");
        }else {
            System.out.println("Impossible de modifier un commentaire qui ne vous appartient pas !");
        }
    }

    @Test
    void supprimerCommentaire() {
        Utilisateur user1 = utilisateurService.TrouverParEmail("tiec@gmail.com");
        Role admin = roleService.getLibelleRolee("ADMIN");
        Commentaire commentaire = commentaireService.RecupererParIdCommentaire(5L);
        if (user1==null){
            System.out.println("Email Incorrect!");
        }
        else if (!user1.getPassword().equals("admin")) {
            System.out.println("Mot de passe incorrect!");
        }else if (commentaire.getUtilisateur()==user1 || user1.getRole()==admin ){
            commentaireService.SupprimerCommentaire(5L);
            System.out.println("Commentaire supprimé avec succès !");
        }else {
            System.out.println("Impossible de supprimer un commentaire qui ne vous appartient pas !");
        }
    }

    @Test
    void recupererParIdCommentaire() {
        Commentaire commentaire = new Commentaire();
        try {
            repoCommentaire.findById(commentaire.getIdComm()).get();
        }catch (Exception e){
            System.out.println("");
        }
    }

    @Test
    List<Commentaire> AfficherTousCommentaire(){
        Solution solution = solutionService.RecupererIdSolution(1L);
        return solution.getCommentaireList();
    }
}