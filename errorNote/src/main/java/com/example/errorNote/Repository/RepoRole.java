package com.example.errorNote.Repository;

import com.example.errorNote.Modele.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepoRole extends JpaRepository<Role,Long> {

    //================DEBUT DE LA METHODE PERMETTANT DE TROUVER LE LIBELLE DU ROLE=========================
    Role findByLibelleRole(String libelleRole);

    //================FIN DE LA METHODE PERMETTANT DE TROUVER LE LIBELLE DU ROLE=========================
}
