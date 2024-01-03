package fr.insa.jacob.projets3.repository;

import fr.insa.jacob.projets3.entity.Machine;
import fr.insa.jacob.projets3.entity.Produit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MachineRepository extends JpaRepository<Machine, Integer>, JpaSpecificationExecutor<Machine> {

    /***
     * Find all Machine with search filter on reference or description
     */
    @Query("select m from Machine m " +
            "where lower(m.reference) like lower(concat('%', :searchTerm, '%')) " +
            "or lower(m.description) like lower(concat('%', :searchTerm, '%'))")
    List<Machine> search(@Param("searchTerm") String searchTerm);

}