package de.uniba.dsg.dsam.spring.boot.beveragestorespringboot.restful.backend.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

import static com.fasterxml.jackson.annotation.JsonProperty.Access.READ_ONLY;

public abstract class AbstractDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @JsonProperty(access = READ_ONLY)
    protected int id;

    @JsonProperty(access = READ_ONLY)
    private int version;

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
}
