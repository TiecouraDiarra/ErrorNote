package com.example.errorNote.Repository;

import com.example.errorNote.Modele.EtatProbleme;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepoEtat extends JpaRepository<EtatProbleme, Long> {
}
