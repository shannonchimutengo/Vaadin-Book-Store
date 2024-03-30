package org.vaadin.example.views;

import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.HighlightConditions;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.theme.lumo.LumoUtility;
import org.vaadin.example.security.SecurityService;

public class MainLayout extends AppLayout {

    private final SecurityService service;

    public MainLayout(SecurityService service) {
        this.service = service;
        createHeader();
        createDrawer();
    }

    private void createHeader() {
        H1 logo = new H1("Vaadin CRM");
        logo.addClassNames(
                LumoUtility.FontSize.LARGE,
                LumoUtility.Margin.MEDIUM);
        Button logout = new Button("log Out");
        logout.addClickListener(e -> {
            service.logout();
        });
        HorizontalLayout header = new HorizontalLayout(new DrawerToggle(), logo,logout);

        header.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER);
        header.expand(logo);
        header.setWidthFull();
        logo.addClassNames(
                LumoUtility.Padding.Vertical.NONE,
                LumoUtility.Padding.Horizontal.MEDIUM);

        addToNavbar(header);
    }

    private void createDrawer() {
        RouterLink listview = new RouterLink("List", MainView.class);
        RouterLink dashboard = new RouterLink("Dashboard", DashboardView.class);

        listview.setHighlightCondition(HighlightConditions.sameLocation());
        addToDrawer(new VerticalLayout(listview,dashboard));
    }
}
