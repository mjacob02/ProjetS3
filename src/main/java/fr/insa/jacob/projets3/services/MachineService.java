package fr.insa.jacob.projets3.services;

import fr.insa.jacob.projets3.entity.Machine;
import fr.insa.jacob.projets3.entity.Produit;
import fr.insa.jacob.projets3.repository.MachineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class MachineService {

    @Autowired
    private MachineRepository machineRepository;


    public void delete(Integer id) {
        machineRepository.deleteById(id);
    }


    private Machine requireOne(Integer id) {
        return machineRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Resource not found: " + id));
    }

    public List<Machine> findAll(String stringFilter) {
        if ((stringFilter) == null || (stringFilter).isEmpty()) {
            return machineRepository.findAll();
        } else {
            return machineRepository.search(stringFilter);
        }
    }

    /***
     * Delete Machine
     */
    public void delete(Machine machine) {
        machineRepository.delete(machine);
    }

    /***
     * Save Machine
     */
    public Machine save(Machine machine) {
        if (machine == null) {
            System.err.println("Machine is null. Are you sure you have connected your form to the application?");
            return null;
        }
        return machineRepository.save(machine);
    }

}
