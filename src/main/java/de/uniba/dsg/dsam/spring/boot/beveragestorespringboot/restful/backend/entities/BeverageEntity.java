package de.uniba.dsg.dsam.spring.boot.beveragestorespringboot.restful.backend.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "beverage_boot")
public class BeverageEntity extends AbstractEntity{

    @Column(name = "manufacturer")
    private String manufacturer;

    @Column(name = "name")
    private String name;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "available_quantity")
    private int availableQuantity;

    @Column(name = "price")
    private double price;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "incentive_boot_id")
    private IncentiveEntity incentiveEntity;

    @OneToMany(mappedBy = "beverageEntity", fetch = FetchType.LAZY, targetEntity = CustomerOrderEntity.class)
    private List<CustomerOrderEntity> customerOrderEntities = new ArrayList<>();

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getAvailableQuantity() {
        return availableQuantity;
    }

    public void setAvailableQuantity(int availableQuantity) {
        this.availableQuantity = availableQuantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public IncentiveEntity getIncentiveEntity() {
        return incentiveEntity;
    }

    public void setIncentiveEntity(IncentiveEntity incentiveEntity) {
        this.incentiveEntity = incentiveEntity;
    }

    public List<CustomerOrderEntity> getCustomerOrderEntities() {
        return customerOrderEntities;
    }

    public void setCustomerOrderEntities(List<CustomerOrderEntity> customerOrderEntities) {
        this.customerOrderEntities = customerOrderEntities;
    }

    @Override
    public String toString() {
        return "BeverageEntity{" +
                "manufacturer='" + manufacturer + '\'' +
                ", name='" + name + '\'' +
                ", quantity=" + quantity +
                ", availableQuantity=" + availableQuantity +
                ", price=" + price +
                ", incentiveEntity=" + incentiveEntity +
                ", customerOrderEntities=" + customerOrderEntities +
                "} " + super.toString();
    }
}
