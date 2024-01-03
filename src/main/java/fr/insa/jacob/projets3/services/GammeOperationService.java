package fr.insa.jacob.projets3.services;

import fr.insa.jacob.projets3.entity.Gamme;
import fr.insa.jacob.projets3.entity.GammeOperation;
import fr.insa.jacob.projets3.repository.GammeOperationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GammeOperationService {
    @Autowired
    private GammeOperationRepository gammeOperationRepository;

    // No findAll : GammeOperation is a liaison table

    public GammeOperation save(GammeOperation gammeOperation) {
        if (gammeOperation == null) {
            System.err.println("GammeOperation is null. Are you sure you have connected your form to the application?");
            return null;
        }
        return gammeOperationRepository.save(gammeOperation);
    }

    public void delete(GammeOperation gammeOperation) {
        gammeOperationRepository.delete(gammeOperation);
    }

    public List<GammeOperation> listAll() {
        return gammeOperationRepository.findAll();
    }

}
