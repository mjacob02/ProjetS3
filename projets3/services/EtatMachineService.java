package fr.insa.jacob.projets3.services;

import fr.insa.jacob.projets3.entity.EtatMachine;
import fr.insa.jacob.projets3.repository.EtatMachineRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class EtatMachineService {

    @Autowired
    private EtatMachineRepository etatMachineRepository;

    public void delete(Integer id) {
        etatMachineRepository.deleteById(id);
    }


    private EtatMachine requireOne(Integer id) {
        return etatMachineRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Resource not found: " + id));
    }
}
