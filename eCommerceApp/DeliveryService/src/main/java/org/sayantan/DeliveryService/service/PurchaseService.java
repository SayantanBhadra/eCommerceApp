package org.sayantan.DeliveryService.service;

import org.sayantan.DeliveryService.entities.Purchase;
import org.sayantan.DeliveryService.exception.DeliveryServiceRuntimeException;

public interface PurchaseService {
	public Purchase getPurchaseInformation(Long purchaseId) throws DeliveryServiceRuntimeException;
	public Purchase createPurchase(Purchase purchase)throws DeliveryServiceRuntimeException;
}
