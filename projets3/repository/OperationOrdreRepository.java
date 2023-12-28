package fr.insa.jacob.projets3.repository;

import fr.insa.jacob.projets3.entity.OperationOrdre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface OperationOrdreRepository extends JpaRepository<OperationOrdre, Integer>, JpaSpecificationExecutor<OperationOrdre> {

}