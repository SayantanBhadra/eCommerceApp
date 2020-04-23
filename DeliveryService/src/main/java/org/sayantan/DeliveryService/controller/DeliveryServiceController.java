package org.sayantan.DeliveryService.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.sayantan.DeliveryService.entities.Delivery;
import org.sayantan.DeliveryService.entities.Purchase;
import org.sayantan.DeliveryService.rest.proxy.RestServiceProxy;
import org.sayantan.DeliveryService.rest.proxy.bean.AddressBean;
import org.sayantan.DeliveryService.rest.proxy.bean.BillingBean;
import org.sayantan.DeliveryService.rest.proxy.bean.ProductBean;
import org.sayantan.DeliveryService.rest.proxy.bean.SellerBean;
import org.sayantan.DeliveryService.rest.proxy.bean.UserBean;
import org.sayantan.DeliveryService.service.DeliveryService;
import org.sayantan.DeliveryService.service.PurchaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
public class DeliveryServiceController {
	Logger logger = LoggerFactory.getLogger(DeliveryServiceController.class);
	@Autowired
	private PurchaseService pService;
	@Autowired
	private DeliveryService dService;
	@Autowired
	private RestServiceProxy restServiceProxy;
	
	
	@GetMapping(path="/purchase/{id}")
	public Purchase getPurchase(@PathVariable(name="id")Long purchaseId) {
		return pService.getPurchaseInformation(purchaseId);
	}
	
	@PostMapping(path = "/purchase")
	public ResponseEntity<Object> createPurchase(@Valid @RequestBody Purchase purchase){
		pService.createPurchase(purchase);
		URI location=ServletUriComponentsBuilder.fromCurrentRequest().path("{id}").buildAndExpand(purchase.getPurchaseId()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	@GetMapping(path="/delivery/{id}")
	public List<Delivery> getDeliveryList(@PathVariable(value="id") Long purchaseId){
		return dService.getDeliveryInformation(purchaseId);
	}
	
	@PostMapping(path="/delivery/{id}")
	public ResponseEntity<Object> saveDelivery(@PathVariable(value="id")Long purchaseId,@Valid @RequestBody Delivery delivery){
		dService.createDeliveryInformation(purchaseId, delivery);
		URI location=ServletUriComponentsBuilder.fromCurrentRequest().path("{id}").buildAndExpand(purchaseId).toUri();
		return ResponseEntity.created(location).build();
	}
	@HystrixCommand(fallbackMethod = "generateBill_Backup")
	@GetMapping(path="/bill/{purchaseId}")
	public BillingBean generateBill(@PathVariable(value="purchaseId")Long purchaseId){
		logger.info("Request for /bill/{purchaseId} raise for id {}",purchaseId);
		Purchase purchase = pService.getPurchaseInformation(purchaseId);
		UserBean user = restServiceProxy.findByUserId(purchase.getUserId());
		AddressBean address=restServiceProxy.findByAddressId(purchase.getAddressId());
		ProductBean product=restServiceProxy.findByProductId(purchase.getProductId());
		SellerBean seller = restServiceProxy.findBySellerId(purchase.getSellerId());
		BillingBean bill=new BillingBean(user,address,product,seller);
		return bill;
	}
	public BillingBean generateBill_Backup(Long purchaseId) {
		logger.error("Hysterix Fallback method enabled");
		return new BillingBean();
	}
}
