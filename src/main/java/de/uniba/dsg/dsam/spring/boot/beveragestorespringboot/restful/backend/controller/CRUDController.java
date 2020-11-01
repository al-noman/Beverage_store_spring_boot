package de.uniba.dsg.dsam.spring.boot.beveragestorespringboot.restful.backend.controller;

import de.uniba.dsg.dsam.spring.boot.beveragestorespringboot.restful.backend.converters.GenericMapper;
import de.uniba.dsg.dsam.spring.boot.beveragestorespringboot.restful.backend.dtos.AbstractDTO;
import de.uniba.dsg.dsam.spring.boot.beveragestorespringboot.restful.backend.entities.WithId;
import de.uniba.dsg.dsam.spring.boot.beveragestorespringboot.restful.backend.service.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

public abstract class CRUDController<ENTITY extends WithId, DTO extends AbstractDTO> {

    @Autowired
    protected CrudService<ENTITY> crudService;

    @Autowired
    protected GenericMapper<ENTITY, DTO> mapper;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<DTO> getAll(){
        List<ENTITY> entityList = this.crudService.getAll();
        return entityList.stream()
                .map(entity -> this.mapper.convertEntityToDTO(entity))
                .collect(toList());
    }

    @GetMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public DTO getById(@PathVariable int id) throws RuntimeException {
        Optional<ENTITY> maybeEntity = this.crudService.getOne(id);
        return maybeEntity
                .map(entity -> this.mapper.convertEntityToDTO(entity))
                .orElseThrow(() -> new EntityNotFoundException(""));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DTO addOne(@Valid @RequestBody DTO dto){
        ENTITY entity = this.mapper.convertDTOToEntity(dto);
        ENTITY storedEntity = this.crudService.addOne(entity);
        return this.mapper.convertEntityToDTO(storedEntity);
    }

    @PutMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public DTO updateOne(@PathVariable int id, @Valid @RequestBody DTO dto){
        Optional<ENTITY> maybeEntity = this.crudService.getOne(id);
        ENTITY entity = maybeEntity.orElseThrow(() -> new EntityNotFoundException());
        dto.setId(entity.getId());
        return this.mapper.convertEntityToDTO(
                this.crudService.updateOne(
                this.mapper.convertDTOToEntity(dto)));
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAll(){
        this.crudService.deleteAll();
    }

    @DeleteMapping(path = "/{id}")
    public void deleteOne(@PathVariable int id){
        Optional<ENTITY> maybeEntity = this.crudService.getOne(id);
        maybeEntity.orElseThrow(() -> new EntityNotFoundException());
        this.crudService.deleteOne(id);
    }
}
