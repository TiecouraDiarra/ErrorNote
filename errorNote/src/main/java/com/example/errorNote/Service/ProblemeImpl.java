package com.example.errorNote.Service;

import com.example.errorNote.Modele.Probleme;
import com.example.errorNote.Repository.RepoProbleme;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProblemeImpl implements ProblemeService {

    @Autowired
    private RepoProbleme repoProbleme;
    @Override
    public Probleme CreerProbleme(Probleme probleme) {

        return repoProbleme.save(probleme);
    }

    @Override
    public Probleme RecupererParIdProbleme(Long idProbleme) {
        return repoProbleme.findById(idProbleme).get();
    }

    @Override
    public List<Probleme> Rechercher(String titreProbleme) {
        return repoProbleme.Rechercher(titreProbleme);
    }
}
