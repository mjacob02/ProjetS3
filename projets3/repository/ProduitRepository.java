package fr.insa.jacob.projets3.repository;

import fr.insa.jacob.projets3.entity.Produit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProduitRepository extends JpaRepository<Produit, Integer>, JpaSpecificationExecutor<Produit> {

    /***
     * Find all Produit with search filter on reference or description
     */
    @Query("select p from Produit p " +
            "where lower(p.reference) like lower(concat('%', :searchTerm, '%')) " +
            "or lower(p.description) like lower(concat('%', :searchTerm, '%'))")
    List<Produit> search(@Param("searchTerm") String searchTerm);

}