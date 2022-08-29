package com.example.errorNote.Controller;


import com.example.errorNote.Modele.EtatProbleme;
import com.example.errorNote.Modele.Probleme;
import com.example.errorNote.Modele.Role;
import com.example.errorNote.Modele.Utilisateur;
import com.example.errorNote.Service.EtatService;
import com.example.errorNote.Service.ProblemeService;
import com.example.errorNote.Service.RoleService;
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
@RequestMapping(value = "Probleme")
@NoArgsConstructor
@AllArgsConstructor
@Api(value = "hello", description = "Gestion des problèmes")
public class ControllerProbleme {
    @Autowired
    private ProblemeService problemeService;
    @Autowired
    private UtilisateurService utilisateurService;
    @Autowired
    private RoleService roleService;

    @Autowired
    private EtatService etatService;

    @ApiOperation(value = "Créer un problème")
    @PostMapping("/creer/{idUtilisateur}")
    public Object creerProbleme(@RequestBody Probleme probleme, @PathVariable("idUtilisateur") Long idUtilisateur){
        Utilisateur utilisateur = utilisateurService.RecupererParId(idUtilisateur);
        if (utilisateur!=null){
            probleme.setUtilisateur(utilisateur);
            problemeService.CreerProbleme(probleme);
            return "Problème créé avec succès ! ";
        }else {
            return "Vous essayez de créer un problème par un utilisateur qui n'existe pas !";
        }


    }

    @ApiOperation(value = "Recherche de probleme par mot clé (Description) ")
    @GetMapping("/recherche/{descriptionProbleme}")
    public List<Probleme> RechercherProbleme(@Param("descriptionProbleme") @PathVariable String descriptionProbleme){

        return problemeService.Rechercher(descriptionProbleme);
    }

    @ApiOperation(value = "Modifier un problème ")
    @PutMapping("/modifier/{idProbleme}")
    public Probleme update(@PathVariable Long idProbleme, @RequestBody Probleme probleme){
        return problemeService.modifier(idProbleme, probleme);
    }

    @ApiOperation(value = "Modifier l'Etat du problème")
    @PostMapping("/changeretat/{idProbleme}/{idEtat}")
    public String changerEtat(@PathVariable("idProbleme") Long idProbleme,@PathVariable("idEtat") Long idEtat) {
        return problemeService.changerEtatProbleme(idProbleme,idEtat);
    }

    @ApiOperation(value = "Supprimer un problème")
    @DeleteMapping("/supprimer/{idProbleme}/{idRole}")
    public String deleteProbleme(@PathVariable Long idProbleme, @PathVariable("idRole") Long idRole){

        Role role1 = roleService.RecupererParIdRole(idRole);
        if (role1!=null && role1.getLibelleRole().equals("ADMIN")){
            return problemeService.SupprimerProbleme(idProbleme);
        }else {
            return "Vous n'avez pas le droit de supprimer un probleme";
        }
    }

    @ApiOperation(value = "Afficher la liste des problèmes")
    @GetMapping("/AfficherProbleme")
    public Iterable<Object[]> getProbleme(){

        return problemeService.AfficherTousLesProblemes();
    }
}
