package fr.insa.jacob.projets3.repository;

import fr.insa.jacob.projets3.entity.OperationEffectuee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface OperationEffectueeRepository extends JpaRepository<OperationEffectuee, Integer>, JpaSpecificationExecutor<OperationEffectuee> {

}