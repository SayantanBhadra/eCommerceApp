package org.sayantan.AccountService.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.sayantan.AccountService.entities.Address;
import org.sayantan.AccountService.entities.Payment;
import org.sayantan.AccountService.entities.User;
import org.sayantan.AccountService.service.AccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

@RestController
public class AccountServiceController {
	Logger logger = LoggerFactory.getLogger(AccountServiceController.class);
	@Autowired
	private AccountService service;

	@GetMapping(path = "/users")
	public MappingJacksonValue getAllUsers(){
		List<User>userList = service.getUserList();
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("firstName","middleName","lastName","userId","dob","mobileNo","emailId","userName","activeStatus");
		FilterProvider filterProvider=new SimpleFilterProvider().addFilter("DefaultUserFilter", filter);
		MappingJacksonValue mapping = new MappingJacksonValue(userList);
		mapping.setFilters(filterProvider);
		return mapping;
	}

	@GetMapping(path = "/users/{id}")
	public MappingJacksonValue getUsersById(@PathVariable(name = "id")Long userId){
		logger.info("Inside getUserById method of AccountServie");
		User user = service.getUserById(userId);
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("firstName","middleName","lastName","userId","dob","mobileNo","emailId","userName","activeStatus");
		FilterProvider filterProvider=new SimpleFilterProvider().addFilter("DefaultUserFilter", filter);
		MappingJacksonValue mapping = new MappingJacksonValue(user);
		mapping.setFilters(filterProvider);
		return mapping;
	}

	@PostMapping(path="/users")
	public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
		User savedUser=service.saveUser(user);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getUserId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@PutMapping(path="/users")
	public ResponseEntity<Object> modifyUser(@Valid @RequestBody User user) {
		service.updateUser(user);
		return ResponseEntity.ok().build();
	}

	@DeleteMapping(path="/users/{id}")
	public ResponseEntity<Object> deleteUser(@PathVariable(name = "id")Long userId) {
		service.deleteUser(userId);
		return ResponseEntity.ok().build();
	}


	@GetMapping(path="/address/{userId}")
	public List<Address> getAllAddresses(@PathVariable(name = "userId")Long userId){
		return service.getAllAddressForUser(userId);
	}

	@PostMapping(path="/address/{userId}")
	public ResponseEntity<Object> createAddress(@PathVariable(name="userId")Long userId,@Valid @RequestBody Address address){

		service.saveAddress(userId,address); 
		URI uri =ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();
		return ResponseEntity.created(uri).build();
	}

	@PutMapping(path="/address/{addressId}")
	public ResponseEntity<Object> modifyAddress(@Valid @RequestBody Address address){		
		service.modifyAddress(address); 
		return ResponseEntity.ok().build();
	}
	@DeleteMapping(path="/address/{addressId}")
	public ResponseEntity<Object> deleteAddress(@PathVariable(name="addressId")Long addressId){		
		service.deleteAddress(addressId);
		return ResponseEntity.ok().build();
	}
	@GetMapping(path="/address/order/{addressId}")
	public Address getAddress(@PathVariable(name = "addressId")Long addressId){
		return service.getAddress(addressId);
	}
	@PutMapping(path="/address/{userId}/{addressId}")
	public ResponseEntity<Object> setDefaultAddresses(@PathVariable(name = "userId")Long userId,@PathVariable(name = "addressId")Long addressId){
		service.setDefaultAddress(userId, addressId);
		return ResponseEntity.ok().build();
	}
	@GetMapping(path="/payment/{userId}")
	public List<Payment> getAllPayments(@PathVariable(name = "userId")Long userId){
		return service.getAllPaymentForUser(userId);
	}

	@PostMapping(path="/payment/{userId}")
	public ResponseEntity<Object> createPayment(@PathVariable(name="userId")Long userId,@Valid @RequestBody Payment payment){

		service.savePayment(userId,payment); 
		URI uri =ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();
		return ResponseEntity.created(uri).build();
	}

	@DeleteMapping(path="/payment/{paymentId}")
	public ResponseEntity<Object> deletePayment(@PathVariable(name="paymentId")Long paymentId){		
		service.deletePayment(paymentId);
		return ResponseEntity.ok().build();
	}
	@GetMapping(path="/payment/{userId}/{payemntId}")
	public ResponseEntity<Object> setDefaultPayment(@PathVariable(name = "userId")Long userId,@PathVariable(name = "payemntId")Long payemntId){
		service.setDefaultPayment(userId, payemntId);
		return ResponseEntity.ok().build();
	}
}
