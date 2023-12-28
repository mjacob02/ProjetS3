package fr.insa.jacob.projets3.repository;


import fr.insa.jacob.projets3.entity.Gamme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface GammeRepository extends JpaRepository<Gamme, Integer>, JpaSpecificationExecutor<Gamme> {

    List<Gamme> search(String stringFilter);
}