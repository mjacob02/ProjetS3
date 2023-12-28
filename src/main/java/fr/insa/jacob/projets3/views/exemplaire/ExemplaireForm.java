package fr.insa.jacob.projets3.views.exemplaire;

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
import fr.insa.jacob.projets3.entity.Gamme;
import fr.insa.jacob.projets3.entity.Exemplaire;
import fr.insa.jacob.projets3.views.exemplaire.ExemplaireForm;

import java.util.List;

public class ExemplaireForm extends FormLayout {
    TextField produit = new TextField("Produit");
    TextField numerodeserie = new TextField("Numéro de série");
    Button operationseffectueespourlexemplaire = new Button("Opérations effectuées pour l'exemplaire");
    Button save = new Button("Save");
    Button delete = new Button("Delete");
    Button close = new Button("Cancel");
    // Other fields omitted
    Binder<Exemplaire> binder = new BeanValidationBinder<>(Exemplaire.class); // To validate the form 
    

    private Component createButtonsLayout() {
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        delete.addThemeVariants(ButtonVariant.LUMO_ERROR);
        close.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

        save.addClickShortcut(Key.ENTER);
        close.addClickShortcut(Key.ESCAPE);

        save.addClickListener(event -> validateAndSave());
        delete.addClickListener(event -> fireEvent(new ExemplaireForm.DeleteEvent(this, binder.getBean())));
        close.addClickListener(event -> fireEvent(new ExemplaireForm.CloseEvent(this)));

        binder.addStatusChangeListener(e -> save.setEnabled(binder.isValid()));
        return new HorizontalLayout(save, delete, close);
    }

    private void validateAndSave() {
        if (binder.isValid()) {
            fireEvent(new ExemplaireForm.SaveEvent(this, binder.getBean()));
        }
    }


    public void setExemplaire(Exemplaire exemplaire) {
        binder.setBean(exemplaire);    // To fill the form with the data of the selected entity
    }

    // Events
    public static abstract class ExemplaireFormEvent extends ComponentEvent<ExemplaireForm> {
        private Exemplaire exemplaire;

        protected ExemplaireFormEvent(ExemplaireForm source, Exemplaire exemplaire) {
            super(source, false);
            this.exemplaire = exemplaire;
        }

        public Exemplaire getExemplaire() {return exemplaire;}
    }

    public static class SaveEvent extends ExemplaireFormEvent {
        SaveEvent(ExemplaireForm source, Exemplaire exemplaire) {
            super(source, exemplaire);
        }
    }

    public static class DeleteEvent extends ExemplaireFormEvent {
        DeleteEvent(ExemplaireForm source, Exemplaire exemplaire) { super(source, exemplaire); }

    }

    public static class CloseEvent extends ExemplaireFormEvent {
        CloseEvent(ExemplaireForm source) { super(source, null); }
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

