package org.sayantan.InventoryService.service.impl;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.sayantan.InventoryService.dao.ProductDetailsDAO;
import org.sayantan.InventoryService.dao.ProductSummaryDAO;
import org.sayantan.InventoryService.entities.ProductDetails;
import org.sayantan.InventoryService.entities.ProductSummary;
import org.sayantan.InventoryService.exception.InventoryServiceRuntimeException;
import org.sayantan.InventoryService.exception.ResourceNotFoundException;
import org.sayantan.InventoryService.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class ProductServiceImpl implements ProductService {
	@Autowired
	private ProductSummaryDAO pSummaryDao;
	@Autowired
	private ProductDetailsDAO pDetailsDao;
	
	@Override
	public ProductSummary createProduct(ProductSummary product) throws InventoryServiceRuntimeException{
		pSummaryDao.save(product);
		for(ProductDetails details:product.getDetails()) {
			details.setProduct(product);
			pDetailsDao.save(details);
		}
		return product;
	}

	@Override
	public ProductSummary getProductSummary(Long productId) throws InventoryServiceRuntimeException {
		// TODO Auto-generated method stub
		Optional<ProductSummary> pDuctSummaryOptional = pSummaryDao.findById(productId);
		if(pDuctSummaryOptional.isPresent()) {
			return pDuctSummaryOptional.get();
		}
		else {
			throw new ResourceNotFoundException("Resource Not Found for following:Parent Product Summary with id:"+productId);
		}
	}

	@Override
	public List<ProductDetails> getProductDetailsList(Long productId) throws InventoryServiceRuntimeException {
		// TODO Auto-generated method stub
		Optional<ProductSummary> pDuctSummaryOptional = pSummaryDao.findById(productId);
		if(pDuctSummaryOptional.isPresent()) {
			return pDuctSummaryOptional.get().getDetails();
		}
		else {
			throw new ResourceNotFoundException("Resource Not Found for following:Parent Product Summary with id:"+productId);
		}
	}

	@Override
	public ProductSummary createProductSummary(@Valid ProductSummary product) throws InventoryServiceRuntimeException {
		// TODO Auto-generated method stub
		return pSummaryDao.save(product);
	}

	@Override
	public ProductDetails createProductDetails(Long productId, @Valid ProductDetails productDetails)
			throws InventoryServiceRuntimeException {
		// TODO Auto-generated method stub
		Optional<ProductSummary> pDuctSummaryOptional = pSummaryDao.findById(productId);
		if(pDuctSummaryOptional.isPresent()) {
			ProductSummary pSummary = pDuctSummaryOptional.get();
			productDetails.setProduct(pSummary);
			return pDetailsDao.save(productDetails);
		}
		else {
			throw new ResourceNotFoundException("Resource Not Found for following:Parent Product Summary with id:"+productId);
		}
	}

	@Override
	public ProductSummary modifyProductSummary(ProductSummary pSummary) throws InventoryServiceRuntimeException{
		// TODO Auto-generated method stub
		Optional<ProductSummary> productSummaryOptional=pSummaryDao.findById(pSummary.getProductId());
		if(productSummaryOptional.isPresent()) {
			ProductSummary productSummary = productSummaryOptional.get();
			productSummary.setProductName(pSummary.getProductName());
			productSummary.setDescription(pSummary.getDescription());
			productSummary.setPrice(pSummary.getPrice());
			productSummary.setDenomination(pSummary.getDenomination());
			return pSummaryDao.save(productSummary);
		}
		else {
			throw new ResourceNotFoundException("Resource Not Found for following:Parent Product Summary with id:"+pSummary.getProductId());
		}
	}

	@Override
	public ProductDetails modifyProductDetails(ProductDetails pDetails) throws InventoryServiceRuntimeException{
		// TODO Auto-generated method stub
		Optional<ProductDetails> productDetailsOptional=pDetailsDao.findById(pDetails.getProductDetailsId());
		if(productDetailsOptional.isPresent()) {
			ProductDetails productDetails = productDetailsOptional.get();
			productDetails.setCategoryName(pDetails.getCategoryName());
			productDetails.setCategoryValue(pDetails.getCategoryValue());
			return pDetailsDao.save(productDetails);
		}
		else {
			throw new ResourceNotFoundException("Resource Not Found for following:Parent Product Details with id:"+pDetails.getProductDetailsId());
		}
	}

	@Override
	public void deleteProductSummary(Long productSummaryId) throws InventoryServiceRuntimeException {
		// TODO Auto-generated method stub
		if(pSummaryDao.existsById(productSummaryId)) {
			pSummaryDao.deleteById(productSummaryId);
		}
		else {
			throw new ResourceNotFoundException("Resource Not Found for following:Parent Product Summary with id:"+productSummaryId);
		}
	}

	@Override
	public void deleteProductDetails(Long productDetailsId) throws InventoryServiceRuntimeException {
		// TODO Auto-generated method stub
		if(pDetailsDao.existsById(productDetailsId)) {
			pDetailsDao.deleteById(productDetailsId);
		}
		else {
			throw new ResourceNotFoundException("Resource Not Found for following:Parent Product Summary with id:"+productDetailsId);
		}
	}
	
}
