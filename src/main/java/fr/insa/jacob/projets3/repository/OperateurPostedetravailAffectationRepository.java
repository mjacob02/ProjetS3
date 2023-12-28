package fr.insa.jacob.projets3.repository;

import fr.insa.jacob.projets3.entity.OperateurPostedetravailAffectation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface OperateurPostedetravailAffectationRepository extends JpaRepository<OperateurPostedetravailAffectation, Void>, JpaSpecificationExecutor<OperateurPostedetravailAffectation> {

}