package fr.insa.jacob.projets3.services;

import fr.insa.jacob.projets3.entity.Exemplaire;
import fr.insa.jacob.projets3.entity.OperationEffectuee;
import fr.insa.jacob.projets3.entity.Produit;
import fr.insa.jacob.projets3.repository.OperationEffectueeRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class OperationEffectueeService {

    @Autowired
    private OperationEffectueeRepository operationEffectueeRepository;

    public void delete(OperationEffectuee operationEffectuee) {
        operationEffectueeRepository.delete(operationEffectuee);
    }


    private OperationEffectuee requireOne(Integer id) {
        return operationEffectueeRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Resource not found: " + id));
    }

    public void save(OperationEffectuee operationEffectuee) {
    }
    public List<OperationEffectuee> findAll(String stringFilter) {
        if (stringFilter == null || stringFilter.isEmpty()) {
            return operationEffectueeRepository.findAll();
        } else {
            return operationEffectueeRepository.search(stringFilter);
        }
    }


}
