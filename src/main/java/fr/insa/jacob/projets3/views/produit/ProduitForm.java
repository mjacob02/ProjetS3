package fr.insa.jacob.projets3.views.produit;

import fr.insa.jacob.projets3.entity.Gamme;
import fr.insa.jacob.projets3.entity.Produit;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.shared.Registration;

import java.util.List;

public class ProduitForm extends FormLayout {
    TextField reference = new TextField("Référence");
    TextField description = new TextField("Description");

    ComboBox<Gamme> gamme = new ComboBox<>("Gamme");

    Button save = new Button("Save");
    Button delete = new Button("Delete");
    Button close = new Button("Cancel");
    // Other fields omitted
    Binder<Produit> binder = new BeanValidationBinder<>(Produit.class); // To validate the form

    public ProduitForm(List<Gamme> gammes) {
        addClassName("produit-form"); // To style the form with CSS
        binder.bindInstanceFields(this);  // To bind the fields of the form to the fields of the entity

        gamme.setItems(gammes);
        gamme.setItemLabelGenerator(Gamme::getReference);  // To display the name of the company in the combo box

        add(reference,
                description,
                gamme,
                createButtonsLayout()); // To display the fields in the form
    }

    private Component createButtonsLayout() {
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        delete.addThemeVariants(ButtonVariant.LUMO_ERROR);
        close.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

        save.addClickShortcut(Key.ENTER);
        close.addClickShortcut(Key.ESCAPE);

        save.addClickListener(event -> validateAndSave());
        delete.addClickListener(event -> fireEvent(new DeleteEvent(this, binder.getBean())));
        close.addClickListener(event -> fireEvent(new CloseEvent(this)));

        binder.addStatusChangeListener(e -> save.setEnabled(binder.isValid()));
        return new HorizontalLayout(save, delete, close);
    }

    private void validateAndSave() {
        if (binder.isValid()) {
            fireEvent(new SaveEvent(this, binder.getBean()));
        }
    }


    public void setProduit(Produit produit) {
        binder.setBean(produit);    // To fill the form with the data of the selected entity
    }

    // Events
    public static abstract class ProduitFormEvent extends ComponentEvent<ProduitForm> {
        private Produit produit;

        protected ProduitFormEvent(ProduitForm source, Produit produit) {
            super(source, false);
            this.produit = produit;
        }

        public Produit getProduit() {
            return produit;
        }
    }

    public static class SaveEvent extends ProduitFormEvent {
        SaveEvent(ProduitForm source, Produit produit) {
            super(source, produit);
        }
    }

    public static class DeleteEvent extends ProduitFormEvent {
        DeleteEvent(ProduitForm source, Produit produit) {
            super(source, produit);
        }

    }

    public static class CloseEvent extends ProduitFormEvent {
        CloseEvent(ProduitForm source) {
            super(source, null);
        }
    }

    public Registration addDeleteListener(ComponentEventListener<DeleteEvent> listener) {
        return addListener(DeleteEvent.class, listener);
    }

    public Registration addSaveListener(ComponentEventListener<SaveEvent> listener) {
        return addListener(SaveEvent.class, listener);
    }

    public Registration addCloseListener(ComponentEventListener<CloseEvent> listener) {
        return addListener(CloseEvent.class, listener);
    }
}

