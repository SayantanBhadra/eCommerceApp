package org.sayantan.InventoryService.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.sayantan.InventoryService.entities.Category;
import org.sayantan.InventoryService.entities.ProductDetails;
import org.sayantan.InventoryService.entities.ProductSummary;
import org.sayantan.InventoryService.entities.Seller;
import org.sayantan.InventoryService.service.CategoryService;
import org.sayantan.InventoryService.service.ProductService;
import org.sayantan.InventoryService.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class InventoryServiceController {
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private SellerService sellerService;
	@Autowired
	private ProductService productService;

	@GetMapping(path = "/category/{categoryId}")
	public Category getCategoriesById(@PathVariable(name="categoryId")Long categoryId){
		return categoryService.getCategoryById(categoryId);
	}
	@GetMapping(path = "/category/root")
	public List<Category> getRootCategories(){
		return categoryService.getRootCategories();
	}
	@GetMapping(path = "/category/child/{categoryId}")
	public List<Category> getCategoryHierarchy(@PathVariable(name = "categoryId")Long categoryId){
		return categoryService.getChildCategories(categoryId);
	}
	@PostMapping(path = "/category/root")
	public ResponseEntity<Object> createRootCategory(@Valid @RequestBody Category category){
		Category categoryCreated = categoryService.createRootCategories(category);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("{id}").buildAndExpand(categoryCreated.getCategoryId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	@PostMapping(path = "/category/child/{parentId}")
	public ResponseEntity<Object> createChildCategory(@PathVariable("parentId")Long parentId,@Valid @RequestBody Category category){
		Category categoryCreated = categoryService.createChildCategories(parentId,category);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("{id}").buildAndExpand(categoryCreated.getParent().getCategoryId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	@PutMapping(path="/category")
	public ResponseEntity<Object> modifyEntity(@Valid @RequestBody Category category){
		categoryService.modifyCategories(category);
		return ResponseEntity.ok().build();
	}
	@DeleteMapping(path="/category/{id}")
	public ResponseEntity<Object> deleteCategory(@PathVariable(name="id")Long categoryId){
		categoryService.deleteCategory(categoryId);
		return ResponseEntity.ok().build();
	}

	@GetMapping(path="/seller")
	public List<Seller> getAllSeller(){
		return sellerService.getAllSellers();
	}

	@GetMapping(path="/seller/{id}")
	public Seller getSellerById(@PathVariable(name="id")Long sellerId){
		return sellerService.getSeller(sellerId);
	}

	@PostMapping(path="/seller")
	public ResponseEntity<Object> createSeller(@Valid @RequestBody Seller seller) {
		sellerService.saveSeller(seller);
		URI location=ServletUriComponentsBuilder.fromCurrentRequest().path("{id}").buildAndExpand(seller.getSellerId()).toUri();
		return ResponseEntity.created(location).build();
	}

	@PutMapping(path="/seller")
	public ResponseEntity<Object> modifySeller(@Valid @RequestBody Seller seller){
		sellerService.modifySeller(seller);
		return ResponseEntity.ok().build();
	}

	@DeleteMapping(path="/seller/{id}")
	public ResponseEntity<Object> deleteSeller(@PathVariable(name="id")Long sellerId){
		sellerService.deleteSeller(sellerId);
		return ResponseEntity.ok().build();
	}
	@GetMapping(path="/products/{productId}")
	public ProductSummary getProductSummary(@PathVariable(name="productId")Long id) {
		return productService.getProductSummary(id);
	}
	@GetMapping(path="/products/details/{productId}")
	public List<ProductDetails> getProductDetails(@PathVariable(name="productId")Long id) {
		return productService.getProductDetailsList(id);
	}
	@PostMapping(path="/products/{categoryId}/{sellerId}")
	public ResponseEntity<Object> createProduct(@PathVariable(name="categoryId") Long categoryId,@PathVariable(name="sellerId") Long sellerId,@Valid @RequestBody ProductSummary product){
		Category category=this.getCategoriesById(categoryId);
		Seller seller=this.getSellerById(sellerId);
		product.setCategory(category);
		product.setSeller(seller);
		productService.createProduct(product);
		URI location=ServletUriComponentsBuilder.fromCurrentRequest().path("{id}").buildAndExpand(product.getProductId()).toUri();
		return ResponseEntity.created(location).build();
	}
	@PostMapping(path="/products/summary/{categoryId}/{sellerId}")
	public ResponseEntity<Object> createProductSummary(@PathVariable(name="categoryId") Long categoryId,@PathVariable(name="sellerId") Long sellerId,@Valid @RequestBody ProductSummary product){
		Category category=this.getCategoriesById(categoryId);
		Seller seller=this.getSellerById(sellerId);
		product.setCategory(category);
		product.setSeller(seller);
		productService.createProductSummary(product);
		URI location=ServletUriComponentsBuilder.fromCurrentRequest().path("{id}").buildAndExpand(product.getProductId()).toUri();
		return ResponseEntity.created(location).build();
	}
	@PostMapping(path="/products/details/{productId}")
	public ResponseEntity<Object> createProductDetails(@PathVariable(name="productId") Long productId,@Valid @RequestBody ProductDetails productDetails){
		productService.createProductDetails(productId,productDetails);
		URI location=ServletUriComponentsBuilder.fromCurrentRequest().path("{id}").buildAndExpand(productId).toUri();
		return ResponseEntity.created(location).build();
	}
	@PutMapping(path="/products/summary")
	public ResponseEntity<Object> modifyProductSummary(@Valid @RequestBody ProductSummary pSummary){
		productService.modifyProductSummary(pSummary);
		return ResponseEntity.ok().build();
	}
	@PutMapping(path="/products/details")
	public ResponseEntity<Object> modifyProductSummary(@Valid @RequestBody ProductDetails pDetails){
		productService.modifyProductDetails(pDetails);
		return ResponseEntity.ok().build();
	}
	@DeleteMapping(path="/product/summary/{productId}")
	public ResponseEntity<Object> deleteProductSummary(@PathVariable(value="productId")Long productSummaryId){
		productService.deleteProductSummary(productSummaryId);
		return ResponseEntity.ok().build();
	}
	@DeleteMapping(path="/product/details/{productId}")
	public ResponseEntity<Object> deleteProductDetails(@PathVariable(value="productId")Long productDetailsId){
		productService.deleteProductDetails(productDetailsId);
		return ResponseEntity.ok().build();
	}
}

