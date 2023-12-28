package fr.insa.jacob.projets3.services;

import fr.insa.jacob.projets3.entity.Machine;
import fr.insa.jacob.projets3.repository.MachineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public String findAll(String stringFilter) {
        if ((stringFilter) == null || (stringFilter).isEmpty()) {
            return String.valueOf((Machine) machineRepository.findAll());
        } else {
            return (stringFilter);
        }
    }

    public void delete(Machine machine) {
    }

    public void save(Machine machine) {
    }
}
