package com.example.errorNote.Controller;

import com.example.errorNote.Modele.Commentaire;
import com.example.errorNote.Service.CommentaireService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "AjouterCommentaire")
@NoArgsConstructor
@AllArgsConstructor
@Api(value = "hello", description = "Gestion des commentaires")
public class ControllerCommentaire {
    @Autowired

    private CommentaireService commentaireService;

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
}
