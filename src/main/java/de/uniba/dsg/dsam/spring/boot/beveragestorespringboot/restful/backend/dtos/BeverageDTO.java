package de.uniba.dsg.dsam.spring.boot.beveragestorespringboot.restful.backend.dtos;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.PositiveOrZero;

public class BeverageDTO extends AbstractDTO{

    @NotEmpty
    private String manufacturer;

    @NotEmpty
    private String name;

    @PositiveOrZero
    private int quantity;

    @PositiveOrZero
    private double price;

    private IncentiveDTO incentiveDTO;

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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public IncentiveDTO getIncentiveDTO() {
        return incentiveDTO;
    }

    public void setIncentiveDTO(IncentiveDTO incentiveDTO) {
        this.incentiveDTO = incentiveDTO;
    }

    @Override
    public String
    toString() {
        return "BeverageDTO{" +
                "manufacturer='" + manufacturer + '\'' +
                ", name='" + name + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                '}';
    }
}
