package fr.insa.jacob.projets3.repository;

import fr.insa.jacob.projets3.entity.GammeOperation;
import fr.insa.jacob.projets3.entity.Produit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GammeOperationRepository extends JpaRepository<GammeOperation, Void>, JpaSpecificationExecutor<GammeOperation> {


}