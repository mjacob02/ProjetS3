package fr.insa.jacob.projets3.repository;

import fr.insa.jacob.projets3.entity.Exemplaire;
import fr.insa.jacob.projets3.entity.OperationEffectuee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OperationEffectueeRepository extends JpaRepository<OperationEffectuee, Integer>, JpaSpecificationExecutor<OperationEffectuee> {
    @Query("select e from OperationEffectuee e " +
            "where e.debutOperation = :searchTerm")  // Maybe : cast(:searchterm as integer)
        // "where cast(e.integerColumn as string) like concat('%', :searchTerm, '%')")
    List<OperationEffectuee> search(@Param("searchTerm") String searchTerm);

}