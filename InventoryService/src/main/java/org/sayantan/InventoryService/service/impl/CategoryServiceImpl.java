package org.sayantan.InventoryService.service.impl;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.sayantan.InventoryService.dao.CategoryDAO;
import org.sayantan.InventoryService.dao.ProductDetailsDAO;
import org.sayantan.InventoryService.dao.ProductSummaryDAO;
import org.sayantan.InventoryService.dao.SellerDAO;
import org.sayantan.InventoryService.entities.Category;
import org.sayantan.InventoryService.exception.InventoryServiceRuntimeException;
import org.sayantan.InventoryService.exception.ResourceNotFoundException;
import org.sayantan.InventoryService.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class CategoryServiceImpl implements CategoryService {
	@Autowired
	private CategoryDAO categoryDao;
	
	@Override
	public Category getCategoryById(Long categoryId) throws InventoryServiceRuntimeException{
		if(categoryDao.existsById(categoryId)) {
			return categoryDao.findById(categoryId).get();
		}
		else {
			throw new ResourceNotFoundException("Resource Not Found for following:Parent Category with id:"+categoryId);
		}
	}
	
	@Override
	public Category createRootCategories(Category category) throws InventoryServiceRuntimeException{
		return categoryDao.save(category);
	}
	@Override
	public Category createChildCategories(Long parentId,Category childCategory) throws InventoryServiceRuntimeException{
		Optional<Category> parentCategoryOptional=categoryDao.findById(parentId);
		if(parentCategoryOptional.isPresent()) {
			Category parentCategory = parentCategoryOptional.get();
			childCategory.setParent(parentCategory);
			return categoryDao.save(childCategory);
		}
		else {
			throw new ResourceNotFoundException("Resource Not Found for following:Parent Category with id:"+parentId);
		}
	}
	@Override
	public List<Category> getRootCategories() throws InventoryServiceRuntimeException{
		Optional<List<Category>> categoryListOptional=categoryDao.findRootCategories();
		if(categoryListOptional.isPresent()) {
			List<Category> categoryList=categoryListOptional.get();
			for(Category category:categoryList) {
				if(category.getParent()!=null) {
					category.setRoot(false);
				}
				else {
					category.setRoot(true);
				}
				if(category.getChildren().size()>0) {
					category.setLeaf(false);
				}
				else {
					category.setLeaf(true);
				}
			}
			return categoryList;
		}
		else {
			throw new ResourceNotFoundException("Resource Not Found for following:Root Categories");
		}
	}
	@Override
	public List<Category> getChildCategories(Long categoryId)throws InventoryServiceRuntimeException{
		Optional<Category> categoryOptional=categoryDao.findById(categoryId);
		if(categoryOptional.isPresent()) {
			List<Category> categoryList = categoryOptional.get().getChildren();	
			for(Category category:categoryList) {
				if(category.getParent()!=null) {
					category.setRoot(false);
				}
				else {
					category.setRoot(true);
				}
				if(category.getChildren().size()>0) {
					category.setLeaf(false);
				}
				else {
					category.setLeaf(true);
				}
			}
			return categoryList;
		}
		else {
			throw new ResourceNotFoundException("Resource Not Found for following:Category with Id:"+categoryId);
		}
	}
	@Override
	public Category modifyCategories(@Valid Category category) throws InventoryServiceRuntimeException{
		// TODO Auto-generated method stub
		if(categoryDao.existsById(category.getCategoryId())) {
			Category persistedCategory = categoryDao.findById(category.getCategoryId()).get();
			persistedCategory.setCategoryName(category.getCategoryName());
			persistedCategory.setDescription(category.getDescription());
			persistedCategory.setIsMandatory(category.getIsMandatory());
			categoryDao.save(persistedCategory);
		}
		else {
			throw new ResourceNotFoundException("Resource Not Found for following:Category with Id:"+category.getCategoryId());
		}
		return null;
	}
	@Override
	public void deleteCategory(Long categoryId) throws InventoryServiceRuntimeException{
		// TODO Auto-generated method stub
		if(categoryDao.existsById(categoryId)) {
			categoryDao.deleteById(categoryId);
		}
		else {
			throw new ResourceNotFoundException("Resource Not Found for following:Category with Id:"+categoryId);
		}
	}
}
