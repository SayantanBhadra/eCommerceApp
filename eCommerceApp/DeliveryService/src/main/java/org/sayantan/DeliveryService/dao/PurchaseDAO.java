package org.sayantan.DeliveryService.dao;

import org.sayantan.DeliveryService.entities.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface PurchaseDAO extends JpaRepository<Purchase, Long> {

}
