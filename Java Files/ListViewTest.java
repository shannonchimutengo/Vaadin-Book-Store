

/*
 * Copyright (c) 2024. This Project is free to use(You may sell,duplicate and edit).
 * Visit my Youtube channel @ZimCoding and enjoy more free projects like this cheers...
 */

package org.zimcoding.views;

//Integration Test

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.data.provider.ListDataProvider;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.vaadin.example.Entities.Contact;

@SpringBootTest
public class ListViewTest {

    static {
        // Prevent Vaadin Development mode to launch browser window
        System.setProperty("vaadin.launch-browser", "false");
    }
    @Autowired
    MainView mainView;

    @Test
    public  void FormShownWhenContactIsSelected(){
        Grid<Contact> grid = mainView.grid;
        Contact firstContact = getFirstItem(grid);

        ContactForm form = mainView.form;
        assertFalse(form.isVisible());
        grid.asSingleSelect().setValue(firstContact);

        assertTrue(form.isVisible());
        assertEquals(firstContact.getFirstName(), form.firstName.getValue());


    }

    private Contact getFirstItem(Grid<Contact> grid) {
        return ((ListDataProvider<Contact>)grid.getDataProvider()).getItems().iterator().next();
    }

}
