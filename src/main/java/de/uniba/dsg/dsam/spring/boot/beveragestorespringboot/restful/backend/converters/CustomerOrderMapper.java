package de.uniba.dsg.dsam.spring.boot.beveragestorespringboot.restful.backend.converters;

import de.uniba.dsg.dsam.spring.boot.beveragestorespringboot.restful.backend.dtos.CustomerOrderDTO;
import de.uniba.dsg.dsam.spring.boot.beveragestorespringboot.restful.backend.entities.CustomerOrderEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CustomerOrderMapper extends GenericMapper<CustomerOrderEntity, CustomerOrderDTO> {

    @Override
    @Mapping(source = "beverageEntity", target = "beverageDTO")
    CustomerOrderDTO convertEntityToDTO(CustomerOrderEntity customerOrderEntity);

    @Override
    @Mapping(source = "beverageDTO", target = "beverageEntity")
    CustomerOrderEntity convertDTOToEntity(CustomerOrderDTO customerOrderDTO);
}
