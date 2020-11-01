package de.uniba.dsg.dsam.spring.boot.beveragestorespringboot.restful.backend.converters;

public interface GenericMapper<ENTITY, DTO> {
    DTO convertEntityToDTO(ENTITY entity);
    ENTITY convertDTOToEntity(DTO dto);
}
