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

import java.util.List;

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
    @ApiOperation(value = "Pour la création d'un user simple")
    @PostMapping("/create/")
    public Object create(@RequestBody Utilisateur user) {
        Utilisateur u = utilisateurService.TrouverParEmail(user.getEmailUtilisateur());
        Role role = roleService.getLibelleRolee("USER");

        if (u == null) {
            user.setRole(role);
            return utilisateurService.AjouterUtilisateur(user);

        } else {
            return "Cet utilisateur existe deja!!";
        }

    }

    //================FIN DE LA METHODE PERMETTANT DE CREER UN UTILISATEUR====================================

    //================DEBUT DE LA METHODE PERMETTANT DE CREER UN ADMIN====================================
    @ApiOperation(value = "Pour la création d'un administrateur")
    @PostMapping("/create/{idAdmin}")
    public Object createAdmin(@RequestBody Utilisateur user, @PathVariable Long idAdmin) {
        Utilisateur u = utilisateurService.TrouverParEmail(user.getEmailUtilisateur());
        Utilisateur admin = utilisateurService.RecupererParId(idAdmin);
        Role role = roleService.getLibelleRolee("ADMIN");

        if (u == null) {
            if (admin != null && admin.getRole() == role) {
                user.setRole(role);
                return utilisateurService.AjouterUtilisateur(user);
            } else {
                return "Vous essayez d'attribuez un role qui n'exsite pas!";
            }
        } else {
            return "Cet utilisateur existe deja!!";
        }

    }
    //================FIN DE LA METHODE PERMETTANT DE CREER UN ADMIN====================================

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
    @DeleteMapping("/supprimer/{emailUtilisateur}/{password}/{idUtilisateurASupprimer}/{idRole}")
    public String deleteUser(@PathVariable Long idUtilisateurASupprimer, @PathVariable("idRole") Long idRole, @PathVariable("emailUtilisateur") String emailUtilisateur, @PathVariable("password") String password){
        //instanciation de User en user et user1 pour recuperer l'email et le mot de pass
        Utilisateur user1 = utilisateurService.TrouverParEmail(emailUtilisateur);
        Role role1 = roleService.RecupererParIdRole(idRole);
        if (user1 == null) return "Email incorrect!";
        else if (!user1.getPassword().equals(password)) return "Mot de passe incorrect!";
        else if (role1!=null && role1.getLibelleRole().equals("ADMIN")){
            return utilisateurService.Supprimer(idUtilisateurASupprimer);
        }else {
            return "Vous n'avez pas le droit de supprimer un utilisateur";
        }
    }
    //================FIN DE LA METHODE PERMETTANT DE SUPPRIMER UN UTILISATEUR====================================

    //================DEBUT DE LA METHODE PERMETTANT D'AFFICHER TOUS LES UTILISATEURS ET ADMINS====================================
    @ApiOperation(value = "Afficher tous les utilisateurs et Administrateurs")
    @GetMapping("/AfficherUtilisateursAdmins")
    public List<Utilisateur> readLesUsers(){
        return utilisateurService.AfficherTousLesUtilisateurs();
    }
    //================FIN DE LA METHODE PERMETTANT D'AFFICHER TOUS LES UTILISATEURS ET ADMINS====================================

    //================DEBUT DE LA METHODE PERMETTANT D'AFFICHER TOUS LES ADMINISTRATEURS====================================
    @ApiOperation(value = "Afficher tous les administrateurs")
    @GetMapping("/AfficherAdmin")
    public List<Utilisateur> readLesAdmins(){
        return utilisateurService.AfficherTousLesAdmin();
    }
    //================FIN DE LA METHODE PERMETTANT D'AFFICHER TOUS LES ADMINISTRATEURS====================================

    //================DEBUT DE LA METHODE PERMETTANT D'AFFICHER TOUS LES UTILISATEURS SIMPLES====================================
    @ApiOperation(value = "Afficher tous les utilisateurs simples")
    @GetMapping("/AfficherUtilisateursSimples")
    public List<Utilisateur> readLesUserSimples(){
        return utilisateurService.AfficherTousLesUserSimple();
    }
    //================FIN DE LA METHODE PERMETTANT D'AFFICHER TOUS LES UTILISATEURS SIMPLES====================================

}
