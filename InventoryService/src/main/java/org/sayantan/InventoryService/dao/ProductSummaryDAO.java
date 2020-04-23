package org.sayantan.InventoryService.dao;

import org.sayantan.InventoryService.entities.ProductSummary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface ProductSummaryDAO extends JpaRepository<ProductSummary,Long> {

}
