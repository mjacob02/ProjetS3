package fr.insa.jacob.projets3.repository;

import fr.insa.jacob.projets3.entity.Machine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface MachineRepository extends JpaRepository<Machine, Integer>, JpaSpecificationExecutor<Machine> {

}