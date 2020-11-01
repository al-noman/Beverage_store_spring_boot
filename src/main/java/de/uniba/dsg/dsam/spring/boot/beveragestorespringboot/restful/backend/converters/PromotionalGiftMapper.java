package de.uniba.dsg.dsam.spring.boot.beveragestorespringboot.restful.backend.converters;

import de.uniba.dsg.dsam.spring.boot.beveragestorespringboot.restful.backend.dtos.PromotionalGiftDTO;
import de.uniba.dsg.dsam.spring.boot.beveragestorespringboot.restful.backend.entities.PromotionalGiftEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PromotionalGiftMapper extends GenericMapper<PromotionalGiftEntity, PromotionalGiftDTO> {
}
