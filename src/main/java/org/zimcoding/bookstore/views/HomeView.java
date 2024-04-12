/*
 * Copyright (c) 2024. This Project is free to use(You may sell,duplicate and edit).
 * Visit my Youtube channel @ZimCoding and enjoy more free projects like this cheers...
 */

package org.zimcoding.bookstore.views;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.cookieconsent.CookieConsent;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.server.auth.AnonymousAllowed;

@Route("")
@AnonymousAllowed
public class HomeView extends VerticalLayout {
    public HomeView() {
        add(new H1("Home View"),Routes(),new CookieConsent());
    }
    public Component Routes(){

        return new HorizontalLayout(
                new RouterLink("Admin View", AdminView.class),
                new RouterLink("Add New Book ", NewBook.class),
                new RouterLink("Login", LoginView.class),
                new RouterLink("Reports", ReportsView.class)
        );
    }
}
