package com.example.errorNote.Service;

import com.example.errorNote.Modele.Role;


public interface RoleService {

    //================METHODE PERMETTANT DE RECUPERER L'IDENTIFIANT D'UN ROLE=========================
    Role RecupererParIdRole(Long idRole);

    //================METHODE PERMETTANT DE RECUPERER LIBELLE D'UN ROLE=========================
    Role getLibelleRolee(String libelleRole);

    //================METHODE PERMETTANT D'AJOUTER UN ROLE=========================
    Role AjouterRole(Role role);


}
