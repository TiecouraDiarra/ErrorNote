package com.example.errorNote.Controller;

import com.example.errorNote.Modele.EtatProbleme;
import com.example.errorNote.Modele.Role;
import com.example.errorNote.Service.EtatService;
import com.example.errorNote.Service.RoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "Role")
@NoArgsConstructor
@AllArgsConstructor
@Api(value = "hello", description = "Gestion des Rôles")
public class ControllerRole {
    @Autowired
    private RoleService roleService;

    //================DEBUT DE LA METHODE PERMETTANT D'AJOUTER UN ROLE====================================
    @ApiOperation(value = "Ajouter un Rôle")
    @PostMapping("/ajouter")
    public String create(@RequestBody Role role){
        roleService.AjouterRole(role);
        return "Role ajouté avec succès";
    }
    //================DEBUT DE LA METHODE PERMETTANT D'AJOUTER UN ROLE====================================
}
