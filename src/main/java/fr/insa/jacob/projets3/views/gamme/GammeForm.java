package fr.insa.jacob.projets3.views.gamme;

import fr.insa.jacob.projets3.entity.Gamme;
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
import fr.insa.jacob.projets3.entity.Operation;

import java.util.List;

public class GammeForm extends FormLayout {
    TextField reference = new TextField("Référence");
    TextField description = new TextField("Description");

    ComboBox<Operation> operation = new ComboBox<>("Sélectionner les opérations de la gamme");  // TODO : remplacer par MultiSelectComboBox<Operation> https://vaadin.com/docs/latest/components/multi-select-combo-box

    Button save = new Button("Save");
    Button delete = new Button("Delete");
    Button close = new Button("Cancel");
    // Other fields omitted
    Binder<Gamme> binder = new BeanValidationBinder<>(Gamme.class); // To validate the form

    public GammeForm(List<Gamme> gammes) {
        addClassName("gamme-form"); // To style the form with CSS
        binder.bindInstanceFields(this);  // To bind the fields of the form to the fields of the entity

        // C'est cette ComboBox qui doit contenir les opérations de la gamme
        operation.setItems(operation);
        operation.setItemLabelGenerator(Operation::getReference);  // To display the name of the company in the combo box

        add(reference,
                description,
                operation,
                createButtonsLayout()); // To display the fields in the form
    }

    private Component createButtonsLayout() {
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        delete.addThemeVariants(ButtonVariant.LUMO_ERROR);//permet d'avoir un beau style de bouton
        close.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

        save.addClickShortcut(Key.ENTER);
        close.addClickShortcut(Key.ESCAPE);

        save.addClickListener(event -> validateAndSave());
        delete.addClickListener(event -> fireEvent(new fr.insa.jacob.projets3.views.produit.GammeForm.DeleteEvent(this, binder.getBean())));
        close.addClickListener(event -> fireEvent(new CloseEvent(this)));

        binder.addStatusChangeListener(e -> save.setEnabled(binder.isValid()));
        return new HorizontalLayout(save, delete, close);
    }

    private void validateAndSave() {
        if (binder.isValid()) {
            fireEvent(new fr.insa.jacob.projets3.views.produit.GammeForm.SaveEvent(this, binder.getBean()));
        }
    }


    public void setGamme(Gamme gamme) {
        binder.setBean(gamme);    // To fill the form with the data of the selected entity
    }

    // Events
    public static abstract class GammeFormEvent extends ComponentEvent<fr.insa.jacob.projets3.views.gamme.GammeForm> {
        private Gamme gamme;

        protected GammeFormEvent(fr.insa.jacob.projets3.views.gamme.GammeForm source, Gamme gamme) {
            super(source, false);
            this.gamme = gamme;
        }

        public Gamme getGamme() {
            return gamme;
        }
    }

    public static class SaveEvent extends fr.insa.jacob.projets3.views.gamme.GammeForm.GammeFormEvent {
        SaveEvent(fr.insa.jacob.projets3.views.gamme.GammeForm source, Gamme gamme) {
            super(source, gamme);
        }
    }

    public static class DeleteEvent extends GammeFormEvent {
        DeleteEvent(fr.insa.jacob.projets3.views.gamme.GammeForm source, Gamme gamme) {
            super(source, gamme);
        }

    }

    public static class CloseEvent extends GammeFormEvent {
        CloseEvent(fr.insa.jacob.projets3.views.gamme.GammeForm source) {
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

