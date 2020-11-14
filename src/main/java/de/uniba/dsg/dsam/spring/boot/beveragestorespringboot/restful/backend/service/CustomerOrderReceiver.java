package de.uniba.dsg.dsam.spring.boot.beveragestorespringboot.restful.backend.service;

import de.uniba.dsg.dsam.spring.boot.beveragestorespringboot.restful.backend.converters.GenericMapper;
import de.uniba.dsg.dsam.spring.boot.beveragestorespringboot.restful.backend.dtos.CustomerOrderDTO;
import de.uniba.dsg.dsam.spring.boot.beveragestorespringboot.restful.backend.entities.CustomerOrderEntity;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class CustomerOrderReceiver extends AbstractCRUDServiceImpl<CustomerOrderEntity> {

    private final GenericMapper<CustomerOrderEntity, CustomerOrderDTO> converter;

    public CustomerOrderReceiver(GenericMapper<CustomerOrderEntity, CustomerOrderDTO> converter) {
        this.converter = converter;
    }

    @RabbitListener(queues = "${beveragestore.rabbitmq.queue}")
    public CustomerOrderDTO receiveCustomerOrder(CustomerOrderDTO customerOrderDTO){
        CustomerOrderEntity entity = this.repository.save(this.converter.convertDTOToEntity(customerOrderDTO));
        return this.converter.convertEntityToDTO(entity);
    }
}
