package com.example.errorNote.Controller;

import com.example.errorNote.Modele.Probleme;
import com.example.errorNote.Modele.Solution;
import com.example.errorNote.Modele.Utilisateur;
import com.example.errorNote.Service.ProblemeService;
import com.example.errorNote.Service.SolutionService;
import com.example.errorNote.Service.UtilisateurService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    //================DEBUT DE LA METHODE PERMETTANT DE CREER UNE SOLUTION====================================
    @ApiOperation(value = "Créer une solution")
    @PostMapping("/solution/{emailUtilisateur}/{password}/{idProbleme}")
    public Object creerSolution(@RequestBody Solution solution, @PathVariable("idProbleme") Long idProbleme, @PathVariable("emailUtilisateur") String emailUtilisateur, @PathVariable("password") String password){
        //instanciation de User en user et user1 pour recuperer l'email et le mot de pass
        Utilisateur user1 = utilisateurService.TrouverParEmail(emailUtilisateur);
        Probleme probleme = problemeService.RecupererParIdProbleme(idProbleme);
        if (user1 == null) return "Email incorrect!";
        else if (!user1.getPassword().equals(password)) return "Mot de passe incorrect!";
        else if(probleme!=null){
            solution.setProbleme(probleme);
            solutionService.CreerSolution(solution);
            return "Solution créée avec succès";
        } else {
            return "Vous essayez de donner la solution d'un problème qui n'existe pas !";
        }
    }
    //================FIN DE LA METHODE PERMETTANT DE CREER UNE SOLUTION====================================


    //================DEBUT DE LA METHODE PERMETTANT DE MODIFIER UNE SOLUTION====================================
    @ApiOperation(value = "Modifier une solution ")
    @PutMapping("/modifier/{emailUtilisateur}/{password}/{idSolution}")
    public String update(@PathVariable Long idSolution, @RequestBody Solution solution, @PathVariable("emailUtilisateur") String emailUtilisateur, @PathVariable("password") String password){
        //instanciation de User en user et user1 pour recuperer l'email et le mot de pass
        Utilisateur user1 = utilisateurService.TrouverParEmail(emailUtilisateur);
        if (user1 == null) return "Email incorrect!";
        else if (!user1.getPassword().equals(password)) return "Mot de passe incorrect!";
        else {
            this.solutionService.ModifierSolution(idSolution, solution);
        }
        return "Solution modifiée avec succès";
    }
    //================FIN DE LA METHODE PERMETTANT DE MODIFIER UNE SOLUTION====================================


    //================DEBUT DE LA METHODE PERMETTANT DE SUPPRIMER UEN SOLUTION====================================
    @ApiOperation(value = "Supprimer une solution")
    @DeleteMapping("/supprimer/{emailUtilisateur}/{password}/{idSolution}")
    public String delete(@PathVariable Long idSolution, @PathVariable("emailUtilisateur") String emailUtilisateur, @PathVariable("password") String password){
        //instanciation de User en user et user1 pour recuperer l'email et le mot de pass
        Utilisateur user1 = utilisateurService.TrouverParEmail(emailUtilisateur);
        if (user1 == null) return "Email incorrect!";
        else if (!user1.getPassword().equals(password)) return "Mot de passe incorrect!";
        else {
            solutionService.SupprimerSolution(idSolution);
        }
        return "Solution supprimée avec succès";
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
