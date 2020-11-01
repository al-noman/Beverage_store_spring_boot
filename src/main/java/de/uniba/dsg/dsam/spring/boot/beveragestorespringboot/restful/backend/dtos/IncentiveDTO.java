package de.uniba.dsg.dsam.spring.boot.beveragestorespringboot.restful.backend.dtos;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        property = "incentive_type",
        include = JsonTypeInfo.As.PROPERTY
)
@JsonSubTypes({
        @Type(name = "trial_package", value = TrialPackageDTO.class),
        @Type(name = "promotional_gift", value = PromotionalGiftDTO.class)
})
public abstract class IncentiveDTO extends AbstractDTO {

    protected String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Incentive{" +
                "name='" + name + '\'' +
                '}';
    }
}
