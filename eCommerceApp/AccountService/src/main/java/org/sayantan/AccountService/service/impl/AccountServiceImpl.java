package org.sayantan.AccountService.service.impl;

import java.util.List;
import java.util.Optional;


import org.sayantan.AccountService.dao.AddessDAO;
import org.sayantan.AccountService.dao.PaymentDAO;
import org.sayantan.AccountService.dao.UserDAO;
import org.sayantan.AccountService.entities.Address;
import org.sayantan.AccountService.entities.Payment;
import org.sayantan.AccountService.entities.User;
import org.sayantan.AccountService.exception.AccountServiceRuntimeException;
import org.sayantan.AccountService.exception.ResourceNotFoundException;
import org.sayantan.AccountService.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
public class AccountServiceImpl implements AccountService {
	@Autowired
	private UserDAO userDAO;
	@Autowired
	private AddessDAO addressDAO;
	@Autowired
	private PaymentDAO paymentDAO;

	@Override
	public List<User> getUserList() throws AccountServiceRuntimeException{
		return userDAO.findAll();
	}
	@Override
	@Transactional
	public User saveUser(User user) throws AccountServiceRuntimeException{
		return userDAO.save(user);
	}
	@Override
	public User getUserById(Long userId) throws AccountServiceRuntimeException{
		Optional<User> userOptional = userDAO.findById(userId);
		if(userOptional.isPresent())
		{
			return userOptional.get();
		}
		else {
			throw new ResourceNotFoundException("Following Resource not Found:User with id:"+userId);
		}
	}
	@Override
	@Transactional
	public void deleteUser(Long userId) throws AccountServiceRuntimeException{
		if(userDAO.existsById(userId)) {
			User user=userDAO.findById(userId).get();
			user.setActiveStatus("N");
			//userDAO.deleteById(userId);
		}else {
			throw new ResourceNotFoundException("Following Resource not Found:User with id:"+userId);
		}
	}
	@Override
	@Transactional
	public User updateUser(User user) throws AccountServiceRuntimeException{
		if(userDAO.existsById(user.getUserId())) {
			return userDAO.save(user);
		}
		else {
			throw new ResourceNotFoundException("Following Resource not Found:User with id:"+user.getUserId());
		}
	}
	@Override
	public List<Address> getAllAddressForUser(Long userId) throws AccountServiceRuntimeException{
		if(userDAO.existsById(userId)) {
			return userDAO.findById(userId).get().getAddresses();
		}
		else {
			throw new ResourceNotFoundException("Following Resource not Found:User with id:"+userId);
		}
	}
	@Override
	@Transactional
	public Address saveAddress(Long userId,Address address) throws AccountServiceRuntimeException
	{
		if(userDAO.existsById(userId)) {
			User user=userDAO.findById(userId).get();
			//user.getAddresses().add(address);
			address.setUser(user);
			return addressDAO.save(address);
		}
		else {
			throw new ResourceNotFoundException("Following Resource not Found:User with id:"+userId);
		}
	}
	@Override
	@Transactional
	public Address modifyAddress(Address address) throws AccountServiceRuntimeException
	{
		if(addressDAO.existsById(address.getAddressId())) {
			User user = addressDAO.findById(address.getAddressId()).get().getUser();
			address.setUser(user);
			return addressDAO.save(address);
		}
		else {
			throw new ResourceNotFoundException("Following Resource not Found:Address with id:"+address.getAddressId());
		}
	}
	@Override
	@Transactional
	public void deleteAddress(Long addressId) throws AccountServiceRuntimeException
	{
		if(addressDAO.existsById(addressId)) {
			Address address = addressDAO.findById(addressId).get();
			addressDAO.delete(address);
		}
		else {
			throw new ResourceNotFoundException("Following Resource not Found:Address with id:"+addressId);
		}
	}

	@Override
	@Transactional
	public void setDefaultAddress(Long userId,Long addressId) throws AccountServiceRuntimeException
	{
		if(userDAO.existsById(userId)) {
			User user = userDAO.findById(userId).get();
			List<Address> addressList = user.getAddresses();
			Boolean addressPresent=false;
			for(Address address:addressList) {
				if(address.getAddressId().equals(addressId)) {
					addressPresent=true;
					break;
				}
			}
			if(addressPresent) {
				for(Address address:addressList) {
					if(address.getAddressId().equals(addressId)) {
						address.setIsDefault("Y");
					}
					else {
						address.setIsDefault("N");
					}
				}
			}
			else {
				throw new ResourceNotFoundException("Following Resource not Found:Address with id:"+addressId);
			}
		}
		else {
			throw new ResourceNotFoundException("Following Resource not Found:User with id:"+userId);
		}
	}
	@Override
	public Address getAddress(Long addressId) throws AccountServiceRuntimeException{
		if(addressDAO.existsById(addressId)) {
			return addressDAO.findById(addressId).get();
		}
		else {
			throw new ResourceNotFoundException("Following Resource not Found:User with id:"+addressId);
		}
	}
	@Override
	public List<Payment> getAllPaymentForUser(Long userId) throws AccountServiceRuntimeException{
		if(userDAO.existsById(userId)) {
			return userDAO.findById(userId).get().getPayments();
		}
		else {
			throw new ResourceNotFoundException("Following Resource not Found:User with id:"+userId);
		}
	}
	@Override
	@Transactional
	public Payment savePayment(Long userId,Payment payment) throws AccountServiceRuntimeException
	{
		if(userDAO.existsById(userId)) {
			User user=userDAO.findById(userId).get();
			//user.getAddresses().add(address);
			payment.setUser(user);
			return paymentDAO.save(payment);
		}
		else {
			throw new ResourceNotFoundException("Following Resource not Found:User with id:"+userId);
		}
	}
	@Override
	@Transactional
	public void deletePayment(Long paymentId) throws AccountServiceRuntimeException
	{
		if(paymentDAO.existsById(paymentId)) {
			Payment payment = paymentDAO.findById(paymentId).get();
			paymentDAO.delete(payment);
		}
		else {
			throw new ResourceNotFoundException("Following Resource not Found:Payment with id:"+paymentId);
		}
	}

	@Override
	@Transactional
	public void setDefaultPayment(Long userId,Long paymentId) throws AccountServiceRuntimeException
	{
		if(userDAO.existsById(userId)) {
			User user = userDAO.findById(userId).get();
			List<Payment> paymentList = user.getPayments();
			Boolean paymentPresent=false;
			for(Payment payment:paymentList) {
				if(payment.getPaymentId().equals(paymentId)) {
					paymentPresent=true;
					break;
				}
			}
			if(paymentPresent) {
				for(Payment payment:paymentList) {
					if(payment.getPaymentId().equals(paymentId)) {
						payment.setIsDefault("Y");
					}
					else {
						payment.setIsDefault("N");
					}
				}
			}
			else {
				throw new ResourceNotFoundException("Following Resource not Found:Payment with id:"+paymentId);
			}
		}
		else {
			throw new ResourceNotFoundException("Following Resource not Found:User with id:"+userId);
		}
	}
}
