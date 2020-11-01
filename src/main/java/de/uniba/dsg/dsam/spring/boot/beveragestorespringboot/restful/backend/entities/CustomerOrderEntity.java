package de.uniba.dsg.dsam.spring.boot.beveragestorespringboot.restful.backend.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "customer_order_boot")
public class CustomerOrderEntity extends AbstractEntity{

    @Column(name = "issue_date")
    private Date issueDate;

    @Column(name = "order_amount")
    private int orderAmount;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "beverage_boot_id")
    private BeverageEntity beverageEntity;

    public CustomerOrderEntity() {

    }
    public Date getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }

    public int getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(int orderAmount) {
        this.orderAmount = orderAmount;
    }

    public BeverageEntity getBeverageEntity() {
        return beverageEntity;
    }

    public void setBeverageEntity(BeverageEntity beverageEntity) {
        this.beverageEntity = beverageEntity;
    }
}
