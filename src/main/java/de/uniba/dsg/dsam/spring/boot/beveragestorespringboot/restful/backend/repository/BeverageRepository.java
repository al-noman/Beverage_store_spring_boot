package de.uniba.dsg.dsam.spring.boot.beveragestorespringboot.restful.backend.repository;

import de.uniba.dsg.dsam.spring.boot.beveragestorespringboot.restful.backend.entities.BeverageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BeverageRepository extends JpaRepository<BeverageEntity, Integer> {
}
