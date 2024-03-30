package org.vaadin.example.views;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.PermitAll;
import org.springframework.context.annotation.Scope;
import org.vaadin.example.Entities.Contact;
import org.vaadin.example.Service.CrmService;

/**
 * A sample Vaadin view class.
 * <p>
 * To implement a Vaadin view just extend any Vaadin component and use @Route
 * annotation to announce it in a URL as a Spring managed bean.
 * <p>
 * A new instance of this class is created for every new user and every browser
 * tab/window.
 * <p>
 * The main view contains a text field for getting the username and a button
 * that shows a greeting message in a notification.
 */
@org.springframework.stereotype.Component
@Scope("prototype")
@PageTitle("Contacts | Vaadin CRM")
@Route(value = "", layout = MainLayout.class)
@PermitAll
public class MainView extends VerticalLayout {

    private final CrmService service;
    Grid<Contact> grid = new Grid<>(Contact.class);
    TextField filtertext = new TextField();
    ContactForm form;


    public MainView(CrmService service) {
        this.service = service;
        addClassName("list-view");
        setSizeFull();

        configGrid();
        configform();
        getContent();
        add(toolbar(),getContent());
        updateList();
        closeEditor();



    }

    private void closeEditor() {
        form.setContact(null);
        form.setVisible(false);
        removeClassName("editing") ;
    }

    private void updateList() {
        grid.setItems(service.findAllContacts(filtertext.getValue()));
    }

    private Component getContent() {
        HorizontalLayout content = new HorizontalLayout(grid,form);
        content.setFlexGrow(2, grid);
        content.setFlexGrow(1, form);
        content.setSizeFull();
        addClassNames("content");
        return content;
    }

    private void configform() {
        form = new ContactForm(service.findAllCompanies(),service.findAllStatuses());
        form.setWidth("25em");
        form.addSaveListener(this::saveContact);
        form.addDeleteListener(this::deleteContact);
        form.addCloseListener(this:: closeTab);
    }

    private void closeTab(ContactForm.CloseEvent closeEvent) {
        closeEditor(); 
    }

    private void deleteContact(ContactForm.DeleteEvent deleteEvent) {
        service.deleteContact(deleteEvent.getContact());
        updateList();
        closeEditor();
    }

    private void saveContact(ContactForm.SaveEvent event){
        service.saveContact(event.getContact());
        updateList();
        closeEditor();

    }

    private Component toolbar() {
        filtertext.setPlaceholder("Enter Filter Text");
        filtertext.setClearButtonVisible(true);
        filtertext.setValueChangeMode(ValueChangeMode.LAZY);
        filtertext.addValueChangeListener(e-> updateList());

        Button addContact = new Button("Add Contact");
        addContact.addClickListener(e -> createContact());
        HorizontalLayout toolbar = new HorizontalLayout(filtertext, addContact);
        toolbar.addClassName("toolbar");
        return toolbar;
    }

    private void createContact() {
        grid.asSingleSelect().clear();
        editContact(new Contact());
    }

    private void configGrid() {
        grid.addClassNames("contact-grid");
        grid.setSizeFull();
        grid.removeColumnByKey("company");
        grid.removeColumnByKey("status");
        grid.addColumn(contact -> contact.getStatus().getName()).setHeader("Status");
        grid.addColumn(contact -> contact.getCompany().getName()).setHeader("Company");
        grid.getColumns().forEach(col ->col.setAutoWidth(true));
        grid.asSingleSelect().addValueChangeListener(e -> editContact(e.getValue()));

    }

    private void editContact(Contact contact) {
        if (contact == null){
            closeEditor();
        }else{
            form.setContact(contact);
            form.setVisible(true);
            addClassName("editing");
        }
    }

}

