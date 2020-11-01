package de.uniba.dsg.dsam.spring.boot.beveragestorespringboot.restful.backend.controller;

import de.uniba.dsg.dsam.spring.boot.beveragestorespringboot.restful.backend.dtos.IncentiveDTO;
import de.uniba.dsg.dsam.spring.boot.beveragestorespringboot.restful.backend.entities.IncentiveEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/incentives")
public class IncentiveController extends CRUDController<IncentiveEntity, IncentiveDTO>{

    @PostMapping(path = "/test")
    @ResponseStatus(HttpStatus.CREATED)
    public IncentiveDTO test(@Valid @RequestBody IncentiveDTO dto){
        System.out.println(dto);
        System.out.println(dto.getClass().getSimpleName());
        return dto;
    }
}
