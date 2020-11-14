package de.uniba.dsg.dsam.spring.boot.beveragestorespringboot.restful.backend.entities;


public class DetailedReport {

    private String incentiveType;
    private double revenue;

    public DetailedReport() {
    }

    public DetailedReport(String incentiveType, double revenue) {
        this.incentiveType = incentiveType;
        this.revenue = revenue;
    }

    public String getIncentiveType() {
        return incentiveType;
    }

    public void setIncentiveType(String incentiveType) {
        this.incentiveType = incentiveType;
    }

    public double getRevenue() {
        return revenue;
    }

    public void setRevenue(double revenue) {
        this.revenue = revenue;
    }

    @Override
    public String toString() {
        return "DetailedReport{" +
                "incentiveType=" + incentiveType +
                ", revenue=" + revenue +
                '}';
    }
}
