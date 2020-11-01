package de.uniba.dsg.dsam.spring.boot.beveragestorespringboot.restful.backend.dtos;

import java.util.Date;

public final class CustomerOrderDTO extends AbstractDTO {

    private Date issueDate;
    private int orderAmount;
    private BeverageDTO orderItems;

    /**
     * @return the issueDate
     */
    public Date getIssueDate() {
        return issueDate;
    }

    /**
     * @param issueDate the issueDate to set
     */
    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }

    /**
     * @return the orderAmount
     */
    public int getOrderAmount() {
        return orderAmount;
    }

    /**
     * @param orderAmount the orderAmount to set
     */
    public void setOrderAmount(int orderAmount) {
        this.orderAmount = orderAmount;
    }

    /**
     * @return the orderItems
     */
    public BeverageDTO getOrderItems() {
        return orderItems;
    }

    /**
     * @param orderItems the orderItems to set
     */
    public void setOrderItems(BeverageDTO orderItems) {
        this.orderItems = orderItems;
    }

    /**
     * @see Object#toString()
     */
    @Override
    public String toString() {
        return "CustomerOrder [issueDate=" + issueDate + ", orderAmount=" + orderAmount + ", orderItems=" + orderItems
                + "]";
    }
}
