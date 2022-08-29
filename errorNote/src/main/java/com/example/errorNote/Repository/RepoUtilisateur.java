package com.example.errorNote.Repository;

import com.example.errorNote.Modele.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepoUtilisateur extends JpaRepository<Utilisateur,Long> {

    //Utilisateur findByEmail(String emailUtilisateur);
}
