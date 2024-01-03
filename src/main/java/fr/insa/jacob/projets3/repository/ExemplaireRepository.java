package fr.insa.jacob.projets3.repository;

import fr.insa.jacob.projets3.entity.Exemplaire;
import fr.insa.jacob.projets3.entity.Produit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ExemplaireRepository extends JpaRepository<Exemplaire, Integer>, JpaSpecificationExecutor<Exemplaire> {

    /***
     * Find all Exemplaire with search filter on right numero de serie
     */
    @Query("select e from Exemplaire e " +
            "where e.numeroDeSerie = :searchterm")  // Maybe : cast(:searchterm as integer)
    // "where cast(e.integerColumn as string) like concat('%', :searchTerm, '%')")
    List<Exemplaire> search(@Param("searchTerm") String searchTerm);

}