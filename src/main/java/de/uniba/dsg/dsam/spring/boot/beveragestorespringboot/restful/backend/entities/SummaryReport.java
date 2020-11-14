package de.uniba.dsg.dsam.spring.boot.beveragestorespringboot.restful.backend.entities;


public class SummaryReport {

    private int customerOrderId;
    private double revenue;

    public SummaryReport() {
    }

    public SummaryReport(int customerOrderId, double revenue) {
        this.customerOrderId = customerOrderId;
        this.revenue = revenue;
    }

    public int getCustomerOrderId() {
        return customerOrderId;
    }

    public void setCustomerOrderId(int customerOrderId) {
        this.customerOrderId = customerOrderId;
    }

    public double getRevenue() {
        return revenue;
    }

    public void setRevenue(double revenue) {
        this.revenue = revenue;
    }

    @Override
    public String toString() {
        return "SummaryReport{" +
                "customerOrderId=" + customerOrderId +
                ", price=" + revenue +
                '}';
    }
}
