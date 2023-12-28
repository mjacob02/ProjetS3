package fr.insa.jacob.projets3.repository;

import fr.insa.jacob.projets3.entity.Operateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface OperateurRepository extends JpaRepository<Operateur, Integer>, JpaSpecificationExecutor<Operateur> {

}