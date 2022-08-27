package com.example.errorNote.Repository;

import com.example.errorNote.Modele.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepoUtilisateur extends JpaRepository<Utilisateur,Long> {
}
