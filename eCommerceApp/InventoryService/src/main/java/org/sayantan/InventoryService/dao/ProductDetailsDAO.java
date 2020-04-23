package org.sayantan.InventoryService.dao;

import org.sayantan.InventoryService.entities.ProductDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface ProductDetailsDAO extends JpaRepository<ProductDetails,Long> {

}
