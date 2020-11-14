package de.uniba.dsg.dsam.spring.boot.beveragestorespringboot.restful.backend.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "customer_order_boot")
public class CustomerOrderEntity implements WithId, Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private int id;

    @Version
    private int version;

    @Column(name = "issue_date")
    private Date issueDate;

    @Column(name = "order_amount")
    private int orderAmount;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "beverage_boot_id", referencedColumnName = "id")
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

    @Override
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return "CustomerOrderEntity{" +
                "id=" + id +
                ", version=" + version +
                ", issueDate=" + issueDate +
                ", orderAmount=" + orderAmount +
                ", beverageEntity=" + beverageEntity +
                '}';
    }
}
