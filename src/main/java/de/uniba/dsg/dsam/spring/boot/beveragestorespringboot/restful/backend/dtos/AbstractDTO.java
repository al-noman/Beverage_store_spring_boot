package de.uniba.dsg.dsam.spring.boot.beveragestorespringboot.restful.backend.dtos;

import de.uniba.dsg.dsam.spring.boot.beveragestorespringboot.restful.backend.entities.WithId;

import java.io.Serializable;

public abstract class AbstractDTO implements WithId, Serializable {
    private static final long serialVersionUID = 1L;

    protected int id;

    private int version;

    @Override
    public int getId() {
        return id;
    }

    @Override
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
