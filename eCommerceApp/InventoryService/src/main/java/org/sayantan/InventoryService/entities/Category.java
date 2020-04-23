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
import javax.persistence.Transient;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "category",schema = "inventorydb")
public class Category {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="categoryid",columnDefinition = "INT")
	private Long categoryId;
	@Column(name="categoryname")
	@Size(min=1,max=45,message = "Category Name should be 1-45 character long")
	private String categoryName;
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="parentid") 
	private	Category parent;
	@JsonIgnore
	@OneToMany(mappedBy = "parent",fetch = FetchType.LAZY,cascade = CascadeType.REMOVE) 
	private List<Category> children;
	@Column(name="description")
	@Size(min=10,max=45,message = "Description should be 10-45 character long")
	private String description;
	@Column(name="ismandatory")
	@Size(min=1,max=1,message = "IsMandatory flag should be 1 character long")
	private String isMandatory;
	@Transient
	private Boolean leaf;
	@Transient
	private Boolean root;

	public Category() {
		super();
	}
	public Category(String categoryName, String description, String isMandatory) {
		super();
		this.categoryName = categoryName;
		this.description = description;
		this.isMandatory = isMandatory;
	}
	public Long getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getIsMandatory() {
		return isMandatory;
	}
	public void setIsMandatory(String isMandatory) {
		this.isMandatory = isMandatory;
	}

	public Category getParent() { 
		return parent; 
	} 
	public void setParent(Category parent) { 
		this.parent = parent; 
	} 
	public List<Category> getChildren() {
		return children; 
	} 
	public void setChildren(List<Category> children) {
		this.children = children; 
	}
	public Boolean getLeaf() {
		return leaf;
	}
	public void setLeaf(Boolean leaf) {
		this.leaf = leaf;
	}
	public Boolean getRoot() {
		return root;
	}
	public void setRoot(Boolean root) {
		this.root = root;
	}
	
}
