package fr.insa.jacob.projets3.repository;

import fr.insa.jacob.projets3.entity.Exemplaire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ExemplaireRepository extends JpaRepository<Exemplaire, Integer>, JpaSpecificationExecutor<Exemplaire> {

}