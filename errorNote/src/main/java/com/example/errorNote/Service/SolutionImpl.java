package com.example.errorNote.Service;


import com.example.errorNote.Modele.Solution;
import com.example.errorNote.Repository.RepoSolution;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SolutionImpl implements SolutionService {
    private RepoSolution repoSolution;
    @Override
    public Solution CreerSolution(Solution solution) {
        return repoSolution.save(solution);
    }
}
