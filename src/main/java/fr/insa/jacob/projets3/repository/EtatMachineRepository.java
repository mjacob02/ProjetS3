package fr.insa.jacob.projets3.repository;

import fr.insa.jacob.projets3.entity.EtatMachine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface EtatMachineRepository extends JpaRepository<EtatMachine, Integer>, JpaSpecificationExecutor<EtatMachine> {

}