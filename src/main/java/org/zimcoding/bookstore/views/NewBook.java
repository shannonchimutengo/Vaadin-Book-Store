/*
 * Copyright (c) 2024. This Project is free to use(You may sell,duplicate and edit).
 * Visit my Youtube channel @ZimCoding and enjoy more free projects like this cheers...
 */

package org.zimcoding.bookstore.views;

import com.vaadin.collaborationengine.CollaborationBinder;
import com.vaadin.collaborationengine.CollaborationMessageInput;
import com.vaadin.collaborationengine.CollaborationMessageList;
import com.vaadin.collaborationengine.UserInfo;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.zimcoding.bookstore.Entity.Book;
import org.zimcoding.bookstore.Service.BookService;


@Route("new")
@RolesAllowed("ADMIN")
public class NewBook extends VerticalLayout {
    com.vaadin.flow.component.textfield.TextField title = new com.vaadin.flow.component.textfield.TextField("Title");
    DatePicker published = new DatePicker ("Published");
    TextField rating = new TextField("Rating");

    public NewBook(BookService bookService) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        var username = new UserInfo(userDetails.getUsername(), userDetails.getUsername());

        var binder = new CollaborationBinder<>(Book.class,username);
        binder.bindInstanceFields(this);
        binder.setTopic("new-book", Book::new);
        CollaborationMessageList massageList = new CollaborationMessageList(username,"New-Book");
        add(new H1("New Book"),Routes(),
                new HorizontalLayout(
                        new VerticalLayout(new FormLayout(title,published,rating),
                                new Button("Save Book", buttonClickEvent -> {
                                    var book = new Book();
                                    binder.writeBeanIfValid(book);
                                    bookService.add(book);
                                    Notification.show("Book Saved");
                                    binder.reset(new Book());
                                })),
                        new VerticalLayout(massageList,new CollaborationMessageInput(massageList))
                ));


    }
    public Component Routes(){

        return new HorizontalLayout(
                new RouterLink("Home Page", HomeView.class),
                new RouterLink("Administration ", AdminView.class),
                new RouterLink("Login", LoginView.class)
        );
    }
}
