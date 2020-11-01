package de.uniba.dsg.dsam.spring.boot.beveragestorespringboot.restful.backend.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "incentive_boot")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype", discriminatorType = DiscriminatorType.STRING)
public abstract class IncentiveEntity extends AbstractEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "dtype", insertable = false, updatable = false)
    private String dtype;

    @OneToMany(mappedBy = "incentiveEntity", fetch = FetchType.LAZY, targetEntity = BeverageEntity.class)
    private Set<BeverageEntity> beverageEntities = new HashSet<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<BeverageEntity> getBeverageEntities() {
        return beverageEntities;
    }

    public String getDtype() {
        return dtype;
    }

    public void setDtype(String dtype) {
        this.dtype = dtype;
    }

    public void setBeverageEntities(Set<BeverageEntity> beverageEntities) {
        this.beverageEntities = beverageEntities;
    }

    @Override
    public String toString() {
        return "IncentiveEntity{" +
                "name='" + name + '\'' +
                ", dtype='" + dtype + '\'' +
                ", beverageEntities=" + beverageEntities +
                "} " + super.toString();
    }
}
