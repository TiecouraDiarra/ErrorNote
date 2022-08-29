package com.example.errorNote.Service;

import com.example.errorNote.Modele.Probleme;
import com.example.errorNote.Modele.Solution;

public interface SolutionService {

    //================METHODE PERMETTANT DE CREER UNE SOLUTION=========================
    Solution CreerSolution(Solution solution);

    //================METHODE PERMETTANT DE RECUPERER L'IDENTIFIANT D'UNE SOLUTION=========================
    Solution RecupererIdSolution(Long idSolution);

    //================METHODE PERMETTANT DE MODIFIER UNE SOLUTION=========================
    Solution ModifierSolution(Long idSolution, Solution solution);

    //================METHODE PERMETTANT DE SUPPRIMER UNE SOLUTION=========================
    String SupprimerSolution(Long idSolution);
}
