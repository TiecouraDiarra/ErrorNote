package com.example.errorNote.Repository;

import com.example.errorNote.Modele.Probleme;
import com.example.errorNote.Modele.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface RepoUtilisateur extends JpaRepository<Utilisateur,Long> {

    Utilisateur findByEmailUtilisateur(String emailUtilisateur);


    //================DEBUT DE LA REQUETE PERMETTANT D'AFFICHER TOUS LES ADMINISTRATEURS=========================
    @Query(value = "select * from utilisateur where id_role=1", nativeQuery = true)
    List<Utilisateur> AfficherTousLesAdmins();
    //================FIN DE LA REQUETE PERMETTANT D'AFFICHER TOUS LES ADMINISTRATEURS=========================


    //================DEBUT DE LA REQUETE PERMETTANT D'AFFICHER TOUS LES UTILISATEURS SIMPLES=========================
    @Query(value = "select * from utilisateur where id_role=2", nativeQuery = true)
    List<Utilisateur> AfficherTousLesUserSimples();
    //================FIN DE LA REQUETE PERMETTANT D'AFFICHER TOUS LES UTILISATEURS SIMPLES=========================
}
