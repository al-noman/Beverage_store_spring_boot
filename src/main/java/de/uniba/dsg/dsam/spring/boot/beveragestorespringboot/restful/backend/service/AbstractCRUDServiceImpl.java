package de.uniba.dsg.dsam.spring.boot.beveragestorespringboot.restful.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public abstract class AbstractCRUDServiceImpl<ENTITY> implements CrudService<ENTITY>{

    @Autowired
    protected JpaRepository<ENTITY, Integer> repository;

    @Override
    public ENTITY addOne(ENTITY entity) {
        return repository.save(entity);
    }

    @Override
    public Optional<ENTITY> getOne(int id) {
        return repository.findById(id);
    }

    @Override
    public List<ENTITY> getAll() {
        return repository.findAll();
    }

    @Override
    public ENTITY updateOne(ENTITY entity) {
        return repository.save(entity);
    }

    @Override
    public void deleteOne(int id) {
        repository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        repository.deleteAll();
    }
}
