package fr.insa.jacob.projets3.repository;


import fr.insa.jacob.projets3.entity.Gamme;
import fr.insa.jacob.projets3.entity.Produit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GammeRepository extends JpaRepository<Gamme, Integer>, JpaSpecificationExecutor<Gamme> {
    /***
     * Find all GammeRepository with search filter on reference or description
     */
    @Query("select g from GammeRepository g " +
            "where lower(g.reference) like lower(concat('%', :searchTerm, '%')) " +
            "or lower(g.description) like lower(concat('%', :searchTerm, '%'))")
    List<Gamme> search(@Param("searchTerm") String searchTerm);

}