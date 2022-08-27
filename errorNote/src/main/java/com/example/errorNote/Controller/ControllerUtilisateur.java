package com.example.errorNote.Controller;

import com.example.errorNote.Modele.Role;
import com.example.errorNote.Modele.Utilisateur;
import com.example.errorNote.Service.RoleService;
import com.example.errorNote.Service.UtilisateurService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "CreerUtilisateur")
@NoArgsConstructor
@AllArgsConstructor
@Api(value = "hello", description = "Gestion des utilisateurs")
public class ControllerUtilisateur {
    @Autowired
    private UtilisateurService utilisateurService;
    @Autowired
    private RoleService roleService;
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
}
