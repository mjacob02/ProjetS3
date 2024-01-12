package fr.insa.jacob.projets3.services;

import fr.insa.jacob.projets3.entity.Exemplaire;
import fr.insa.jacob.projets3.entity.Operation;
import fr.insa.jacob.projets3.entity.Produit;
import fr.insa.jacob.projets3.repository.OperationRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class OperationService {

    @Autowired
    private OperationRepository operationRepository;



    public void delete(Operation operation) {
        operationRepository.delete(operation);
    }

    private Operation requireOne(Integer id) {
        return operationRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Resource not found: " + id));
    }

    public Operation save(Operation operation) {
        if (operation == null) {
            System.err.println("Produit is null. Are you sure you have connected your form to the application?");
            return null;
        }
        return operationRepository.save(operation);
    }

    public List<Operation> findAll(String stringFilter) {
        if (stringFilter == null || stringFilter.isEmpty()) {
            return operationRepository.findAll();
        } else {
            return operationRepository.search(stringFilter);
        }
    }

    /***
     * List all Operation
     */
    public List<Operation> listAll() {
        return operationRepository.findAll();
    }
}
