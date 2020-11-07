package de.uniba.dsg.dsam.spring.boot.beveragestorespringboot.restful.backend.dtos;

import java.io.Serializable;

public abstract class AbstractDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    protected int id;

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
