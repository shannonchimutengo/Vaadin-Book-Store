package org.vaadin.example.views;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.charts.Chart;
import com.vaadin.flow.component.charts.model.ChartType;
import com.vaadin.flow.component.charts.model.DataSeries;
import com.vaadin.flow.component.charts.model.DataSeriesItem;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility;
import jakarta.annotation.security.PermitAll;
import org.vaadin.example.Service.CrmService;

@Route(value = "dashboard", layout = MainLayout.class)
@PageTitle("Dashboard | Vaadin CRM")
@PermitAll
public class DashboardView extends VerticalLayout {
    CrmService service;

    public DashboardView(CrmService service) {
        this.service = service;
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        addClassName("dashboard-view");
        add(setContactStats(), getCompaniesChart());
    }

    private Component setContactStats() {
        Span stats = new Span(service.countContacts() + " contacts");
        stats.addClassNames(
                LumoUtility.FontSize.XXLARGE,
                LumoUtility.Margin.Top.MEDIUM
        );
        return  stats;
    }
    private Component getCompaniesChart() {
        Chart chart = new Chart(ChartType.PIE);
        DataSeries dataSeries = new DataSeries();

        service.findAllCompanies().forEach(company -> dataSeries.add(new DataSeriesItem(company.getName(), company.getEmployeeCount())));
        chart.getConfiguration().addSeries(dataSeries);

        return chart;
    }
}
