package operation;

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

import java.util.List;

public class OperationForm extends FormLayout {
    TextField typeOperation = new TextField("Type d'Opération");
    TextField operation = new TextField("Opération");
    TextField description = new TextField("Description");
    TextField operationAmont = new TextField("Opération Amont");
    TextField operationAval = new TextField("Opération Aval");

    /*ComboBox<Gamme> gamme = new ComboBox<>("Gamme");*/

    Button save = new Button("Save");
    Button delete = new Button("Delete");
    Button close = new Button("Cancel");
    // Other fields omitted
    /*Binder<Produit> binder = new BeanValidationBinder<>(Produit.class); // To validate the form

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
        delete.addClickListener(event -> fireEvent(new fr.insa.jacob.projets3.views.produit.ProduitForm.DeleteEvent(this, binder.getBean())));
        close.addClickListener(event -> fireEvent(new fr.insa.jacob.projets3.views.produit.ProduitForm.CloseEvent(this)));

        binder.addStatusChangeListener(e -> save.setEnabled(binder.isValid()));
        return new HorizontalLayout(save, delete, close);
    }

    private void validateAndSave() {
        if (binder.isValid()) {
            fireEvent(new fr.insa.jacob.projets3.views.produit.ProduitForm.SaveEvent(this, binder.getBean()));
        }
    }


    public void setProduit(Produit produit) {
        binder.setBean(produit);    // To fill the form with the data of the selected entity
    }
*/
    // Events
    public static abstract class OperationFormEvent extends ComponentEvent<fr.insa.jacob.projets3.views.operation.OperationForm> {
        private Operation operation;

        protected OperationFormEvent(fr.insa.jacob.projets3.views.operation.OperationForm source, Operation operation) {
            super(source, false);
            this.operation = operation;
        }

        public Operation getOperation() {
            return operation;
        }
    }

    public static class SaveEvent extends fr.insa.jacob.projets3.views.operation.OperationForm.OperationFormEvent {
        SaveEvent(fr.insa.jacob.projets3.views.operation.OperationForm source, Operation operation) {
            super(source, operation);
        }
    }

    public static class DeleteEvent extends fr.insa.jacob.projets3.views.operation.OperationForm.OperationFormEvent {
        DeleteEvent(fr.insa.jacob.projets3.views.operation.OperationForm source, Operation operation) {
            super(source, operation);
        }

    }

    public static class CloseEvent extends fr.insa.jacob.projets3.views.operation.OperationForm.OperationFormEvent {
        CloseEvent(fr.insa.jacob.projets3.views.operation.OperationForm source) {
            super(source, null);
        }
    }

    public Registration addDeleteListener(ComponentEventListener<fr.insa.jacob.projets3.views.operation.OperationForm.DeleteEvent> listener) {
        return addListener(fr.insa.jacob.projets3.views.operation.OperationForm.DeleteEvent.class, listener);
    }

    public Registration addSaveListener(ComponentEventListener<fr.insa.jacob.projets3.views.operation.OperationForm.SaveEvent> listener) {
        return addListener(fr.insa.jacob.projets3.views.operation.OperationForm.SaveEvent.class, listener);
    }

    public Registration addCloseListener(ComponentEventListener<fr.insa.jacob.projets3.views.operation.OperationForm.CloseEvent> listener) {
        return addListener(fr.insa.jacob.projets3.views.operation.OperationForm.CloseEvent.class, listener);
    }
}


