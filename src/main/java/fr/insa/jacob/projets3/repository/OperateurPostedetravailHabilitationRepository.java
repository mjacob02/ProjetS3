package fr.insa.jacob.projets3.repository;

import fr.insa.jacob.projets3.entity.OperateurPostedetravailHabilitation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface OperateurPostedetravailHabilitationRepository extends JpaRepository<OperateurPostedetravailHabilitation, Void>, JpaSpecificationExecutor<OperateurPostedetravailHabilitation> {

}