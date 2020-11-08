package de.uniba.dsg.dsam.spring.boot.beveragestorespringboot.restful.backend.dtos;

import javax.validation.constraints.Positive;
import java.util.Date;

public class CustomerOrderDTO extends AbstractDTO {

    private Date issueDate;

    @Positive
    private int orderAmount;

    @Positive
    private int beverageId;

    private BeverageDTO beverageDTO;

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
    public BeverageDTO getBeverageDTO() {
        return beverageDTO;
    }

    /**
     * @param beverageDTO the orderItems to set
     */
    public void setBeverageDTO(BeverageDTO beverageDTO) {
        this.beverageDTO = beverageDTO;
    }

    public int getBeverageId() {
        return beverageId;
    }

    public void setBeverageId(int beverageId) {
        this.beverageId = beverageId;
    }

    /**
     * @see Object#toString()
     */
    @Override
    public String toString() {
        return "CustomerOrder [issueDate=" + issueDate + ", orderAmount=" + orderAmount + ", orderItems=" + beverageDTO
                + "]";
    }
}
