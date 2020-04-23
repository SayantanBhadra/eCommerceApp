package org.sayantan.InventoryService.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "productdetails",schema = "inventorydb")
public class ProductDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="productdetailsid",columnDefinition = "INT")
	private Long productDetailsId;
	@ManyToOne(fetch = FetchType.LAZY,optional = false)
	@JoinColumn(name="productid",nullable = false)
	@JsonIgnore
	private ProductSummary product;
	@Column(name = "categoryname")
	@Size(min=10,max=45,message = "Category Name should be 10-45 character long")
	private String categoryName;
	@Column(name = "categoryvalue")
	@Size(min=10,max=45,message = "Category Value should be 10-45 character long")
	private String categoryValue;
	
	public ProductDetails() {
		super();
	}
	public ProductDetails(String categoryName, String categoryValue) {
		super();
		this.categoryName = categoryName;
		this.categoryValue = categoryValue;
	}

	public Long getProductDetailsId() {
		return productDetailsId;
	}
	public void setProductDetailsId(Long productDetailsId) {
		this.productDetailsId = productDetailsId;
	}
	public ProductSummary getProduct() {
		return product;
	}
	public void setProduct(ProductSummary product) {
		this.product = product;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public String getCategoryValue() {
		return categoryValue;
	}
	public void setCategoryValue(String categoryValue) {
		this.categoryValue = categoryValue;
	}
	
	
}
