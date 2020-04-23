package org.sayantan.InventoryService.service;

import java.util.List;

import org.sayantan.InventoryService.entities.ProductDetails;
import org.sayantan.InventoryService.entities.ProductSummary;
import org.sayantan.InventoryService.exception.InventoryServiceRuntimeException;

public interface ProductService {
	public ProductSummary getProductSummary(Long productId)throws InventoryServiceRuntimeException;
	public List<ProductDetails> getProductDetailsList(Long productId)throws InventoryServiceRuntimeException;
	public ProductSummary createProduct(ProductSummary product) throws InventoryServiceRuntimeException;
	public ProductSummary createProductSummary(ProductSummary product) throws InventoryServiceRuntimeException;
	public ProductDetails createProductDetails(Long productId,ProductDetails productDetails) throws InventoryServiceRuntimeException;
	public ProductSummary modifyProductSummary(ProductSummary pSummary)throws InventoryServiceRuntimeException;
	public ProductDetails modifyProductDetails(ProductDetails pDetails)throws InventoryServiceRuntimeException;
	public void deleteProductSummary(Long productSummaryId)throws InventoryServiceRuntimeException;
	public void deleteProductDetails(Long productDetailsId)throws InventoryServiceRuntimeException;
}
