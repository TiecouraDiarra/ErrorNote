package com.example.errorNote.Repository;

import com.example.errorNote.Modele.Probleme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RepoProbleme extends JpaRepository<Probleme, Long> {
    @Query(value = "select * from probleme where probleme.titre_probleme=:titre_probleme", nativeQuery = true)
    List<Probleme> Rechercher(@Param("titre_probleme") String titre_probleme);
}
