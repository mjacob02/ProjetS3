package fr.insa.jacob.projets3.views.operation;

import fr.insa.jacob.projets3.entity.Operation;
import fr.insa.jacob.projets3.services.GammeService;
import fr.insa.jacob.projets3.services.ProduitService;
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
import fr.insa.jacob.projets3.views.produit.ProduitForm;
import jakarta.annotation.security.PermitAll;
import org.springframework.context.annotation.Scope;

@SpringComponent
@Scope("prototype")
@PermitAll
@Route(value = "produits", layout = MainLayout.class)
@PageTitle("Produits | Vaadin CRM")
public class OperationView extends VerticalLayout {
    Grid<Operation> grid = new Grid<>(Operation.class);
    TextField filterText = new TextField();
    ProduitForm form;
    OperationService OperationService;
   /*GammeService gammeService;*/

    public OperationView(OperationService service/*, GammeService gammeService*/) {
        this.OperationService = service;
        /*this.gammeService = gammeService;*/

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
       /* form = new ProduitForm(gammeService.listAll()); // Instanciate the form with the list of gamme
        form.setWidth("25em");*/
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

    private void deleteProduit(ProduitForm.DeleteEvent event) {
        operationService.delete(event.getOperation());
        updateList();
        closeEditor();
    }

    private void configureGrid() {
        grid.addClassNames("Operation-grid");
        grid.setSizeFull();
        grid.setColumns("typeOperation", "operation","description","operationAmont","operationAval"); // Add columns to the grid
//        grid.addColumn(produit -> produit.getGamme().getReference()).setHeader("Gamme");    // If not null, add a column with the gamme reference
       /* grid.addColumn(produit -> produit.getGamme() != null ? produit.getGamme().getReference() : "").setHeader("Gamme");  // Add a column with the gamme reference (that can be null)
        grid.getColumns().forEach(col -> col.setAutoWidth(true));

        grid.asSingleSelect().addValueChangeListener(event ->
                editProduit(event.getValue()));*/
    }

    private Component getToolbar() {
        filterText.setPlaceholder("Filter by typeOperation...");
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.LAZY);
        filterText.addValueChangeListener(e -> updateList());

        Button addProduitButton = new Button("Add typeOperation");
        addProduitButton.addClickListener(click -> addProduit());

        var toolbar = new HorizontalLayout(filterText, addTypeOperationButton);
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

