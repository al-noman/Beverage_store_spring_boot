package de.uniba.dsg.dsam.spring.boot.beveragestorespringboot.restful.backend.service;

import java.util.List;
import java.util.Optional;

public interface CrudService<ENTITY> {
    ENTITY addOne(ENTITY entity);
    Optional<ENTITY> getOne(int id);
    List<ENTITY> getAll();
    ENTITY updateOne(ENTITY entity);
    void deleteOne(int id);
    void deleteAll();
}
