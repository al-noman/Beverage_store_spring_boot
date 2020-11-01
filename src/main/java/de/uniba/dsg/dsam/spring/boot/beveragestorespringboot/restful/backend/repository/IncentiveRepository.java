package de.uniba.dsg.dsam.spring.boot.beveragestorespringboot.restful.backend.repository;

import de.uniba.dsg.dsam.spring.boot.beveragestorespringboot.restful.backend.entities.IncentiveEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IncentiveRepository extends JpaRepository<IncentiveEntity, Integer> {
}
