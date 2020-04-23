package org.sayantan.InventoryService.dao;

import java.util.List;
import java.util.Optional;

import org.sayantan.InventoryService.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryDAO extends JpaRepository<Category, Long> {
	@Query("select c from Category c where c.parent is null")
	public Optional<List<Category>> findRootCategories();
}
