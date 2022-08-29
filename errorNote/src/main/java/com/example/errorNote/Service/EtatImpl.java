package com.example.errorNote.Service;

import com.example.errorNote.Modele.EtatProbleme;
import com.example.errorNote.Repository.RepoEtat;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EtatImpl implements EtatService{

    //================LA DEPENDANCE DU REPOSITORY DES ETATS D'UN PROBLEME=========================
    @Autowired
    private RepoEtat repoEtat;

    //================DEBUT DE LA METHODE PERMETTANT DE RECUPERER L'IDENTIFIANT DE L'ETAT D'UN PROBLEME=========================
    @Override
    public EtatProbleme RecupererParIdEtat(Long idEtat) {

        return repoEtat.findById(idEtat).get();
    }
    //================FIN DE LA METHODE PERMETTANT DE RECUPERER L'IDENTIFIANT DE L'ETAT D'UN PROBLEME=========================

}
