package de.uniba.dsg.dsam.spring.boot.beveragestorespringboot.restful.backend.entities;

public interface WithIdAndVersion {
    default int getId(){
        return 0;
    }
    default void setId(int id){}
    default int getVersion(){ return 0; }
    default void setVersion(int version){}
}
