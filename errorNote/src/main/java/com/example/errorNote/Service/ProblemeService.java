package com.example.errorNote.Service;

import com.example.errorNote.Modele.Probleme;
import com.example.errorNote.Modele.Utilisateur;

import java.util.List;

public interface ProblemeService {
    Probleme CreerProbleme(Probleme probleme);

    Probleme RecupererParIdProbleme(Long idProbleme);

    List<Probleme> Rechercher(String titreProbleme);
}
