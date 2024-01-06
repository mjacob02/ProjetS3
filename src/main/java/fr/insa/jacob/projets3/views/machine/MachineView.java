package fr.insa.jacob.projets3.views.machine;

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
import fr.insa.jacob.projets3.entity.Machine;
import fr.insa.jacob.projets3.services.EtatMachineService;
import fr.insa.jacob.projets3.services.MachineService;
import fr.insa.jacob.projets3.views.MainLayout;
import jakarta.annotation.security.PermitAll;
import org.springframework.context.annotation.Scope;

@SpringComponent
@Scope("prototype")
@PermitAll
@Route(value = "machine", layout = MainLayout.class)
@PageTitle("Machine | Vaadin CRM")
public class MachineView extends VerticalLayout {
    Grid<Machine> grid = new Grid<>(Machine.class);
    TextField filterText = new TextField();
    MachineForm form;
    MachineService machineService;
    EtatMachineService etatMachineService;

    public MachineView (MachineService service, EtatMachineService etatMachineService) {
        this.machineService = service;
        this.etatMachineService = etatMachineService;

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
        form = new MachineForm(etatMachineService.listAll()); // Instanciate the form with the list of gamme
        form.setWidth("25em");
        // Listen to the events fired by the form and handle them in this class :
        form.addSaveListener(this::saveMachine);
        form.addDeleteListener(this::deleteMachine);
        form.addCloseListener(e -> closeEditor());
    }

    private void saveMachine(MachineForm.SaveEvent event) {
        machineService.save(event.getMachine());
        updateList();
        closeEditor();
    }

    private void deleteMachine(MachineForm.DeleteEvent event) {
        machineService.delete(event.getMachine());
        updateList();
        closeEditor();
    }

    private void configureGrid() {
        grid.addClassNames("Machine-grid");
        grid.setSizeFull();
        grid.setColumns("Référence", "Description","Puissance ","CoutHoraire"); // Add columns to the grid
//        grid.addColumn(produit -> produit.getGamme().getReference()).setHeader("Gamme");    // If not null, add a column with the gamme reference
        grid.addColumn(machine -> machine.getEtatMachine() != null ? machine.getEtatMachine() : "").setHeader("Etat de la machine");  // Add a column with the gamme reference (that can be null)
        grid.getColumns().forEach(col -> col.setAutoWidth(true));

        grid.asSingleSelect().addValueChangeListener(event ->
                editMachine(event.getValue()));
    }

    private Component getToolbar() {
        filterText.setPlaceholder("Filter by reference...");
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.LAZY);
        filterText.addValueChangeListener(e -> updateList());

        Button addMachineButton = new Button("Add machine");
        addMachineButton.addClickListener(click -> addmachine());

        var toolbar = new HorizontalLayout(filterText, addMachineButton);
        toolbar.addClassName("toolbar");
        return toolbar;
    }

    public void editMachine(Machine machine) {
        if (machine == null) {
            closeEditor();
        } else {
            form.setMachine(machine);
            form.setVisible(true);
            addClassName("editing");
        }
    }

    private void closeEditor() {
        form.setMachine(null);
        form.setVisible(false);
        removeClassName("editing");
    }

    private void addmachine() {
        grid.asSingleSelect().clear();
        editMachine(new Machine());
    }


    private void updateList() {
        grid.setItems(machineService.findAll(filterText.getValue()));
    }
}

