package fr.insa.jacob.projets3.views.operationEffectuee;

import fr.insa.jacob.projets3.entity.OperationEffectuee;
import fr.insa.jacob.projets3.services.GammeService;
import fr.insa.jacob.projets3.services.OperationEffectueeService;
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
import fr.insa.jacob.projets3.views.operationEffectuee.OperationEffectueeForm;
import jakarta.annotation.security.PermitAll;
import org.springframework.context.annotation.Scope;

@SpringComponent
@Scope("prototype")
@PermitAll
@Route(value = "produits", layout = MainLayout.class)
@PageTitle("Produits | Vaadin CRM")
public class OperationEffectueeView extends VerticalLayout {
    Grid<OperationEffectuee> grid = new Grid<>(OperationEffectuee.class);
    TextField filterText = new TextField();
    OperationEffectueeForm form;
    OperationEffectueeService operationEffectueeService;
    /* GammeService gammeService;*/

    public OperationEffectueeView(OperationEffectueeService service /*,GammeService gammeService*/) {
        this.operationEffectueeService = service;
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
       /* form = new OperationEffectueeForm(gammeService.listAll()); // Instanciate the form with the list of gamme
        form.setWidth("25em");*/
        // Listen to the events fired by the form and handle them in this class :
        form.addSaveListener(this::saveOperationEffectuee);
        form.addDeleteListener(this::deleteOperationEffectuee);
        form.addCloseListener(e -> closeEditor());
    }

    private void saveOperationEffectuee(OperationEffectueeForm.SaveEvent event) {
        operationEffectueeService.save(event.getOperationEffectuee());
        updateList();
        closeEditor();
    }

    private void deleteOperationEffectuee(OperationEffectueeForm.DeleteEvent event) {
        operationEffectueeService.delete(event.getOperationEffectuee());
        updateList();
        closeEditor();
    }

    private void configureGrid() {
        grid.addClassNames("OperationEffectuee-grid");
        grid.setSizeFull();
        grid.setColumns("exemplaire", "operation","machine","debutOperation", "finOperation"); // Add columns to the grid
//        grid.addColumn(produit -> produit.getGamme().getReference()).setHeader("Gamme");    // If not null, add a column with the gamme reference
       /* grid.addColumn(produit -> produit.getGamme() != null ? produit.getGamme().getReference() : "").setHeader("Gamme");  // Add a column with the gamme reference (that can be null)
        grid.getColumns().forEach(col -> col.setAutoWidth(true));

        grid.asSingleSelect().addValueChangeListener(event ->
                editProduit(event.getValue()));*/
    }

    private Component getToolbar() {
        filterText.setPlaceholder("Filter by exmplaire...");
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

