package org.sayantan.DeliveryService.service.impl;

import java.util.List;
import java.util.Optional;

import org.sayantan.DeliveryService.dao.DeliveryDAO;
import org.sayantan.DeliveryService.dao.PurchaseDAO;
import org.sayantan.DeliveryService.entities.Delivery;
import org.sayantan.DeliveryService.entities.Purchase;
import org.sayantan.DeliveryService.exception.DeliveryServiceRuntimeException;
import org.sayantan.DeliveryService.service.DeliveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
@Service
public class DeliveryServiceImpl implements DeliveryService {
	@Autowired
	private PurchaseDAO pDao;
	@Autowired
	private DeliveryDAO dDao;
	@Override
	public List<Delivery> getDeliveryInformation(Long purchaseId) throws DeliveryServiceRuntimeException {
		// TODO Auto-generated method stub
		Optional<Purchase> oPurchase = pDao.findById(purchaseId);
		if(oPurchase.isPresent()) {
			return oPurchase.get().getDeliveries();
		}
		else {
			throw new ResourceNotFoundException("Following Resource not Found:Purchase with id:"+purchaseId);
		}
	}

	@Override
	public Delivery createDeliveryInformation(Long purchaseId, Delivery delivery)
			throws DeliveryServiceRuntimeException {
		// TODO Auto-generated method stub
		Optional<Purchase> oPurchase = pDao.findById(purchaseId);
		if(oPurchase.isPresent()) {
			delivery.setPurchase(oPurchase.get());
			dDao.save(delivery);
			return delivery;
		}
		else {
			throw new ResourceNotFoundException("Following Resource not Found:Purchase with id:"+purchaseId);
		}
	}

}
