package org.sayantan.InventoryService.dao;

import org.sayantan.InventoryService.entities.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface SellerDAO extends JpaRepository<Seller,Long> {

}
