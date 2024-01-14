package fr.insa.jacob.projets3.views.operation;

import fr.insa.jacob.projets3.entity.Operation;
import fr.insa.jacob.projets3.entity.TypeOperation;
import fr.insa.jacob.projets3.services.GammeService;
import fr.insa.jacob.projets3.services.OperationService;
import fr.insa.jacob.projets3.services.OperationService;
import fr.insa.jacob.projets3.services.TypeOperationService;
import fr.insa.jacob.projets3.views.MainLayout;
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
import fr.insa.jacob.projets3.views.operation.OperationForm;
import jakarta.annotation.security.PermitAll;
import org.springframework.context.annotation.Scope;

@SpringComponent
@Scope("prototype")
@PermitAll
@Route(value = "Operation", layout = MainLayout.class)
@PageTitle("Operation | Vaadin CRM")
public class OperationView extends VerticalLayout {
    Grid<Operation> grid = new Grid<>(Operation.class);
    TextField filterText = new TextField();
    OperationForm form;
    OperationService operationService;
    TypeOperationService typeOperationService;

    public OperationView(OperationService service, TypeOperationService typeOperationService ) {
        this.operationService = service;
        this.typeOperationService = typeOperationService;

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
        form = new OperationForm(operationService.listAll(),typeOperationService.listAll()); // Instanciate the form with the list of typeOperation
        form.setWidth("25em");
        // Listen to the events fired by the form and handle them in this class :
        form.addSaveListener(this::saveOperation);
        form.addDeleteListener(this::deleteOperation);
        form.addCloseListener(e -> closeEditor());
    }

    private void saveOperation(OperationForm.SaveEvent event) {
        operationService.save(event.getOperation());
        updateList();
        closeEditor();
    }

    private void deleteOperation(OperationForm.DeleteEvent event) {
        operationService.delete(event.getOperation());
        updateList();
        closeEditor();
    }

    private void configureGrid() {
        grid.addClassNames("Operation-grid");
        grid.setSizeFull();
        grid.setColumns( "reference","description"); // Add columns to the grid
        // definition de la colonne type operation
        grid.addColumn(operation -> operation.getTypeOperation() != null ? operation.getTypeOperation().getDescription() : "").setHeader(" Type d'Operation");  // Add a column with the gamme reference (that can be null)

        grid.getColumns().forEach(col -> col.setAutoWidth(true));

        grid.asSingleSelect().addValueChangeListener(event ->
                editOperation(event.getValue()));
    }


    private Component getToolbar() {
        filterText.setPlaceholder("Filter by typeOperation...");
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.LAZY);
        filterText.addValueChangeListener(e -> updateList());

        Button addOperationButton = new Button("Add operation");
        addOperationButton.addClickListener(click -> addOperation());

        var toolbar = new HorizontalLayout(filterText, addOperationButton);
        toolbar.addClassName("toolbar");
        return toolbar;
    }

    public void editOperation(Operation operation) {
        if (operation == null) {
            closeEditor();
        } else {
            form.setOperation(operation);
            form.setVisible(true);
            addClassName("editing");
        }
    }

    private void closeEditor() {
        form.setOperation(null);
        form.setVisible(false);
        removeClassName("editing");
    }

    private void addOperation() {
        grid.asSingleSelect().clear();
        editOperation(new Operation());
    }


    private void updateList() {
        grid.setItems(operationService.findAll(filterText.getValue()));
    }
}

