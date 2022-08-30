package com.example.errorNote.Controller;

import com.example.errorNote.Modele.EtatProbleme;
import com.example.errorNote.Service.EtatService;
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
@RequestMapping(value = "Etat")
@NoArgsConstructor
@AllArgsConstructor
@Api(value = "hello", description = "Gestion des Etats")
public class ControllerEtat {
    @Autowired
    private EtatService etatService;

    @ApiOperation(value = "Ajouter un état")
    @PostMapping("/ajouter")
    public String create(@RequestBody EtatProbleme etatProbleme){
        etatService.AjouterEtat(etatProbleme);
        return "Etat ajouté avec succès";
    }

}
