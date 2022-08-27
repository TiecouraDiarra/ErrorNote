package com.example.errorNote.Service;

import com.example.errorNote.Modele.Utilisateur;

public interface UtilisateurService {
    Utilisateur AjouterUtilisateur(Utilisateur utilisateur);

    Utilisateur RecupererParId(Long idUtilisateur);

}
