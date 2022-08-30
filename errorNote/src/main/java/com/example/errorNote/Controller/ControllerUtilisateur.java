package com.example.errorNote.Controller;

import com.example.errorNote.Modele.Probleme;
import com.example.errorNote.Modele.Role;
import com.example.errorNote.Modele.Utilisateur;
import com.example.errorNote.Service.ProblemeService;
import com.example.errorNote.Service.RoleService;
import com.example.errorNote.Service.UtilisateurService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "Utilisateur")
@NoArgsConstructor
@AllArgsConstructor
@Api(value = "hello", description = "Gestion des utilisateurs")
public class ControllerUtilisateur {
    @Autowired
    private UtilisateurService utilisateurService;
    @Autowired
    private RoleService roleService;

    @Autowired
    private ProblemeService problemeService;


    //================DEBUT DE LA METHODE PERMETTANT DE CREER UN UTILISATEUR====================================
    @ApiOperation(value = "Creation d'un utilisateur")
    @PostMapping(value = "/creerUtilisateur/{idRole}")
    public Object ajouterUtilisateur(@RequestBody Utilisateur utilisateur, @PathVariable("idRole") Long idRole) {
        Role role = roleService.RecupererParIdRole(idRole);
        if(role!=null){
            utilisateur.setRole(role);
            utilisateurService.AjouterUtilisateur(utilisateur);
        }
        return "Compte creer avec succes " + utilisateur.getNomUtilisateur();
    }

    //================FIN DE LA METHODE PERMETTANT DE CREER UN UTILISATEUR====================================

    //================DEBUT DE LA METHODE PERMETTANT DE MODIFIER UN UTILISATEUR====================================
    @ApiOperation(value = "Modifier un utilisateur")
    @PutMapping("/modifier/{emailUtilisateur}/{password}/{idUtilisateur}")
    public String update(@PathVariable Long idUtilisateur, @RequestBody Utilisateur utilisateur,@PathVariable("emailUtilisateur") String emailUtilisateur, @PathVariable("password") String password){
        //instanciation de User en user et user1 pour recuperer l'email et le mot de pass
        Utilisateur user1 = utilisateurService.TrouverParEmail(emailUtilisateur);

        if (user1 == null) return "Email incorrect!";
        else if (!user1.getPassword().equals(password)) return "Mot de passe incorrect!";
        //recupère le password de l'email qu'il a saisie et verifie s'il est egale au password saisie en url
        else {
            utilisateurService.modifier(idUtilisateur, utilisateur);
            return "Utilisateur modifié avec succès";
        }
    }
    //================FIN DE LA METHODE PERMETTANT DE MODIFIER UN UTILISATEUR====================================

    //================DEBUT DE LA METHODE PERMETTANT DE SUPPRIMER UN UTILISATEUR====================================
    @ApiOperation(value = "Supprimer un utilisateur")
    @DeleteMapping("/supprimer/{idUtilisateur}/{idRole}")
    public String deleteUser(@PathVariable Long idUtilisateur, @RequestBody Utilisateur utilisateur, @PathVariable("idRole") Long idRole, @PathVariable("emailUtilisateur") String emailUtilisateur, @PathVariable("password") String password){
        //instanciation de User en user et user1 pour recuperer l'email et le mot de pass
        Utilisateur user1 = utilisateurService.TrouverParEmail(emailUtilisateur);
        Role role1 = roleService.RecupererParIdRole(idRole);
        if (user1 == null) return "Email incorrect!";
        else if (!user1.getPassword().equals(password)) return "Mot de passe incorrect!";
        else if (role1!=null && role1.getLibelleRole().equals("ADMIN")){
            return utilisateurService.Supprimer(idUtilisateur);
        }else {
            return "Vous n'avez pas le droit de supprimer un utilisateur";
        }
    }
    //================FIN DE LA METHODE PERMETTANT DE SUPPRIMER UN UTILISATEUR====================================

}
