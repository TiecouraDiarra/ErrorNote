package com.example.errorNote.Service;

import com.example.errorNote.Modele.EtatProbleme;
import com.example.errorNote.Modele.Probleme;
import com.example.errorNote.Repository.RepoEtat;
import com.example.errorNote.Repository.RepoProbleme;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProblemeImpl implements ProblemeService {

    //================LA DEPENDANCE DU REPOSITORY DES PROBLEMES=========================
    @Autowired
    private RepoProbleme repoProbleme;

    @Autowired
    private RepoEtat repoEtat;

    //================DEBUT DE LA METHODE PERMETTANT DE CREER UN PROBLEME=========================
    @Override
    public Probleme CreerProbleme(Probleme probleme) {
        probleme.setEtat(new EtatProbleme(1L));
        return repoProbleme.save(probleme);
    }
    //================FIN DE LA METHODE PERMETTANT DE CREER UN PROBLEME=========================

    //================DEBUT DE LA METHODE PERMETTANT DE RECUPERER L'IDENTIFIANT D'UN PROBLEME=========================
    @Override
    public Probleme RecupererParIdProbleme(Long idProbleme) {
        try {
            return repoProbleme.findById(idProbleme).get();
        }catch (Exception e){
            return null;
        }

    }
    //================FIN DE LA METHODE PERMETTANT DE RECUPERER L'IDENTIFIANT D'UN PROBLEME=========================


    //================DEBUT DE LA METHODE PERMETTANT DE RECHERCHER UN PROBLEME PAR TITRE=========================
    @Override
    public List<Probleme> Rechercher(String descriptionProbleme) {

        return repoProbleme.Rechercher(descriptionProbleme);
    }
    //================FIN DE LA METHODE PERMETTANT DE RECHERCHER UN PROBLEME PAR TITRE=========================

    //================DEBUT DE LA METHODE PERMETTANT DE MODIFIER UN PROBLEME=========================
    @Override
    public Probleme modifier(Long idProbleme, Probleme probleme) {
        return repoProbleme.findById(idProbleme)
                .map(p->{
                    p.setTitreProbleme(probleme.getTitreProbleme());
                    p.setDescriptionProbleme(probleme.getDescriptionProbleme());
                    return repoProbleme.save(p);
                }).orElseThrow(() -> new RuntimeException("Problème non trouvé !"));
    }

    //================FIN DE LA METHODE PERMETTANT DE MODIFIER UN PROBLEME=========================

    //================DEBUT DE LA METHODE PERMETTANT DE SUPPRIMER UN PROBLEME=========================
    @Override
    public String SupprimerProbleme(Long idProbleme) {
        repoProbleme.deleteById(idProbleme);
        return "Problème suprimé avec succès";
    }

    //================FIN DE LA METHODE PERMETTANT DE SUPPRIMER UN PROBLEME=========================

    //================DEBUT DE LA METHODE PERMETTANT D'AFFICHER TOUS LES PROBLEMES=========================
    @Override
    public Iterable<Object[]> AfficherTousLesProblemes() {
        return repoProbleme.AfficherTousLesProblemes();
    }

    //================FIN DE LA METHODE PERMETTANT D'AFFICHER TOUS LES PROBLEMES=========================


    //================DEBUT DE LA METHODE PERMETTANT DE CHANGER L'ETAT D'UN PROBLEME=========================
    @Override
    public String changerEtatProbleme(Long idProbleme, Long idEtat) {
        Probleme problemeAncienEtat = repoProbleme.findById(idProbleme).orElse(null);
        EtatProbleme nouveletat = repoEtat.findById(idEtat).orElse(null);

        if (problemeAncienEtat==null) return "Ce problème n'existe pas !";
        if (nouveletat == null) return "Etat invalide !";
        else {
            problemeAncienEtat.setEtat(new EtatProbleme(idEtat));
            repoProbleme.save(problemeAncienEtat);
            return "Etat du problème changer en : " +nouveletat.getEtat();
        }
    }
    //================FIN DE LA METHODE PERMETTANT DE CHANGER L'ETAT D'UN PROBLEME=========================

}
