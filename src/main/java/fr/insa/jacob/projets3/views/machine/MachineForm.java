package fr.insa.jacob.projets3.views.machine;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.shared.Registration;
import fr.insa.jacob.projets3.entity.Machine;
import fr.insa.jacob.projets3.entity.EtatMachine;


import java.util.List;

public class MachineForm extends FormLayout {
    TextField reference = new TextField("Référence");
    TextField description = new TextField("Description");
    TextField puissance= new TextField("Puissance");
    TextField coutHoraire= new TextField("Cout Horraire");

    Button save = new Button("Save");
    Button delete = new Button("Delete");
    Button close = new Button("Cancel");
    // Other fields omitted
    Binder<Machine> binder = new BeanValidationBinder<>(Machine.class); // To validate the form

    public MachineForm(List<EtatMachine> etatMachine) {

    }




    private Component createButtonsLayout() {
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        delete.addThemeVariants(ButtonVariant.LUMO_ERROR);
        close.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

        save.addClickShortcut(Key.ENTER);
        close.addClickShortcut(Key.ESCAPE);

        save.addClickListener(event -> validateAndSave());
        delete.addClickListener(event -> fireEvent(new MachineForm.DeleteEvent(this, binder.getBean())));
        close.addClickListener(event -> fireEvent(new MachineForm.CloseEvent(this)));

        binder.addStatusChangeListener(e -> save.setEnabled(binder.isValid()));
        return new HorizontalLayout(save, delete, close);
    }

    private void validateAndSave() {
        if (binder.isValid()) {
            fireEvent(new MachineForm.SaveEvent(this, binder.getBean()));
        }
    }


    public void setMachine(Machine machine) {
        binder.setBean(machine);    // To fill the form with the data of the selected entity
    }

    // Events
    public static abstract class MachineFormEvent extends ComponentEvent<MachineForm> {
        private Machine machine;

        protected MachineFormEvent(MachineForm source, Machine machine) {
            super(source, false);
            this.machine = machine;
        }

        public Machine getMachine() {
            return machine;
        }
    }

    public static class SaveEvent extends MachineFormEvent {
        SaveEvent(MachineForm source, Machine machine) {
            super(source, machine);
        }
    }

    public static class DeleteEvent extends MachineFormEvent {
        DeleteEvent(MachineForm source, Machine machine) {
            super(source, machine);
        }

    }

    public static class CloseEvent extends MachineFormEvent {
        CloseEvent(MachineForm source) {
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
