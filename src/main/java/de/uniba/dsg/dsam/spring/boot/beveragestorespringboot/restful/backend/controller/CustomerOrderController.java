package de.uniba.dsg.dsam.spring.boot.beveragestorespringboot.restful.backend.controller;

import de.uniba.dsg.dsam.spring.boot.beveragestorespringboot.configuration.RabbitMQConfigConstants;
import de.uniba.dsg.dsam.spring.boot.beveragestorespringboot.restful.backend.converters.GenericMapper;
import de.uniba.dsg.dsam.spring.boot.beveragestorespringboot.restful.backend.dtos.BeverageDTO;
import de.uniba.dsg.dsam.spring.boot.beveragestorespringboot.restful.backend.dtos.CustomerOrderDTO;
import de.uniba.dsg.dsam.spring.boot.beveragestorespringboot.restful.backend.entities.BeverageEntity;
import de.uniba.dsg.dsam.spring.boot.beveragestorespringboot.restful.backend.service.CrudService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
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
    private final RabbitMQConfigConstants configConstants;
    private final RabbitTemplate rabbitTemplate;

    public CustomerOrderController(CrudService<BeverageEntity> beverageService,
                                   GenericMapper<BeverageEntity, BeverageDTO> beverageConverter,
                                   RabbitMQConfigConstants configConstants,
                                   RabbitTemplate rabbitTemplate) {
        this.beverageService = beverageService;
        this.beverageConverter = beverageConverter;
        this.configConstants = configConstants;
        this.rabbitTemplate = rabbitTemplate;
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

            // Publishing to RabbitMQ
            CustomerOrderDTO receivedDto = (CustomerOrderDTO) rabbitTemplate.convertSendAndReceive(
                    configConstants.getExchange(),
                    configConstants.getRoutingKey(),
                    customerOrderDTO);
            customerOrderDTO.setId(receivedDto.getId());
            customerOrderDTO.setVersion(receivedDto.getVersion());
        });
        return dtos;
    }
}
