package fr.insa.jacob.projets3.repository;

import fr.insa.jacob.projets3.entity.Produit;
import fr.insa.jacob.projets3.entity.TypeOperation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TypeOperationRepository extends JpaRepository<TypeOperation, Integer>, JpaSpecificationExecutor<TypeOperation> {

    @Query("select t from TypeOperation t " +
            "where lower(t.description) like lower(concat('%', :searchTerm, '%')) ")
            List<TypeOperation> search(@Param("searchTerm") String searchTerm);

}