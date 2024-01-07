package fr.insa.jacob.projets3.views.TypeOperation;

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
import fr.insa.jacob.projets3.entity.Gamme;
import fr.insa.jacob.projets3.entity.TypeOperation;
import fr.insa.jacob.projets3.views.typeOperation.TypeOperationForm;

import java.util.List;

public class TypeOperationForm extends FormLayout {

    TextField description = new TextField("Description");

    Button save = new Button("Save");
    Button delete = new Button("Delete");
    Button close = new Button("Cancel");
    // Other fields omitted
    Binder<TypeOperationForm> binder = new BeanValidationBinder<>(TypeOperationForm.class); // To validate the form

    /*public TypeOperationFormForm(List<Gamme> gammes) {
        addClassName("produit-form"); // To style the form with CSS
        binder.bindInstanceFields(this);  // To bind the fields of the form to the fields of the entity

        gamme.setItems(gammes);
        gamme.setItemLabelGenerator(Gamme::getReference);  // To display the name of the company in the combo box

        add(reference,
                description,
                gamme,
                createButtonsLayout()); // To display the fields in the form
    }*/

    private Component createButtonsLayout() {
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        delete.addThemeVariants(ButtonVariant.LUMO_ERROR);
        close.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

        save.addClickShortcut(Key.ENTER);
        close.addClickShortcut(Key.ESCAPE);

        save.addClickListener(event -> validateAndSave());
        delete.addClickListener(event -> fireEvent(new TypeOperationForm.DeleteEvent(this, binder.getBean())));
        close.addClickListener(event -> fireEvent(new TypeOperationForm.CloseEvent(this)));

        binder.addStatusChangeListener(e -> save.setEnabled(binder.isValid()));
        return new HorizontalLayout(save, delete, close);
    }

    private void validateAndSave() {
        if (binder.isValid()) {
            fireEvent(new SaveEvent(this, binder.getBean()));
        }
    }


    public void setTypeOperation(TypeOperation typeOperation) {
        binder.setBean(typeOperation);    // To fill the form with the data of the selected entity
    }

    // Events
    public static abstract class TypeOperationFormEvent extends ComponentEvent<TypeOperationForm> {
        private TypeOperation typeOperation;

        protected TypeOperationFormEvent(TypeOperationForm source, TypeOperation typeOperation) {
            super(source, false);
            this.typeOperation = typeOperation;
        }

        public TypeOperation getTypeOperation() {
            return typeOperation;
        }
    }

    public static class SaveEvent extends TypeOperationFormEvent {
        SaveEvent(TypeOperationForm source, TypeOperation typeOperation) {
            super(source, typeOperation);
        }
    }

    public static class DeleteEvent extends TypeOperationFormEvent {
        DeleteEvent(TypeOperationForm source, TypeOperation typeOperation) {
            super(source, typeOperation);
        }

    }

    public static class CloseEvent extends TypeOperationFormEvent {
        CloseEvent(TypeOperationForm source) {
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
