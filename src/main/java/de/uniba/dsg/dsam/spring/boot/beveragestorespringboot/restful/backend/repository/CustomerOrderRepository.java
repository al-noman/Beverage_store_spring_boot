package de.uniba.dsg.dsam.spring.boot.beveragestorespringboot.restful.backend.repository;

import de.uniba.dsg.dsam.spring.boot.beveragestorespringboot.restful.backend.entities.CustomerOrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerOrderRepository extends JpaRepository<CustomerOrderEntity, Integer> {
}
