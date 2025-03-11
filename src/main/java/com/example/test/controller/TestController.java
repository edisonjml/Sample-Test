package com.example.test.controller;

import java.lang.reflect.Field;
import java.util.*;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import com.example.test.model.*;

import io.swagger.annotations.Api;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@Api
public class TestController {
	
	private List<Customer> customers = new ArrayList<>();
	private int count = 0;
	
	@RequestMapping(value = "/api/v1/account", method = RequestMethod.POST)
	public ResponseEntity<Object> addCustomerAccount(@RequestBody Customer customer, HttpServletRequest request){
		
		Map<String, Object> transactionResponse = new HashMap<>();
		
		//Validate if required fields are empty
		boolean missingRequiredFields = false;
		missingRequiredFields = customer.getCustomerName().isBlank()
				|| customer.getCustomerMobile().isBlank()
				|| customer.getCustomerEmail().isBlank()
				|| customer.getAddress1().isBlank();
		
		if(!missingRequiredFields) {
			//Set the customer number based on integer incrementing from zero
			customer.setCustomerNumber(count++);
			
			customers.add(customer);
			transactionResponse.put("customerNumber", customer.getCustomerNumber());
			transactionResponse.put("transactionStatusCode", Integer.parseInt(HttpStatus.CREATED.toString().substring(0, 3)));
			transactionResponse.put("transactionStatusDescription", "Customer account created");
			return new ResponseEntity<>(transactionResponse, HttpStatus.CREATED);
		} else {
			transactionResponse.put("transactionStatusCode", Integer.parseInt(HttpStatus.BAD_REQUEST.toString().substring(0, 3)));
			transactionResponse.put("transactionStatusDescription", "Please fill out all the required fields");
			return new ResponseEntity<>(transactionResponse, HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(path = "/api/v1/account/{customerNumber}", method = RequestMethod.GET)
	public ResponseEntity<Object> getCustomerNumber(HttpServletRequest request, @PathVariable int customerNumber){
		
		Map<String, Object> transactionResponse = new HashMap<>();
		
		//Finds the customer based on the customer number
		boolean isFound = false;
		for(Customer customer : customers) {
			isFound = customer.getCustomerNumber() == customerNumber;
			if(isFound) {
				this.convertObjectToMap(customer, transactionResponse);
				break;
			}
		}
		
		if(isFound) {
			transactionResponse.put("transactionStatusCode", Integer.parseInt(HttpStatus.FOUND.toString().substring(0, 3)));
			transactionResponse.put("transactionStatusDescription", "Customer Account Found");
			return new ResponseEntity<>(transactionResponse, HttpStatus.FOUND);
		} else {
			transactionResponse.put("transactionStatusCode", Integer.parseInt(HttpStatus.NOT_FOUND.toString().substring(0, 3)));
			transactionResponse.put("transactionStatusDescription", "Customer not Found");
			return new ResponseEntity<>(transactionResponse, HttpStatus.NOT_FOUND);
		}
	}
	
	private Map<String, Object> convertObjectToMap(Object object, Map<String, Object> map) {
	    Field[] fields = object.getClass().getDeclaredFields();
	    
	    try {
		    for (Field field: fields) {
		        field.setAccessible(true);
		        map.put(field.getName(), field.get(object));
		    }
		} catch (IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
		}

	    return map;
	}

}
