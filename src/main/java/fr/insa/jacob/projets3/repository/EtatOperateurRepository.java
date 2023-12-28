package fr.insa.jacob.projets3.repository;

import fr.insa.jacob.projets3.entity.EtatOperateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface EtatOperateurRepository extends JpaRepository<EtatOperateur, Integer>, JpaSpecificationExecutor<EtatOperateur> {

}