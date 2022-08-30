package com.example.errorNote.Controller;

import com.example.errorNote.Modele.Commentaire;
import com.example.errorNote.Modele.Probleme;
import com.example.errorNote.Modele.Solution;
import com.example.errorNote.Modele.Utilisateur;
import com.example.errorNote.Service.CommentaireService;
import com.example.errorNote.Service.SolutionService;
import com.example.errorNote.Service.UtilisateurService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "Commentaire")
@NoArgsConstructor
@AllArgsConstructor
@Api(value = "hello", description = "Gestion des commentaires")
public class ControllerCommentaire {
    @Autowired
    private CommentaireService commentaireService;

    @Autowired
    private SolutionService solutionService;

    @Autowired
    private UtilisateurService utilisateurService;

    //================DEBUT DE LA METHODE PERMETTANT D'AJOUTER UN COMMENTAIRE====================================
    @ApiOperation(value = "Ajout de commentaire")
    @PostMapping("/ajoutCommentaire/{emailUtilisateur}/{password}")
    public String AjoutCommentaireAutreUser(@RequestBody Commentaire commentaire , @PathVariable("emailUtilisateur") String emailUtilisateur, @PathVariable("password") String password){
        //instanciation de User en user et user1 pour recuperer l'email et le mot de pass
        Utilisateur user = utilisateurService.TrouverParEmail(emailUtilisateur);
        if (user==null) return "Email Incorrect!";
        else if (!user.getPassword().equals(password)) return "Mot de passe incorrect!";
        else {
            commentaire.setDateCommentaire(new Date());
            commentaire.setUtilisateur(user);
            commentaireService.AjoutCommentaire(commentaire);
            return "Commentaire ajouté par " +user.getNomUtilisateur() + " " +user.getPrenomUtilisateur();
        }

    }
    //================FIN DE LA METHODE PERMETTANT D'AJOUTER UN COMMENTAIRE====================================

    //================DEBUT DE LA METHODE PERMETTANT D'AFFICHER LES COMMENTAIRES====================================
    @ApiOperation(value = "Afficher les commentaires")
    @GetMapping("/AfficherCommentaire/{idSolution}")
    public List<Commentaire> AfficherTousCommentaire(@Param("idSolution") @PathVariable long idSolution){
        Solution solution = solutionService.RecupererIdSolution(idSolution);
        return solution.getCommentaireList();
    }
    //================FIN DE LA METHODE PERMETTANT D'AFFICHER LES COMMENTAIRES====================================

    //================DEBUT DE LA METHODE PERMETTANT D'AFFICHER LES COMMENTAIRES====================================
    @ApiOperation(value = "Modifier un commentaire")
    @PutMapping("/Modifier/{emailUtilisateur}/{password}/{idComm}")
    public String update(@RequestBody Commentaire commentaire, @Param("idComm") @PathVariable long idComm,@PathVariable("emailUtilisateur") String emailUtilisateur, @PathVariable("password") String password){
        //instanciation de User en user et user1 pour recuperer l'email et le mot de pass
        Utilisateur user1 = utilisateurService.TrouverParEmail(emailUtilisateur);
        Solution solution = solutionService.RecupererIdSolution(idComm);

        if (user1 == null) return "Email incorrect!";
        else if (!user1.getPassword().equals(password)) return "Mot de passe incorrect!";
        //recupère le password de l'email qu'il a saisie et verifie s'il est egale au password saisie en url
        else {
            commentaireService.modifier(idComm,commentaire);
            return "Commentaire modifié avec succès";
        }

        //return solution.getCommentaireList();
    }
    //================FIN DE LA METHODE PERMETTANT D'AFFICHER LES COMMENTAIRES====================================
}
