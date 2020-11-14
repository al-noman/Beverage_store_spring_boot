package de.uniba.dsg.dsam.spring.boot.beveragestorespringboot.restful.backend.repository;

import de.uniba.dsg.dsam.spring.boot.beveragestorespringboot.restful.backend.entities.CustomerOrderEntity;
import de.uniba.dsg.dsam.spring.boot.beveragestorespringboot.restful.backend.entities.DetailedReport;
import de.uniba.dsg.dsam.spring.boot.beveragestorespringboot.restful.backend.entities.SummaryReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CustomerOrderRepository extends JpaRepository<CustomerOrderEntity, Integer> {

    @Query("select new de.uniba.dsg.dsam.spring.boot.beveragestorespringboot.restful.backend.entities.SummaryReport( " +
            "coe.id, (be.price * coe.orderAmount)) from CustomerOrderEntity coe inner join BeverageEntity be " +
            "on coe.beverageEntity.id = be.id")
    List<SummaryReport> getSummaryReport();

    @Query("select new de.uniba.dsg.dsam.spring.boot.beveragestorespringboot.restful.backend.entities.DetailedReport( " +
            "coe.beverageEntity.incentiveEntity.dtype, sum (be.price * coe.orderAmount)) from CustomerOrderEntity coe " +
            "inner join BeverageEntity be on coe.beverageEntity.id = be.id " +
            "group by coe.beverageEntity.incentiveEntity.dtype")
    List<DetailedReport> getDetailedReport();
}
