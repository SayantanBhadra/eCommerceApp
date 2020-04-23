package org.sayantan.InventoryService.service;

import java.util.List;

import org.sayantan.InventoryService.entities.Seller;
import org.sayantan.InventoryService.exception.InventoryServiceRuntimeException;

public interface SellerService {
	public List<Seller> getAllSellers()throws InventoryServiceRuntimeException;
	public Seller getSeller(Long sellerId)throws InventoryServiceRuntimeException;
	public Seller saveSeller(Seller seller)throws InventoryServiceRuntimeException;
	public Seller modifySeller(Seller seller)throws InventoryServiceRuntimeException;
	public void deleteSeller(Long sellerId)throws InventoryServiceRuntimeException;
}
