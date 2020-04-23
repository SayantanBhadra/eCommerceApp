package org.sayantan.InventoryService.service;

import java.util.List;

import javax.validation.Valid;

import org.sayantan.InventoryService.entities.Category;
import org.sayantan.InventoryService.exception.InventoryServiceRuntimeException;

public interface CategoryService{

	public List<Category> getChildCategories(Long categoryId)throws InventoryServiceRuntimeException;

	public List<Category> getRootCategories()throws InventoryServiceRuntimeException;

	public Category createRootCategories(Category category) throws InventoryServiceRuntimeException;

	public Category createChildCategories(Long parentId, Category childCategory) throws InventoryServiceRuntimeException;

	public Category modifyCategories(@Valid Category category)throws InventoryServiceRuntimeException;

	public void deleteCategory(Long categoryId)throws InventoryServiceRuntimeException;

	public Category getCategoryById(Long categoryId) throws InventoryServiceRuntimeException;

}
