package fr.insa.jacob.projets3.views.produit;

import fr.insa.jacob.projets3.entity.Produit;
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
import jakarta.annotation.security.PermitAll;
import org.springframework.context.annotation.Scope;

@SpringComponent
@Scope("prototype")
@PermitAll
@Route(value = "produits", layout = MainLayout.class)
@PageTitle("Produits | Vaadin CRM")
public class ProduitView extends VerticalLayout {
    Grid<Produit> grid = new Grid<>(Produit.class);
    TextField filterText = new TextField();
    ProduitForm form;
    ProduitService produitService;
    GammeService gammeService;

    public ProduitView(ProduitService service, GammeService gammeService) {
        this.produitService = service;
        this.gammeService = gammeService;

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
        form = new ProduitForm(gammeService.listAll()); // Instanciate the form with the list of gamme
        form.setWidth("25em");
        // Listen to the events fired by the form and handle them in this class :
        form.addSaveListener(this::saveProduit);
        form.addDeleteListener(this::deleteProduit);
        form.addCloseListener(e -> closeEditor());
    }

    private void saveProduit(ProduitForm.SaveEvent event) {
        produitService.save(event.getProduit());
        updateList();
        closeEditor();
    }

    private void deleteProduit(ProduitForm.DeleteEvent event) {
        produitService.delete(event.getProduit());
        updateList();
        closeEditor();
    }

    private void configureGrid() {
        grid.addClassNames("Produit-grid");
        grid.setSizeFull();
        grid.setColumns("reference", "description"); // Add columns to the grid
//        grid.addColumn(produit -> produit.getGamme().getReference()).setHeader("Gamme");    // If not null, add a column with the gamme reference
        grid.addColumn(produit -> produit.getGamme() != null ? produit.getGamme().getReference() : "").setHeader("Gamme");  // Add a column with the gamme reference (that can be null)
        grid.getColumns().forEach(col -> col.setAutoWidth(true));

        grid.asSingleSelect().addValueChangeListener(event ->
                editProduit(event.getValue()));
    }

    private Component getToolbar() {
        filterText.setPlaceholder("Filter by reference...");
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.LAZY);
        filterText.addValueChangeListener(e -> updateList());

        Button addProduitButton = new Button("Add produit");
        addProduitButton.addClickListener(click -> addProduit());

        var toolbar = new HorizontalLayout(filterText, addProduitButton);
        toolbar.addClassName("toolbar");
        return toolbar;
    }

    public void editProduit(Produit produit) {
        if (produit == null) {
            closeEditor();
        } else {
            form.setProduit(produit);
            form.setVisible(true);
            addClassName("editing");
        }
    }

    private void closeEditor() {
        form.setProduit(null);
        form.setVisible(false);
        removeClassName("editing");
    }

    private void addProduit() {
        grid.asSingleSelect().clear();
        editProduit(new Produit());
    }


    private void updateList() {
        grid.setItems(produitService.findAll(filterText.getValue()));
    }
}
