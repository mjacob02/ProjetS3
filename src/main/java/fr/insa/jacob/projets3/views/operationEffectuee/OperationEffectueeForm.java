package fr.insa.jacob.projets3.views.operationEffectuee;

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
import fr.insa.jacob.projets3.entity.*;

import java.util.List;

public class OperationEffectueeForm extends FormLayout {
    TextField exemplaire = new TextField("Exemplaire");
    TextField operation = new TextField("Opération");
    TextField machine = new TextField("Machine");
    TextField debutOperation = new TextField("Début Opération");
    TextField finOperation = new TextField("Fin Opération");

    Binder<OperationEffectuee> binder = new BeanValidationBinder<>(OperationEffectuee.class); // To validate the form


    Button save = new Button("Save");
    Button delete = new Button("Delete");
    Button close = new Button("Cancel");
    // Other fields omitted



    // Events
    public static abstract class OperationEffectueeFormEvent extends ComponentEvent<OperationEffectueeForm> {
        private OperationEffectuee operationEffectuee;

        protected OperationEffectueeEvent(OperationEffectueeForm source, OperationEffectuee operationEffectuee) {
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

    public Registration addSaveListener(ComponentEventListener<SaveEvent> listener) {
        return addListener(SaveEvent.class, listener);
    }

    public Registration addCloseListener(ComponentEventListener<CloseEvent> listener) {
        return addListener(CloseEvent.class, listener);
    }




    /*Test avec video vaadin*/
    package com.example.application.views.list;

import com.example.application.data.Company;
import com.example.application.data.Status;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.TextField;

import java.util.List;

    public class ContactForm extends FormLayout {
        TextField exemplaire = new TextField("Exemplaire");
        TextField operation = new TextField("Operation");
        TextField machine = new TextField("Machine");
        TextField debutOperation = new TextField("Debut Operation");
        TextField FinOperation = new TextField("Operation");
        /*ComboBox<Status> status = new ComboBox<>("Status");
        ComboBox<Company> company = new ComboBox<>("Company");*/

        Button save = new Button("Save");
        Button delete = new Button("Delete");
        Button close = new Button("Cancel");

       /* public ContactForm(List<Company> companies, List<Status> statuses) {
            addClassName("contact-form");

            company.setItems(companies);
            company.setItemLabelGenerator(Company::getName);
            status.setItems(statuses);
            status.setItemLabelGenerator(Status::getName);

            add(firstName,
                    lastName,
                    email,
                    company,
                    status,
                    createButtonsLayout());
        }*/

        private HorizontalLayout createButtonsLayout() {
            save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
            delete.addThemeVariants(ButtonVariant.LUMO_ERROR);
            close.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

            save.addClickShortcut(Key.ENTER);
            close.addClickShortcut(Key.ESCAPE);

            return new HorizontalLayout(save, delete, close);

        }
    }
}
