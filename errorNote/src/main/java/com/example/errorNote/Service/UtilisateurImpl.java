package com.example.errorNote.Service;

import com.example.errorNote.Modele.Utilisateur;
import com.example.errorNote.Repository.RepoUtilisateur;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UtilisateurImpl implements UtilisateurService {

    private RepoUtilisateur repoUtilisateur;
    @Override
    public Utilisateur AjouterUtilisateur(Utilisateur utilisateur) {
        return repoUtilisateur.save(utilisateur);
    }

    @Override
    public Utilisateur RecupererParId(Long idUtilisateur) {
        return repoUtilisateur.findById(idUtilisateur).get();
    }
}
