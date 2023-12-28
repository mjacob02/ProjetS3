package fr.insa.jacob.projets3.services;

import fr.insa.jacob.projets3.entity.Gamme;
import fr.insa.jacob.projets3.entity.GammeOperation;
import fr.insa.jacob.projets3.entity.Produit;

import java.util.List;

public class GammeOperationService {
    public List<GammeOperation> findAll(String stringFilter) {
        if (stringFilter == null || stringFilter.isEmpty()) {
            return gammeOperationRepository.findAll();
        } else {
            return gammeOperationRepository.search(stringFilter);
        }
    }
}
