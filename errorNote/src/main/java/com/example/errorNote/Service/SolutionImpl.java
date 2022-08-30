package com.example.errorNote.Service;


import com.example.errorNote.Modele.Solution;
import com.example.errorNote.Repository.RepoSolution;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class SolutionImpl implements SolutionService {

    //================LA DEPENDANCE DU REPOSITORY DE SOLUTION=========================
    private RepoSolution repoSolution;

    //================DEBUT DE LA METHODE PERMETTANT DE CREER UNE SOLUTION=========================
    @Override
    public Solution CreerSolution(Solution solution) {
        return repoSolution.save(solution);
    }

    //================FIN DE LA METHODE PERMETTANT DE CREER UNE SOLUTION=========================

    //================DEBUT DE LA METHODE PERMETTANT DE RECUPERER L'IDENTIFIANT D'UNE SOLUTION=========================
    @Override
    public Solution RecupererIdSolution(Long idSolution) {
        return repoSolution.findById(idSolution).get();
    }

    //================FIN DE LA METHODE PERMETTANT DE RECUPERER L'IDENTIFIANT D'UNE SOLUTION=========================

    //================DEBUT DE LA METHODE PERMETTANT DE MODIFIER UNE SOLUTION=========================
    @Override
    public Solution ModifierSolution(Long idSolution, Solution solution) {
        return repoSolution.findById(idSolution)
                .map(p->{
                    p.setDescriptionSolution(solution.getDescriptionSolution());
                    p.setRessources(solution.getRessources());
                    p.setTechnologie(solution.getTechnologie());
                    p.setMethodologieAdop(solution.getMethodologieAdop());
                    p.setDateSolution(solution.getDateSolution());
                    return repoSolution.save(p);
                }).orElseThrow(() -> new RuntimeException("Solution non trouvée !"));
    }

    //================FIN DE LA METHODE PERMETTANT DE MODIFIER UNE SOLUTION=========================

    //================DEBUT DE LA METHODE PERMETTANT DE SUPPRIMER UNE SOLUTION=========================
    @Override
    public String SupprimerSolution(Long idSolution) {
        repoSolution.deleteById(idSolution);
        return "Solution supprimée avec succès";
    }

    @Override
    public List<Solution> AfficherToutesLesSolutions() {
        return repoSolution.findAll();
    }
    //================FIN DE LA METHODE PERMETTANT DE SUPPRIMER UNE SOLUTION=========================

}
