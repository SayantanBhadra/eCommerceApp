package org.sayantan.DeliveryService.service.impl;

import java.util.Optional;

import org.sayantan.DeliveryService.dao.PurchaseDAO;
import org.sayantan.DeliveryService.entities.Purchase;
import org.sayantan.DeliveryService.exception.DeliveryServiceRuntimeException;
import org.sayantan.DeliveryService.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
@Service
public class PurchaseServiceImpl implements PurchaseService {
	@Autowired
	private PurchaseDAO dao;
	@Override
	public Purchase getPurchaseInformation(Long purchaseId) throws DeliveryServiceRuntimeException {
		// TODO Auto-generated method stub
		Optional<Purchase> oPurchase = dao.findById(purchaseId);
		if(oPurchase.isPresent()) {
			return oPurchase.get();
		}
		else {
			throw new ResourceNotFoundException("Following Resource not Found:Purchase with id:"+purchaseId);
		}
	}

	@Override
	public Purchase createPurchase(Purchase purchase) throws DeliveryServiceRuntimeException {
		// TODO Auto-generated method stub
		return dao.save(purchase);
	}

}
