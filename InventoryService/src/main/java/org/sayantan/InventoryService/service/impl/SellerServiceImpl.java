package org.sayantan.InventoryService.service.impl;

import java.util.List;

import org.sayantan.InventoryService.dao.SellerDAO;
import org.sayantan.InventoryService.entities.Seller;
import org.sayantan.InventoryService.exception.InventoryServiceRuntimeException;
import org.sayantan.InventoryService.exception.ResourceNotFoundException;
import org.sayantan.InventoryService.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class SellerServiceImpl implements SellerService {
	@Autowired
	private SellerDAO sellerDao;
	@Override
	public List<Seller> getAllSellers() throws InventoryServiceRuntimeException{
		// TODO Auto-generated method stub
		return sellerDao.findAll();
	}

	@Override
	public Seller getSeller(Long sellerId) throws InventoryServiceRuntimeException{
		// TODO Auto-generated method stub
		if(sellerDao.existsById(sellerId)){
			return sellerDao.findById(sellerId).get();
		}
		else {
			throw new ResourceNotFoundException("Resource Not Found for following:Seller with id:"+sellerId);
		}
	}

	@Override
	public Seller saveSeller(Seller seller) throws InventoryServiceRuntimeException{
		// TODO Auto-generated method stub
		return sellerDao.save(seller);
	}

	@Override
	public Seller modifySeller(Seller seller) throws InventoryServiceRuntimeException{
		// TODO Auto-generated method stub
		if(sellerDao.existsById(seller.getSellerId())){
			return sellerDao.save(seller);
		}
		else {
			throw new ResourceNotFoundException("Resource Not Found for following:Seller with id:"+seller.getSellerId());
		}
	}

	@Override
	public void deleteSeller(Long sellerId) throws InventoryServiceRuntimeException{
		// TODO Auto-generated method stub
		if(sellerDao.existsById(sellerId)){
			sellerDao.deleteById(sellerId);
		}
		else {
			throw new ResourceNotFoundException("Resource Not Found for following:Seller with id:"+sellerId);
		}
	}

}
