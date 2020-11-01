package de.uniba.dsg.dsam.spring.boot.beveragestorespringboot.restful.backend.converters;

import de.uniba.dsg.dsam.spring.boot.beveragestorespringboot.restful.backend.dtos.TrialPackageDTO;
import de.uniba.dsg.dsam.spring.boot.beveragestorespringboot.restful.backend.entities.TrialPackageEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TrialPackageMapper extends GenericMapper<TrialPackageEntity, TrialPackageDTO> {
}
