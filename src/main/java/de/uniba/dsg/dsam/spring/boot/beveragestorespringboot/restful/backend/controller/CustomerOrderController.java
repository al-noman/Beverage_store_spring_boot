package de.uniba.dsg.dsam.spring.boot.beveragestorespringboot.restful.backend.controller;

import de.uniba.dsg.dsam.spring.boot.beveragestorespringboot.restful.backend.converters.GenericMapper;
import de.uniba.dsg.dsam.spring.boot.beveragestorespringboot.restful.backend.dtos.BeverageDTO;
import de.uniba.dsg.dsam.spring.boot.beveragestorespringboot.restful.backend.dtos.CustomerOrderDTO;
import de.uniba.dsg.dsam.spring.boot.beveragestorespringboot.restful.backend.entities.BeverageEntity;
import de.uniba.dsg.dsam.spring.boot.beveragestorespringboot.restful.backend.entities.CustomerOrderEntity;
import de.uniba.dsg.dsam.spring.boot.beveragestorespringboot.restful.backend.service.CrudService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(path = "/customer_order")
public class CustomerOrderController {

    private final CrudService<BeverageEntity> beverageService;
    private final GenericMapper<BeverageEntity, BeverageDTO> beverageConverter;
    private final CrudService<CustomerOrderEntity> customerOrderService;
    private final GenericMapper<CustomerOrderEntity, CustomerOrderDTO> customerOrderConverter;

    public CustomerOrderController(CrudService<BeverageEntity> beverageService,
                                   GenericMapper<BeverageEntity, BeverageDTO> beverageConverter,
                                   CrudService<CustomerOrderEntity> customerOrderService,
                                   GenericMapper<CustomerOrderEntity, CustomerOrderDTO> customerOrderConverter) {
        this.beverageService = beverageService;
        this.beverageConverter = beverageConverter;
        this.customerOrderService = customerOrderService;
        this.customerOrderConverter = customerOrderConverter;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public List<CustomerOrderDTO> addOne(@RequestBody @NotEmpty List<@Valid CustomerOrderDTO> dtos){

        dtos.forEach(customerOrderDTO -> {
            int beverageId = customerOrderDTO.getBeverageId();
            BeverageEntity beverageEntity = beverageService.getOne(beverageId)
                    .orElseThrow(() -> new EntityNotFoundException(String.valueOf(beverageId)));
            BeverageDTO beverageDTO = beverageConverter.convertEntityToDTO(beverageEntity);
            beverageDTO.setQuantity(customerOrderDTO.getOrderAmount());
            beverageDTO.setAvailableQuantity(beverageDTO.getAvailableQuantity() - beverageDTO.getQuantity());
            customerOrderDTO.setBeverageDTO(beverageDTO);
            customerOrderDTO.setIssueDate(new Date());

            // TODO
            // customerOrderService.addOne(customerOrderConverter.convertDTOToEntity(customerOrderDTO));
        });

        return dtos;
    }
}
