/*
 * Copyright (c) 2024. This Project is free to use(You may sell,duplicate and edit).
 * Visit my Youtube channel @ZimCoding and enjoy more free projects like this cheers...
 */

package org.zimcoding.bookstore.views;

import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;
import jakarta.annotation.security.RolesAllowed;
import org.vaadin.crudui.crud.impl.GridCrud;
import org.zimcoding.bookstore.Entity.Book;
import org.zimcoding.bookstore.Service.BookService;

@Route("admin")
@RolesAllowed("ADMIN")
public class AdminView extends VerticalLayout {
    public AdminView(BookService service) {
        add(new H1("Admin View"));
        var grid = new GridCrud<>(Book.class, service);
        grid.getGrid().setColumns("title","published","rating");
        grid.getCrudFormFactory().setVisibleProperties("title","published","rating");
        grid.setAddOperationVisible(false);
        grid.getCrudLayout().addToolbarComponent(new RouterLink("New Book",NewBook.class));
        add(grid
        );
    }

}
