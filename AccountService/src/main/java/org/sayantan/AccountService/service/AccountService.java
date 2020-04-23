package org.sayantan.AccountService.service;

import java.util.List;

import org.sayantan.AccountService.entities.Address;
import org.sayantan.AccountService.entities.Payment;
import org.sayantan.AccountService.entities.User;
import org.sayantan.AccountService.exception.AccountServiceRuntimeException;
import org.springframework.stereotype.Service;
@Service
public interface AccountService {

	public List<User> getUserList();

	public User saveUser(User user);

	public User getUserById(Long userId);

	public List<Address> getAllAddressForUser(Long userId) throws AccountServiceRuntimeException;

	public User updateUser(User user) throws AccountServiceRuntimeException;

	public void deleteUser(Long userId) throws AccountServiceRuntimeException;

	public Address saveAddress(Long userId,Address address) throws AccountServiceRuntimeException;

	public Address modifyAddress(Address address) throws AccountServiceRuntimeException;

	public void deleteAddress(Long addressId) throws AccountServiceRuntimeException;

	public void setDefaultAddress(Long userId, Long addressId) throws AccountServiceRuntimeException;

	public List<Payment> getAllPaymentForUser(Long userId) throws AccountServiceRuntimeException;

	public Payment savePayment(Long userId, Payment payment) throws AccountServiceRuntimeException;

	public void deletePayment(Long paymentId) throws AccountServiceRuntimeException;

	public void setDefaultPayment(Long userId, Long paymentId) throws AccountServiceRuntimeException;

	public Address getAddress(Long addressId) throws AccountServiceRuntimeException;

}
