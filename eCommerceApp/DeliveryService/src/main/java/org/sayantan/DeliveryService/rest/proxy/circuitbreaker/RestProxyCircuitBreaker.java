package org.sayantan.DeliveryService.rest.proxy.circuitbreaker;

import org.sayantan.DeliveryService.rest.proxy.RestServiceProxy;
import org.sayantan.DeliveryService.rest.proxy.bean.AddressBean;
import org.sayantan.DeliveryService.rest.proxy.bean.ProductBean;
import org.sayantan.DeliveryService.rest.proxy.bean.SellerBean;
import org.sayantan.DeliveryService.rest.proxy.bean.UserBean;
import org.springframework.stereotype.Component;
@Component
public class RestProxyCircuitBreaker implements RestServiceProxy {

	@Override
	public UserBean findByUserId(Long userId) {
		// TODO Auto-generated method stub
		return new UserBean();
	}

	@Override
	public AddressBean findByAddressId(Long addressId) {
		// TODO Auto-generated method stub
		return new AddressBean();
	}

	@Override
	public ProductBean findByProductId(Long productId) {
		// TODO Auto-generated method stub
		return new ProductBean();
	}

	@Override
	public SellerBean findBySellerId(Long sellerId) {
		// TODO Auto-generated method stub
		return new SellerBean();
	}

}
