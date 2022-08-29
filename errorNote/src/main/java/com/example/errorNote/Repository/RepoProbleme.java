package com.example.errorNote.Repository;

import com.example.errorNote.Modele.Probleme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RepoProbleme extends JpaRepository<Probleme, Long> {

    //================DEBUT DE LA REQUETE PERMETTANT DE RECHERCHER PAR MOT CLEF UN PROBLEME=========================
    @Query(value = "select * from probleme where probleme.description_probleme like %?%", nativeQuery = true)
    List<Probleme> Rechercher(String descriptionProbleme);

    //================FIN DE LA REQUETE PERMETTANT DE RECHERCHER PAR MOT CLEF UN PROBLEME=========================


    //================DEBUT DE LA REQUETE PERMETTANT D'AFFICHER LA LISTE DES PROBLEMES=========================
    @Query(value = "SELECT * from probleme", nativeQuery = true)
    Iterable<Object[]> AfficherTousLesProblemes();

    //================FIN DE LA REQUETE PERMETTANT D'AFFICHER LA LISTE DES PROBLEMES=========================
}
