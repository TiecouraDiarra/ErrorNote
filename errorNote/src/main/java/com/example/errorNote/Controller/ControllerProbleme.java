package com.example.errorNote.Controller;


import com.example.errorNote.Modele.*;
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

import java.util.Date;
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

    /*@ApiOperation(value = "Créer un problème")
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


    }*/

    //================DEBUT DE LA METHODE PERMETTANT DE CREER UN PROBLEME====================================
    @ApiOperation(value = "Créer un problème(Verification)")
    @PostMapping("/create/{emailUtilisateur}/{password}")
    public String create(@RequestBody Probleme probleme, @PathVariable("emailUtilisateur") String emailUtilisateur, @PathVariable("password") String password){

        //instanciation de User en user et user1 pour recuperer l'email et le mot de pass
        Utilisateur user = utilisateurService.TrouverParEmail(emailUtilisateur);
        //Utilisateur utilisateur = utilisateurService.RecupererParId(idUtilisateur);
        //Role admin = roleService.getLibelleRolee("ADMIN");

        if (user == null) return "Email incorrect!";
        else if (!user.getPassword().equals(password)) return "Mot de passe incorrect!";
        //recupère le password de l'email qu'il a saisie et verifie s'il est egale au password saisie en url
        else {
            probleme.setDateProbleme(new Date());
            // A la table probleme on affecte la valeur recuperer dans user1 et user
            probleme.setUtilisateur(user);
            this.problemeService.CreerProbleme(probleme);

            return "Problème crée avec succès";
        }

    }
    //================FIN DE LA METHODE PERMETTANT DE CREER UN PROBLEME====================================


    //================DEBUT DE LA METHODE PERMETTANT DE RECHERCHER UN PROBLEME PAR MOT CLEF====================================
    @ApiOperation(value = "Recherche de probleme par mot clé (Description) ")
    @GetMapping("/recherche/{descriptionProbleme}")
    public List<Probleme> RechercherProbleme(@Param("descriptionProbleme") @PathVariable String descriptionProbleme){

        return problemeService.Rechercher(descriptionProbleme);
    }

    //================FIN DE LA METHODE PERMETTANT DE RECHERCHER UN PROBLEME PAR MOT CLEF====================================


    //================DEBUT DE LA METHODE PERMETTANT DE MODIFIER UN PROBLEME====================================
    @ApiOperation(value = "Modifier un problème ")
    @PutMapping("/modifier/{emailUtilisateur}/{password}/{idProbleme}")
    public String update(@PathVariable Long idProbleme, @RequestBody Probleme probleme,@PathVariable("emailUtilisateur") String emailUtilisateur, @PathVariable("password") String password){

        //instanciation de User en user et user1 pour recuperer l'email et le mot de pass
        Utilisateur user1 = utilisateurService.TrouverParEmail(emailUtilisateur);
        Role admin = roleService.getLibelleRolee("ADMIN");

        if (user1 == null) return "Email incorrect!";
        else if (!user1.getPassword().equals(password)) return "Mot de passe incorrect!";
            //recupère le password de l'email qu'il a saisie et verifie s'il est egale au password saisie en url
        else if (probleme.getUtilisateur()==user1 || user1.getRole()==admin ){
            probleme.setDateProbleme(new Date());
            probleme.setUtilisateur(user1);
            this.problemeService.modifier(idProbleme, probleme);
            return "Problème modifié avec succès\" !";
        }else {
            return "Impossible de modifier un problème qui ne vous appartient pas !";
        }
        /*else {
            // A la table probleme on affecte la valeur recuperer dans user1 et user
            probleme.setUtilisateur(user1);

            this.problemeService.modifier(idProbleme, probleme);

            return "Problème modifié avec succès";
        }*/
    }
    //================FIN DE LA METHODE PERMETTANT DE MODIFIER UN PROBLEME====================================

    //================DEBUT DE LA METHODE PERMETTANT DE CHANGER L'ETAT UN PROBLEME====================================
    @ApiOperation(value = "Modifier l'Etat du problème")
    @PostMapping("/changeretat/{emailUtilisateur}/{password}/{idProbleme}/{idEtat}")
    public String changerEtat(Probleme probleme,@PathVariable("idProbleme") Long idProbleme,@PathVariable("idEtat") Long idEtat, @PathVariable("emailUtilisateur") String emailUtilisateur, @PathVariable("password") String password) {
        //instanciation de User en user et user1 pour recuperer l'email et le mot de pass
        Utilisateur user1 = utilisateurService.TrouverParEmail(emailUtilisateur);
        Role admin = roleService.getLibelleRolee("ADMIN");

        if (user1 == null) return "Email incorrect!";
        else if (!user1.getPassword().equals(password)) return "Mot de passe incorrect!";
            //recupère le password de l'email qu'il a saisie et verifie s'il est egale au password saisie en url
        else if (probleme.getUtilisateur()==user1 || user1.getRole()==admin ){
            return problemeService.changerEtatProbleme(idProbleme,idEtat);
        }else {
            return "Impossible de modifier l'état d'un problème qui ne vous appartient pas !";
        }
        /*else {
            return problemeService.changerEtatProbleme(idProbleme,idEtat);
        }*/
    }
    //================FIN DE LA METHODE PERMETTANT DE CHANGER L'ETAT UN PROBLEME====================================


    //================DEBUT DE LA METHODE PERMETTANT DE SUPPRIMER UN PROBLEME====================================
    @ApiOperation(value = "Supprimer un problème")
    @DeleteMapping("/supprimer/{emailUtilisateur}/{password}/{idProbleme}/{idRole}")
    public String deleteProbleme(@PathVariable Long idProbleme, @PathVariable("idRole") Long idRole, @PathVariable("emailUtilisateur") String emailUtilisateur, @PathVariable("password") String password){

        Role role1 = roleService.RecupererParIdRole(idRole);
        Utilisateur user1 = utilisateurService.TrouverParEmail(emailUtilisateur);
        if (user1 == null) return "Email incorrect!";
        else if (!user1.getPassword().equals(password)) return "Mot de passe incorrect!";
        else if (role1!=null && role1.getLibelleRole().equals("ADMIN")){
            return problemeService.SupprimerProbleme(idProbleme);
        }else {
            return "Vous n'avez pas le droit de supprimer un probleme";
        }
    }
    //================FIN DE LA METHODE PERMETTANT DE SUPPRIMER UN PROBLEME====================================


    //================DEBUT DE LA METHODE PERMETTANT D'AFFICHER LA LISTE DES PROBLEMES====================================
    @ApiOperation(value = "Afficher la liste des problèmes1")
    @GetMapping("/AfficherProbleme")
    public List<Probleme> read(){
        return problemeService.AfficherTousLesProblemes1();
    }
    //================FIN DE LA METHODE PERMETTANT D'AFFICHER LA LISTE DES PROBLEMES====================================
}
