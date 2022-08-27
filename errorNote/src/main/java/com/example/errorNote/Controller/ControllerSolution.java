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
@RequestMapping(value = "CreerSolution")
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
            return solutionService.CreerSolution(solution);
        } else {
            return "Vous essayez de créer un problème par un utilisateur qui n'existe pas !";
        }
        //System.out.println("Solution ajoutée avec succès");
    }
}
