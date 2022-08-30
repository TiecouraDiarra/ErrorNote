package com.example.errorNote.Service;

import com.example.errorNote.Modele.Role;
import com.example.errorNote.Modele.Utilisateur;
import com.example.errorNote.Repository.RepoRole;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RoleImpl implements RoleService{

    //================LA DEPENDANCE DU REPOSITORY DE ROLE=========================
    @Autowired
    private RepoRole repoRole;

    //================DEBUT DE LA METHODE PERMETTANT DE RECUPERER L'IDENTIFIANT D'UN ROLE=========================
    @Override
    public Role RecupererParIdRole(Long idRole) {
        return repoRole.findById(idRole).get();
    }
    //================FIN DE LA METHODE PERMETTANT DE RECUPERER L'IDENTIFIANT D'UN ROLE=========================

    //================DEBUT DE LA METHODE PERMETTANT DE RECUPERER LE LIBELLE D'UN ROLE=========================
    @Override
    public Role getLibelleRolee(String libelleRole) {
        return repoRole.findByLibelleRole(libelleRole);
    }
    //================FIN DE LA METHODE PERMETTANT DE RECUPERER LE LIBELLE D'UN ROLE=========================


    //================DEBUT DE LA METHODE PERMETTANT D'AJOUTER UN ROLE=========================
    @Override
    public Role AjouterRole(Role role) {
        return repoRole.save(role);
    }
    //================FIN DE LA METHODE PERMETTANT D'AJOUTER UN ROLE=========================
}
