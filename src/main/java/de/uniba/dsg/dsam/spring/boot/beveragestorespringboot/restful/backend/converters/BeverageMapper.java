package de.uniba.dsg.dsam.spring.boot.beveragestorespringboot.restful.backend.converters;

import de.uniba.dsg.dsam.spring.boot.beveragestorespringboot.restful.backend.dtos.BeverageDTO;
import de.uniba.dsg.dsam.spring.boot.beveragestorespringboot.restful.backend.dtos.PromotionalGiftDTO;
import de.uniba.dsg.dsam.spring.boot.beveragestorespringboot.restful.backend.dtos.TrialPackageDTO;
import de.uniba.dsg.dsam.spring.boot.beveragestorespringboot.restful.backend.entities.BeverageEntity;
import de.uniba.dsg.dsam.spring.boot.beveragestorespringboot.restful.backend.entities.PromotionalGiftEntity;
import de.uniba.dsg.dsam.spring.boot.beveragestorespringboot.restful.backend.entities.TrialPackageEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BeverageMapper extends GenericMapper<BeverageEntity, BeverageDTO>{
    @Override
    BeverageDTO convertEntityToDTO(BeverageEntity beverageEntity);

    @Override
    BeverageEntity convertDTOToEntity(BeverageDTO beverageDTO);

    PromotionalGiftDTO convertIncentiveEntityToDTO(PromotionalGiftEntity entity);
    PromotionalGiftEntity convertIncentiveDTOToEntity(PromotionalGiftDTO dto);

    TrialPackageDTO convertIncentiveEntityToDTO(TrialPackageEntity entity);
    TrialPackageEntity convertIncentiveDTOToEntity(TrialPackageDTO dto);
}
