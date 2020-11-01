package de.uniba.dsg.dsam.spring.boot.beveragestorespringboot.restful.backend.converters;

import de.uniba.dsg.dsam.spring.boot.beveragestorespringboot.restful.backend.dtos.BeverageDTO;
import de.uniba.dsg.dsam.spring.boot.beveragestorespringboot.restful.backend.entities.BeverageEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BeverageMapper extends GenericMapper<BeverageEntity, BeverageDTO>{
}
