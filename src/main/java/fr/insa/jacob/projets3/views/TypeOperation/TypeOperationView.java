package fr.insa.jacob.projets3.views.TypeOperation;

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
import fr.insa.jacob.projets3.entity.TypeOperation;
import fr.insa.jacob.projets3.entity.Produit;
import fr.insa.jacob.projets3.services.TypeOperationService;
import fr.insa.jacob.projets3.services.ProduitService;
import fr.insa.jacob.projets3.views.MainLayout;
import fr.insa.jacob.projets3.views.typeOperation.TypeOperationForm;
import fr.insa.jacob.projets3.views.produit.ProduitForm;
import jakarta.annotation.security.PermitAll;
import org.springframework.context.annotation.Scope;

@SpringComponent
@Scope("TypeOperation")
@PermitAll
@Route(value = "TypeOperation", layout = MainLayout.class)
@PageTitle("TypeOperation | Vaadin CRM")
public class TypeOperationView extends VerticalLayout {
    Grid<TypeOperation> grid = new Grid<>(TypeOperation.class);
    TextField filterText = new TextField();
    TypeOperationForm form;
    TypeOperationService typeOperationService;

    public TypeOperationView(TypeOperationService service) {
        this.typeOperationService = service;


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
        // est ce que c'est juste ligne 59 ?
        form = new TypeOperationForm(typeOperationService.listAll()); // Instanciate the form with the list of gamme
        form.setWidth("25em");
        // Listen to the events fired by the form and handle them in this class :
        form.addSaveListener(this::saveTypeOperation);
        form.addDeleteListener(this::deleteTypeOperation);
        form.addCloseListener(e -> closeEditor());
    }

    private void saveTypeOperation(TypeOperationForm.SaveEvent event) {
        typeOperationService.save(event.getTypeOperation());
        updateList();
        closeEditor();
    }

    private void deleteTypeOperation(TypeOperationForm.DeleteEvent event) {
        typeOperationService.delete(event.getTypeOperation());
        updateList();
        closeEditor();
    }

    private void configureGrid() {
        grid.addClassNames("TypeOperation-grid");
        grid.setSizeFull();
        //grid.addColumn(exemplaire -> exemplaire.getProduit() != null ? exemplaire.getProduit().getReference() : "").setHeader("Reference");
        grid.setColumns("Description"); // Add columns to the grid
        grid.getColumns().forEach(col -> col.setAutoWidth(true));

        grid.asSingleSelect().addValueChangeListener(event ->
                editTypeOperation(event.getValue()));
    }

    private Component getToolbar() {
        filterText.setPlaceholder("Filter by Type Operation...");
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.LAZY);
        filterText.addValueChangeListener(e -> updateList());

        Button addTypeOperationButton = new Button("Add Type Operation");
        addTypeOperationButton.addClickListener(click -> addTypeOperation());


        var toolbar = new HorizontalLayout(filterText);
        toolbar.addClassName("toolbar");
        return toolbar;
    }

    public void editTypeOperation(TypeOperation typeOperation) {
        if (typeOperation == null) {
            closeEditor();
        } else {
            form.setTypeOperation(typeOperation);
            form.setVisible(true);
            addClassName("editing");
        }
    }

    private void closeEditor() {
        form.setTypeOperation(null);
        form.setVisible(false);
        removeClassName("editing");
    }

    private void addTypeOperation() {
        grid.asSingleSelect().clear();
        editTypeOperation(new TypeOperation());
    }


    private void updateList() {
        grid.setItems(typeOperationService.findAll(filterText.getValue()));
    }
}


