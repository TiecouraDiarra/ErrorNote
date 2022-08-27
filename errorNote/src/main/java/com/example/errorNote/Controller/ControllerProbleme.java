package com.example.errorNote.Controller;


import com.example.errorNote.Modele.EtatProbleme;
import com.example.errorNote.Modele.Probleme;
import com.example.errorNote.Modele.Utilisateur;
import com.example.errorNote.Service.EtatService;
import com.example.errorNote.Service.ProblemeService;
import com.example.errorNote.Service.UtilisateurService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping(value = "CreerProbleme")
@NoArgsConstructor
@AllArgsConstructor
@Api(value = "hello", description = "Gestion des problèmes")
public class ControllerProbleme {
    @Autowired
    private ProblemeService problemeService;
    @Autowired
    private UtilisateurService utilisateurService;

    @Autowired
    private EtatService etatService;

    @ApiOperation(value = "Créer un problème")
    @PostMapping("/probleme/{idUtilisateur}/{idEtat}")
    public Object creerProbleme(@RequestBody Probleme probleme, @PathVariable("idUtilisateur") Long idUtilisateur, @PathVariable("idEtat") Long idEtat){
        Utilisateur utilisateur = utilisateurService.RecupererParId(idUtilisateur);
        EtatProbleme etatProbleme = etatService.RecupererParIdEtat(idEtat);

        if(utilisateur!=null || etatProbleme!=null){
            probleme.setUtilisateur(utilisateur);
            probleme.setEtat(etatProbleme);
            return problemeService.CreerProbleme(probleme);
        } else {
            return "Vous essayez de créer un problème par un utilisateur qui n'existe pas !";
        }
    }

    @ApiOperation(value = "Recherche de probleme avec le titre ")
    @GetMapping("/probleme/{titreProbleme}")
    public List<Probleme> RechercherProbleme(@Param("titreProbleme") @PathVariable String titreProbleme){

        return problemeService.Rechercher(titreProbleme);
    }
}
