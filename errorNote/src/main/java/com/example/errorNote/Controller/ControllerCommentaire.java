package com.example.errorNote.Controller;

import com.example.errorNote.Modele.*;
import com.example.errorNote.Service.CommentaireService;
import com.example.errorNote.Service.RoleService;
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

    @Autowired
    private RoleService roleService;

    //================DEBUT DE LA METHODE PERMETTANT D'AJOUTER UN COMMENTAIRE====================================
    @ApiOperation(value = "Ajout de commentaire")
    @PostMapping("/ajoutCommentaire/{emailUtilisateur}/{password}/{idSolution}")
    public String AjoutCommentaireAutreUser(@RequestBody Commentaire commentaire , @PathVariable("emailUtilisateur") String emailUtilisateur, @PathVariable("password") String password, @PathVariable("idSolution") Long idSolution){
        //instanciation de User en user et user1 pour recuperer l'email et le mot de pass
        Solution solution = solutionService.RecupererIdSolution(idSolution);
        Utilisateur user = utilisateurService.TrouverParEmail(emailUtilisateur);
        if (user==null) return "Email Incorrect!";
        else if (!user.getPassword().equals(password)) return "Mot de passe incorrect!";
        else {
            commentaire.setDateCommentaire(new Date());
            commentaire.setUtilisateur(user);
            commentaire.setSolution(solution);
            commentaireService.AjoutCommentaire(commentaire);
            return "Commentaire ajout?? par " +user.getNomUtilisateur() + " " +user.getPrenomUtilisateur();
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

    //================DEBUT DE LA METHODE PERMETTANT DE MODIFIER UN COMMENTAIRE====================================
    @ApiOperation(value = "Modifier un commentaire")
    @PutMapping("/Modifier/{emailUtilisateur}/{password}/{idComm}")
    public String update(@RequestBody Commentaire commentaire, @Param("idComm") @PathVariable long idComm,@PathVariable("emailUtilisateur") String emailUtilisateur, @PathVariable("password") String password){
        //instanciation de User en user et user1 pour recuperer l'email et le mot de pass
        Utilisateur user1 = utilisateurService.TrouverParEmail(emailUtilisateur);
        Role admin = roleService.getLibelleRolee("ADMIN");

        if (user1 == null) return "Email incorrect!";
        else if (!user1.getPassword().equals(password)) return "Mot de passe incorrect!";
        else if (commentaire.getUtilisateur()==user1 || user1.getRole()==admin ){
            commentaire.setDateCommentaire(new Date());
            //solution.setProbleme(probleme);
            commentaireService.modifier(idComm, commentaire);
            return "Commentaire modifi?? avec succ??s !";
        }else {
            return "Impossible de modifier un commentaire qui ne vous appartient pas !";
        }
    }
    //================FIN DE LA METHODE PERMETTANT DE MODIFIER UN COMMENTAIRE====================================

    //================DEBUT DE LA METHODE PERMETTANT DE SUPPRIMER UN COMMENTAIRE====================================
    @ApiOperation(value = "Supprimer un commentaire")
    @DeleteMapping("/supprimer/{emailUtilisateur}/{password}/{idCommentaire}")
    public String deleteProbleme(@PathVariable Long idCommentaire, @PathVariable("emailUtilisateur") String emailUtilisateur, @PathVariable("password") String password){
        Utilisateur user1 = utilisateurService.TrouverParEmail(emailUtilisateur);
        Role admin = roleService.getLibelleRolee("ADMIN");
        Commentaire commentaire = commentaireService.RecupererParIdCommentaire(idCommentaire);
        if (user1 == null) return "Email incorrect!";
        else if (!user1.getPassword().equals(password)) return "Mot de passe incorrect!";
        else if (commentaire.getUtilisateur()==user1 || user1.getRole()==admin ){
            commentaireService.SupprimerCommentaire(idCommentaire);
            return "Commentaire supprim?? avec succ??s !";
        }else {
            return "Impossible de supprimer un commentaire qui ne vous appartient pas !";
        }
    }
    //================FIN DE LA METHODE PERMETTANT DE SUPPRIMER UN COMMENTAIRE====================================
}
