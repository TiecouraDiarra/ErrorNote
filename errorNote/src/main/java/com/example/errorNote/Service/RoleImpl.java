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

    @Autowired
    private RepoRole repoRole;
    @Override
    public Role RecupererParIdRole(Long idRole) {
        return repoRole.findById(idRole).get();
    }
}
