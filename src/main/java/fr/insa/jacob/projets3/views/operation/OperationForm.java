package fr.insa.jacob.projets3.views.operation;

import fr.insa.jacob.projets3.entity.Gamme;
import fr.insa.jacob.projets3.entity.Operation;
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
import fr.insa.jacob.projets3.entity.TypeOperation;

import java.util.List;

public class OperationForm extends FormLayout {
    ComboBox<TypeOperation> typeOperation = new ComboBox<>("Type Opération");
    TextField operation = new TextField("Référence");
    TextField description = new TextField("Description");
    TextField operationAmont = new TextField("Opération Amont");
    TextField operationAval = new TextField("Opération Aval");

    MultiSelectComboBox<Operation> comboBox = new MultiSelectComboBox<>("Opérations");
    comboBox.setItems(DataService.getOperation());
    comboBox.setItemLabelGenerator(Operation::getDescription);
    add(comboBox);


    /*ComboBox<Gamme> gamme = new ComboBox<>("Gamme");*/

    Button save = new Button("Save");
    Button delete = new Button("Delete");
    Button close = new Button("Cancel");

    // Other fields omitted
    Binder<Operation> binder = new BeanValidationBinder<>(Operation.class); // To validate the form

    public OperationForm(List<TypeOperation> typeOperation) {
        }

    private Component createButtonsLayout() {
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        delete.addThemeVariants(ButtonVariant.LUMO_ERROR);
        close.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

        save.addClickShortcut(Key.ENTER);
        close.addClickShortcut(Key.ESCAPE);

        save.addClickListener(event -> validateAndSave());
        delete.addClickListener(event -> fireEvent(new OperationForm.DeleteEvent(this, binder.getBean())));
        close.addClickListener(event -> fireEvent(new OperationForm.CloseEvent(this)));

        binder.addStatusChangeListener(e -> save.setEnabled(binder.isValid()));
        return new HorizontalLayout(save, delete, close);
    }

    private void validateAndSave() {
        if (binder.isValid()) {
            fireEvent(new OperationForm.SaveEvent(this, binder.getBean()));
        }
    }


    public void setOperation(Operation operation) {
        binder.setBean(operation);    // To fill the form with the data of the selected entity
    }

    // Events
    public static abstract class OperationFormEvent extends ComponentEvent<OperationForm> {
        private Operation operation;

        protected OperationFormEvent(OperationForm source, Operation operation) {
            super(source, false);
            this.operation = operation;
        }

        public Operation getOperation() {
            return operation;
        }
    }

    public static class SaveEvent extends OperationForm.OperationFormEvent {
        SaveEvent(OperationForm source, Operation operation) {
            super(source, operation);
        }
    }

    public static class DeleteEvent extends OperationFormEvent {
        DeleteEvent(OperationForm source, Operation operation) {
            super(source, operation);
        }

    }

    public static class CloseEvent extends OperationFormEvent {
        CloseEvent(OperationForm source) {
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


