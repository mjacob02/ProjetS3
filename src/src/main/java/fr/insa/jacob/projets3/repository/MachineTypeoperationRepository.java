package fr.insa.jacob.projets3.repository;

import fr.insa.jacob.projets3.entity.MachineTypeoperation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface MachineTypeoperationRepository extends JpaRepository<MachineTypeoperation, Void>, JpaSpecificationExecutor<MachineTypeoperation> {

}