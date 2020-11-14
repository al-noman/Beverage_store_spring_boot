package de.uniba.dsg.dsam.spring.boot.beveragestorespringboot.restful.backend.controller;

import de.uniba.dsg.dsam.spring.boot.beveragestorespringboot.restful.backend.entities.DetailedReport;
import de.uniba.dsg.dsam.spring.boot.beveragestorespringboot.restful.backend.entities.SummaryReport;
import de.uniba.dsg.dsam.spring.boot.beveragestorespringboot.restful.backend.repository.CustomerOrderRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/sales-report")
public class SalesReportController {

    private final CustomerOrderRepository customerOrderRepository;

    public SalesReportController(CustomerOrderRepository customerOrderRepository) {
        this.customerOrderRepository = customerOrderRepository;
    }

    @GetMapping(path = "/summary")
    public List<SummaryReport> getSalesReport(){
        List<SummaryReport> entities = this.customerOrderRepository.getSummaryReport();
        return entities;
    }

    @GetMapping(path = "/detail")
    public List<DetailedReport> getReportBrokenToIncentive(){
        List<DetailedReport> entities = this.customerOrderRepository.getDetailedReport();
        return entities;
    }
}
