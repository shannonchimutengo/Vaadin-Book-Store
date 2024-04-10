package org.zimcoding.bookstore.views;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.RolesAllowed;
import org.vaadin.reports.PrintPreviewReport;
import org.zimcoding.bookstore.Entity.Book;
import org.zimcoding.bookstore.Service.BookService;

@Route("reports")
@RolesAllowed("ADMIN")
public class ReportsView extends VerticalLayout {

    public ReportsView(BookService bookService) {
        setSizeFull();
        var reports = new PrintPreviewReport<>(Book.class, "id","title","published","rating");
        reports.setItems(bookService.findAll());
add(reports);

    }
}
