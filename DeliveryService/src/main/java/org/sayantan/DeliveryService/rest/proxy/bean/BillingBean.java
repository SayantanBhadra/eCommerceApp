package org.sayantan.DeliveryService.rest.proxy.bean;

public class BillingBean {
	private UserBean user;
	private AddressBean address;
	private ProductBean product;
	private SellerBean seller;
	
	public BillingBean() {
		super();
	}
	public BillingBean(UserBean user,AddressBean address,ProductBean product,SellerBean seller) {
		this.user=user;
		this.address=address;
		this.product=product;
		this.seller=seller;
	}
	public UserBean getUser() {
		return user;
	}
	public void setUser(UserBean user) {
		this.user = user;
	}
	public AddressBean getAddress() {
		return address;
	}
	public void setAddress(AddressBean address) {
		this.address = address;
	}
	public ProductBean getProduct() {
		return product;
	}
	public void setProduct(ProductBean product) {
		this.product = product;
	}
	public SellerBean getSeller() {
		return seller;
	}
	public void setSeller(SellerBean seller) {
		this.seller = seller;
	}
	
	
}
