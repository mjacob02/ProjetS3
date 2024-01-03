package fr.insa.jacob.projets3.repository;

import fr.insa.jacob.projets3.entity.GammeOperation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


import java.util.List;

public interface GammeOperationRepository extends JpaRepository<GammeOperation, Void>, JpaSpecificationExecutor<GammeOperation> {


}