package com.example.errorNote.Repository;

import com.example.errorNote.Modele.Solution;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RepoSolution extends JpaRepository<Solution,Long> {

}
