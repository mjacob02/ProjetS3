package fr.insa.jacob.projets3.views.gamme;

import fr.insa.jacob.projets3.entity.Gamme;
import fr.insa.jacob.projets3.services.GammeOperationService;
import fr.insa.jacob.projets3.services.GammeService;
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
import jakarta.annotation.security.PermitAll;
import org.springframework.context.annotation.Scope;

@SpringComponent
@Scope("prototype")
@PermitAll
@Route(value = "gamme", layout = MainLayout.class)
@PageTitle("Gamme | Vaadin CRM")
public class GammeView extends VerticalLayout {
    Grid<Gamme> grid = new Grid<>(Gamme.class);
    TextField filterText = new TextField();
    GammeForm form;
    GammeService gammeService;
    GammeOperationService gammeOperationService;//pas sûr

    public GammeView(GammeService service, GammeOperationService gammeOperationService) {//pas sur
        this.gammeService = service;//pas sur
        this.gammeOperationService = gammeOperationService;//pas sur

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

    private void configureForm() {//pas sur
        form = new GammeForm(gammeOperationService.listAll()); // Instanciate the form with the list of gamme
        form.setWidth("25em");
        // Listen to the events fired by the form and handle them in this class :
        form.addSaveListener(this::saveGamme);
        form.addDeleteListener(this::deleteGamme);
        form.addCloseListener(e -> closeEditor());
    }

    private void saveGamme(GammeForm.SaveEvent event) {
        gammeService.save(event.getGamme());
        updateList();
        closeEditor();
    }

    private void deleteGamme(GammeForm.DeleteEvent event) {
        gammeService.delete(event.getGamme());
        updateList();
        closeEditor();
    }

    private void configureGrid() {//pas sur
        grid.addClassNames("Gamme-grid");
        grid.setSizeFull();
        grid.setColumns("reference", "description"); // Add columns to the grid
//        grid.addColumn(gamme -> gamme.getGammeOperation().getReference()).setHeader("GammeOperation");    // If not null, add a column with the gammeOperation reference
        grid.addColumn(gamme -> gamme.getGammeOperation() != null ? gamme.getGammeOperation().getReference() : "").setHeader("GammeOperation");  // Add a column with the gammeoperation reference (that can be null)
        grid.getColumns().forEach(col -> col.setAutoWidth(true));

        grid.asSingleSelect().addValueChangeListener(event ->
                editGamme(event.getValue()));
    }

    private Component getToolbar() {
        filterText.setPlaceholder("Filter by reference...");
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.LAZY);
        filterText.addValueChangeListener(e -> updateList());

        Button addGammeButton = new Button("Add gamme");
        addGammeButton.addClickListener(click -> addGamme());

        var toolbar = new HorizontalLayout(filterText, addGammeButton);
        toolbar.addClassName("toolbar");
        return toolbar;
    }

    public void editGamme(Gamme gamme) {
        if (gamme == null) {
            closeEditor();
        } else {
            form.setGamme(gamme);
            form.setVisible(true);
            addClassName("editing");
        }
    }

    private void closeEditor() {
        form.setGamme(null);
        form.setVisible(false);
        removeClassName("editing");
    }

    private void addGamme() {
        grid.asSingleSelect().clear();
        editGamme(new Gamme());
    }


    private void updateList() {
        grid.setItems(gammeService.findAll(filterText.getValue()));
    }
}
