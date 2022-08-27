package com.example.errorNote.Repository;

import com.example.errorNote.Modele.Commentaire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

public interface RepoCommentaire extends JpaRepository<Commentaire,Long> {
    @Transactional
    @Modifying
    @Query(value = "INSERT INTO `commentaire` (`commentaire`, `id_solution`, `id_autres_utilisateurs`) VALUES (:commentaire, :id_solution , :id_autres_utilisateurs)",nativeQuery = true)
    int ajoutCommAutreUser(@Param("commentaire") String commentaire, @Param("id_solution") Long id_solution, @Param("id_autres_utilisateurs") Long id_autres_utilisateurs);
}
