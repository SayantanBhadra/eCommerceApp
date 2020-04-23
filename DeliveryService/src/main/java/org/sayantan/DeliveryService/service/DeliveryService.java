package org.sayantan.DeliveryService.service;

import java.util.List;

import org.sayantan.DeliveryService.entities.Delivery;
import org.sayantan.DeliveryService.exception.DeliveryServiceRuntimeException;

public interface DeliveryService {
	public List<Delivery> getDeliveryInformation(Long purchaseId)throws DeliveryServiceRuntimeException;
	public Delivery createDeliveryInformation(Long purchaseId,Delivery delivery)throws DeliveryServiceRuntimeException;
}
