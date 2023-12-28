package operationEffectuee;

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
import fr.insa.jacob.projets3.entity.Exemplaire;
import fr.insa.jacob.projets3.entity.OperationEffectuee;
import fr.insa.jacob.projets3.entity.Operation;
import fr.insa.jacob.projets3.entity.Machine;

import java.util.List;

public class OperationEffectueeForm extends FormLayout {
    TextField exemplaire = new TextField("Exemplaire");
    TextField operation = new TextField("Opération");
    TextField machine = new TextField("Machine");
    TextField debutOperation = new TextField("Début Opération");
    TextField finOperation = new TextField("Fin Opération");


    Button save = new Button("Save");
    Button delete = new Button("Delete");
    Button close = new Button("Cancel");
    // Other fields omitted



    // Events
    public static abstract class OperationEffectueeFormEvent extends ComponentEvent<fr.insa.jacob.projets3.views.operationEffectuee.OperationEffectueeForm> {
        private OperationEffectuee operationEffectuee;

        protected OperationEffectueeEvent(fr.insa.jacob.projets3.views.operationEffectuee.OperationEffectueeForm source, OperationEffectuee operationEffectuee) {
            super(source, false);
            this.operationEffectuee = operationEffectuee;
        }

        public OperationEffectuee getOperationEffectuee() {
            return operationEffectuee;
        }
    }

    public static class SaveEvent extends fr.insa.jacob.projets3.views.operationEffectuee.OperationEffectueeForm.OperationEffectueeFormEvent {
        SaveEvent(fr.insa.jacob.projets3.views.operationEffectuee.OperationEffectueeForm source, OperationEffectuee operationEffectuee) {
            super(source, operationEffectuee);
        }
    }

    public static class DeleteEvent extends fr.insa.jacob.projets3.views.operationEffectuee.OperationEffectueeForm.OperationEffectueeFormEvent {
        DeleteEvent(fr.insa.jacob.projets3.views.operationEffectuee.OperationEffectueeForm source, OperationEffectuee operationEffectuee) {
            super(source, operationEffectuee);
        }

    }

    public static class CloseEvent extends fr.insa.jacob.projets3.views.operationEffectuee.OperationEffectueeForm.OperationEffectueeFormEvent {
        CloseEvent(fr.insa.jacob.projets3.views.operationEffectuee.OperationEffectueeForm source) {
            super(source, null);
        }
    }

    public Registration addDeleteListener(ComponentEventListener<fr.insa.jacob.projets3.views.operationEffectuee.OperationEffectueeForm.DeleteEvent> listener) {
        return addListener(fr.insa.jacob.projets3.views.operationEffectuee.OperationEffectueeForm.DeleteEvent.class, listener);
    }

    public Registration addSaveListener(ComponentEventListener<fr.insa.jacob.projets3.views.operationEffectuee.OperationEffectueeForm.SaveEvent> listener) {
        return addListener(fr.insa.jacob.projets3.views.operationEffectuee.OperationEffectueeForm.SaveEvent.class, listener);
    }

    public Registration addCloseListener(ComponentEventListener<fr.insa.jacob.projets3.views.operationEffectuee.OperationEffectueeForm.CloseEvent> listener) {
        return addListener(fr.insa.jacob.projets3.views.operationEffectuee.OperationEffectueeForm.CloseEvent.class, listener);
    }
}
