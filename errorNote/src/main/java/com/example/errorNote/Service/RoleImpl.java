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
}
