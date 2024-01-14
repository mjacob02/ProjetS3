package fr.insa.jacob.projets3.views.exemplaire;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.annotation.SpringComponent;
import fr.insa.jacob.projets3.entity.Exemplaire;
import fr.insa.jacob.projets3.entity.Produit;
import fr.insa.jacob.projets3.services.ExemplaireService;
import fr.insa.jacob.projets3.services.ProduitService;
import fr.insa.jacob.projets3.views.MainLayout;
import fr.insa.jacob.projets3.views.produit.ProduitForm;
import jakarta.annotation.security.PermitAll;
import org.springframework.context.annotation.Scope;

@SpringComponent
@Scope("prototype")
@PermitAll
@Route(value = "exemplaire", layout = MainLayout.class)
@PageTitle("Exemplaire | Vaadin CRM")
public class ExemplaireView extends VerticalLayout {
    Grid<Exemplaire> grid = new Grid<>(Exemplaire.class);
    TextField filterText = new TextField();
    ExemplaireForm form;
    ExemplaireService exemplaireService;
    ProduitService produitService;

    public ExemplaireView(ExemplaireService service, ProduitService produitService) {
        this.exemplaireService = service;
        this.produitService = produitService;


        addClassName("list-view");
        setSizeFull();
        configureGrid();
        configureForm();

        add(getToolbar(), getContent());
        updateList();
        closeEditor();
    }

    private HorizontalLayout getContent() {
        HorizontalLayout content = new HorizontalLayout(grid, form);
        content.setFlexGrow(2, grid);
        content.setFlexGrow(1, form);
        content.addClassNames("content");
        content.setSizeFull();
        return content;
    }

    private void configureForm() {
        form = new ExemplaireForm(produitService.listAll()); // Instanciate the form with the list of gamme
        form.setWidth("25em");
        // Listen to the events fired by the form and handle them in this class :
        form.addSaveListener(this::saveExemplaire);
        form.addDeleteListener(this::deleteExemplaire);
        form.addCloseListener(e -> closeEditor());
    }

    private void saveExemplaire(ExemplaireForm.SaveEvent event) {
        exemplaireService.save(event.getExemplaire());
        updateList();
        closeEditor();
    }

    private void deleteExemplaire(ExemplaireForm.DeleteEvent event) {
        exemplaireService.delete(event.getExemplaire());
        updateList();
        closeEditor();
    }

    private void configureGrid() {
        grid.addClassNames("Exemplaire-grid");
        grid.setSizeFull();
        grid.addColumn(exemplaire -> exemplaire.getProduit() != null ? exemplaire.getProduit().getReference() : "").setHeader("Reference");
        grid.setColumns("numeroDeSerie"); // Add columns to the grid
        grid.getColumns().forEach(col -> col.setAutoWidth(true));

        grid.asSingleSelect().addValueChangeListener(event ->
                editExemplaire(event.getValue()));
    }

    private Component getToolbar() {
        filterText.setPlaceholder("Filter by reference...");
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.LAZY);
        filterText.addValueChangeListener(e -> updateList());

        Button addExemplaireButton = new Button("Add exemplaire");
        addExemplaireButton.addClickListener(click -> addExemplaire());


        var toolbar = new HorizontalLayout(filterText, addExemplaireButton);
        toolbar.addClassName("toolbar");
        return toolbar;
    }

    public void editExemplaire(Exemplaire exemplaire) {
        if (exemplaire == null) {
            closeEditor();
        } else {
            form.setExemplaire(exemplaire);
            form.setVisible(true);
            addClassName("editing");
        }
    }

    private void closeEditor() {
        form.setExemplaire(null);
        form.setVisible(false);
        removeClassName("editing");
    }

    private void addExemplaire() {
        grid.asSingleSelect().clear();
        editExemplaire(new Exemplaire());
    }


    private void updateList() {
        grid.setItems(exemplaireService.findAll(filterText.getValue()));
    }
}




