package de.uniba.dsg.dsam.spring.boot.beveragestorespringboot.restful.backend.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.Positive;
import java.util.Date;

import static com.fasterxml.jackson.annotation.JsonProperty.Access.READ_ONLY;
import static com.fasterxml.jackson.annotation.JsonProperty.Access.WRITE_ONLY;

public final class CustomerOrderDTO extends AbstractDTO {

    @JsonProperty(access = READ_ONLY)
    private Date issueDate;

    @JsonProperty(access = WRITE_ONLY)
    @Positive
    private int orderAmount;

    @JsonProperty(access = WRITE_ONLY)
    @Positive
    private int beverageId;

    @JsonProperty(access = READ_ONLY)
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
