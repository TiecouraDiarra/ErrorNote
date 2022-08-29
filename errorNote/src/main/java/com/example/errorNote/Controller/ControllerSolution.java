package com.example.errorNote.Controller;

import com.example.errorNote.Modele.Probleme;
import com.example.errorNote.Modele.Solution;
import com.example.errorNote.Service.ProblemeService;
import com.example.errorNote.Service.SolutionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @ApiOperation(value = "Créer une solution")
    @PostMapping("/solution/{idProbleme}")
    public Object creerSolution(@RequestBody Solution solution, @PathVariable("idProbleme") Long idProbleme){
        Probleme probleme = problemeService.RecupererParIdProbleme(idProbleme);
        if(probleme!=null){
            solution.setProbleme(probleme);
            solutionService.CreerSolution(solution);
            return "Solution créée avec succès";
        } else {
            return "Vous essayez de donner la solution d'un problème qui n'existe pas !";
        }
        //System.out.println("Solution ajoutée avec succès");
    }

    @ApiOperation(value = "Modifier une solution ")
    @PutMapping("/modifier/{idSolution}")
    public Solution update(@PathVariable Long idSolution, @RequestBody Solution solution){
        return solutionService.ModifierSolution(idSolution, solution);
    }

    @ApiOperation(value = "Supprimer une solution")
    @DeleteMapping("/supprimer/{idSolution}")
    public String delete(@PathVariable Long idSolution){
        return solutionService.SupprimerSolution(idSolution);
    }
}
