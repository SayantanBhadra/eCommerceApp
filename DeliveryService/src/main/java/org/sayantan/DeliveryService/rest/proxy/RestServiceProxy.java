package org.sayantan.DeliveryService.rest.proxy;

import org.sayantan.DeliveryService.rest.proxy.bean.AddressBean;
import org.sayantan.DeliveryService.rest.proxy.bean.ProductBean;
import org.sayantan.DeliveryService.rest.proxy.bean.SellerBean;
import org.sayantan.DeliveryService.rest.proxy.bean.UserBean;
import org.sayantan.DeliveryService.rest.proxy.circuitbreaker.RestProxyCircuitBreaker;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "ZuulAPIGateway",fallback = RestProxyCircuitBreaker.class)
@RibbonClient(name = "AccountService")
public interface RestServiceProxy {
	@RequestMapping(method = RequestMethod.GET,value = "/accountservice/users/{userId}")
	public UserBean findByUserId(@PathVariable(value="userId")Long userId);
	@RequestMapping(method = RequestMethod.GET,value = "/accountservice/address/order/{addressId}")
	public AddressBean findByAddressId(@PathVariable(value="addressId")Long addressId);
	@RequestMapping(method = RequestMethod.GET,value = "/inventoryservice/products/{productId}")
	public ProductBean findByProductId(@PathVariable(value="productId")Long productId);
	@RequestMapping(method = RequestMethod.GET,value = "/inventoryservice/seller/{sellerId}")
	public SellerBean findBySellerId(@PathVariable(value="sellerId")Long sellerId);
}
