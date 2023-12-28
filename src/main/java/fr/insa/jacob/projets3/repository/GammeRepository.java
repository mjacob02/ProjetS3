package fr.insa.jacob.projets3.repository;


import fr.insa.jacob.projets3.entity.Gamme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface GammeRepository extends JpaRepository<Gamme, Integer>, JpaSpecificationExecutor<Gamme> {

}