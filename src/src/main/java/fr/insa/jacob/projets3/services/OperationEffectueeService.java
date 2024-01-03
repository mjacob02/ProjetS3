package fr.insa.jacob.projets3.services;

import fr.insa.jacob.projets3.entity.OperationEffectuee;
import fr.insa.jacob.projets3.repository.OperationEffectueeRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class OperationEffectueeService {

    @Autowired
    private OperationEffectueeRepository operationEffectueeRepository;

    public void delete(Integer id) {
        operationEffectueeRepository.deleteById(id);
    }


    private OperationEffectuee requireOne(Integer id) {
        return operationEffectueeRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Resource not found: " + id));
    }

    public void save(OperationEffectuee operationEffectuee) {
    }
}
