package de.uniba.dsg.dsam.spring.boot.beveragestorespringboot.restful.backend.entities;

public interface WithId {
    default int getId(){
        return 0;
    }
    default void setId(int id){}
}
