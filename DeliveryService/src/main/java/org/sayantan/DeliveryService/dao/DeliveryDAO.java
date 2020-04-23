package org.sayantan.DeliveryService.dao;

import org.sayantan.DeliveryService.entities.Delivery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface DeliveryDAO extends JpaRepository<Delivery, Long> {

}
