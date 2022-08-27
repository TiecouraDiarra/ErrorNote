package com.example.errorNote.Service;

import com.example.errorNote.Modele.EtatProbleme;
import com.example.errorNote.Repository.RepoEtat;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EtatImpl implements EtatService{
    @Autowired
    private RepoEtat repoEtat;
    @Override
    public EtatProbleme RecupererParIdEtat(Long idEtat) {
        return repoEtat.findById(idEtat).get();
    }
}
