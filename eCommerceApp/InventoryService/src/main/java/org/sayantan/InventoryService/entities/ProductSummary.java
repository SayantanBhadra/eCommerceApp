package org.sayantan.InventoryService.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "productsummary",schema = "inventorydb")
public class ProductSummary {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="productid",columnDefinition = "INT")
	private Long productId;
	@Column(name="productname")
	@Size(min=10,max=45,message = "Product Name should be 10-45 character long")
	private String productName;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="sellerid",nullable = false)
	@JsonIgnore
	private Seller seller;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="categoryid",nullable = false)
	@JsonIgnore
	private Category category;
	@OneToMany(mappedBy = "product",cascade = CascadeType.REMOVE)
	private List<ProductDetails> details;
	@Column(name="description")
	@Size(min=10,max=999,message = "Description should be 10-999 character long")
	private String description;
	@Column(name="price",columnDefinition = "INT")
	@Min(value = 100L, message = "Minimum Value should be equal to or greater than 100")
	@Max(value = 499999L, message = "Amximum Value should be equal to or less than 499999")
	private Integer price;
	@Column(name="denomination")
	@Size(min=3,max=3,message = "Denomination should be 3 character long")
	private String denomination;
	
	public ProductSummary() {
		super();
	}

	public ProductSummary(String productName, String description, Integer price, String denomination) {
		super();
		this.productName = productName;
		this.description = description;
		this.price = price;
		this.denomination = denomination;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Seller getSeller() {
		return seller;
	}

	public void setSeller(Seller seller) {
		this.seller = seller;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public String getDenomination() {
		return denomination;
	}

	public void setDenomination(String denomination) {
		this.denomination = denomination;
	}

	public List<ProductDetails> getDetails() {
		return details;
	}

	public void setDetails(List<ProductDetails> details) {
		this.details = details;
	}
	
	
}
