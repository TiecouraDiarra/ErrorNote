package com.example.errorNote.Controller;

import com.example.errorNote.Modele.Commentaire;
import com.example.errorNote.Modele.Solution;
import com.example.errorNote.Service.CommentaireService;
import com.example.errorNote.Service.SolutionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

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

    /*@ApiOperation(value = "Ajouter un commentaire")
    @PostMapping("/commentaire/{idUtilisateur}")
    public Commentaire AjouterComm(@RequestBody Commentaire commentaire, @PathVariable("idUtilisateur") Long idUtilisateur){
        return commentaireService.AjouterCommentaire(commentaire);
    }*/

    // Ajout commentaire des autre user
    @ApiOperation(value = "Ajout de commentaire")
    @PostMapping("/ajoutCommentaire/{idSolution}/{commentaire}/{idUtilisateur}")
    public String AjoutCommentaireAutreUser(@Param("idSolution") @PathVariable long idSolution, @Param("commentaire") @PathVariable String commentaire , @Param("idUtilisateur") @PathVariable long idUtilisateur){
        return commentaireService.AjouterCommentaireAutre(commentaire,idSolution,idUtilisateur);
    }

    @ApiOperation(value = "Afficher les commentaires")
    @GetMapping("/AfficherCommentaire/{idSolution}")
    public List<Commentaire> AfficherTousCommentaire(@Param("idSolution") @PathVariable long idSolution){
        Solution solution = solutionService.RecupererIdSolution(idSolution);
        return solution.getCommentaireList();
    }
}
