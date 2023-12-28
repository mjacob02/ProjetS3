package fr.insa.jacob.projets3.repository;

import fr.insa.jacob.projets3.entity.TypeOperation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface TypeOperationRepository extends JpaRepository<TypeOperation, Integer>, JpaSpecificationExecutor<TypeOperation> {

}