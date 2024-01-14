package fr.insa.jacob.projets3.views.operationEffectuee;

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
import fr.insa.jacob.projets3.entity.*;
import fr.insa.jacob.projets3.services.ExemplaireService;
import fr.insa.jacob.projets3.views.exemplaire.ExemplaireForm;
import fr.insa.jacob.projets3.entity.Exemplaire;


import java.util.List;

public class OperationEffectueeForm extends FormLayout {
    TextField exemplaire = new TextField("Exemplaire");
    TextField operation = new TextField("Opération");
    TextField machine = new TextField("Machine");
    TextField debutOperation = new TextField("Début Opération");
    TextField finOperation = new TextField("Fin Opération");

     //ComboBox<Exemplaire> exemplaire = new ComboBox<>("Exemplaire");

    Button save = new Button("Save");
    Button delete = new Button("Delete");
    Button close = new Button("Cancel");
    // Other fields omitted
    Binder<OperationEffectuee> binder = new BeanValidationBinder<>(OperationEffectuee.class); // To validate the form
   //private ExemplaireService exemplaireService;  //  pour déclarer le service
    public OperationEffectueeForm(List<Exemplaire> exemplaires)
        this.exemplaireService = exemplaireService;  // Initialisation du service dans le constructeur
        exemplaire.setItems(exemplaires);
        exemplaire.setItemLabelGenerator(exemplaire -> {
            // Retournez la représentation textuelle de l'exemplaire dans le ComboBox
            return exemplaire != null ? exemplaire.getNumeroDeSerie().toString() : "";
        });
        add(exemplaire, createButtonsLayout()); // Ajoutez le ComboBox dans le formulaire

    }

    public void setExemplaireId(Integer exemplaireId) {
        Exemplaire exemplaire = exemplaireService.requireOne(exemplaireId);
        this.exemplaire.setValue(exemplaire);
    }
    private Component createButtonsLayout() {
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        delete.addThemeVariants(ButtonVariant.LUMO_ERROR);
        close.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

        save.addClickShortcut(Key.ENTER);
        close.addClickShortcut(Key.ESCAPE);

        save.addClickListener(event -> validateAndSave());
        delete.addClickListener(event -> fireEvent(new OperationEffectueeForm.DeleteEvent(this, binder.getBean())));
        close.addClickListener(event -> fireEvent(new OperationEffectueeForm.CloseEvent(this)));

        binder.addStatusChangeListener(e -> save.setEnabled(binder.isValid()));
        return new HorizontalLayout(save, delete, close);
    }


    private void validateAndSave() {
        if (binder.isValid()) {
            fireEvent(new OperationEffectueeForm.SaveEvent(this, binder.getBean()));
        }
    }
 private void validateAndSave() {
     if (binder.isValid()) {
         OperationEffectuee operationEffectuee = binder.getBean();
         operationEffectuee.setExemplaire(exemplaire.getValue());
         fireEvent(new SaveEvent(this, operationEffectuee));
     }
 }
    public void setOperationEffectuee(OperationEffectuee operationEffectuee) {
        binder.setBean(operationEffectuee);    // To fill the form with the data of the selected entity
    }

    // Events
    public static abstract class OperationEffectueeFormEvent extends ComponentEvent<OperationEffectueeForm> {
        private OperationEffectuee operationEffectuee;

        protected OperationEffectueeFormEvent(OperationEffectueeForm source, OperationEffectuee operationEffectuee) {
            super(source, false);
            this.operationEffectuee = operationEffectuee;
        }

        public OperationEffectuee getOperationEffectuee() {
            return operationEffectuee;
        }
    }

    public static class SaveEvent extends OperationEffectueeFormEvent {
        SaveEvent(OperationEffectueeForm source, OperationEffectuee operationEffectuee) {
            super(source, operationEffectuee);
        }
    }

    public static class DeleteEvent extends OperationEffectueeFormEvent {
        DeleteEvent(OperationEffectueeForm source, OperationEffectuee operationEffectuee) {
            super(source, operationEffectuee);
        }

    }

    public static class CloseEvent extends OperationEffectueeFormEvent {
        CloseEvent(OperationEffectueeForm source) {
            super(source, null);
        }
    }

    public Registration addDeleteListener(ComponentEventListener<DeleteEvent> listener) {
        return addListener(DeleteEvent.class, listener);
    }

    public Registration addSaveListener(ComponentEventListener<OperationEffectueeForm.SaveEvent> listener) {
        return addListener(OperationEffectueeForm.SaveEvent.class, listener);
    }

    public Registration addCloseListener(ComponentEventListener<OperationEffectueeForm.CloseEvent> listener) {
        return addListener(OperationEffectueeForm.CloseEvent.class, listener);
    }

}
