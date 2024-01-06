package fr.insa.jacob.projets3.services;

import fr.insa.jacob.projets3.entity.EtatMachine;
import fr.insa.jacob.projets3.entity.Exemplaire;
import fr.insa.jacob.projets3.entity.Gamme;
import fr.insa.jacob.projets3.repository.EtatMachineRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class EtatMachineService {

    @Autowired
    private EtatMachineRepository etatMachineRepository;
    public EtatMachine save(EtatMachine etatMachine) {
        if (etatMachine == null) {
            System.err.println("EtatMachine is null. Are you sure you have connected your form to the application?");
            return null;
        }
        return etatMachineRepository.save(etatMachine);
    }
    public void delete(Integer id) {
        etatMachineRepository.deleteById(id);
    }
    public void delete(EtatMachine etatMachine) {
    }

    private EtatMachine requireOne(Integer id) {
        return etatMachineRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Resource not found: " + id));
    }

    public List<EtatMachine> listAll() {
        return etatMachineRepository.findAll();
    }
}
