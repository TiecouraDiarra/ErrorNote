package com.example.errorNote.Repository;

import com.example.errorNote.Modele.EtatProbleme;
import com.example.errorNote.Modele.Probleme;
import com.example.errorNote.Modele.Solution;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RepoSolution extends JpaRepository<Solution,Long> {

    //================DEBUT DE LA METHODE PERMETTANT DE TROUVER LE PROBLEME=========================
    Solution findByProbleme(Probleme probleme);
    //================FIN DE LA METHODE PERMETTANT DE TROUVER LE PROBLEME=========================

    //================DEBUT DE LA METHODE PERMETTANT DE TROUVER L'ETAT=========================
    //Solution findByEtat(EtatProbleme etatProbleme);
    //================FIN DE LA METHODE PERMETTANT DE TROUVER L'ETAT=========================
}
