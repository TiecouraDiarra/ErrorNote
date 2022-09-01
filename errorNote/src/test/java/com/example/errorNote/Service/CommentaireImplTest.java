package com.example.errorNote.Service;

import com.example.errorNote.Modele.Commentaire;
import com.example.errorNote.Modele.Role;
import com.example.errorNote.Modele.Solution;
import com.example.errorNote.Modele.Utilisateur;
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
            commentaire.setCommentaire("Fatim Bleni");
            commentaire.setDateCommentaire(new Date());
            commentaire.setSolution(solution);
            commentaire.setUtilisateur(user);
            commentaireService.AjoutCommentaire(commentaire);
            System.out.println("Commentaire Ajouté avec succès");
        }
    }

    @Test
    void modifier() {
        Commentaire commentaire = new Commentaire();
        Solution solution = solutionService.RecupererIdSolution(1L);
        Utilisateur user = utilisateurService.TrouverParEmail("tiec@gmail.com");
        Role admin = roleService.getLibelleRolee("ADMIN");
        if (user==null){
            System.out.println("Email Incorrect!");
        }
        else if (!user.getPassword().equals("admin")){
            System.out.println("Mot de passe incorrect!");
        }else if (commentaire.getUtilisateur()==user || user.getRole()==admin ){
            commentaire.setDateCommentaire(new Date());
            //solution.setProbleme(probleme);
            commentaireService.modifier(1L,commentaire);
            System.out.println("Commentaire modifié avec succès !");
        }else {
            System.out.println("Impossible de modifier un commentaire qui ne vous appartient pas !");
        }
    }

    @Test
    void supprimerCommentaire() {
    }

    @Test
    void recupererParIdCommentaire() {
    }

    @Test
    List<Commentaire> AfficherTousCommentaire(){
        Solution solution = solutionService.RecupererIdSolution(1L);
        return solution.getCommentaireList();
    }
}