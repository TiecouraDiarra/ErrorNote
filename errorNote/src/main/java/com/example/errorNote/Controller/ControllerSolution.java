package com.example.errorNote.Controller;

import com.example.errorNote.Modele.*;
import com.example.errorNote.Service.ProblemeService;
import com.example.errorNote.Service.RoleService;
import com.example.errorNote.Service.SolutionService;
import com.example.errorNote.Service.UtilisateurService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "Solution")
@NoArgsConstructor
@AllArgsConstructor
@Api(value = "hello", description = "Gestion des solutions")
public class ControllerSolution {
    @Autowired
    private SolutionService solutionService;
    @Autowired
    private ProblemeService problemeService;

    @Autowired
    private UtilisateurService utilisateurService;
    @Autowired
    private RoleService roleService;

    //================DEBUT DE LA METHODE PERMETTANT DE CREER UNE SOLUTION====================================
    @ApiOperation(value = "Créer une solution")
    @PostMapping("/solution/{emailUtilisateur}/{password}/{idProbleme}")
    public String creerSolution(@RequestBody Solution solution, @PathVariable("idProbleme") Long idProbleme, @PathVariable("emailUtilisateur") String emailUtilisateur, @PathVariable("password") String password){
        //instanciation de User en user et user1 pour recuperer l'email et le mot de pass
        Utilisateur user1 = utilisateurService.TrouverParEmail(emailUtilisateur);

        Probleme probleme = problemeService.RecupererParIdProbleme(idProbleme);
        Solution s = solutionService.RetrouverParProbleme(probleme);
        Role admin = roleService.getLibelleRolee("ADMIN");
        if (user1 == null) return "Email incorrect!";
        else if (!user1.getPassword().equals(password)) return "Mot de passe incorrect!";
        else if (s==null){
            if (probleme!= null && user1 !=null){
                if (probleme.getUtilisateur()==user1 || user1.getRole()==admin ){
                    solution.setDateSolution(new Date());
                    solution.setProbleme(probleme);
                    solution.getProbleme().setEtat(new EtatProbleme(3L));
                    //probleme.setEtat(new EtatProbleme(3L));
                    solutionService.CreerSolution(solution);
                    return "Probleme résolu avec succès !";
                }else {
                    return "Impossible de donner la solution à un problème qui ne vous appartient pas !";
                }
            }else {
                return "Vous essayez d'attribuer un problème à un utilisateur qui n'existe pas !";
            }
        }else {
            return "Ce problème est déja résolu";
        }
    }
    //================FIN DE LA METHODE PERMETTANT DE CREER UNE SOLUTION====================================


    //================DEBUT DE LA METHODE PERMETTANT DE MODIFIER UNE SOLUTION====================================
    @ApiOperation(value = "Modifier une solution ")
    @PutMapping("/modifier/{emailUtilisateur}/{password}/{idSolution}")
    public String update(@PathVariable Long idSolution, @RequestBody Solution solution, @PathVariable("emailUtilisateur") String emailUtilisateur, @PathVariable("password") String password/*, Long idUtilisateur*/){
        //instanciation de User en user et user1 pour recuperer l'email et le mot de pass
        Utilisateur user1 = utilisateurService.TrouverParEmail(emailUtilisateur);
        Solution solution1 = solutionService.RecupererIdSolution(idSolution);
        //Probleme probleme = problemeService.RecupererParIdProbleme(idProbleme);
        Role admin = roleService.getLibelleRolee("ADMIN");
        //Utilisateur utilisateur = utilisateurService.RecupererParId(idUtilisateur);
        if (user1 == null) return "Email incorrect!";
        else if (!user1.getPassword().equals(password)) return "Mot de passe incorrect!";
        else if (solution1.getProbleme().getUtilisateur()==user1 || user1.getRole()==admin ){
            solution.setDateSolution(new Date());
            //solution.setProbleme(probleme);
            this.solutionService.ModifierSolution(idSolution, solution);
            return "Solution modifiée avec succès !";
        }else {
            return "Impossible de modifier une solution qui ne vous appartient pas !";
        }
        /*else {
            this.solutionService.ModifierSolution(idSolution, solution);
        }
        return "Solution modifiée avec succès";*/
    }
    //================FIN DE LA METHODE PERMETTANT DE MODIFIER UNE SOLUTION====================================


    //================DEBUT DE LA METHODE PERMETTANT DE SUPPRIMER UEN SOLUTION====================================
    @ApiOperation(value = "Supprimer une solution")
    @DeleteMapping("/supprimer/{emailUtilisateur}/{password}/{idSolution}")
    public String delete(@PathVariable Long idSolution, @PathVariable("emailUtilisateur") String emailUtilisateur, @PathVariable("password") String password){
        //instanciation de User en user et user1 pour recuperer l'email et le mot de pass
        Utilisateur user1 = utilisateurService.TrouverParEmail(emailUtilisateur);
        Role admin = roleService.getLibelleRolee("ADMIN");
        Solution solution1 = solutionService.RecupererIdSolution(idSolution);
        if (user1 == null) return "Email incorrect!";
        else if (!user1.getPassword().equals(password)) return "Mot de passe incorrect!";
        else if (solution1.getProbleme().getUtilisateur()==user1 || user1.getRole()==admin ){
            solutionService.SupprimerSolution(idSolution);
            return "Solution supprimée avec succès";
        } else {
            //solutionService.SupprimerSolution(idSolution);
            return "Impossible de supprimer une solution qui ne vous appartient pas !";
        }
        //return "Solution supprimée avec succès";
    }
    //================FIN DE LA METHODE PERMETTANT DE SUPPRIMER UEN SOLUTION====================================

    //================DEBUT DE LA METHODE PERMETTANT D'AFFICHER TOUTES LES SOLUTIONS====================================
    @ApiOperation(value = "Afficher toutes les solutions")
    @GetMapping("/AfficherToutesLesSolutions")
    public List<Solution> read(){
        return solutionService.AfficherToutesLesSolutions();
    }
    //================FIN DE LA METHODE PERMETTANT D'AFFICHER TOUTES LES SOLUTIONS====================================
}
