package fr.insa.jacob.projets3.repository;

import fr.insa.jacob.projets3.entity.Exemplaire;
import fr.insa.jacob.projets3.entity.Operation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OperationRepository extends JpaRepository<Operation, Integer>, JpaSpecificationExecutor<Operation> {

    @Query("select o from Operation o " +
            "where o.description = :searchTerm")  // Maybe : cast(:searchterm as integer)
        // "where cast(e.integerColumn as string) like concat('%', :searchTerm, '%')")
    List<Operation> search(@Param("searchTerm") String searchTerm);
}